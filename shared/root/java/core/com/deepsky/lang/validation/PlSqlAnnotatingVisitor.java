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

package com.deepsky.lang.validation;

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.conf.PluginSettingsBean;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.*;
import com.deepsky.lang.plsql.psi.ref.DDLTable;
import com.deepsky.lang.plsql.psi.ref.Table;
import com.deepsky.lang.plsql.psi.resolve.NameNotResolvedException;
import com.deepsky.lang.plsql.psi.resolve.ResolveContext777;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.annotation.Annotation;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;


public class PlSqlAnnotatingVisitor extends PlSqlElementVisitor implements Annotator {
    static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlAnnotatingVisitor");

    private AnnotationHolder myHolder;
//    boolean cacheIsReady = false;

    private static final int RESOLVE_REFERENCE = 1;
    private static final int RESOLVE_UDT = 2;
    private static final int VALIDATE_FUNC_SIGNATURE = 3;
    private static final int VALIDATE_TABLE = 4;
    private static final int VALIDATE_INSERT = 5;
    private static final int HIGHLIGHT_SYNTAX_ERRORS = 6;


    private boolean isEnabled(int feature) {
        PluginSettingsBean settings = getCachedSettings();
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


    private PluginSettingsBean getCachedSettings() {
        // todo --
        return PluginKeys.PLUGIN_SETTINGS.getData();
    }

    public synchronized void annotate(@NotNull PsiElement psiElement, @NotNull AnnotationHolder holder) {
        myHolder = holder;
        psiElement.accept(this);
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

//
        for(Map.Entry<String, ProfileData> e: metrics.entrySet()){
            log.info(" -- profiler: " + e.getKey() + " counter: " + e.getValue().counter + " timespent: " + e.getValue().timespent);
        }

//        PluginSettingsBean settings = PluginKeys.PLUGIN_SETTINGS.getData();
//        if(settings != null){
//            this.setResolveReference(settings.getResolveReference());
//            this.setResolveUdt(settings.getResolveUdt());
//            this.setValidateFunc(settings.getValidateFunc());
//            this.setValidateTables(settings.getValidateTables());
//            this.setValidateInsert(settings.getValidateInsert());
//        }

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
        if (isEnabled(VALIDATE_INSERT) && ObjectCacheFactory.getObjectCache().isReady()) {
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

//    static int counterANN = 0;

    public void visitObjectReference(ObjectReference node) {
        ProfileObject profile = createProfile("visitObjectReference");
        if (isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
//                if (++counterANN % 100 == 0) {
//                    log.info("*** error !!! counterANN: " + counterANN);
//                }

                node.getResolveContext();
//                Type t = node.getExpressionType();
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                String message = refineExcMessage(e.getMessage(), "Name not resolved: " + node.getText());
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, message);
                } else {
                    myHolder.createErrorAnnotation(node, message);
                }
            } catch (NameNotResolvedException e) {
                PsiElement causeElem = e.getCauseElement();
                String message = refineExcMessage(e.getMessage(), "Name not resolved: " + node.getText());
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, message);
                } else {
                    myHolder.createErrorAnnotation(node, message);
                }
            }
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

        if (isEnabled(RESOLVE_UDT) && ObjectCacheFactory.getObjectCache().isReady()) {
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

        if (isEnabled(VALIDATE_TABLE) && ObjectCacheFactory.getObjectCache().isReady()) {
            DbObject[] objs = ObjectCacheFactory.getObjectCache().findByNameForType(
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
        if (isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                Type t = node.getExpression().getExpressionType();
                if (t == null) {
                    myHolder.createErrorAnnotation(node, "INTERNAL ERROR! NullPointerException for this name!");
                } else if (t.typeId() == Type.UNKNOWN) {
                    myHolder.createWarningAnnotation(node, "Type unknown");
                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            }
        }
        saveProfile(profile);
    }

    public void visitWhereCondition(WhereCondition node) {
        ProfileObject profile = createProfile("visitWhereCondition");
        if (isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
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
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            }
        }
        saveProfile(profile);
    }

    public void visitSequenceExpr(SequenceExpr expr) {
        ProfileObject profile = createProfile("visitSequenceExpr");
        if (isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
            PlSqlElement sequenceName = expr.getSequenceName();
            DbObject[] objs = ObjectCacheFactory.getObjectCache().findByNameForType(
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
        if (isEnabled(RESOLVE_REFERENCE) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                if (node.getExpressionType().typeId() != Type.BOOLEAN) {
                    myHolder.createWarningAnnotation(node, "Invalid relational operator");
                }
//                Type t = node.getExpressionType();
//                if (t == null) {
//                    // todo - looks strange
//                    myHolder.createWarningAnnotation(node, "Invalid relational operator");
//                } else if (t.typeId() != Type.BOOLEAN) {
//                    myHolder.createWarningAnnotation(node, "Invalid relational operator");
//                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
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
        if (isEnabled(VALIDATE_FUNC_SIGNATURE) && ObjectCacheFactory.getObjectCache().isReady()) {
            try {
                ResolveContext777 ctx = node.getCompositeName().getResolveContext();
            } catch (NameNotResolvedException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            }

/*
            try {
                ExecutableDescriptor edesc = ResolveHelper3.resolveCallable(node);
/ *
  todo - skip argument validation for now
                CallArgumentList argList = node.getCallArgumentListNode();
                if (argList != null) {
                    validateCallArgumentList(argList, edesc);
                }
* /
            } catch (NameNotResolvedException e) {
//                myHolder.createErrorAnnotation(node.getFunctionName2(), "Procedure " + node.getFunctionName() + " not resolved");
                PsiElement causeElem = e.getCauseElem();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            }
*/
        }
        saveProfile(profile);
    }


    /**
     * Validate argument list
     *
     * @param node
     */

    public void visitFunctionCall(FunctionCall node) {
        ProfileObject profile = createProfile("visitFunctionCall");
        if (isEnabled(VALIDATE_FUNC_SIGNATURE) && ObjectCacheFactory.getObjectCache().isReady()) {

            try {
                ResolveContext777 ctx = node.getCompositeName().getResolveContext();
            } catch (NameNotResolvedException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            }

/*
            if (node.getParent() instanceof CollectionMethodCall) {
                // collection specific validation

            } else {
                try {
                    ExecutableDescriptor edesc = ResolveHelper3.resolveCallable(node);
/ *
  todo - skip argument validation for now
                CallArgumentList argList = node.getCallArgumentListNode();
                if (argList != null) {
                    validateCallArgumentList(argList, edesc);
                }
* /
                } catch (NameNotResolvedException e) {
                    // try to evaluate call as Collection addressing
                    if (ResolveHelper3.resolveCollectionCall(node).length == 1) {
                        // this is referencing to collection item 
                    } else {
                        PsiElement causeElem = e.getCauseElem();
                        if (causeElem != null) {
                            myHolder.createErrorAnnotation(causeElem, e.getMessage());
                        } else {
                            myHolder.createErrorAnnotation(node, e.getMessage());
                        }
                    }

                } catch (ValidationException e) {
                    PsiElement causeElem = e.getCauseElement();
                    if (causeElem != null) {
                        myHolder.createErrorAnnotation(causeElem, e.getMessage());
                    } else {
                        myHolder.createErrorAnnotation(node, e.getMessage());
                    }
                }
            }
*/
        }
        saveProfile(profile);
    }

/*
    public void visitCallArgumentList(CallArgumentList node) {
        if (cacheIsReady) {
//            Callable call = SupportStuff.findParent(node, Callable.class);
//            if (call == null) {
//                return;
//            }
//            PsiElement elem = SupportStuff.findParentNode(node.getNode(), new PlSqlElementType[] {
//                        PlSqlElementTypes.FUNCTION_CALL,
//                        PlSqlElementTypes.PROCEDURE_CALL
//                    }
//                );
//
//            if(elem == null || !(elem instanceof Callable)){
//                return;
//            }

            if (node.getParent() instanceof Callable) {
                Callable call = (Callable) node.getParent();
                ExecutableDescriptor edesc = null;
                try {
                    edesc = ResolveHelper3.resolveCallable(call);
                } catch (NameNotResolvedException e) {
                    return;
                }

                for (int i = 0; i < node.getArguments().length; i++) {
                    CallArgument a = node.getArguments()[i];
                    try {

                        String variableName = a.getRecordItemName();
                        if (variableName != null && variableName.length() > 0) {
                            // binding by name
                            Type t = edesc.getArgumentType(variableName);
                            if (t.isTypeReference()) {
                                t = ResolveHelper.resolve_TypeReference(t);
                            }
                            Expression expr = a.getExpression();
                            Type t1 = expr.getExpressionType();
                            if (t1.isTypeReference()) {
                                t1 = ResolveHelper.resolve_TypeReference(t1);
                            }

                            if (!TypeValidationHelper.canBeAssigned(t, t1)) {
                                myHolder.createErrorAnnotation(a, "Types mismatched");
                            }
                        } else {
                            // binding by position
                            Type t = edesc.getArgumentType(i);
                            Expression expr = a.getExpression();
                            Type t1 = expr.getExpressionType();

                            if (!TypeValidationHelper.canBeAssigned(t, t1)) {
                                myHolder.createErrorAnnotation(a, "Types mismatched");
                            }
                        }

                    } catch (ValidationException e) {
                        PsiElement causeElem = e.getCauseElement();
                        if (causeElem != null) {
                            myHolder.createErrorAnnotation(causeElem, e.getMessage());
                        } else {
                            myHolder.createErrorAnnotation(node, e.getMessage());
                        }
                    } catch (NameNotResolvedException e) {
                        PsiElement causeElem = e.getCauseElem();
                        if (causeElem != null) {
                            myHolder.createErrorAnnotation(causeElem, e.getMessage());
                        } else {
                            myHolder.createErrorAnnotation(node, e.getMessage());
                        }
                    }
                }
            } else {
                // todo
            }
        }
    }
*/

    void validateCallArgumentList(CallArgumentList node, ExecutableDescriptor edesc) {
        for (int i = 0; i < node.getArguments().length; i++) {
            CallArgument a = node.getArguments()[i];
            try {

                String variableName = a.getVariableName();
                if (variableName != null && variableName.length() > 0) {
                    // binding by name
                    Type t = edesc.getArgumentType(variableName);
                    if (t.isTypeReference()) {
                        t = ResolveHelper.resolve_TypeReference(t);
                    }
                    Expression expr = a.getExpression();
                    Type t1 = expr.getExpressionType();
                    if (t1.isTypeReference()) {
                        t1 = ResolveHelper.resolve_TypeReference(t1);
                    }

                    if (!TypeValidationHelper.canBeAssigned(t, t1)) {
                        myHolder.createErrorAnnotation(a, "Types mismatched");
                    }
                } else {
                    // binding by position
                    Type t = edesc.getArgumentType(i);
                    Expression expr = a.getExpression();
                    Type t1 = expr.getExpressionType();

                    if (!TypeValidationHelper.canBeAssigned(t, t1)) {
                        myHolder.createErrorAnnotation(a, "Types mismatched");
                    }
                }

            } catch (ValidationException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            } catch (NameNotResolvedException e) {
                PsiElement causeElem = e.getCauseElement();
                if (causeElem != null) {
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            }
        }
    }

    /**
     * Argument List validation: Validate formal argument types vs real ones
     *
     * @return - result of the validation
     */
/*
    static public boolean validateCallArgumentList(ExecutableDescriptor edesc, Callable exec) {
        // todo - validate argument types
        CallArgument[] args = exec.getCallArgumentList();

        if (args.length == 0) {
            if (edesc.getArgumentNames().length == 0) {
                // it's ok
            } else {
                // there is possible a case when all of arguments have default values, check it out ...
                for (String name : edesc.getArgumentNames()) {
                    if (!edesc.hasDefault(name)) {
                        // Error: mismatch number of formal and real parameters!
//                        throw new ValidationException("Function: " + "!!!!" + ", number of formal and real parameters mismatched", exec);
//                        myHolder.createErrorAnnotation(exec, "Function: " + "!!!!" + ", number of formal and real parameters mismatched");
                        return false;
                    }
                }
            }
        } else {
            for (int i = 0; i < args.length; i++) {
                CallArgument arg = args[i];
                String var = arg.getRecordItemName();
                if (var != null) {
                    // binding parameter by name
                    Type t = edesc.getArgumentType(var);
                    if (t == null) {
//                        throw new ValidationException("Parameter with name not found: " + var, exec);
//                        myHolder.createErrorAnnotation(exec, "Parameter with name not found: " + var);
                        return false;
                    } else if (!TypeValidationHelper.canBeAssigned(
                            t.typeId(),
                            arg.getExpression().getExpressionType().typeId())) {
//                        throw new ValidationException("Formal type and real type mismatched, parameter: " + i, exec);
//                        myHolder.createErrorAnnotation(exec, "Formal type and real type mismatched, parameter: " + i);
                        return false;
                    }
                } else {
                    // ... by position
                    String name = edesc.getArgumentName(i);
                    if (name == null) {
//                        throw new ValidationException("Function: " + "!!!!!" + ", number of formal and real parameters mismatched", exec);
//                        myHolder.createErrorAnnotation(exec, "Function: " + "!!!!!" + ", number of formal and real parameters mismatched");
                        return false;
                    }
                    Type t = edesc.getArgumentType(name);
                    Type t1 = arg.getExpression().getExpressionType();
                    if (t1 == null || t == null) {
                        int hh = 0;
                    }
                    if (!TypeValidationHelper.canBeAssigned(
                            t.typeId(),
                            t1.typeId()
                    )) {
//                        throw new ValidationException(
//                                "Function: " + "!!!!!" + ", type of argument does not fit a formal parameter, position: " + (i+1), exec);
//                        myHolder.createErrorAnnotation(exec, "Function: " + "!!!!!" + ", type of argument does not fit a formal parameter, position: " + (i+1));
                        return false;
                    }
                }
            }
        }

        return true;
    }
*/
/*
    public void visitArithmeticExpression(ArithmeticExpression node) {

        if (cacheIsReady) {
            try {
                Type t = node.getExpressionType();
                if(t == null){
                    myHolder.createErrorAnnotation(node, "INTERNAL ERROR! NullPointerException for this name!");
                } else if(t.typeId() == Type.UNKNOWN){
                    myHolder.createWarningAnnotation(node, "Type unknown");
                }
            } catch (ValidationException e) {
//                ASTNode causeNode = e.getCauseNode();
                PsiElement causeElem = e.getCauseElement();
                if(causeElem != null){
                    myHolder.createErrorAnnotation(causeElem, e.getMessage());
                } else {
                    myHolder.createErrorAnnotation(node, e.getMessage());
                }
            }
        }
    }
*/
//    void processValidationError(String message, PsiElement causeElem){
//            myHolder.createErrorAnnotation(causeElem, message);
//       } else {
//            myHolder.createErrorAnnotation(node, e.getMessage());
//        }
//    }

    /*
        public void visitCallArgument(CallArgument node) {
            if (cacheIsReady) {
                try {
                    Type t = node.getExpression().getExpressionType();
                    if(t == null){
                        myHolder.createErrorAnnotation(node, "INTERNAL ERROR! NullPointerException for this name!");
                    } else if(t.typeId() == Type.UNKNOWN){
                        myHolder.createWarningAnnotation(node, "Type unknown");
                    }
                } catch (ValidationException e) {
                    PsiElement causeElem = e.getCauseElement();
                    if(causeElem != null){
                        myHolder.createErrorAnnotation(causeElem, e.getMessage());
                    } else {
                        myHolder.createErrorAnnotation(node, e.getMessage());
                    }
                }
            }
        }
    */

/*
    ExecutableDescriptor chooseEDesc(ExecutableDescriptor[] descs, CallArgument[] args){
        for(ExecutableDescriptor edesc: descs){
            if(edesc.getArgumentNames().length == 0 && args.length == 0){
                return edesc;
            }

            for(int i=0; i < edesc.getArgumentNames().length; i++){
                if(args[i].getRecordItemName() != null)
                if(edesc.getArgumentName(i).equalsIgnoreCase())
            }
        }
    }
*/

//    public void visitTable(Table node) {
//        Annotation ann = myHolder.createInfoAnnotation(node, "");
//        TextAttributes textAttributes = new TextAttributes();
//        textAttributes.setForegroundColor(Color.CYAN);//setBackgroundColor(Color.red);
//
//        ann.setEnforcedTextAttributes(textAttributes);
//    }
//    private TextAttributesKey findAttributes(String name) {
//        TextAttributesKey attr = TextAttributesKey.find(name);
//        if (attr.getDefaultAttributes() != null) {
//            return attr;
//        } else {
//            TextAttributes textAttributes = new TextAttributes();
//            if("SQL.SYNONYM".equalsIgnoreCase(name)){
//                textAttributes.setForegroundColor(new Color(22, 33, 133)); //Color.CYAN);
//            } else if("SQL.VIEW".equalsIgnoreCase(name)){
//                textAttributes.setForegroundColor(new Color(123, 33, 33)); //Color.CYAN);
//            } else if("SQL.TABLE".equalsIgnoreCase(name)){
//                textAttributes.setForegroundColor(new Color(22, 120, 33)); //Color.CYAN);
//            } else {
//                textAttributes = HighlighterColors.JAVA_DOT.getDefaultAttributes(); //.setForegroundColor(new Color(22, 33, 33)); //Color.CYAN);
//            }
//            TextAttributesKey attr2 = TextAttributesKey.createTextAttributesKey(
//                    name,
//                    textAttributes
//            );
//
//            return attr2;
//        }
//    }


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
