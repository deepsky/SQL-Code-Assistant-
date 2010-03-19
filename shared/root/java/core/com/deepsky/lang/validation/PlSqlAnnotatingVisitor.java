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

import com.deepsky.database.ObjectCache;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.conf.PluginSettingsBean;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.*;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.psi.ref.Table;
import com.deepsky.lang.plsql.psi.ref.TableWithLink;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiVariableContext;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class PlSqlAnnotatingVisitor extends PlSqlElementVisitor implements Annotator {
    static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlAnnotatingVisitor");

    private AnnotationHolder myHolder;
    private Project project;

    private static final int RESOLVE_REFERENCE = 1;
    private static final int RESOLVE_UDT = 2;
    private static final int VALIDATE_FUNC_SIGNATURE = 3;
    private static final int VALIDATE_TABLE = 4;
    private static final int VALIDATE_INSERT = 5;
    private static final int HIGHLIGHT_SYNTAX_ERRORS = 6;


    private boolean isEnabled(int feature) {
        PluginSettingsBean settings = PluginKeys.PLUGIN_SETTINGS.getData(project);
        switch (feature) {
            case RESOLVE_REFERENCE:
                return settings.getResolveReference();
            case RESOLVE_UDT:
                return settings.getResolveUdt();
            case VALIDATE_FUNC_SIGNATURE:
                return settings.getValidateFunc();
            case VALIDATE_TABLE:
                return settings.getValidateTables();
            case VALIDATE_INSERT:
                return settings.getValidateInsert();
            case HIGHLIGHT_SYNTAX_ERRORS:
                return settings.getHighlightSyntaxErrors();
        }
        return true;
    }

    private boolean isEnabled2(int tag) {
        return isEnabled(tag) && PluginKeys.OBJECT_CACHE.getData(project).isReady();
    }

    public synchronized void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
        myHolder = holder;

        this.project = psiElement.getProject();
        try {
            psiElement.accept(this);
        } catch (SyntaxTreeCorruptedException e) {
        }
        myHolder = null;
    }

    Map<String, ProfileData> metrics = new HashMap<String, ProfileData>();

    public void visitPlSqlFile(PlSqlFile node) {
        log.info("#visitPlSqlFile, file: " + node.getName() + " address: " + node + " annotator: " + this);
//        node.invalidateCaches();

        GenericTableBase.cache.clear();
        // todo - dirty hack
        NameFragmentRefImpl.resolveCache.clear();
        CollectionMethodCallImpl.resolveCache.clear();
        CallableCompositeNameBase.resolveCache.clear();


        for (Map.Entry<String, ProfileData> e : metrics.entrySet()) {
            log.info(" -- profiler: " + e.getKey() + " counter: " + e.getValue().counter + " timespent: " + e.getValue().timespent);
        }

        // validate RangeHighlighter
        node.getModel().validate();

        // clean up metrics
        metrics.clear();
    }
/*
todo -- Gutter example 
    public void visitSelectStatement(SelectStatement node) {
        Annotation ann= myHolder.createInfoAnnotation(node, null);
        ann.setGutterIconRenderer(new StatementGutterRenderer());

    }
*/

//    public void visitSelectStatement(SelectStatement node) {
//        RangeHighlighter m = ((PlSqlFile)node.getContainingFile()).getUserData2(node.getTextOffset());
//        if(m != null){
//            Annotation ann= myHolder.createInfoAnnotation(node, null);
//            ann.setGutterIconRenderer(new StatementGutterRenderer());
//        }
//    }


    public void visitSqlPlusCommand(SqlPlusCommand command) {
        Annotation ann = myHolder.createInfoAnnotation(command.getCommand(), null);
        TextAttributesKey attr = TextAttributesKey.find("SQLPLUS.CMD");
        ann.setTextAttributes(attr);
    }

    public void visitDatatype(DataType dataType) {
        Annotation ann = myHolder.createInfoAnnotation(dataType.getTypeName(), null);
        TextAttributesKey attr = TextAttributesKey.find("SQL.DATA_TYPE");
        ann.setTextAttributes(attr);
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


    public void visitInsertStatement(InsertStatement node) {
        if (isEnabled2(VALIDATE_INSERT)) { //isEnabled(VALIDATE_INSERT) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                StatementValidator.validate(myHolder, node);
            } catch (SyntaxTreeCorruptedException e) {
                // todo --
            }
        }
    }

    public void visitUpdateStatement(UpdateStatement node) {
        // todo
    }

    public void visitDeleteStatement(DeleteStatement node) {
        // todo
    }

/*
    public void visitColumnSpec(ColumnSpec spec) {
        ProfileObject profile = createProfile("visitColumnSpec");
        if (ObjectCacheFactory.getObjectCache().isReady()) {
            PsiElement psi = spec.getParent().getParent();
            if (psi == null) {
                // skip name validation
            } else if (psi.getNode().getElementType() == PLSqlTypesAdopted.INSERT_COMMAND) {
                try {
                    // we are not interested in an actual type
                    spec.getColumnType();
                } catch (ValidationException e) {
                    InsertStatement insert = (InsertStatement) psi;
                    myHolder.createErrorAnnotation(spec, "Table " + insert.getIntoTable().getTableName() + " has no column " + spec.getColumnNameRef());
                }
            } else if (psi.getNode().getElementType() == PLSqlTypesAdopted.UPDATE_COMMAND) {
                try {
                    // we are not interested in an actual type
                    spec.getColumnType();
                } catch (ValidationException e) {
                    UpdateStatement upd = (UpdateStatement) psi;
                    myHolder.createErrorAnnotation(spec, "Table " + upd.getTargetTable().getTableName() + " has no column " + spec.getColumnNameRef());
                }
            } else if (psi.getNode().getElementType() == PLSqlTypesAdopted.CREATE_VIEW) {
                // skip name validation
            }

        }
        saveProfile(profile);
    }
*/


    public void visitVariableDecl(VariableDecl decl){
        PsiElement vname = decl.getVariableName();
        Annotation ann = myHolder.createInfoAnnotation(vname, null);
        TextAttributesKey attr = TextAttributesKey.find("PLSQL.VAR");
        ann.setTextAttributes(attr);
    }

    public void visitObjectReference(ObjectReference node) {
        ProfileObject profile = createProfile("visitObjectReference");
        PsiElement causeElem = null;
        String message = null;
        try {
            NameFragmentRef[] fragments = node.getNamePieces();
            ResolveContext777 ctx = node.getResolveContext();
            if (ctx instanceof PsiVariableContext) {
                Annotation ann = myHolder.createInfoAnnotation(fragments[0], null);
                TextAttributesKey attr = TextAttributesKey.find("PLSQL.VAR");
                ann.setTextAttributes(attr);
            }

            if (isEnabled2(RESOLVE_REFERENCE)) {
                int pos = fragments.length;
                for (int i = 1; --pos > 0; i++) {
                    ctx = ctx.resolve(fragments[i]);
                }
            }

//                NameFragmentRef last = fragments[fragments.length - 1];
//                ResolveContext777 ctx = last.resolveContext();

//                ResolveContext777 ctx = node.getResolveContext();
//                Type t = node.getExpressionType();
        } catch (ValidationException e) {
            causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
            message = refineExcMessage(e.getMessage(), "Name not resolved: " + node.getText());
        } catch (NameNotResolvedException e) {
            causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
            message = refineExcMessage(e.getMessage(), "Name not resolved: " + node.getText());
        }

        if (isEnabled2(RESOLVE_REFERENCE) && causeElem != null) {
            myHolder.createErrorAnnotation(causeElem, message);
        }

        saveProfile(profile);
    }

    String refineExcMessage(String message, String _default) {
        if (message == null) {
            return _default;
        } else if (message.length() == 0) {
            return _default;
        } else {
            return message;
        }
    }

    public void visitTypeNameReference(TypeNameReference reference) {
        ProfileObject profile = createProfile("visitTypeNameReference");

        // highlight the reference
        NameFragmentRef[] fragments = reference.getNamePieces();
        NameFragmentRef last = fragments[fragments.length - 1];
        Annotation ann = myHolder.createInfoAnnotation(last, null);
        TextAttributesKey attr = TextAttributesKey.find("SQL.USER_DEFINED_TYPE");
        ann.setTextAttributes(attr);

        if (isEnabled2(RESOLVE_UDT)) { //isEnabled(RESOLVE_UDT) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                reference.validate();
                //Type type = reference.resolveType();
                //ann.setTooltip("[" + type.typeName() + "] ...");

                // highlight the reference
//                Annotation ann = myHolder.createInfoAnnotation(last, "");
//                TextAttributesKey attr = TextAttributesKey.find("SQL.USER_DEFINED_TYPE");
//                ann.setTextAttributes(attr);
//                ann.setTooltip("[" + type.typeName() + "] ...");

            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(reference, e.getMessage());
                }
            }
        }
        saveProfile(profile);
    }

    public void visitTable(Table node) {
        String tableName = node.getTableName();
        TextRange tableNameRange = node.getTableNameRange();
        ProfileObject profile = createProfile("visitTable");

        if (isEnabled2(VALIDATE_TABLE)) { //isEnabled(VALIDATE_TABLE) && ObjectCacheFactory.getObjectCache().isReady()) {
            DbObject[] objs = PluginKeys.OBJECT_CACHE.getData(project) //ObjectCacheFactory.getObjectCache().findByNameForType(
                    .findByNameForType(
                            ObjectCache.TABLE | ObjectCache.VIEW, tableName);

            if (objs.length == 0) {
                myHolder.createErrorAnnotation(tableNameRange, "Table not found: " + tableName);
            } else {
                Annotation ann = myHolder.createInfoAnnotation(tableNameRange, "");
                if (objs[0] instanceof SynonymDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.SYNONYM");
                    ann.setTextAttributes(attr);
                    ann.setTooltip(
                            "[Synonym, referenced name: "
                                    + ((SynonymDescriptor) objs[0]).getReferencedTableName() + "] "
                                    + tableName.toLowerCase());
                } else if (objs[0] instanceof ViewDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.VIEW");
                    ann.setTextAttributes(attr);
                    ann.setTooltip("[View] " + tableName.toLowerCase());
                } else if (objs[0] instanceof TableDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.TABLE");
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


    public void visitTableWithLink(TableWithLink node) {
        String tableName = node.getTableName();
        TextRange tableNameRange = node.getTableNameRange();
        ProfileObject profile = createProfile("visitTable");

        if (isEnabled2(VALIDATE_TABLE)) { //isEnabled(VALIDATE_TABLE) && ObjectCacheFactory.getObjectCache().isReady()) {
            DbObject[] objs = PluginKeys.OBJECT_CACHE.getData(project) ///ObjectCacheFactory.getObjectCache()
                    .findByNameForType(
                            ObjectCache.TABLE | ObjectCache.VIEW, tableName);

            if (objs.length == 0) {
                myHolder.createErrorAnnotation(tableNameRange, "Table not found: " + tableName);
            } else {
                Annotation ann = myHolder.createInfoAnnotation(tableNameRange, "");
                if (objs[0] instanceof SynonymDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.SYNONYM");
                    ann.setTextAttributes(attr);
                    ann.setTooltip(
                            "[Synonym, referenced name: "
                                    + ((SynonymDescriptor) objs[0]).getReferencedTableName() + "] "
                                    + tableName.toLowerCase());
                } else if (objs[0] instanceof ViewDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.VIEW");
                    ann.setTextAttributes(attr);
                    ann.setTooltip("[View] " + tableName.toLowerCase());
                } else if (objs[0] instanceof TableDescriptor) {
                    TextAttributesKey attr = TextAttributesKey.find("SQL.TABLE");
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

    public void visitDDLTable(DDLTable node) {
        TextRange tableNameRange = node.getTableNameRange();
        Annotation ann = myHolder.createInfoAnnotation(tableNameRange, null);
        TextAttributesKey attr = TextAttributesKey.find("SQL.TABLE");
        ann.setTextAttributes(attr);
    }


    public void visitSelectField(SelectFieldExpr node) {
        ProfileObject profile = createProfile("visitSelectField");
        if (isEnabled2(RESOLVE_REFERENCE)) { //isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                Type t = node.getExpression().getExpressionType();
                if (t == null) {
                    myHolder.createErrorAnnotation(node, "INTERNAL ERROR! NullPointerException for this name!");
                } else if (t.typeId() == Type.UNKNOWN) {
                    myHolder.createWarningAnnotation(node, "Type unknown");
                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
                myHolder.createErrorAnnotation(causeElem, e.getMessage());
            }
        }
        saveProfile(profile);
    }

    public void visitWhereCondition(WhereCondition node) {
        ProfileObject profile = createProfile("visitWhereCondition");
        if (isEnabled2(RESOLVE_REFERENCE)) { //isEnabled(RESOLVE_REFERENCE)&& ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                Expression expr = node.getCondition();
                if (expr != null)
                    if (expr.getExpressionType() != null)
                        if (expr.getExpressionType().typeId() == Type.BOOLEAN) {
                            // all checks passed
                            return;
                        }
                // some checks failed
                myHolder.createWarningAnnotation(node, "Invalid relational operator");
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
                myHolder.createErrorAnnotation(causeElem, e.getMessage());
            }
        }
        saveProfile(profile);
    }

    public void visitSequenceExpr(SequenceExpr expr) {
        ProfileObject profile = createProfile("visitSequenceExpr");
        if (isEnabled2(RESOLVE_REFERENCE)) { //isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
            PlSqlElement sequenceName = expr.getSequenceName();
            DbObject[] objs = PluginKeys.OBJECT_CACHE.getData(project) ///ObjectCacheFactory.getObjectCache()
                    .findByNameForType(
                            ObjectCache.SEQUENCE, sequenceName.getText()
                    );

            if (objs.length != 1) {
                myHolder.createErrorAnnotation(sequenceName, "Sequence not found: " + sequenceName.getText());
            }
        }
        saveProfile(profile);
    }


    /**
     * Make sure that returning type is BOOLEAN
     *
     * @param node
     */
    public void visitCondition(Condition node) {
        ProfileObject profile = createProfile("visitCondition");
        if (isEnabled2(RESOLVE_REFERENCE)) { //isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                if (node.getExpressionType().typeId() != Type.BOOLEAN) {
                    myHolder.createWarningAnnotation(node, "Invalid relational operator");
                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
                myHolder.createErrorAnnotation(causeElem, e.getMessage());
            } catch (SyntaxTreeCorruptedException e) {
                myHolder.createErrorAnnotation(node, "Invalid relational operator");
            }
        }

        saveProfile(profile);
    }


    /**
     * Validate argument list
     *
     * @param node
     */
    public void visitProcedureCall(ProcedureCall node) {
        ProfileObject profile = createProfile("visitProcedureCall");
        if (isEnabled2(VALIDATE_FUNC_SIGNATURE)) {
            try {
                ResolveContext777 ctx = node.getCompositeName().getResolveContext();
            } catch (NameNotResolvedException e) {
                PsiElement causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
                myHolder.createErrorAnnotation(causeElem, e.getMessage());
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
                myHolder.createErrorAnnotation(causeElem, e.getMessage());
            }
        }
        saveProfile(profile);
    }

    public void visitFunctionCall(FunctionCall node) {
        ProfileObject profile = createProfile("visitFunctionCall");

        if (isEnabled2(VALIDATE_FUNC_SIGNATURE)) {
            try {
                ResolveContext777 ctx = node.getCompositeName().getResolveContext();

            } catch (NameNotResolvedException e) {
                PsiElement causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
                myHolder.createErrorAnnotation(causeElem, e.getMessage());
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement() != null ? e.getCauseElement() : node;
                myHolder.createErrorAnnotation(causeElem, e.getMessage());
            }
        }

        saveProfile(profile);
    }

    public void visitCallableCompositeName(CallableCompositeName node) {
        NameFragmentRef[] parts = node.getNamePieces();
        if (parts.length == 1) {
            ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
            if (ocache.findSystemFunction(parts[0].getText()).size() > 0) {
                Annotation ann = myHolder.createInfoAnnotation(node, null);
                TextAttributesKey attr = TextAttributesKey.find("PLSQL.SYSFUNC");
                ann.setTextAttributes(attr);
            }
        }
    }

    static class ProfileObject {
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

    static class ProfileData {
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
