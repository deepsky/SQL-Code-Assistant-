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

package com.deepsky.findUsages;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.findUsages.options.SqlSearchOptions;
import com.deepsky.findUsages.wordProc.BulkReferenceSearcher;
import com.deepsky.lang.common.PlSqlFile;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.common.PluginKeys2;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.IndexManager;
import com.deepsky.lang.plsql.sqlIndex.WordIndexManager;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.search.searches.ReferencesSearch;
import com.intellij.util.Processor;
import com.intellij.util.QueryExecutor;

public class FindUsagesProcessor implements QueryExecutor<PsiReference, ReferencesSearch.SearchParameters> {
    public boolean execute(ReferencesSearch.SearchParameters queryParameters, Processor<PsiReference> consumer) {

        PsiElement _target = queryParameters.getElementToSearch();
        if(!(_target instanceof PlSqlElement)){
            return true;
        }

        PlSqlElement target = (PlSqlElement) _target;
        final Project project = target.getProject();

        SqlSearchOptions searchOptions = _target.getUserData(SqlFindUsagesHelper.SQL_SEARCH_OPTIONS);

        if(searchOptions != null){
            PsiElement[] elemsToSearch = searchOptions.getElementsToSearch();
            DbUrl dbUrl = searchOptions.getTargetSchema();

            IndexManager indexMan = PluginKeys2.SQL_INDEX_MAN.getData(project);
            AbstractSchema schema = indexMan.getIndex(dbUrl, 0);
            if(schema != null){
                WordIndexManager wordIndexer = schema.getWordIndexManager();
                new BulkReferenceSearcher(wordIndexer).doSearch(project, elemsToSearch, consumer);
            }
        }

        // IMPORTANT! false should be returned regardless of search results
        return false;
    }
}
