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

package com.deepsky.lang.common;

import com.deepsky.lang.integration.PlSqlFileChangeTracker;
import com.deepsky.lang.plsql.parser.WrappedPackageException;
import com.deepsky.lang.plsql.resolver.factory.SqlASTFactory;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.text.BlockSupport;
import com.intellij.psi.tree.IFileElementType;

public class PlSqlFileElementType extends IFileElementType {

    static final LoggerProxy log = LoggerProxy.getInstance("#PlSqlFileElementType");

    SqlASTFactory astFactory;
    PlSqlFileChangeTracker tracker;

    public PlSqlFileElementType() {
        super("FILE", Language.findInstance(PlSqlLanguage.class));
    }

    public ASTNode parseContents(ASTNode chameleon) {
        final Project project = chameleon.getPsi().getProject();
        if(astFactory == null){
             astFactory = (SqlASTFactory) LanguageASTFactory.INSTANCE.forLanguage(getLanguage());
        }

        if(tracker == null){
             tracker = PluginKeys.PLSQLFILE_CHANGE_TRACKER.getData(project);
        }

        long start = System.currentTimeMillis();
        ASTNode reparsed = chameleon.getUserData(BlockSupport.TREE_TO_BE_REPARSED);
        try {
            astFactory.clearUpdateCounter();

            final PsiBuilderFactory factory = PsiBuilderFactory.getInstance();
            final PsiBuilder builder = factory.createBuilder(
                project,
                chameleon,
                null,
                getLanguage(),
                chameleon.getChars()
            );

            final PsiParser parser = LanguageParserDefinitions.INSTANCE.forLanguage(getLanguage()).createParser(project);
            ASTNode root = parser.parse(this, builder);

            return root.getFirstChildNode();
        } catch (WrappedPackageException e) {
            log.warn("Wrapped package occured");
            // todo -- makes a sense to assign ISLOCKED for VirtualFile and change ICON?
            return null;
        } finally {
            if(reparsed != null && astFactory.getUpdateCounter() > 0){
                // code was modified
                astFactory.clearUpdateCounter();
                tracker.indexPlSqlFile((PlSqlFile) reparsed.getPsi());
            }
            log.info("#parseContent: END [time: " + (System.currentTimeMillis()-start) + "]");
        }
    }

}
