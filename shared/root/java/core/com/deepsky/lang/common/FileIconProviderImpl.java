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
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.psi.impl.TableDefinitionImpl;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.sqlIndex.*;
import com.deepsky.view.Icons;
import com.intellij.ide.FileIconProvider;
import com.intellij.openapi.fileTypes.FileNameMatcher;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class FileIconProviderImpl implements FileIconProvider {

    public Icon getIcon(VirtualFile file, int flags, @Nullable Project project) {

        if (!matchSqlFile(file)) {
            return null;
        }

        try {
            IndexManager indexManager = PluginKeys.SQL_INDEX_MAN.getData(project);
            if (file instanceof DbDumpedSqlFile) {
                // SQL file from DbSchemaIndex
                DbUrl dbUrl = ((DbDumpedSqlFile) file).getDbUrl();
                AbstractSchema schema = indexManager.getIndex(dbUrl, 0);
                if (schema != null) {
                    String encoded = ((SqlFile) file).getEncodedFilePathCtx();
                    return getFileIcon(schema.getIndexTree(), ContextPathUtil.extractFilePath(encoded)); //sqlFile.getEncodedFilePathCtx()); //file.getPath());
                } else {
                    return null;
                }
            } else if (file instanceof FSSqlFile) {
                // SQL file from FSIndex
                SqlDomainIndex index = indexManager.getFSIndex();
                return getFileIcon(index.getIndex(IndexManager.FS_SCHEMA_NAME), file.getPath());
            } else if (file instanceof SysSqlFile) {
                // SQL file from Sys const
                DbUrl dbUrl = ((SysSqlFile) file).getDbUrl();
                AbstractSchema schema = indexManager.getIndex(dbUrl, 0);
                if (schema != null) {
                    String encoded = ((SqlFile) file).getEncodedFilePathCtx();
                    return getFileIcon(schema.getIndexTree(), ContextPathUtil.extractFilePath(encoded)); //sqlFile.getEncodedFilePathCtx()); //file.getPath());
                } else {
                    return null;
                }

            } else {
                // SQL file from FSIndex
                String path = file.getPath();
                IndexTree itree = indexManager.getFSIndex().getIndex(IndexManager.FS_SCHEMA_NAME);
                if (!itree.fileExists(path)) {
                    // not found in the index, file is outside of the content tree
                    return Icons.SQL_FILE_OUTSIDE;
                } else {
                    return getFileIcon(itree, file.getPath());
                }
            }
        } catch (ConfigurationException e) {
            return null;
        }
    }


    private static Icon getFileIcon(IndexTree itree, String filePath) {
        final Integer[] ctxType1 = {null};
        final String[] value1 = new String[1];

        itree.iterateTopNodesForFile(filePath, new IndexEntriesWalkerInterruptable() {
            public boolean process(String ctxPath, String value) {
                String[] path = ctxPath.split("/");

                // validate root context
                switch (path.length) {
                    case 0:
                    case 1:
                        return true;
                    default:
                        String name = "/" + path[2];
                        int ctxType = ContextPathUtil.prefix2type(name.substring(0, 4));
                        ctxType1[0] = ctxType;
                        value1[0] = value;
                        return false;
                }
                //return true;
            }
        });

        if (ctxType1[0] != null) {
            switch (ctxType1[0]) {
                case ContextPath.PACKAGE_SPEC:
                    return Icons.PACKAGE_SPEC;
                case ContextPath.PACKAGE_BODY:
                    return Icons.PACKAGE_BODY;
                case ContextPath.FUNCTION_SPEC:
                    return Icons.FUNCTION_SPEC;
                case ContextPath.FUNCTION_BODY:
                    return Icons.FUNCTION_BODY;
                case ContextPath.PROCEDURE_SPEC:
                    return Icons.PROCEDURE_SPEC;
                case ContextPath.PROCEDURE_BODY:
                    return Icons.PROCEDURE_BODY;
                case ContextPath.TABLE_DEF: {
                    int ttype = ContextPathUtil.extractTableTypeFromTableValue(value1[0]);
                    int ptype = ContextPathUtil.extractPartitionTypeFromTableValue(value1[0]);
                    return TableDefinitionImpl.chooseIcon(ttype, ptype);
                }
                case ContextPath.VIEW_DEF:
                    return Icons.VIEW;
                case ContextPath.SEQUENCE:
                    return Icons.SEQUENCE;
                case ContextPath.SYNONYM:
                    return Icons.SYNONYM;
                case ContextPath.CREATE_TRIGGER:
                    return Icons.TRIGGER;
            }
        }
        return null;
    }


    private boolean matchSqlFile(VirtualFile file) {
        for (FileNameMatcher m : FileTypeManager.getInstance().getAssociations(PlSqlFileType.FILE_TYPE)) {
            if (m.accept(file.getName())) {
                return true;
            }
        }

        return false;
    }

}
