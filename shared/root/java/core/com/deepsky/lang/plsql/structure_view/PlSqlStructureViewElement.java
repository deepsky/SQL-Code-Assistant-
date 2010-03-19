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

package com.deepsky.lang.plsql.structure_view;

import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ctrl.CommitStatement;
import com.deepsky.lang.plsql.psi.ctrl.RollbackStatement;
import com.deepsky.lang.plsql.psi.ddl.*;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.Icons;
import com.intellij.ide.structureView.StructureViewTreeElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PlSqlStructureViewElement implements StructureViewTreeElement {
    private PlSqlElement myElement;

    public PlSqlStructureViewElement(final PlSqlElement element) {
        myElement = element;
    }

    public PlSqlElement getValue() {
        return myElement;
    }

    public void navigate(boolean requestFocus) {
        ((NavigationItem) myElement).navigate(requestFocus);
    }

    public boolean canNavigate() {
        return ((NavigationItem) myElement).canNavigate();
    }

    public boolean canNavigateToSource() {
        return ((NavigationItem) myElement).canNavigateToSource();
    }

    public StructureViewTreeElement[] getChildren() {
        final List<PlSqlElement> childrenElements = new ArrayList<PlSqlElement>();

        myElement.acceptChildren(new PlSqlElementVisitor() {

            public void visitElement(PsiElement element) {
                element.acceptChildren(this);
            }

            public void visitPlSqlBlock(final PlSqlBlock node) {
                childrenElements.add(node);
            }

            public void visitFunction(final Function node) {
                childrenElements.add(node);
            }

            public void visitFunctionSpec(final FunctionSpec node) {
                childrenElements.add(node);
            }

            public void visitProcedure(final Procedure node) {
                childrenElements.add(node);
            }

            public void visitProcedureSpec(final ProcedureSpec node) {
                childrenElements.add(node);
            }

            public void visitInsertStatement(final InsertStatement node) {
                childrenElements.add(node);
            }

            public void visitMergeStatement(final MergeStatement node) {
                childrenElements.add(node);
            }

            public void visitSubquery(final Subquery node) {
                childrenElements.add(node);
            }

            public void visitPackageBody(final PackageBody node) {
                childrenElements.add(node);
            }

            public void visitPackageSpec(final PackageSpec node) {
                childrenElements.add(node);
            }

            public void visitSelectStatement(final SelectStatement node) {
                childrenElements.add(node);
            }

            public void visitUpdateStatement(final UpdateStatement node) {
                childrenElements.add(node);
            }

            public void visitDeleteStatement(final DeleteStatement node) {
                childrenElements.add(node);
            }

            public void visitCursorDecl(final CursorDecl node) {
                childrenElements.add(node);
            }

            public void visitVariableDecl(final VariableDecl node) {
                childrenElements.add(node);
            }

            public void visitPackageInitSection(PackageInitSection node) {
                childrenElements.add(node);
            }

            public void visitRecordTypeDecl(RecordTypeDecl node) {
                childrenElements.add(node);
            }

            public void visitTableCollectionDecl(TableCollectionDecl node) {
                childrenElements.add(node);
            }

            public void visitVarrayCollectionDecl(VarrayCollectionDecl decl) {
                childrenElements.add(decl);
            }

            public void visitObjectTypeDecl(ObjectTypeDecl decl) {
                childrenElements.add(decl);
            }

            public void visitTableDefinition(TableDefinition tableDefinition) {
                childrenElements.add(tableDefinition);
            }

            public void visitCreateIndex(CreateIndex index) {
                childrenElements.add(index);
            }

            public void visitCreateView(CreateView view) {
                childrenElements.add(view);
            }

            public void visitSqlPlusCommand(SqlPlusCommand command) {
                childrenElements.add(command);
            }

            public void visitAlterTable(AlterTable table){
                childrenElements.add(table);
            }
            public void visitComment(Comment comment){
                childrenElements.add(comment);
            }
            public void visitCreateTrigger(CreateTrigger trigger) {
                childrenElements.add(trigger);
            }
            public void visitCreateTriggerDML(CreateTriggerDML trigger) {
                childrenElements.add(trigger);
            }

            public void visitSelectStatementUnion(SelectStatementUnion select) {
                childrenElements.add(select);
            }
            public void visitCommitStatement(CommitStatement statement) {
                childrenElements.add(statement);
            }
            public void visitRollbackStatement(RollbackStatement statement) {
                childrenElements.add(statement);
            }
            public void visitDeclarationList(DeclarationList declarationList) {
                childrenElements.add(declarationList);
            }

        });

        StructureViewTreeElement[] children = new StructureViewTreeElement[childrenElements.size()];
        for (int i = 0; i < children.length; i++) {
            children[i] = new PlSqlStructureViewElement(childrenElements.get(i));
        }

        return children;
    }

    public ItemPresentation getPresentation() {
        return new ItemPresentation() {
            String locationString = null;
            public String getPresentableText() {
                try {
                    if (myElement instanceof SelectStatementUnion) {
                        SelectStatementUnion select = (SelectStatementUnion) myElement;
                        return "SELECT UNION";//[from " + tabList + "]";
                    } else if (myElement instanceof SelectStatement) {
                        SelectStatement select = (SelectStatement) myElement;
                        GenericTable[] tabs = select.getFromClause().getTableList();
                        String tabList = "";
                        for (int i = 0; i < 3 && i < tabs.length; i++) {
                            if (i != 0) {
                                tabList = tabList + ",";
                            }
                            String tabName = "";
                            if (tabs[i] instanceof PlainTable) {
                                tabName = StringUtils.discloseDoubleQuotes(((PlainTable) tabs[i]).getTableName().toUpperCase());
                            } else {
                                tabName = "SUBQUERY";
                            }

                            tabList = tabList + tabName;
                            if (i > 3) {
                                tabList = tabList + " ...";
                                break;
                            }
                        }
                        locationString =  "from " + tabList;
                        return "select";
                    } else if (myElement instanceof InsertStatement) {
                        InsertStatement insert = (InsertStatement) myElement;
                        String tab = StringUtils.discloseDoubleQuotes(insert.getIntoTable().getTableName());
                        locationString =  "into " + tab.toUpperCase();
                        return "insert";
                    } else if (myElement instanceof UpdateStatement) {
                        UpdateStatement update = (UpdateStatement) myElement;
                        String tab = StringUtils.discloseDoubleQuotes(update.getTargetTable().getTableName());
                        locationString =  "table " + tab.toUpperCase();
                        return "update";
                    } else if (myElement instanceof DeleteStatement) {
                        DeleteStatement delete = (DeleteStatement) myElement;
                        String tab = delete.getTargetTable().getTableName();
                        locationString =  "table " + tab.toUpperCase();
                        return "delete";
                    } else if (myElement instanceof MergeStatement) {
                        MergeStatement merge = (MergeStatement) myElement;
                        String tab = merge.getIntoTable().getTableName();
                        locationString =  "into " + tab.toUpperCase();
                        return "merge";
                    } else if (myElement instanceof Function) {
                        Function func = (Function) myElement;
                        String name = func.getEName();
                        String argList = Formatter.formatArgList(func);
                        String returnType = func.getReturnType().typeName().toLowerCase();
                        return name.toLowerCase() + argList + ":" + returnType;
                    } else if (myElement instanceof FunctionSpec) {
                        FunctionSpec func = (FunctionSpec) myElement;
                        String name = func.getEName();
                        String argList = Formatter.formatArgList(func);
                        String returnType = func.getReturnType().typeName().toLowerCase();
                        return name.toLowerCase() + argList + ":" + returnType;
                    } else if (myElement instanceof PlSqlBlock) {
                        return "PLSQL BLOCK";
                    } else if (myElement instanceof DeclarationList) {
                        return "DECLARE";
                    } else if (myElement instanceof Subquery) {
                        return "SUBQUERY";
                    } else if (myElement instanceof Procedure) {
                        Procedure proc = (Procedure) myElement;
                        String name = proc.getEName();
                        String argList = Formatter.formatArgList(proc);
                        return name.toLowerCase() + argList;
                    } else if (myElement instanceof ProcedureSpec) {
                        ProcedureSpec proc = (ProcedureSpec) myElement;
                        String name = proc.getEName();
                        String argList = Formatter.formatArgList(proc);
                        return name.toLowerCase() + argList;
                    } else if (myElement instanceof CursorDecl) {
                        CursorDecl cursor = (CursorDecl) myElement;
                        return cursor.getDeclName().toLowerCase() + " [Cursor Declaration]";
                    } else if (myElement instanceof PackageBody) {
                        PackageBody pkg = (PackageBody) myElement;
                        String name = pkg.getPackageName();
                        locationString =  "Package Body";
                        return name.toLowerCase();
                    } else if (myElement instanceof PackageSpec) {
                        PackageSpec pkg = (PackageSpec) myElement;
                        String name = pkg.getPackageName();
                        locationString =  "Package Specification";
                        return name.toLowerCase();
                    } else if (myElement instanceof VariableDecl) {
                        VariableDecl var = (VariableDecl) myElement;
                        String name = var.getDeclName();
                        return name.toLowerCase() + ":" + var.getType().typeName().toLowerCase();
                    } else if (myElement instanceof RecordTypeDecl) {
                        RecordTypeDecl type = (RecordTypeDecl) myElement;
                        String name = type.getDeclName();
                        locationString =  "Record Type";
                        return name.toLowerCase();
                    } else if (myElement instanceof TableCollectionDecl) {
                        TableCollectionDecl type = (TableCollectionDecl) myElement;
                        String name = type.getDeclName();
                        locationString =  "Collection Type";
                        return name.toLowerCase();
                    } else if (myElement instanceof VarrayCollectionDecl) {
                        VarrayCollectionDecl type = (VarrayCollectionDecl) myElement;
                        String name = type.getDeclName();
                        locationString =  "Varray Type";
                        return name.toLowerCase();
                    } else if (myElement instanceof ObjectTypeDecl) {
                        ObjectTypeDecl type = (ObjectTypeDecl) myElement;
                        String name = type.getDeclName();
                        locationString =  "Object Type";
                        return name.toLowerCase();
                    } else if (myElement instanceof PackageInitSection) {
                        return "INIT SECTION";
                    } else if (myElement instanceof TableDefinition) {
                        TableDefinition def = (TableDefinition) myElement;
                        return "CREATE TABLE [" + StringUtils.discloseDoubleQuotes(def.getTableName().toUpperCase()) + "]";
                    } else if (myElement instanceof AlterTable) {
                        AlterTable alter = (AlterTable) myElement;
                        return "ALTER TABLE [" + StringUtils.discloseDoubleQuotes(alter.getTableName().toUpperCase()) + "]";
                    } else if (myElement instanceof Comment) {
                        Comment alter = (Comment) myElement;
                        locationString =  alter.getComment();
                        return "comment";
                    } else if (myElement instanceof CreateIndex) {
                        CreateIndex alter = (CreateIndex) myElement;
                        return "CREATE INDEX [" + alter.getIndexName().toUpperCase() + " on " + alter.getTableName().toUpperCase() + "]";
                    } else if (myElement instanceof CreateView) {
                        CreateView view = (CreateView) myElement;
                        return "CREATE VIEW [" + view.getViewName().toUpperCase() + "]";
                    } else if (myElement instanceof CreateTriggerDML) {
                        CreateTriggerDML trigger = (CreateTriggerDML) myElement;
                        return "CREATE TRIGGER ["
                                + trigger.getTriggerName().toUpperCase() + " on "
                                + trigger.getTableName().toUpperCase() + "]";
                    } else if (myElement instanceof CreateTrigger) {
                        CreateTrigger trigger = (CreateTrigger) myElement;
                        return "CREATE TRIGGER [" + trigger.getTriggerName().toUpperCase() + "]";
                    } else if (myElement instanceof SqlPlusCommand) {
                        SqlPlusCommand sqlplus = (SqlPlusCommand) myElement;
                        locationString = "sqlplus command";
                        return sqlplus.getCommand().getText().toLowerCase();
                    } else if (myElement instanceof CommitStatement) {
                        return "COMMIT";
                    } else if (myElement instanceof RollbackStatement) {
                        return "ROLLBACK";
                    }
                    return  myElement.getText(); //Name();
                } catch (SyntaxTreeCorruptedException e) {
                    return "";
                }
            }

            public TextAttributesKey getTextAttributesKey() {
                if (myElement instanceof Procedure) {
//                    return CodeInsightColors.NOT_USED_ELEMENT_ATTRIBUTES;
//                    TextAttributes attr = new TextAttributes();
//                    attr.setFontType(7);
//                    return  TextAttributesKey.createTextAttributesKey("PLSQL.01", attr );
                } else if (myElement instanceof Function) {
//                    return CodeInsightColors.DUPLICATE_FROM_SERVER;
//                    TextAttributes attr = new TextAttributes();
//                    attr.setFontType(5);
//                    return  TextAttributesKey.createTextAttributesKey("PLSQL.01", attr );
                } else if (myElement instanceof SelectStatement) {
                    //return CodeInsightColors.ERRORS_ATTRIBUTES;
//                    TextAttributes attr = new TextAttributes();
//                    attr.setFontType(3);
//                    return  TextAttributesKey.createTextAttributesKey("PLSQL.01", attr );
                }

                return null;
            }

            public String getLocationString() {
                return locationString;
            }

            public Icon getIcon(boolean open) {
                if (myElement instanceof Procedure) {
                    return Icons.PROCEDURE_BODY;
                } else if (myElement instanceof ProcedureSpec) {
                    return Icons.PROCEDURE_SPEC;
                } else if (myElement instanceof Function) {
                    return Icons.FUNCTION_BODY;
                } else if (myElement instanceof FunctionSpec) {
                    return Icons.FUNCTION_SPEC;
                } else if (myElement instanceof CursorDecl) {
                    return Icons.CURSOR_DECL;
                } else if (myElement instanceof PlSqlBlock) {
                    // todo -
                } else if (myElement instanceof PackageBody) {
                    return Icons.PACKAGE_BODY;
                } else if (myElement instanceof PackageSpec) {
                    return Icons.PACKAGE_SPEC;
                } else if (myElement instanceof VariableDecl) {
                    return Icons.VARIABLE_DECL;
                } else if (myElement instanceof RecordTypeDecl) {
                    return Icons.RECORD_TYPE_DECL;
                } else if (myElement instanceof TableCollectionDecl) {
                    return Icons.TABLE_COLL_DECL;
                } else if (myElement instanceof VarrayCollectionDecl) {
                    // todo -
                    return Icons.TABLE_COLL_DECL;
                } else if (myElement instanceof ObjectTypeDecl) {
                    // todo -
                    return Icons.TABLE_COLL_DECL;
                } else if (myElement instanceof PackageInitSection) {
                    // todo -
                } else if (myElement instanceof SelectStatement) {
                    // todo -
                } else if (myElement instanceof InsertStatement) {
                    // todo -
                } else if (myElement instanceof UpdateStatement) {
                    // todo -
                } else if (myElement instanceof Subquery) {
                    // todo -
                }

                return null; //myElement.getIcon(Iconable.ICON_FLAG_OPEN);
            }
        };
    }

}

