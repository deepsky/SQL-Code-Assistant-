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

package com.deepsky.findUsages.actions;

import com.deepsky.findUsages.persistence.SqlSearchParameters;
import com.intellij.find.findUsages.FindUsagesOptions;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadActionProcessor;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.util.Factory;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.usageView.UsageInfo;
import com.intellij.usages.Usage;
import com.intellij.usages.UsageInfoToUsageConverter;
import com.intellij.usages.UsageSearcher;
import com.intellij.util.Processor;
import com.intellij.util.Query;

import java.util.List;


public class SqlSpecificSearchRunner {

    private static final Logger LOG = Logger.getInstance("#SqlSpecificSearchRunner");

    public static class SearchUsageFactory implements Factory<UsageSearcher> {
        final UsageInfoToUsageConverter.TargetElementsDescriptor descriptor;
        final FindUsagesOptions options;

        public SearchUsageFactory(Project project, final UsageInfoToUsageConverter.TargetElementsDescriptor descriptor, final FindUsagesOptions options) {
            this.descriptor = descriptor;
            this.options = options;

            SqlSearchParameters searchParams = SqlSearchParameters.getInstance(project);
            searchParams.searchScope = options.searchScope; 
        }

        public UsageSearcher create() {
            return new UsageSearcher() {
                public void generate(final Processor<Usage> usageProcessor) {

                    final Processor<UsageInfo> usageInfoProcessorToUsageProcessorAdapter = new Processor<UsageInfo>() {
                        public boolean process(UsageInfo usageInfo) {
                            return usageProcessor.process(UsageInfoToUsageConverter.convert(descriptor, usageInfo));
                        }
                    };

                    List<? extends PsiElement> elements =
                            ApplicationManager.getApplication().runReadAction(new Computable<List<? extends PsiElement>>() {
                                public List<? extends PsiElement> compute() {
                                    return descriptor.getAllElements();
                                }
                            });
                    for (final PsiElement element : elements) {
                        ApplicationManager.getApplication().runReadAction(new Runnable() {
                            public void run() {
                                LOG.assertTrue(element.isValid());
                            }
                        });

                        processElementUsages(element, usageInfoProcessorToUsageProcessorAdapter, options);
                    }
                }
            };
        }
    }

    private static void processElementUsages(PsiElement element, final Processor<UsageInfo> adapter, FindUsagesOptions options) {

        // todo -- take a look at CachesBasedRefSearcher
        Query<PsiReference> query = ReferencesSearch.search(element, options.searchScope, false);

        query.forEach(new ReadActionProcessor<PsiReference>() {
            public boolean processInReadAction(final PsiReference ref) {
                TextRange rangeInElement = ref.getRangeInElement();
                UsageInfo usageInfo = new UsageInfo(ref.getElement(), rangeInElement.getStartOffset(), rangeInElement.getEndOffset(), false);
                return adapter.process(usageInfo);
            }
        });
    }

}
