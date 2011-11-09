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

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.resolver.ResolveFacade;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.deepsky.view.query_pane.markup.SqlStatementMarkupModel;
import com.deepsky.view.query_pane.markup.impl.SqlStatementMarkupModelImpl;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


public class PlSqlFile extends PsiFileBase implements PlSqlElement, ResolveProvider {

    static private final LoggerProxy log = LoggerProxy.getInstance("#PlSqlFile");

    private SqlStatementMarkupModel sqlStmtModel;
    private CtxPath cachedCtxPath = null;

    public PlSqlFile(FileViewProvider fileViewProvider) {
        super(fileViewProvider, PlSqlFileType.FILE_TYPE.getLanguage());
    }

    public boolean isPhysical() {
        return true;
    }

    @NotNull
    public FileType getFileType() {
        return PlSqlFileType.FILE_TYPE;
    }

    @Nullable
    public String getQuickNavigateInfo() {
        return null;
    }

    public String getStrippedText() {
        return null;
    }

    public void process(Visitor proc) {
        // todo -
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitPlSqlFile(this);
        } else {
            super.accept(visitor);
        }
    }

/*
    public void subtreeChanged() {
        log.info("#subtreeChanged");
        super.subtreeChanged();
    }
*/

    private ResolveFacade domainResolver;
    public ResolveFacade getResolver(){
        if(domainResolver == null){
            VirtualFile file = getVirtualFile();
            IndexManager indexMan = PluginKeys.SQL_INDEX_MAN.getData(getProject());
            if(file instanceof DbDumpedSqlFile){
                // SQL file from DbSchemaIndex
                domainResolver = ((SqlFile)file).getSimpleIndex().getResolveFacade(); //indexMan.findOrCreateIndex(dbUrl).getDomainResolver(); //new DomainScopeResolverImpl(indexMan, dbUrl);
            } else if(file instanceof FSSqlFile){
                // SQL file from FSIndex
                domainResolver = ((SqlFile)file).getSimpleIndex().getResolveFacade();
            } else if(file instanceof SysSqlFile){
                domainResolver = ((SqlFile)file).getSimpleIndex().getResolveFacade();
            } else {
                // SQL file from FSIndex or proxied file
                PsiFile origin = getOriginalFile();
                if(origin != this){
                    return ((PlSqlFile)origin).getResolver();
                } else {
                    domainResolver = indexMan
                            .findOrCreateIndex(IndexManager.FS_URL, 0)
                            .getResolveFacade();
                }
            }
        }
        return domainResolver;
    }

    public void setResolver(ResolveFacade resolver){
        this.domainResolver = resolver;
    }


    public SqlStatementMarkupModel getModel() {
        // lazy initialization
        if (sqlStmtModel == null) {
            sqlStmtModel = new SqlStatementMarkupModelImpl(this);
        }
        return sqlStmtModel;
    }

    @Nullable
    public VirtualFile getVirtualFile(){
        return getOriginalFile().getViewProvider().getVirtualFile();
/*
        VirtualFile vf = super.getVirtualFile();
        if(vf instanceof SqlFile){
            //SqlFile sqlFile = (SqlFile) vf;
            return vf;
        } else {
            // Intellij VirtualFile
            if( vf == null){
                // PsiFile Proxy
                vf = getViewProvider().getVirtualFile();
            }
            return vf;
        }
*/
    }

    public CtxPath getCtxPath1() {
        if (cachedCtxPath == null) {
            VirtualFile vf = getVirtualFile();
            if(vf instanceof SqlFile){
                SqlFile sqlFile = (SqlFile) vf;
                cachedCtxPath = new ContextPathUtil.CtxPathImpl(sqlFile.getEncodedFilePathCtx());
            } else {
                String fsPath = vf != null ? vf.getPath() : "memory";
                // encode path
                String ctxPath = ContextPathUtil.encodeFilePathCtx(fsPath);
                // todo - be careful with "/Dummy.sql" - file name created by IntellijIDEA
                cachedCtxPath = new ContextPathUtil.CtxPathImpl(ctxPath);
            }
        }
        return cachedCtxPath;
    }

    public DbUrl getDbUrl() {
        VirtualFile file = getVirtualFile();
        if(file instanceof DbDumpedSqlFile){
            // SQL file from DbSchemaIndex
            return ((DbDumpedSqlFile)file).getDbUrl();
        } else if(file instanceof FSSqlFile){
            // SQL file from FSIndex
            return IndexManager.FS_URL;
        } else {
            // SQL file from FSIndex
            return IndexManager.FS_URL;
        }
    }

    public void resetCaches() {
        cachedCtxPath = null;
        if(getOriginalFile() != this){
            ((PlSqlFile) getOriginalFile()).resetCaches();
        }
    }
}
