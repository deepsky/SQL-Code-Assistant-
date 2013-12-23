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

package com.deepsky.lang.validation;

import com.deepsky.database.ConnectionManager;
import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.SqlPlusPromptRem;
import com.deepsky.lang.plsql.psi.names.ColumnNameDDL;
import com.deepsky.lang.plsql.psi.names.ColumnNameRef;
import com.deepsky.lang.plsql.psi.ref.SequenceRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.psi.ref.TableRefWithLink;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.resolver.ResolveDescriptor;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.utils.ResolveUtil;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.struct.SynonymDescriptor;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.settings.SqlCodeAssistantSettings;
import com.intellij.lang.ASTNode;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.progress.ProcessCanceledException;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class PlSqlAnnotatingVisitorLight extends PlSqlElementVisitor implements Annotator {

    static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlAnnotatingVisitorLight");

    private AnnotationHolder myHolder;
//    private Project project;

    private static final int RESOLVE_REFERENCE = 1;
    private static final int RESOLVE_UDT = 2;
    private static final int VALIDATE_FUNC_SIGNATURE = 3;
    private static final int VALIDATE_TABLE = 4;
    private static final int VALIDATE_INSERT = 5;
    private static final int HIGHLIGHT_SYNTAX_ERRORS = 6;


    private SqlCodeAssistantSettings settings = null;


    private boolean isEnabled(int feature) {
        switch (feature) {
            case RESOLVE_REFERENCE:
                return settings.isResolveReference();
            case RESOLVE_UDT:
                return settings.isResolveUdt();
            case VALIDATE_FUNC_SIGNATURE:
                return settings.isValidateFunc();
            case VALIDATE_TABLE:
                return settings.isValidateTables();
            case VALIDATE_INSERT:
                return settings.isValidateInsert();
            case HIGHLIGHT_SYNTAX_ERRORS:
                return settings.isHighlightSyntaxErrors();
        }
        return true;
    }


    private boolean isEnabled2(int tag) {
        return true; //isEnabled(tag); // todo  && PluginKeys.OBJECT_CACHE.getData(project).isReady();
    }

    private static TokenSet t3 = TokenSet.create(
            PLSqlTypesAdopted.VAR_REF,
            PLSqlTypesAdopted.PLSQL_VAR_REF,
            PLSqlTypesAdopted.ERROR_TOKEN_A
    );


    static private boolean isFS(DbUrl dbUrl) {
        return IndexManager.FS_URL.equals(dbUrl);
    }

    public void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
        myHolder = holder;

        try {
            if (settings == null) {
                settings = PluginKeys.PLUGIN_SETTINGS.getData(psiElement.getProject());
            }
            psiElement.accept(this);
        } catch (SyntaxTreeCorruptedException e) {
            // ignore
        } catch (ProcessCanceledException e) {
            settings = null;
        } catch (Throwable e) {
            // ignore
        }
        myHolder = null;
    }

    public void visitErrorTokenWrapper(ErrorTokenWrapper node) {
        if (isEnabled(HIGHLIGHT_SYNTAX_ERRORS)) {
            String text = node.getText();
            // cut down too long message
            text = text != null ? ((text.length() > 40) ? text.substring(0, 40) + " ..." : text) : "Error token";
            myHolder.createErrorAnnotation(node, "Unexpected token: " + text);
        }
    }

    public void SqlPlusPromptRem(SqlPlusPromptRem promptRem) {
        Annotation ann = myHolder.createInfoAnnotation(promptRem, null);
        TextAttributesKey attr = TextAttributesKey.find("PLSQL.LINE_COMMENT");
        ann.setTextAttributes(attr);
    }


    public void visitSqlPlusCommand(SqlPlusCommand command) {
        Annotation ann = myHolder.createInfoAnnotation(command.getCommand(), null);
        TextAttributesKey attr = TextAttributesKey.find("SQLPLUS.CMD");
        ann.setTextAttributes(attr);
    }

    public void visitDatatype(DataType dataType) {
        for (PsiElement e : dataType.getTypeName()) {
            Annotation ann = myHolder.createInfoAnnotation(e, null);
            ann.setTextAttributes(TextAttributesKey.find("SQL.DATA_TYPE"));
        }
    }


    public void visitVariableDecl(VariableDecl decl) {
        PsiElement vname = decl.getVariableName();
        Annotation ann = myHolder.createInfoAnnotation(vname, null);
        TextAttributesKey attr = TextAttributesKey.find("PLSQL.VAR");
        ann.setTextAttributes(attr);

        // make sure semicolon presents
        ASTNode semi = decl.getNode().findChildByType(PlSqlTokenTypes.SEMI);
        if (semi == null) {
            // error higthlight
            // todo -- error higthlighted not correctly
            int endOffset = decl.getTextRange().getEndOffset();
            myHolder.createErrorAnnotation(new TextRange(endOffset, endOffset + 1), "';' expected");
        }
    }

    public void visitTypeNameReference(TypeNameReference reference) {
        // highlight the reference
        NameFragmentRef[] fragments = reference.getNamePieces();
        NameFragmentRef last = fragments[fragments.length - 1];
        Annotation ann = myHolder.createInfoAnnotation(last, null);
        TextAttributesKey attr = TextAttributesKey.find("SQL.USER_DEFINED_TYPE");
        ann.setTextAttributes(attr);

        if (isEnabled(RESOLVE_UDT)) {
            try {
                if (!reference.isTypeValid()) {
                    myHolder.createErrorAnnotation(reference, "Type not resolved");
                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(reference, e.getMessage());
                }
            }
        }
    }


    public void visitSequenceExpr(SequenceExpr expr) {
        if (isEnabled(RESOLVE_REFERENCE)) {
            SequenceRef sequence = expr.getSequence();
            if (sequence.resolveLight() == null) {
                String seqName = sequence.getSequenceName();
//                myHolder.createWarningAnnotation(sequence, "Sequence not found: " + seqName);
//                myHolder.createErrorAnnotation(sequence, "Sequence not found: " + seqName);
                customizeErrorAnn(sequence, "Sequence not found: " + seqName);
            }
        }
    }

    public void visitTableRef(TableRef node) {
        String tableName = node.getTableName();
        TextRange tableNameRange = node.getTableNameRange();

        node.getProject();
        if (isEnabled(VALIDATE_TABLE)) {
            ResolveDescriptor target = node.resolveLight();
            if (target == null) {
                customizeErrorAnn(node, tableNameRange, "Table not found: " + tableName);
            } else {
                Annotation ann = myHolder.createInfoAnnotation(tableNameRange, null);
                if (target.getTargetType() == ResolveDescriptor.SYSTEM_FUNC) { // todo -- not correct! SynonymDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.SYNONYM");
                    ann.setTextAttributes(attr);
                    ann.setTooltip(
                            "[Synonym, referenced name: "
                                    + ((SynonymDescriptor) target).getReferencedTableName() + "] "
                                    + tableName.toLowerCase());
                } else if (target.getTargetType() == ResolveDescriptor.VIEW) {
                    TextAttributesKey attr =
                            isFS(target.getDbUrl()) ?
                                    TextAttributesKey.find("SQL.VIEW.BOLD") :
                                    TextAttributesKey.find("SQL.VIEW");

                    ann.setTextAttributes(attr);
                    ann.setTooltip("[View] " + tableName.toLowerCase());
                } else if (target.getTargetType() == ResolveDescriptor.TABLE) {
                    TextAttributesKey attr =
                            isFS(target.getDbUrl()) ?
                                    TextAttributesKey.find("SQL.TABLE.BOLD") :
                                    TextAttributesKey.find("SQL.TABLE");
//                    TextAttributesKey attr = TextAttributesKey.find("SQL.TABLE");
                    ann.setTextAttributes(attr);
                    ann.setTooltip("[Table] " + tableName.toLowerCase());
                }
            }
        } else {
            Annotation ann = myHolder.createInfoAnnotation(tableNameRange, null);
            TextAttributesKey attr = TextAttributesKey.find("SQL.TABLE");
            ann.setTextAttributes(attr);
        }
    }


    public void visitTableRefWithLink(TableRefWithLink node) {
        String tableName = node.getTableName();
        TextRange tableNameRange = node.getTableNameRange();
        ProfileObject profile = createProfile("visitTable");

        if (isEnabled(VALIDATE_TABLE)) {
            ResolveDescriptor target = node.resolveLight();
            if (target == null) {
//                myHolder.createErrorAnnotation(tableNameRange, "Table not found: " + tableName);
//                myHolder.createWarningAnnotation(tableNameRange, "Table not found: " + tableName);
                customizeErrorAnn(node, tableNameRange, "Table not found: " + tableName);
            } else {
                Annotation ann = myHolder.createInfoAnnotation(tableNameRange, null);
                if (target.getTargetType() == ResolveDescriptor.SYSTEM_FUNC) { // todo -- not correct! SynonymDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.SYNONYM");
                    ann.setTextAttributes(attr);
                    ann.setTooltip(
                            "[Synonym, referenced name: "
                                    + ((SynonymDescriptor) target).getReferencedTableName() + "] "
                                    + tableName.toLowerCase());
                } else if (target.getTargetType() == ResolveDescriptor.VIEW) { //target instanceof ViewDescriptor) {
//                    TextAttributesKey attr = TextAttributesKey.find("SQL.VIEW");
                    TextAttributesKey attr =
                            isFS(target.getDbUrl()) ?
                                    TextAttributesKey.find("SQL.VIEW.BOLD") :
                                    TextAttributesKey.find("SQL.VIEW");
                    ann.setTextAttributes(attr);
                    ann.setTooltip("[View] " + tableName.toLowerCase());
                } else if (target.getTargetType() == ResolveDescriptor.TABLE) { //target instanceof TableDescriptor) {
//                    TextAttributesKey attr = TextAttributesKey.find("SQL.TABLE");
                    TextAttributesKey attr =
                            isFS(target.getDbUrl()) ?
                                    TextAttributesKey.find("SQL.TABLE.BOLD") :
                                    TextAttributesKey.find("SQL.TABLE");
                    ann.setTextAttributes(attr);
                    ann.setTooltip("[Table] " + tableName.toLowerCase());
                }
            }
        } else {
            Annotation ann = myHolder.createInfoAnnotation(tableNameRange, null);
            TextAttributesKey attr = TextAttributesKey.find("SQL.TABLE");
            ann.setTextAttributes(attr);
        }

        saveProfile(profile);
    }

    public void visitColumnNameRef(ColumnNameRef node) {
        if (isEnabled(RESOLVE_REFERENCE)) {
            ResolveFacade facade = ((PlSqlFile) node.getContainingFile()).getResolver();
            ResolveDescriptor rhlp = ResolveUtil.resolveColumnNameRef(facade.getLLResolver(), node);
            if (rhlp == null) {
                String message = "Column not resolved: " + node.getText();
                customizeErrorAnn(node, message);
            }
        }
        Annotation ann = myHolder.createInfoAnnotation(node, null);
        TextAttributesKey attr = TextAttributesKey.find("SQL.COLUMN");
        ann.setTextAttributes(attr);
    }

    protected void annotate1(PsiElement psiElement, IElementType elementType){
        if(elementType == PlSqlElementTypes.GENERIC_REF){

        } else if(elementType == PlSqlElementTypes.PLSQL_VAR_REF){

        } else if(elementType == PlSqlElementTypes.COLUMN_NAME_REF){

        }else if(elementType == PlSqlElementTypes.PARAMETER_REF){

        }
    }

    public void visitObjectReference(ObjectReference node) {
        ResolveDescriptor r = node.resolveLight();
        if (r == null) {
            annotate1(node, PlSqlElementTypes.GENERIC_REF);
        } else if (r.getTargetType() == ResolveDescriptor.VARIABLE) {
            annotate1(node, PlSqlElementTypes.PLSQL_VAR_REF);
        } else if (r.getTargetType() == ResolveDescriptor.TABLE_COLUMN) {
            annotate1(node, PlSqlElementTypes.COLUMN_NAME_REF);
        } else if (r.getTargetType() == ResolveDescriptor.ARGUMENT) {
            annotate1(node, PlSqlElementTypes.PARAMETER_REF);
        } else {
            int kk = 0;
        }
/*

        ResolveDescriptor r = node.resolveLight();
        if (r == null) {
            if (isEnabled(RESOLVE_REFERENCE)) {
                String message = "Name not resolved: " + node.getText();
                customizeErrorAnn(node, message);
            }
        } else if (r.getTargetType() == ResolveDescriptor.VARIABLE) {
            NameFragmentRef[] pieces = node.getNamePieces();
            Annotation ann = myHolder.createInfoAnnotation(pieces[pieces.length - 1], null);
            TextAttributesKey attr = TextAttributesKey.find("PLSQL.VAR");
            ann.setTextAttributes(attr);
        }
*/
    }

    public void visitColumnNameDDL(ColumnNameDDL columnNameDDL) {
        Annotation ann = myHolder.createInfoAnnotation(columnNameDDL, null);
        TextAttributesKey attr = TextAttributesKey.find("SQL.COLUMN");
        ann.setTextAttributes(attr);
    }

    public void visitCallableCompositeName(CallableCompositeName node) {
        if (isEnabled(VALIDATE_FUNC_SIGNATURE)) {
            ResolveDescriptor r = node.resolveLight();
            if (r != null && r.getTargetType() == ResolveDescriptor.SYSTEM_FUNC) {
                Annotation ann = myHolder.createInfoAnnotation(node, null);
                TextAttributesKey attr = TextAttributesKey.find("PLSQL.SYSFUNC");
                ann.setTextAttributes(attr);
            } else if (r == null) {
                customizeErrorAnn(node, "Function not resolved");
            }
        }
    }

    public void visitSpecFunctionCall(SpecFunctionCall function) {
        PsiElement funcName = function.getPsiFunctionName();
        Annotation ann = myHolder.createInfoAnnotation(funcName, null);
        TextAttributesKey attr = TextAttributesKey.find("PLSQL.SYSFUNC");
        ann.setTextAttributes(attr);
    }

    public void visitSystemFunctionCall(FunctionCall function) {
        if (systems.contains(function.getNode().getElementType())) {
            Annotation ann = myHolder.createInfoAnnotation(function.getFirstChild(), null);
            TextAttributesKey attr = TextAttributesKey.find("PLSQL.SYSFUNC");
            ann.setTextAttributes(attr);
        }
    }

/*
    public void visitExtractFunctionCall(FunctionCall function) {
        ASTNode trim = function.getNode().findChildByType(PlSqlTokenTypes.KEYWORD_EXTRACT);
        if (trim != null) {
            Annotation ann = myHolder.createInfoAnnotation(trim, null);
            TextAttributesKey attr = TextAttributesKey.find("PLSQL.SYSFUNC");
            ann.setTextAttributes(attr);
        }
    }
*/

    private static TokenSet systems = TokenSet.create(
            PlSqlElementTypes.SYSDATE_CONST,
            PlSqlElementTypes.DBTIMEZONE,
            PlSqlElementTypes.COUNT_FUNC
    );


    private void customizeErrorAnn(PlSqlElement elem, String message) {
        PlSqlFile file = (PlSqlFile) elem.getContainingFile();
        if (isFS(file.getDbUrl())) {
            if (doHightlightError(file)) {
                myHolder.createErrorAnnotation(elem, message);
            } else {
                // skip error hightlighting: file on FS case
            }
        } else {
            myHolder.createErrorAnnotation(elem, message);
        }
    }

    private void customizeErrorAnn(TableRef elem, TextRange tableNameRange, String message) {
        PlSqlFile file = (PlSqlFile) elem.getContainingFile();
        if (isFS(file.getDbUrl())) {
            if (doHightlightError(file)) {
                myHolder.createErrorAnnotation(tableNameRange, message);
            } else {
                // skip error hightlighting: file on FS case
            }
        } else {
            myHolder.createErrorAnnotation(tableNameRange, message);
        }
    }

    private static final Key<ConnectionManager> CONN_MANAGER_KEY = Key.create("ConnectionManagerAnnot");

    private static boolean doHightlightError(PlSqlFile file) {
        ConnectionManager c = file.getUserData(CONN_MANAGER_KEY);
        if (c == null) {
            c = PluginKeys.CONNECTION_MANAGER.getData(file.getProject());
            file.putUserData(CONN_MANAGER_KEY, c);
        }

        return c.isConnected();
    }


    public void visitPlSqlFile(PlSqlFile node) {
        settings = null;

/*
        log.info("#visitPlSqlFile, file: " + node.getName());

        for (Map.Entry<String, ProfileData> e : metrics.entrySet()) {
            log.info(" -- profiler: " + e.getKey() + " counter: " + e.getValue().counter + " timespent: " + e.getValue().timespent);
        }
*/

        // clean up metrics
        metrics.clear();
    }


    private Map<String, ProfileData> metrics = new HashMap<String, ProfileData>();

    private static class ProfileObject {
        String name;
        long start;
        long end;

        public ProfileObject(String name) {
            this.name = name;
            start = System.currentTimeMillis();
        }

        public void finish() {
            end = System.currentTimeMillis();
        }
    }

    private static class ProfileData {
        int counter = 0;
        long timespent = 0;
    }


    private void saveProfile(ProfileObject profile) {
        profile.finish();

        ProfileData pdata = metrics.get(profile.name);
        if (pdata == null) {
            pdata = new ProfileData();
            metrics.put(profile.name, pdata);
        }

        pdata.counter++;
        pdata.timespent += profile.end - profile.start;
    }

    private ProfileObject createProfile(String s) {
        return new ProfileObject(s);
    }

}
