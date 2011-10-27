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

import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.utils.ArgumentListHelper;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.struct.Type;
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

public class FunctionLookupElement <T extends LookupElement> extends LookupElementDecorator<T> {

    final private static int MAX_LEN = 69;

    String name;
    String args;
    protected FunctionLookupElement(T delegate, String name, String args) {
        super(delegate);
        this.name = name;
        this.args = args;
    }

    public static FunctionLookupElement create(String _name, ArgumentSpec[] args, Type type, Icon icon){
        String name = _name;
        String tail = "()";
        if(args.length == 0){
//            name = name + "()";
        } else {
//            name = name + Formatter.formatArgList(args);
            tail = Formatter.formatArgList(args);
        }

        String typeName = type.typeName();
        if(tail.length() + name.length() + typeName.length() > MAX_LEN){
            tail = cutOff(tail, MAX_LEN - typeName.length() - name.length());
        }
//        if(name.length() + typeName.length() > MAX_LEN){
//            name = cutOff(name, MAX_LEN - typeName.length());
//        }

        LookupElement e = LookupElementBuilder.create(name)
                            .setIcon(icon)
                            .setTypeText(type.typeName())
                            .setTailText(tail)
                            .setCaseSensitive(false);

        return new FunctionLookupElement<LookupElement>(e, name, new ArgumentListHelper(args).encodeArgumentsWoNames());
    }

    private static String cutOff(String name, int length) {
        return name.substring(0, length - "...".length()) + "...";
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

        final boolean hasParams = true; //datatype.isSizeable(); //DataTypeParenthesesHandler.hasParams(item, allItems, overloadsMatter); //, myMethod);
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
//        return datatype.isSizeable() && !datatype.isSizeOptional();
        return true;
//      if (place == null) return true;
//      return !(place.getParent() instanceof PsiImportStaticReferenceElement);
    }

    private boolean shouldInsertRParenth(char completionChar, TailType tailType, boolean hasParams) {
        if (tailType == TailType.SMART_COMPLETION) {
            return false;
        }

        if (completionChar == '(' ){ //&& datatype.isSizeable()) { //!hasParams) {
            //it's highly probable that the user will type ')' next and it may not be overwritten if the flag is off
            return CodeInsightSettings.getInstance().AUTOINSERT_PAIR_BRACKET;
        }

        return true;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FunctionLookupElement that = (FunctionLookupElement) o;
        return (name+ args).equals(that.name + that.args);
    }


    public int hashCode() {
        return (name+ args).hashCode();
    }

}
