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

package com.deepsky.navigation;

import com.deepsky.lang.plsql.psi.ddl.CreateTempTable;
import com.deepsky.lang.plsql.psi.ddl.TableDefinition;
import com.deepsky.lang.plsql.psi.impl.TableDefinitionImpl;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.resolver.utils.ResolveUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.view.Icons;
import com.intellij.navigation.ItemPresentation;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.vcs.FileStatus;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class NavigationItemProvider {

    public static NavigationItem create(CreateTempTable table) {
        return new NavigationItemImpl(
                table, table.getName(), table.getName(), null, Icons.TEMP_TABLE, TextAttributesKey.find("SQL.TABLE")
        );
    }

    public static ItemPresentation create(String ctxPath, String value){
        ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(ctxPath);
        String ctxName = parser.lastCtxName();
        int ctxType = parser.extractLastCtxType();
        switch(ctxType){
            case ContextPath.PACKAGE_BODY:
                return create(ctxName, null, Icons.PACKAGE_BODY, null);
            case ContextPath.PACKAGE_SPEC:
                return create(ctxName, null, Icons.PACKAGE_SPEC, null);
            case ContextPath.TABLE_DEF: {
                int ttype = ContextPathUtil.extractTableTypeFromTableValue(value);
                int ptype = ContextPathUtil.extractPartitionTypeFromTableValue(value);
                return create(ctxName, null, TableDefinitionImpl.chooseIcon(ttype, ptype), null);
            }
            case ContextPath.VIEW_DEF:
                return create(ctxName, null, Icons.VIEW, null);
            case ContextPath.CREATE_TRIGGER:
                return create(ctxName, null, Icons.TRIGGER, null);
            case ContextPath.COLLECTION_TYPE:
            case ContextPath.RECORD_TYPE:
            case ContextPath.OBJECT_TYPE:
            case ContextPath.VARRAY_TYPE:
                return create(ctxName, null, Icons.RECORD_TYPE_DECL, null);
            case ContextPath.FUNCTION_BODY: {
                String presentationName = decodeExecSignature(ContextPath.FUNCTION_BODY, ctxName, value);
                return create(presentationName, parentScope(ctxPath), Icons.FUNCTION_BODY, null);
            }
            case ContextPath.FUNCTION_SPEC: {
                String presentationName = decodeExecSignature(ContextPath.FUNCTION_SPEC, ctxName, value);
                return create(presentationName, parentScope(ctxPath), Icons.FUNCTION_SPEC, null);
            }
            case ContextPath.PROCEDURE_BODY: {
                String presentationName = decodeExecSignature(ContextPath.PROCEDURE_BODY, ctxName, value);
                return create(presentationName, parentScope(ctxPath), Icons.PROCEDURE_BODY, null);
            }
            case ContextPath.PROCEDURE_SPEC: {
                String presentationName = decodeExecSignature(ContextPath.PROCEDURE_SPEC, ctxName, value);
                return create(presentationName, parentScope(ctxPath), Icons.PROCEDURE_SPEC, null);
            }
            case ContextPath.SEQUENCE:
                return create(ctxName, null, Icons.SEQUENCE, null);
            case ContextPath.SYNONYM:
                return create(ctxName, null, Icons.SYNONYM, null);
        }

        return null;
    }

    private static String parentScope(String ctxPath){
        String parentCtx = new ContextPathUtil.CtxPathParser(ctxPath).getParentCtx();
        ContextPathUtil.CtxPathParser parser = new ContextPathUtil.CtxPathParser(parentCtx);
        switch(parser.extractLastCtxType()){
            case ContextPath.PACKAGE_BODY:
            case ContextPath.PACKAGE_SPEC:
                return "(" + parser.lastCtxName() + ")";
        }
        return null;
    }

    private static String decodeExecSignature(int type, String name, String value){
        switch(type){
            case ContextPath.FUNCTION_SPEC:
            case ContextPath.FUNCTION_BODY: {
                ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
                Type t = ContextPathUtil.extractRetType(value);
                return Formatter.formatFunctionSignature(name, args, t, 40);
            }
            case ContextPath.PROCEDURE_BODY:
            case ContextPath.PROCEDURE_SPEC: {
                ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
                return Formatter.formatProcedureSignature(name, args, 40);
            }
        }
        return null;
    }


    public static ItemPresentation create(
            final String presentableText,
            final String locationString,
            final Icon icon,
            final TextAttributesKey akey) {

        return new ItemPresentation() {
            public String getPresentableText() {
                return presentableText;
            }

            public String getLocationString() {
                return locationString;
            }

            public Icon getIcon(boolean open) {
                return icon;
            }

            public TextAttributesKey getTextAttributesKey() {
                return akey;
            }
        };
    }


    public static NavigationItem create(TableDefinition table) {
        return new NavigationItemImpl(
                table, table.getName(), table.getName(), null, Icons.TEMP_TABLE, TextAttributesKey.find("SQL.TABLE")
        );
    }

    private static class NavigationItemImpl implements NavigationItem {
        NavigationItem delegate;
        String name;
        String presentableText;
        String locationString;
        Icon icon;
        TextAttributesKey akey;

        public NavigationItemImpl(
                Object delegate,
                String name,
                String presentableText,
                String locationString,
                Icon icon,
                TextAttributesKey akey) {
            this.name = name;
            this.presentableText = presentableText;
            this.locationString = locationString;
            this.icon = icon;
            this.akey = akey;
            this.delegate = (delegate instanceof NavigationItem) ? (NavigationItem) delegate : null;
        }

        public String getName() {
            return name;
        }

        public ItemPresentation getPresentation() {
            return new ItemPresentation() {
                public String getPresentableText() {
                    return presentableText;
                }

                public String getLocationString() {
                    return locationString;
                }

                @Nullable
                public Icon getIcon(boolean open) {
                    return icon;
                }

                @Nullable
                public TextAttributesKey getTextAttributesKey() {
                    return akey;
                }
            };
        }

        public FileStatus getFileStatus() {
            return null;
        }

        public void navigate(boolean requestFocus) {
            if (delegate != null)
                delegate.navigate(requestFocus);
        }

        public boolean canNavigate() {
            return (delegate != null) && delegate.canNavigate();
        }

        public boolean canNavigateToSource() {
            return (delegate != null) && delegate.canNavigateToSource();
        }
    }

}
