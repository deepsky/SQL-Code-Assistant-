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

package com.deepsky.lang.completion;

import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.plsql.psi.Statement;
import com.deepsky.view.Icons;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementPresentation;
import com.intellij.codeInsight.lookup.LookupElementRenderer;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.Set;

public class ComplContributor extends CompletionContributor {
    private static final Logger log = Logger.getInstance("#ComplContributor");

    @Override
    public void fillCompletionVariants(CompletionParameters parameters, CompletionResultSet result) {
        log.info("Hello!!!");

/*
      LookupElementBuilder builder = LookupElementBuilder.create("uuiwye");
      builder.addLookupString("iroree");//handleInsert(new InsertHandlerImpl());
      builder.setRenderer(new LookupElementRenderer(){
          @Override
          public void renderElement(LookupElement element, LookupElementPresentation presentation) {
              presentation.setIcon(Icons.SQL_FILE);
              presentation.setTypeText("typeText");//, Icons.CONNECT);
              presentation.setTailText("tailText");
          }
      });
*/
//      final LookupElement item = TailTypeDecorator.withTail(LookupElementBuilder.create("ejhdhd"), TailType.LPARENTH);
//      result.addElement(item); //builder); //.new LookupElementImpl("uuiwye"));
        result.addElement(new LookupElementImpl(parameters.getPosition()));
//        PsiElement elem = parameters.getPosition();
//        PsiElement parent = elem.getParent();
//        if(!(parent instanceof PlSqlFile)){
//            if(parent.getParent() instanceof Statement){
//                // todo
//                int ii =0;
//            }
//
//            int gh =0;
//        }

        super.fillCompletionVariants(parameters, result);
    }
/*
    class LookupElementImpl extends LookupElement {
        String lookUpStr;

        public LookupElementImpl(String var) {
            this.lookUpStr = var;
        }

//        public LookupElementImpl(String[] vars) {
//            this.lookUpStr = var;
//        }

        @NotNull
        public String getLookupString() {
            return lookUpStr;
        }

        public Set<String> getAllLookupStrings() {
            return Collections.singleton(getLookupString());
        }

        public void handleInsert(InsertionContext context) {
            int jj = 0;
        }

        public void renderElement(LookupElementPresentation presentation) {
//          super.renderElement(presentation);
            presentation.setIcon(Icons.SQL_FILE);
            presentation.setItemText("ItemText");
            presentation.setTailText("TailText");
            presentation.setTypeText("TypeText");
        }


        class LookupElementRendererImpl extends LookupElementRenderer<LookupElementImpl> {
            public void renderElement(LookupElementImpl lookupElement, LookupElementPresentation lookupElementPresentation) {
//                lookupElementPresentation.setItemText("itemText", false , true);
                lookupElementPresentation.setIcon(Icons.SQL_FILE);
                lookupElementPresentation.setTypeText("typeText");//, Icons.CONNECT);
                lookupElementPresentation.setTailText("tailText");
            }
        }


        class InsertHandlerImpl implements InsertHandler<LookupElementImpl> {

            public void handleInsert(InsertionContext insertionContext, LookupElementImpl lookupElement) {
                // todo
                //insertionContext.
                int hh = 0;
            }
        }
    }
*/
}
