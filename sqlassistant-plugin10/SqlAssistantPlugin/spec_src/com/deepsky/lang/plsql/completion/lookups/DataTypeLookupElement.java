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

package com.deepsky.lang.plsql.completion.lookups;

import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.CodeInsightSettings;
import com.intellij.codeInsight.TailType;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.codeInsight.lookup.LookupElementDecorator;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsManager;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class DataTypeLookupElement<T extends LookupElement> extends LookupElementDecorator<T> {

    SQLDatatype datatype;
    protected DataTypeLookupElement(SQLDatatype datatype, T delegate) {
        super(delegate);
        this.datatype = datatype;
    }

    public static DataTypeLookupElement create(String name, Icon icon) {
        LookupElement e = LookupElementBuilder.create(name)
                .setIcon(icon)
                .setCaseSensitive(false);

        return new DataTypeLookupElement<LookupElement>(dataTypes.get(name.toUpperCase()), e);
    }


    public void handleInsert(final InsertionContext context) {
        final Editor editor = context.getEditor();
        final char completionChar = context.getCompletionChar();
        final TailType tailType = null;//getTailType(item, context);
        //final Document document = editor.getDocument();
        final PsiFile file = context.getFile();
        //final int offset = editor.getCaretModel().getOffset();

        context.setAddCompletionChar(false);

        final LookupElement[] allItems = context.getElements();
        final boolean overloadsMatter = false;//allItems.length == 1 && item.getUserData(LookupItem.FORCE_SHOW_SIGNATURE_ATTR) == null;

        final boolean hasParams = datatype.isSizeable(); //DataTypeParenthesesHandler.hasParams(item, allItems, overloadsMatter); //, myMethod);
        final boolean needLeftParenth = isToInsertParenth(file.findElementAt(context.getStartOffset()));
        final boolean needRightParenth = shouldInsertRParenth(completionChar, tailType, hasParams);

        if (needLeftParenth) {
            final CodeStyleSettings styleSettings = CodeStyleSettingsManager.getSettings(context.getProject());
            new DataTypeParenthesesHandler(//myMethod,
                    hasParams,
                    overloadsMatter,
                    styleSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES,
                    styleSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES && hasParams,
                    needRightParenth
            ).handleInsert(context, getDelegate()); // item
//            PsiDocumentManager.getInstance(context.getProject()).commitDocument(document);

        }

/*
      insertExplicitTypeParams(item, document, offset, file);

      final PsiType type = myMethod.getReturnType();
      if (completionChar == '!' && type != null && PsiType.BOOLEAN.isAssignableFrom(type)) {
        PsiDocumentManager.getInstance(myMethod.getProject()).commitDocument(document);
        final PsiMethodCallExpression methodCall =
            PsiTreeUtil.findElementOfClassAtOffset(file, offset, PsiMethodCallExpression.class, false);
        if (methodCall != null) {
          FeatureUsageTracker.getInstance().triggerFeatureUsed(CodeCompletionFeatures.EXCLAMATION_FINISH);
          document.insertString(methodCall.getTextRange().getStartOffset(), "!");
        }
      }
*/

        if (needLeftParenth && hasParams) {
            // Invoke parameters popup
            AutoPopupController.getInstance(context.getProject()).autoPopupParameterInfo(editor, null); //f0);
        }
//      if (tailType == TailType.SMART_COMPLETION || needLeftParenth && needRightParenth) {
//        tailType.processTail(editor, context.getTailOffset());
//      }
        editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
    }


    private boolean isToInsertParenth(PsiElement place) {
        return datatype.isSizeable() && !datatype.isSizeOptional();
        //return true;
//      if (place == null) return true;
//      return !(place.getParent() instanceof PsiImportStaticReferenceElement);
    }

    private boolean shouldInsertRParenth(char completionChar, TailType tailType, boolean hasParams) {
        if (tailType == TailType.SMART_COMPLETION) {
            return false;
        }

        if (completionChar == '(' && datatype.isSizeable()) { //!hasParams) {
            //it's highly probable that the user will type ')' next and it may not be overwritten if the flag is off
            return CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET;
        }

        return true;
    }


//            "binary_integer"
//            | "natural"
//            | "positive"
//            | ("number"(OPEN_PAREN! (NUMBER|ASTERISK) (COMMA! NUMBER)? CLOSE_PAREN! )? )
//            | ("char" (OPEN_PAREN! NUMBER ("byte")? CLOSE_PAREN! )? )
//            | ("long" ("raw")?)
//            | "raw" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
//            | "boolean"
//            | "date"
//            | "timestamp" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
//                (("with" "local" "time" "zone") => ("with" "local" "time" "zone")
//                | ("with" "time" "zone"))?
//
//            | "interval"
//                ( ( "year" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "month")
//                | ("day" (OPEN_PAREN! NUMBER CLOSE_PAREN!)? "to" "second" (OPEN_PAREN! NUMBER CLOSE_PAREN!)?)
//                )
//            | "smallint"
//            | "real"
//            | "numeric" (OPEN_PAREN! NUMBER (COMMA! NUMBER)? CLOSE_PAREN! )?
//            | "int"
//            | "integer" (OPEN_PAREN! NUMBER CLOSE_PAREN! )?
//            | "pls_integer"
//            | "double" "precision"
//            | "float" ( OPEN_PAREN! NUMBER CLOSE_PAREN! )?
//            | "decimal"
//            | "varchar" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
//            | "varchar2" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
//            | "nvarchar" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
//            | "nvarchar2" ( OPEN_PAREN! NUMBER ("byte" | "char")? CLOSE_PAREN! )?
//            | "character" ( OPEN_PAREN! NUMBER CLOSE_PAREN! )?
//            | "mlslabel"
//            | "rowid"
//            | "blob"
//            | "clob"
//            | "nclob"


    public static String[] getDataTypeNames() {
        return dataTypes.keySet().toArray(new String[0]);
    }

    private static Map<String, SQLDatatype> dataTypes = new HashMap<String, SQLDatatype>();

    static {

        dataTypes.put("NUMBER", new SQLDatatype("NUMBER", true, true));
        dataTypes.put("INTEGER", new SQLDatatype("INTEGER", true, true));
        dataTypes.put("VARCHAR2", new SQLDatatype("VARCHAR2", true, false));
        dataTypes.put("NVARCHAR2", new SQLDatatype("NVARCHAR2", true, false));
        dataTypes.put("CHAR", new SQLDatatype("CHAR", true, false));
        dataTypes.put("ROWID", new SQLDatatype("ROWID", false, false));
        dataTypes.put("CLOB", new SQLDatatype("CLOB", false, false));
        dataTypes.put("BLOB", new SQLDatatype("BLOB", false, false));
        dataTypes.put("DATE", new SQLDatatype("DATE", false, false));
        dataTypes.put("TIMESTAMP", new SQLDatatype("TIMESTAMP", false, false));
    }

    private static class SQLDatatype {
        private String name;
        private boolean isSizeable;
        private boolean isSizeOptional;

        public SQLDatatype(String name, boolean isSizeable, boolean isSizeOptional){
            this.name = name;
            this.isSizeable = isSizeable;
            this.isSizeOptional = isSizeOptional;
        }

        public String getName(){
            return name;
        }

        public boolean isSizeable(){
            return isSizeable;
        }

        public boolean isSizeOptional(){
            return isSizeOptional;
        }
    }


}
