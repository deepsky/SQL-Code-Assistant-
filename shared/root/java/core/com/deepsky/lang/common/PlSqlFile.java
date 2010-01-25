/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.lang.common;

import com.deepsky.database.SqlScriptManager;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.parser.ParserException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateView;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.parser.PlSqlASTParser;
import com.deepsky.lang.plsql.struct.parser.SyntaxErrorException;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.view.Icons;
import com.deepsky.view.query_pane.markup.SqlStatementMarkupModel;
import com.deepsky.view.query_pane.markup.impl.SqlStatementMarkupModelImpl;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;


public class PlSqlFile extends PsiFileBase implements PlSqlElement {

    static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlFile");

    Icon fileIcon;

    public PlSqlFile(FileViewProvider fileViewProvider) {
        super(fileViewProvider, PlSqlSupportLoader.PLSQL.getLanguage());

        fileIcon = chooseIcon(fileViewProvider.getVirtualFile());
    }

    final String ATTR_TIMESTAMP = "FILE_TIMESTAMP";
    final String ATTR_FILE_ICON = "FILE_ICON";

    private Icon chooseIcon(VirtualFile vfile) {
        Icon icon = null;
        // Try to define appropriate ICON for the file based on the file's content
        // The file may need to be parsed (if cache does not keep the  entry)
        String path = vfile.getPath();
        long timestamp = vfile.getTimeStamp();

        Long _timestamp = (Long) SqlScriptManager.getInstance().getAttribute(vfile, ATTR_TIMESTAMP);
        if (_timestamp != null && _timestamp == timestamp) {
            return (Icon) SqlScriptManager.getInstance().getAttribute(vfile, ATTR_FILE_ICON);
        }

        long ms = System.currentTimeMillis();

        PlSqlASTParser parser = new PlSqlASTParser();
        parser.ignoreSyntaxErrors(true);
        parser.syntaxErrorToStdout(false);

        try {
            Reader r = new InputStreamReader(vfile.getInputStream());
            PlSqlElement[] elems = parser.parseStream(r);

            switch (elems.length) {
                case 0: // file empty?
                    break;
                case 1: // analize element
                    if (elems[0] instanceof PackageBody) {
                        icon = Icons.PACKAGE_BODY;
                    } else if (elems[0] instanceof PackageSpec) {
                        icon = Icons.PACKAGE_SPEC;
                    } else if (elems[0] instanceof Function) {
                        icon = Icons.FUNCTION_BODY;
                    } else if (elems[0] instanceof FunctionSpec) {
                        icon = Icons.FUNCTION_SPEC;
                    } else if (elems[0] instanceof Procedure) {
                        icon = Icons.PROCEDURE_BODY;
                    } else if (elems[0] instanceof ProcedureSpec) {
                        icon = Icons.PROCEDURE_SPEC;
                    }
                    break;
                default:
                    icon = Icons.SQL_FILE;
                    break;
            }

        } catch (IOException e1) {
            // file not found
        } catch (SyntaxErrorException e) {
            // sql structure not valid
        } catch (ParserException e) {
            // SQL construction not supported
            log.warn("Type not supported by parser: " + e.getMessage());
        } catch (Throwable e) {
            log.warn(e.getMessage());
        }
        ms = System.currentTimeMillis() - ms;

        SqlScriptManager.getInstance().setAttribute(vfile, ATTR_TIMESTAMP, timestamp);
        SqlScriptManager.getInstance().setAttribute(vfile, ATTR_FILE_ICON, icon);

        log.info("[ctor] file: " + path + ", parsing taken, ms: " + ms);

        return icon;
    }

    @NotNull
    public FileType getFileType() {
        return PlSqlSupportLoader.PLSQL;
    }

    @Nullable
    public String getQuickNavigateInfo() {
        return null;
    }

    public String getStrippedText() {
        return null;
    }

    public void process(Visitor proc) {
        // todo -
    }

    public String getCtxPath() {
        return "[File]"; 
    }

    public Icon getIcon(int i) {
//        log.info("#getIcon " + i + " file: " + getVirtualFile().getPath());
        return fileIcon != null ? fileIcon : super.getIcon(i);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPlSqlFile(this);
        } else {
            super.accept(visitor);
        }
    }

    public void subtreeChanged() {
//        log.info("#subtreeChanged");        
        super.subtreeChanged();
    }

    @NotNull
    public Statement[] findDeclaration() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                PlSqlElementTypes.SELECT_EXPRESSION, PlSqlElementTypes.INSERT_COMMAND, PlSqlElementTypes.DELETE_COMMAND, PlSqlElementTypes.UPDATE_COMMAND
        ));

        Statement[] out = new Statement[(nodes != null) ? nodes.length : 0];
        for (int i = 0; i < out.length; i++) {
            out[i] = (Statement) nodes[i].getPsi();
        }

        return out;
    }

    public PlSqlElement findDeclaration(ExecutableDescriptor edesc) {
        PackageDescriptor pkg = edesc.getPackage();
        if (pkg != null) {
            ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                    PlSqlElementTypes.PACKAGE_SPEC, PlSqlElementTypes.PACKAGE_BODY
            )
            );

            for (ASTNode node : nodes) {
                PsiElement psi = node.getPsi();
                if (psi instanceof PackageSpec) {
                    PlSqlElement[] execs = ((PackageSpec) psi).findObjectByName(edesc.getName());
                    for (PlSqlElement e : execs) {
                        if (e instanceof ExecutableSpec && ((ExecutableSpec) e).equals2(edesc)) {
                            return e;
                        }
                    }
                } else if (psi instanceof PackageBody) {
                    Executable[] execs = ((PackageBody) psi).findExecutableByName(edesc.getName());
                    for (Executable e : execs) {
                        if (e.equals(edesc)) {
                            return e;
                        }
                    }
                }
            }
        }
        return null;
    }

    public PsiElement findDeclaration(TableDescriptor tdesc) {
        TableDefinition[] tabs = findChildrenByClass(TableDefinition.class);
        for (TableDefinition t : tabs) {
            if (t.getTableName().equalsIgnoreCase(tdesc.getName())) {
                return t;
            }
        }
        return null;
    }

    public PsiElement findDeclaration(ViewDescriptor tdesc) {
        CreateView[] tabs = findChildrenByClass(CreateView.class);
        for (CreateView t : tabs) {
            if (t.getViewName().equalsIgnoreCase(tdesc.getName())) {
                return t;
            }
        }
        return null;
    }

    public PsiElement findDeclaration(ColumnDescriptor cdesc) {
        String tableName = cdesc.getTableName();
        TableDefinition[] tabs = findChildrenByClass(TableDefinition.class);
        for (TableDefinition t : tabs) {
            if (t.getTableName().equalsIgnoreCase(tableName)) {
                return t.getColumnByName(cdesc.getName());
            }
        }

        CreateView[] views = findChildrenByClass(CreateView.class);
        for (CreateView t : views) {
            if (t.getViewName().equalsIgnoreCase(tableName)) {
                return t.getColumnByName(cdesc.getName());
            }
        }
        return views.length > 0 ? views[0] : null;
    }

    public PsiElement findDeclaration(VariableDescriptor vdesc) {
        PackageDescriptor pkg = vdesc.getPackage();
        if (pkg != null) {
            ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                    PlSqlElementTypes.PACKAGE_SPEC, PlSqlElementTypes.PACKAGE_BODY
            )
            );

            for (ASTNode node : nodes) {
                PsiElement psi = node.getPsi();
                if (psi instanceof PackageSpec) {
                    PlSqlElement[] vars = ((PackageSpec) psi).findObjectByName(vdesc.getName());
                    if (vars.length > 0) {
                        return vars[0];
                    }
                } else if (psi instanceof PackageBody) {
                    PlSqlElement[] vars = ((PackageBody) psi).findObjectByName(vdesc.getName());
                    if (vars.length > 0) {
                        return vars[0];
                    }
                }
            }
        } else {
            // todo - needs to support
        }
        return null;
    }

    public PsiElement findDeclaration(UserDefinedTypeDescriptor tdesc) {
        PackageDescriptor pkg = tdesc.getPackage();
        if (pkg != null) {
            ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                    PlSqlElementTypes.PACKAGE_SPEC, PlSqlElementTypes.PACKAGE_BODY
            )
            );
            for (ASTNode node : nodes) {
                PsiElement psi = node.getPsi();
                if (psi instanceof PackageSpec) {
                    PlSqlElement[] vars = ((PackageSpec) psi).findObjectByName(tdesc.getName());
                    if (vars.length > 0) {
                        return vars[0];
                    }
                } else if (psi instanceof PackageBody) {
                    PlSqlElement[] vars = ((PackageBody) psi).findObjectByName(tdesc.getName());
                    if (vars.length > 0) {
                        return vars[0];
                    }
                }
            }
        } else {
            // todo - needs to support
        }
        return this;
    }

    //    public PsiElement findDeclaration(RecordTypeDescriptor tdesc, RecordTypeItemDescriptor item) {

    //    public PsiElement findDeclaration(UserDefinedTypeDescriptor tdesc, RecordTypeItemDescriptor item) {
    public PsiElement findDeclaration(RecordTypeItemDescriptor item) {
        PackageDescriptor pkg = item.getParent().getPackage();
        if (pkg != null) {
            ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                    PlSqlElementTypes.PACKAGE_SPEC, PlSqlElementTypes.PACKAGE_BODY
            )
            );
            for (ASTNode node : nodes) {
                PsiElement psi = node.getPsi();
                if (psi instanceof PackageSpec) {
                    PlSqlElement[] vars = ((PackageSpec) psi).findObjectByName(item.getParent().getName());
                    if (vars.length > 0 && vars[0] instanceof RecordTypeDecl) {
                        PsiElement elem = findByName((RecordTypeDecl) vars[0], item.getName());
                        if (elem != null) {
                            return elem;
                        }
                    } else if (vars.length > 0 && vars[0] instanceof ObjectTypeDecl) {
                        PsiElement elem = findByName((ObjectTypeDecl) vars[0], item.getName());
                        if (elem != null) {
                            return elem;
                        }
                    }
                } else if (psi instanceof PackageBody) {
                    PlSqlElement[] vars = ((PackageBody) psi).findObjectByName(item.getParent().getName());
                    if (vars.length > 0 && vars[0] instanceof RecordTypeDecl) {
                        PsiElement elem = findByName((RecordTypeDecl) vars[0], item.getName());
                        if (elem != null) {
                            return elem;
                        }
                    } else if (vars.length > 0 && vars[0] instanceof ObjectTypeDecl) {
                        PsiElement elem = findByName((ObjectTypeDecl) vars[0], item.getName());
                        if (elem != null) {
                            return elem;
                        }
                    }
                }
            }
        } else {

            ObjectTypeDecl objType = this.findChildByClass(ObjectTypeDecl.class);
            if(objType != null){
                return findByName(objType, item.getName());
            } else {
                // todo - needs to support
            }
        }
        return this;
    }


    private PsiElement findByName(RecordTypeDecl r, String name) {
        for (RecordTypeItem _item : r.getItems()) {
            if (_item.getRecordItemName().equalsIgnoreCase(name)) {
                // declarartion found
                return _item;
            }
        }

        return null;
    }

    private PsiElement findByName(ObjectTypeDecl r, String name) {
        for (RecordTypeItem _item : r.getItems()) {
            if (_item.getRecordItemName().equalsIgnoreCase(name)) {
                // declarartion found
                return _item;
            }
        }

        return null;
    }

    public PsiElement findDeclaration(PackageBodyDescriptor pdesc) {
        if (pdesc != null) {
            ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                    PlSqlElementTypes.PACKAGE_BODY
            )
            );

            if (nodes != null) {
                for (ASTNode node : nodes) {
                    PsiElement psi = node.getPsi();
                    if (psi instanceof PackageBody) {
                        PackageBody body = (PackageBody) psi;
                        if (body.getPackageName().equalsIgnoreCase(pdesc.getName())) {
                            return body;
                        }
                    }
                }
            }
        }
        return null;
    }

    public PsiElement findDeclaration(PackageDescriptor pdesc) {
        if (pdesc != null) {
            ASTNode[] nodes = getNode().getChildren(TokenSet.create(
                    PlSqlElementTypes.PACKAGE_SPEC
            )
            );

            if (nodes != null) {
                for (ASTNode node : nodes) {
                    PsiElement psi = node.getPsi();
                    if (psi instanceof PackageSpec) {
                        PackageSpec spec = (PackageSpec) psi;
                        if (spec.getPackageName().equalsIgnoreCase(pdesc.getName())) {
                            return spec;
                        }
                    }
                }
            }
        }
        return null;
    }


    SqlStatementMarkupModel sqlStmtModel;
    public SqlStatementMarkupModel getModel(){
        // lazy initialization
        if(sqlStmtModel == null){
            sqlStmtModel = new SqlStatementMarkupModelImpl(this);
        }
        return sqlStmtModel;         
    }

    
}
