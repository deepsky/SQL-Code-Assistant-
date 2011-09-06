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

package com.deepsky.lang.plsql.resolver.res_newlook;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.resolver.*;
import com.deepsky.lang.plsql.resolver.helpers.*;
import com.deepsky.lang.plsql.resolver.index.*;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.types.RowidType;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RefRes implements RefResolver, NameResolver {


    final static String ELEMENT_REF = "$get_item$";

    protected Map<String, FunctionValidator> sysFuncValidators = new HashMap<String, FunctionValidator>();

    NameResolver nameResolver;
    protected BaseLookupService itreeORIGIN;

    public int resolveRequest = 0;
    //public long resolveTime = 0;

    // default impl
    protected RefResolverListener cacheListener = new RefResolverListener(){
        public void resolveReference(List<ResolveDescriptor> out, String schemaName, RefHolder ref) {
            // do nothing
        }
    };
    
    DbUrl dbUrl;

    public RefRes(DbUrl dbUrl, BaseLookupService _itree, NameResolver nameResolver) {
        this.dbUrl = dbUrl;
        this.itreeORIGIN = _itree;
        this.nameResolver = nameResolver;
    }

    protected void setContextLevelResolver(NameResolver nameResolver){
        this.nameResolver = nameResolver;
    }

    private int getLastCtx(String ctxPath) {
        if (ctxPath == null || ctxPath.length() == 0) {
            return ContextPath.SCHEMA_CTX;
        } else {
            int excl = ctxPath.lastIndexOf("!");
            return ContextPathUtil.prefix2type(ctxPath.substring(excl - 3, excl + 1));
        }
    }


    private class ContextPathParser2 {
        String[] path;

        public ContextPathParser2(String ctxPath) {
            path = ctxPath.split("/");
        }

        public String getCtxName(int part) {
            if (path.length > part && part >= 0) {
                String p = path[part + 1];
                return p.substring(6, p.length());
            } else {
                return "";
            }
        }

        public int getCtxType(int part) {
            if (path.length > part && part >= 0) {
                String p = path[part + 1];
                return ContextPathUtil.prefix2type("/" + p.substring(0, 3));
            } else {
                return -1;
            }
        }

        public int nbrParts() {
            return path.length - 1;
        }

        public int lastCtxType() {
            if (path.length > 1) {
                String p = path[path.length - 1];
                return ContextPathUtil.prefix2type("/" + p.substring(0, 3));
            } else {
                return -1;
            }
        }

        public String lastCtxName() {
            if (path.length > 1) {
                String p = path[path.length - 1];
                return p.substring(6, p.length());
            } else {
                return "";
            }
        }
    }


    public void resolveForRelContext(final List<ResolveDescriptor> out, String ctxPath, final String name, final int refType) {
        itreeORIGIN.findNodesInContextCommon(ctxPath, name, new TreeNode.TreeNodeHandler(){
            public void handleNode(TreeNode node) {
                dispatch(out, node.getParentPath(), node.getPath(), node.getValue(), node.getType(), refType, name);
            }
        });
    }


    public void resolve(final List<ResolveDescriptor> collisions, RefHolder holder) {
        resolveRequest++;

        // generic case
        int refType = holder.getRefType();

        final String front = holder.getFront();
        final String _ctxPath = holder.getCtxPath();
        final String[] names = holder.getNames();

        switch(refType){
            case ContextPath.PACKAGE_REF:
            case ContextPath.TABLE_REF:
            case ContextPath.SEQUENCE_REF:
                switch (names.length) {
                    case 1: // case: table_name, sequence, package name -> falldown
                        nameResolver.resolveForRelContext(collisions, null, front, refType);
                        return;
                    case 2: // case: schema.table_name (schema.sequence)
                        RefHolder rh = new RefHolder(refType, "", new String[]{names[1]});
                        cacheListener.resolveReference(collisions, names[0], rh);
                    default: // incorrect case
                        return;
                }

            case ContextPath.FUNC_CALL:
            case ContextPath.PROC_CALL:
            case ContextPath.TYPE_REF:
                if(names.length > 1){
                    refType = ContextPath.PLSQL_NAME_REF;
                }

            case ContextPath.GENERIC_NAME_REF: // package name (coming from sql code)
            case ContextPath.PLSQL_NAME_REF: // package name (coming from pl/sql code)
                // search among top nodes first
                nameResolver.resolveForRelContext(collisions, null, front, refType);
                break;
        }

        if(_ctxPath == null || _ctxPath.length() == 0){
            // todo -- check the case: names.length > 1 && collisions.size() > 0
            return;
        }

        final int _refType = refType;
        String additCtxPath = searchContextTree_DownUp(_ctxPath, front, refType, new TreeNodeHandler() {
            public boolean handleNode(TreeNode tnode) {
                int nodeCtxType = tnode.getType();
                switch (nodeCtxType) {
//                    case ContextPath.CURSOR_DECL:
//                        dispatch(collisions, tnode.getParentPath(), tnode.getPath(), tnode.getValue(), nodeCtxType, _refType, front);
//                        return true;
                    case ContextPath.RECORD_TYPE:
                    case ContextPath.VARRAY_TYPE:
                    case ContextPath.OBJECT_TYPE:
                    case ContextPath.COLLECTION_TYPE:
                    case ContextPath.ARGUMENT:
                    case ContextPath.LOOP_INDEX:
                        // try to resolve to the type defined in the closest context
                        int prev_size = collisions.size();
                        dispatch(collisions, tnode.getParentPath(), tnode.getPath(), tnode.getValue(), nodeCtxType, _refType, front);
                        return collisions.size() > prev_size;
                    default:
                        dispatch(collisions, tnode.getParentPath(), tnode.getPath(), tnode.getValue(), nodeCtxType, _refType, front);
                        return false;
                }
            }
        });

        // package case
        if(additCtxPath != null){
            nameResolver.resolveForRelContext(collisions, additCtxPath, front, refType);
        }

        if (names.length > 1 && collisions.size() == 1) {
            // go deep
            ResolveDescriptor rh = collisions.remove(0);
            ResolveDescriptor[] out = new ResolveDescriptor[]{rh};
            for (int i = 1; i < names.length && out.length == 1; i++) {
                out = out[0].resolve(holder.getRefType(), names[i]);
            }
            collisions.addAll(Arrays.asList(out));
        }

        // check for table column
        int lastCtx = getLastCtx(_ctxPath);
        if (refType == ContextPath.GENERIC_NAME_REF) {
            switch (lastCtx) {
                case ContextPath.SELECT_EXPR: {
                    // search down - up thru SQL statement contexts
                    String searchCtx = _ctxPath;
                    while (searchCtx != null) {
                        List<ResolveDescriptor> out = resolveColumnInContext(searchCtx, names);
                        if (out.size() != 0 || collisions.size() > 0) {
                            collisions.addAll(out);
                            break;
                        }

                        // try to search in parent context
                        searchCtx = findParentSqlStmtCtx(searchCtx);
                    }
                    break;
                }
                case ContextPath.UPDATE_STMT: {
                    List<ResolveDescriptor> out = resolveColumnInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
                }
                case ContextPath.DELETE_STMT: {
                    List<ResolveDescriptor> out = resolveColumnInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
                }
                case ContextPath.MERGE_STMT: {
                    List<ResolveDescriptor> out = resolveColumnInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
                }
                case ContextPath.INSERT_STMT:
                    List<ResolveDescriptor> out = resolveColumnInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
            }
        } else if (refType == ContextPath.TABLE_COLUMN_ALIAS_REF) {
            // search for definition among select fields
            List<ResolveDescriptor> out = resolveColumnInContext(_ctxPath, names);
            if (out.size() > 0) {
                collisions.addAll((out));
            } else {
                if (names.length == 1) {
                    // check the case: column alias ref
                    resolveColumnAlias(collisions, _ctxPath, front);
                }
            }
        }
    }

    public ResolveDescriptor[] resolveFront(RefHolder holder) {
        final List<ResolveDescriptor> collisions = new ArrayList<ResolveDescriptor>();

        // generic case
        int refType = holder.getRefType();

        final String front = holder.getFront();
        final String _ctxPath = holder.getCtxPath();
        final String[] names = holder.getNames();

        switch(refType){
            case ContextPath.TABLE_REF:
            case ContextPath.SEQUENCE_REF:
                switch (names.length) {
                    case 1: // case: table_name, falldown
                        nameResolver.resolveForRelContext(collisions, null, front, refType);
                        return collisions.toArray(new ResolveDescriptor[collisions.size()]);
                    case 2: // case: schema.table_name
                        RefHolder rh = new RefHolder(refType, "", new String[]{names[1]});
                        cacheListener.resolveReference(collisions, names[0], rh);
                    default: // incorrect case
                        return collisions.toArray(new ResolveDescriptor[collisions.size()]);
                }

            case ContextPath.FUNC_CALL:
            case ContextPath.PROC_CALL:
            case ContextPath.TYPE_REF:
                if(names.length > 1){
                    refType = ContextPath.PLSQL_NAME_REF;
                }

            case ContextPath.GENERIC_NAME_REF: // package name (coming from sql code)
            case ContextPath.PLSQL_NAME_REF: // package name (coming from pl/sql code)
                // search among top nodes first
                nameResolver.resolveForRelContext(collisions, null, front, refType);
                break;
        }

        if(_ctxPath == null || _ctxPath.length() == 0){
            return collisions.toArray(new ResolveDescriptor[collisions.size()]);
        }


        final int _refType = refType;
        String additCtxPath = searchContextTree_DownUp(_ctxPath, front, refType, new TreeNodeHandler() {
            public boolean handleNode(TreeNode tnode) {
                int nodeCtxType = tnode.getType();
                switch (nodeCtxType) {
//                    case ContextPath.CURSOR_DECL:
//                        dispatch(collisions, tnode.getParentPath(), tnode.getPath(), tnode.getValue(), nodeCtxType, _refType, front);
//                        return true;
                    case ContextPath.RECORD_TYPE:
                    case ContextPath.VARRAY_TYPE:
                    case ContextPath.OBJECT_TYPE:
                    case ContextPath.COLLECTION_TYPE:
                    case ContextPath.ARGUMENT:
                    case ContextPath.LOOP_INDEX:
                        // try to resolve to the type defined in the closest context
                        int prev_size = collisions.size();
                        dispatch(collisions, tnode.getParentPath(), tnode.getPath(), tnode.getValue(), nodeCtxType, _refType, front);
                        return collisions.size() > prev_size;
                    default:
                        dispatch(collisions, tnode.getParentPath(), tnode.getPath(), tnode.getValue(), nodeCtxType, _refType, front);
                        return false;
                }
            }
        });


        // package case
        if(additCtxPath != null){
            nameResolver.resolveForRelContext(collisions, additCtxPath, front, refType);
        }

/*
        if (names.length > 1 && collisions.size() == 1) {
            // go deep
            ResolveDescriptor rh = collisions.remove(0);
            ResolveDescriptor[] out = new ResolveDescriptor[]{rh};
            for (int i = 1; i < names.length && out.length == 1; i++) {
                out = out[0].resolve(holder.getRefType(), names[i]);
            }
            collisions.addAll(Arrays.asList(out));
        }
*/

        // check for table column
         if (refType == ContextPath.GENERIC_NAME_REF) {
            int lastCtx = getLastCtx(_ctxPath);
            switch (lastCtx) {
                case ContextPath.SELECT_EXPR: {
                    // search down - up thru SQL statement contexts
                    String searchCtx = _ctxPath;
                    while (searchCtx != null) {
                        List<ResolveDescriptor> out = resolveFirstNameInContext(searchCtx, names);
                        if (out.size() != 0 || collisions.size() > 0) {
                            collisions.addAll(out);
                            break;
                        }

                        // try to search in parent context
                        searchCtx = findParentSqlStmtCtx(searchCtx);
                    }
                    break;
                }
                case ContextPath.UPDATE_STMT: {
                    List<ResolveDescriptor> out = resolveFirstNameInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
                }
                case ContextPath.DELETE_STMT: {
                    List<ResolveDescriptor> out = resolveFirstNameInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
                }
                case ContextPath.MERGE_STMT: {
                    List<ResolveDescriptor> out = resolveFirstNameInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
                }
                case ContextPath.INSERT_STMT:
                    List<ResolveDescriptor> out = resolveFirstNameInContext(_ctxPath, names);
                    collisions.addAll(out);
                    break;
            }
        }
        return collisions.toArray(new ResolveDescriptor[collisions.size()]);
    }


    private void resolveColumnAlias(List<ResolveDescriptor> out, String ctxPath, String columnRef) {
        // todo - not supported at the moment
    }

    private String findParentSqlStmtCtx(String ctxPath) {
        // [Gen Ref] locked_res.srq_id [Context] /PB!..$ota_scheduler_pkg/Pb!39$message_process_loop/Bl!00$/De!02$/Se!00$
        if (ctxPath == null) {
            return null;
        } else {
            String parentCtxPath = ContextPathUtil.extractParentCtx(ctxPath);
            if (parentCtxPath != null) {
                int ctxType = ContextPathUtil.extractLastCtxType(parentCtxPath);
                switch (ctxType) {
                    case ContextPath.SELECT_EXPR:
                    case ContextPath.UPDATE_STMT:
                    case ContextPath.DELETE_STMT:
                        return parentCtxPath;
                    case ContextPath.SUBQUERY: // get parent context, assuming it is SELECT_EXPR
                        return ContextPathUtil.extractParentCtx(parentCtxPath);
                }
            }
        }

        return null;
    }

    private String getParentCtx(String ctxPath) {
        // [Gen Ref] locked_res.srq_id [Context] /PB!..$ota_scheduler_pkg/Pb!39$message_process_loop/Bl!00$/De!02$/Se!00$
        if (ctxPath == null) {
            return null;
        } else {
            int excl = ctxPath.lastIndexOf('!');
            if (excl > 0) {
                int excl2 = ctxPath.lastIndexOf('!', excl - 1);
                //int ctxType = ContextPathUtil.prefix2type(ctxPath.substring(excl2 - 3, excl2 + 1));
                return ctxPath.substring(0, excl2 + 4);
            }
        }

        return null;
    }


    private List<ResolveDescriptor> resolveColumnInContext(String sqlStmtCtx, String[] columnSep) {
        List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
        switch (columnSep.length) {
            case 1: { // check for the case: <column_name> only
                String val = itreeORIGIN.getContextPathValue(sqlStmtCtx);
                if (val != null) {
                    String[] tabs = val.split("\\,");
                    for (String tab : tabs) {
                        ResolveDescriptor[] rhh = resolveTableColumn(tab, null, columnSep[0]);
                        out.addAll(Arrays.asList(rhh));
                    }
                } else {
                    // val == null
                    int hh = 0;
                }
                break;
            }
            case 2: { // check for the case: <alias>.<column_name>  or <table_name>.<column_name>
                String val = itreeORIGIN.getContextPathValue(sqlStmtCtx);
                if (val != null) {
                    String[] tabs = val.split("\\,");
                    for (String tab : tabs) {
                        ResolveDescriptor[] rhh = resolveTableColumn(tab, columnSep[0], columnSep[1]);
                        out.addAll(Arrays.asList(rhh));
                    }
                } else {
                    int hh = 0;
                }
                break;
            }
            default: { // referencing nested table or queue data?
                String val = itreeORIGIN.getContextPathValue(sqlStmtCtx);
                if (val != null) {
                    String[] tabs = val.split("\\,");
                    for (String tab : tabs) {
                        ResolveDescriptor[] rhh = resolveTableColumn(tab, columnSep[0], columnSep[1]);
                        for (ResolveDescriptor rh : rhh) {
                            ResolveDescriptor[] rhh1 = rh.resolve(columnSep[2]);
                            out.addAll(Arrays.asList(rhh1));
                        }
                    }
                } else {
                    int hh = 0;
                }
                break;
            }
        }

        return out;
    }


    private List<ResolveDescriptor> resolveFirstNameInContext(String sqlStmtCtx, String[] columnSep) {
        List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
        switch (columnSep.length) {
            case 1: { // check for the case: <column_name> only
                String val = itreeORIGIN.getContextPathValue(sqlStmtCtx);
                if (val != null) {
                    String[] tabs = val.split("\\,");
                    for (String tab : tabs) {
                        ResolveDescriptor[] rhh = resolveTableColumn(tab, null, columnSep[0]);
                        out.addAll(Arrays.asList(rhh));
                    }
                } else {
                    // val == null
                    int hh = 0;
                }
                break;
            }
            case 2: { // check for the case: <alias>.<column_name>  or <table_name>.<column_name>
                String val = itreeORIGIN.getContextPathValue(sqlStmtCtx);
                if (val != null) {
                    String[] tabs = val.split("\\,");
                    for (String tab : tabs) {
                        ResolveDescriptor rhh = resolveTableAlias(tab, sqlStmtCtx, columnSep[0]);
                        if (rhh != null) {
                            out.add(rhh);
                        }
                    }
                } else {
                    int hh = 0;
                }
                break;
            }
            default: { // referencing nested table or queue data?
                String val = itreeORIGIN.getContextPathValue(sqlStmtCtx);
                if (val != null) {
                    String[] tabs = val.split("\\,");
                    for (String tab : tabs) {
                        ResolveDescriptor[] rhh = resolveTableColumn(tab, columnSep[0], columnSep[1]);
                        for (ResolveDescriptor rh : rhh) {
                            ResolveDescriptor[] rhh1 = rh.resolve(columnSep[2]);
                            out.addAll(Arrays.asList(rhh1));
                        }
                    }
                } else {
                    int hh = 0;
                }
                break;
            }
        }

        return out;
    }

    private ResolveDescriptor[] resolveTableColumn(String selectEncodedValue, String alias_tab, String column) {
        String[] name_alias_pair = selectEncodedValue.split("\\*");
        boolean isSubquery = name_alias_pair[0].charAt(0) == '.';

        if (alias_tab != null) {
            if (name_alias_pair.length == 2) {
                // case: <table_name>.<column_name>
                // case: <alias>.<column_name>
                if (isSubquery) {
                    if (name_alias_pair[1].equalsIgnoreCase(alias_tab)) {
                        // case: <subquery_alias>.<column_name>
                        return createSubqueryResolver(name_alias_pair[0].substring(1, name_alias_pair[0].length()), column);
                    }
                } else {
                    if (    // case: <table_name>.<column_name>
                            name_alias_pair[0].equalsIgnoreCase(alias_tab)
                                    // case: <table_alias>.<column_name>
                                    || name_alias_pair[1].equalsIgnoreCase(alias_tab)) {

                        return resolveColumn(name_alias_pair[0].toLowerCase(), column);
                    }
                }
            } else {
                // check the case: <table_name>.<column_name>
                if (name_alias_pair[0].equalsIgnoreCase(alias_tab)) {
                    // hitted
                    return resolveColumn(alias_tab, column);
                }

                // Referencing name out of statement: Name ref has an alias but table has no it
                // i.e. Ref: a_run_queue_entry.ora_sid  Table: ota_sch_job_run_queue_t
                return new ResolveDescriptor[0];
            }
        } else {
            // case: <column_name>
            if (isSubquery) {
                return createSubqueryResolver(name_alias_pair[0].substring(1, name_alias_pair[0].length()), column);
            } else {
                return resolveColumn(name_alias_pair[0].toLowerCase(), column);
            }
        }

        return new ResolveDescriptor[0];
    }

    private ResolveDescriptor resolveTableAlias(String selectEncodedValue, String ctxPath, String alias_tab) { //}, String column) {
        String[] name_alias_pair = selectEncodedValue.split("\\*");
        boolean isSubquery = name_alias_pair[0].charAt(0) == '.';

        if (name_alias_pair.length == 2) {
            // case: <table_name>.<column_name>
            // case: <alias>.<column_name>
            if (isSubquery) {
                if (name_alias_pair[1].equalsIgnoreCase(alias_tab)) {
                    // case: <subquery_alias>.<column_name>
                    return new SubqueryAliasResolveHelper(ctxPath, name_alias_pair[0].substring(1, name_alias_pair[0].length()), alias_tab);
                }
            } else {
                if (name_alias_pair[0].equalsIgnoreCase(alias_tab)) {
                    // case: <table_name>.<column_name>
                    return new TableAliasResolveHelper(ctxPath, name_alias_pair[0], alias_tab);
                } else if (name_alias_pair[1].equalsIgnoreCase(alias_tab)) {
                    // case: <table_alias>.<column_name>
                    return new TableAliasResolveHelper(ctxPath, name_alias_pair[0], alias_tab);
                }
            }
        } else {
            // check the case: <table_name>.<column_name>
            if (name_alias_pair[0].equalsIgnoreCase(alias_tab)) {
                // hitted
                return new TableAliasResolveHelper(ctxPath, name_alias_pair[0], alias_tab);
            }

            // Referencing name out of statement: Name ref has an alias but table has no it
            // i.e. Ref: a_run_queue_entry.ora_sid  Table: ota_sch_job_run_queue_t
            return null;
        }

        return null;
    }

    private ResolveDescriptor[] createSubqueryResolver(String path, String columnName) {
        List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();

        String value = itreeORIGIN.getContextPathValue(path);
        if (value != null) {
            String[] fields = value.split(",");
            for (String field : fields) {
                String[] expr_alias = field.split("\\|");
                if (expr_alias[0].charAt(0) == '.') {
                    // expression => '.', check column name against alias
                    if (columnName.equals(expr_alias[1])) {
                        out.add(new SubqueryResolveHelper(path, columnName, expr_alias[1]));
                    }
                } else {
                    // alias => '.', check column name against expression
                    if (expr_alias[0].endsWith(columnName)) {
                        out.add(new SubqueryResolveHelper(path, columnName, expr_alias[0]));
                    }
                }
            }
        }

        return out.toArray(new ResolveDescriptor[out.size()]);
    }


    private ResolveDescriptor[] resolveColumn(String tableName, String columnName) {
        List<ResolveDescriptor> out1 = new ArrayList<ResolveDescriptor>();
        for (ResolveDescriptor rh : resolveTable(tableName, null)) {
            ResolveDescriptor[] rhh = rh.resolve(columnName);
            out1.addAll(Arrays.asList(rhh));
        }

        return out1.toArray(new ResolveDescriptor[out1.size()]);
    }


    private ResolveDescriptor[] resolveTable(final String tableName, String usageCtx) {
        final List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
        String _tableName = tableName.toLowerCase();

        // choose easy way first: search among top nodes
        nameResolver.resolveForRelContext(out, null, _tableName, ContextPath.TABLE_REF);
/*
        for (TreeNode tnode : itree.findNodeInRootContext(_tableName)) {
            dispatch(out, "", tnode.getPath(), tnode.getValue(), tnode.getType(), ContextPath.TABLE_REF, _tableName);
        }
*/

        if (out.size() == 0 && usageCtx != null) {
            // try to search Down->Up starting from the usage context [CURSOR case]
//            itree.searchContextTree_DownUp(usageCtx, tableName, ContextPath.TABLE_REF, new TreeNodeHandler() {
            searchContextTree_DownUp(usageCtx, tableName, ContextPath.TABLE_REF, new TreeNodeHandler() {
                public boolean handleNode(TreeNode tnode) {
                    int nodeCtxType = tnode.getType();
                    dispatch(out, tnode.getParentPath(), tnode.getPath(), tnode.getValue(), nodeCtxType, ContextPath.TABLE_REF, tableName);
                    return false;
                }
            });
        }

        return out.toArray(new ResolveDescriptor[out.size()]);
    }

    protected void dispatch(List<ResolveDescriptor> out, String parentPath, String fullPath, String value, int result, int refType, String name) {
        switch (refType) {
            case ContextPath.TABLE_REF: {
                switch (result) {
                    case ContextPath.TABLE_DEF: {
                        out.add(new TableResolveHelperImpl(fullPath, value));
                        return;
                    }
                    case ContextPath.VIEW_DEF: {
                        out.add(new ViewResolveHelper(fullPath, value));
                        return;
                    }
                    case ContextPath.CURSOR_DECL: {
                        out.add(new CursorResolveHelper(fullPath));
                        return;
                    }
                    case ContextPath.SYNONYM: {
                        String[] dvalue = value.split("\\|");
                        String[] schema_name = dvalue[0].split("\\.");
//                        String[] schema_name = value.split("\\.");
                        switch (schema_name.length) {
                            case 2: // case: schema.object_name
                                RefHolder rh = new RefHolder(refType, "", new String[]{schema_name[1]});
                                cacheListener.resolveReference(out, schema_name[0], rh);
                                return;
                            case 1: {// case: synonym of the object in the current schema
                                RefHolder rh2 = new RefHolder(refType, "", new String[]{schema_name[0]});
                                resolve(out, rh2);
                                break;
                            }
                            default: // invalid synonym specification?
                                break;
                        }
                    }
                }
                break;
            }
            case ContextPath.SEQUENCE_REF: {
                out.add(new SequenceResolveHelperImpl(fullPath, value));
                break;
            }
            case ContextPath.RECORD_ITEM_REF: {
                switch (result) {
                    case ContextPath.RECORD_ITEM: {
                        out.add(new RtItemResolveHelperImpl(fullPath, value));
                        return;
                    }
                }
                break;
            }
            case ContextPath.TABLE_COLUMN_REF: {
                switch (result) {
                    case ContextPath.COLUMN_DEF: {
                        out.add(new TableColumnResolveHelperImpl(fullPath, ContextPathUtil.decodeColumnTypeFromValue(value)));
                        return;
                    }
                }
                break;
            }
            case ContextPath.PLSQL_NAME_REF: {
                // search among Var and Arg
                // "Arg!" "Var!"
                switch (result) {
                    case ContextPath.ARGUMENT: {
                        out.add(new ArgumentResolveHelper(fullPath, value));
                        return;
                    }
                    case ContextPath.VARIABLE_DECL: {
                        out.add(new VariableResolveHelper(fullPath, value));
                        return;
                    }
                    case ContextPath.PACKAGE_SPEC: {
                        out.add(new PackageResolveHelperImpl(fullPath));
                        return;
                    }
                    case ContextPath.SYNONYM: {
                        String[] dvalue = value.split("\\|");
                        String[] schema_name = dvalue[0].split("\\.");
                        switch (schema_name.length) {
                            case 2: // case: schema.object_name
                                RefHolder rh = new RefHolder(refType, "", new String[]{schema_name[1]});
                                cacheListener.resolveReference(out, schema_name[0], rh);
                                return;
                            case 1: {// case: synonym of the object in the current schema
                                RefHolder rh2 = new RefHolder(refType, "", new String[]{schema_name[0]});
                                resolve(out, rh2);
                                break;
                            }
                            default: // invalid synonym specification?
                                break;
                        }
                    }
                }
                break;
            }
            case ContextPath.GENERIC_NAME_REF: {
                // search among Var, Arg and Table column
                // "Arg!" "Var!" ...
//                int result = evalInRange(fullPath, ssize);
                switch (result) {
                    case ContextPath.ARGUMENT: {
                        out.add(new ArgumentResolveHelper(fullPath, value));
                        return;
                    }
                    case ContextPath.VARIABLE_DECL: {
                        out.add(new VariableResolveHelper(fullPath, value));
                        return;
                    }
                    case ContextPath.PACKAGE_SPEC: {
                        out.add(new PackageResolveHelperImpl(fullPath));
//                        createResolveHelper(out, fullPath, value);
                        return;
                    }
                    case ContextPath.FUNCTION_SPEC: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new FunctionResolveHelper(lpath, name, value, false));
                        break;
                    }
                    case ContextPath.FUNCTION_BODY: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new FunctionResolveHelper(lpath, name, value, true));
                        break;
                    }
                    case ContextPath.PROCEDURE_SPEC: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new ProcedureResolveHelper(lpath, name, value, false));
                        break;
                    }
                    case ContextPath.PROCEDURE_BODY: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new ProcedureResolveHelper(lpath, name, value, true));
                        break;
                    }
                    case ContextPath.LOOP_INDEX: {
                        out.add(new LoopIndexResolveHelper(fullPath, value));
                        return;
                    }
                    case ContextPath.SYNONYM: {
                        String[] dvalue = value.split("\\|");
                        String[] schema_name = dvalue[0].split("\\.");
//                        String[] schema_name = value.split("\\.");
                        switch (schema_name.length) {
                            case 2: // case: schema.object_name
                                RefHolder rh = new RefHolder(refType, "", new String[]{schema_name[1]});
                                cacheListener.resolveReference(out, schema_name[0], rh);
                                return;
                            case 1: {// case: synonym of the object in the current schema
                                RefHolder rh2 = new RefHolder(refType, "", new String[]{schema_name[0]});
                                resolve(out, rh2);
                                break;
                            }
                            default: // invalid synonym specification?
                                // todo -- implement me
                                break;
                        }
                    }
                }
                break;
            }
            case ContextPath.TYPE_REF: {
                // "Rt!" "C!" "Ot!" "Vy!"
                switch (result) {
                    case ContextPath.RECORD_TYPE: {
                        UserDefinedType type = new UserDefinedType(null, name);
                        ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(parentPath);
                        if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_SPEC) {
                            String pkg = ctxParser.lastCtxName();
                            type.setDefinitionPackage(pkg);
                        } else if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_BODY) {
                            String pkg = ctxParser.lastCtxName();
                            type.setDefinitionPackage(pkg);
                        } else {
                            // out of packages
                        }

                        out.add(new RtResolveHelperImpl(fullPath, type));
                        return;
                    }
                    case ContextPath.OBJECT_TYPE: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(parentPath);
                        if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_SPEC) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new OtResolveHelper(lpath, pkg, name, value));
                        } else if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_BODY) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new OtResolveHelper(lpath, pkg, name, value));
                        } else {
                            out.add(new OtResolveHelper(lpath, null, name, value));
                        }

                        return;
                    }
                    case ContextPath.COLLECTION_TYPE: {
                        ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(parentPath);
                        if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_SPEC) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new CollectionResolveHelperImpl(fullPath, pkg, name, value));
                        } else if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_BODY) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new CollectionResolveHelperImpl(fullPath, pkg, name, value));
                        } else {
                            out.add(new CollectionResolveHelperImpl(fullPath, null, name, value));
                        }
                        return;
                    }
                    case ContextPath.VARRAY_TYPE: {
                        out.add(new VarrayResolveHelper(fullPath, value));
                        return;
                    }
                    case ContextPath.REF_CURSOR: {
                        out.add(new RefCursorResolveHelper(fullPath));
                        return;
                    }
                    default: {
                        return;
                    }
                }
            }
            case ContextPath.FUNC_CALL: {
                // operator () can be applied to: Func(spec/body), collection type, variable/argument coll type
                switch (result) {
                    case ContextPath.FUNCTION_SPEC: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new FunctionResolveHelper(lpath, name, value, false));
                        // keep searching for overloaded function
                        break;
                    }
                    case ContextPath.FUNCTION_BODY: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new FunctionResolveHelper(lpath, name, value, true));
                        // keep searching for overloaded function
                        break;
                    }
                    case ContextPath.COLLECTION_TYPE: { // collection contructor
                        ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(parentPath);
                        if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_SPEC) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new CollectionResolveHelperImpl(fullPath, pkg, name, value));
                        } else if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_BODY) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new CollectionResolveHelperImpl(fullPath, pkg, name, value));
                        } else {
                            out.add(new CollectionResolveHelperImpl(fullPath, null, name, value));
                        }
                        return;
                    }
                    case ContextPath.VARIABLE_DECL:  // access to element of collection type, i.e. a_dst_tcol_b(l_didx)
                    case ContextPath.ARGUMENT: { // access to element of collection type, i.e. a_dst_tcol_b(l_didx)
                        ResolveDescriptor rh = resolveCollectionItemAccessor(fullPath, value);
                        if (rh != null) {
//                            ResolveHelper[] out1 = rh.resolve(ELEMENT_REF);
//                            out.addAll(Arrays.asList(out1));
                            out.add(rh);
                        }
                        break;
                    }
                    case ContextPath.SYSTEM_FUNC: { // reference to a SYSTEM function
                        out.add(new SysFuncResolveHelper(fullPath, name, value));
                        break;
                    }
                    case ContextPath.OBJECT_TYPE: { // Object Type ctor
                        ContextPathUtil.CtxPathParser ctxParser = new ContextPathUtil.CtxPathParser(parentPath);
                        if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_SPEC) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new OtResolveHelper(fullPath, pkg, name, value));
                        } else if (ctxParser.extractLastCtxType() == ContextPath.PACKAGE_BODY) {
                            String pkg = ctxParser.lastCtxName();
                            out.add(new OtResolveHelper(fullPath, pkg, name, value));
                        } else {
                            out.add(new OtResolveHelper(fullPath, null, name, value));
                        }

//                        out.add(new OtResolveHelper(fullPath, value));
                        break;
                    }
                    case ContextPath.VARRAY_TYPE: { // Varray ctor
                        out.add(new VarrayResolveHelper(fullPath, value));
                        break;
                    }
                    case ContextPath.SYNONYM: { // Synonym
                        String[] dvalue = value.split("\\|");
                        String[] schema_name = dvalue[0].split("\\.");
//                        String[] schema_name = value.split("\\.");
                        switch (schema_name.length) {
                            case 2: // case: schema.object_name
                                RefHolder rh = new RefHolder(refType, "", new String[]{schema_name[1]});
                                cacheListener.resolveReference(out, schema_name[0], rh);
                                return;
                            case 1: {// case: synonym of the object in the current schema
                                RefHolder rh2 = new RefHolder(refType, "", new String[]{schema_name[0]});
                                resolve(out, rh2);
                                break;
                            }
                            default: // invalid synonym specification?
                                break;
                        }
                        break;
                    }
                    default: {
                        return;
                    }
                }
                break;
            }
            case ContextPath.PROC_CALL: {
                switch (result) {
                    case ContextPath.PROCEDURE_SPEC: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new ProcedureResolveHelper(lpath, name, value, false));
                        // keep searching for overloaded procedure
                        break;
                    }
                    case ContextPath.PROCEDURE_BODY: {
                        int ssize = parentPath.length();
                        int end = ssize + 7 + name.length();
                        String lpath = fullPath.substring(0, end);
                        out.add(new ProcedureResolveHelper(lpath, name, value, true));
                        // continue search
                        break;
                    }
                    case ContextPath.VARIABLE_DECL:
                    case ContextPath.ARGUMENT: {
                        ResolveDescriptor rh = resolveCollectionItemAccessor(fullPath, value);
                        if (rh != null) {
//                            ResolveHelper[] out1 = rh.resolve(ELEMENT_REF);
//                            out.addAll(Arrays.asList(out1));
                            out.add(rh);
                        }
                        break;
                    }
                    case ContextPath.SYNONYM: {
                        String[] dvalue = value.split("\\|");
                        String[] schema_name = dvalue[0].split("\\.");
//                        String[] schema_name = value.split("\\.");
                        switch (schema_name.length) {
                            case 2: // case: schema.object_name
                                RefHolder rh = new RefHolder(refType, "", new String[]{schema_name[1]});
                                cacheListener.resolveReference(out, schema_name[0], rh);
                                return;
                            case 1: {// case: synonym of the object in the current schema
                                RefHolder rh2 = new RefHolder(refType, "", new String[]{schema_name[0]});
                                resolve(out, rh2);
                                break;
                            }
                            default: // invalid synonym specification?
                                break;
                        }
                    }
                    default: {
                        return;
                    }
                }
                break;
            }
            case ContextPath.NESTED_CALL: {
                switch (result) {
                    case ContextPath.RECORD_ITEM: {
                        out.add(new RtItemResolveHelperImpl(fullPath, value));
                        break;
                    }
                }
            }
            case ContextPath.PACKAGE_REF: {
                switch (result) {
                    case ContextPath.SYNONYM: {
                        String[] dvalue = value.split("\\|");
                        String[] schema_name = dvalue[0].split("\\.");
                        switch (schema_name.length) {
                            case 2: // case: schema.object_name
                                RefHolder rh = new RefHolder(refType, "", new String[]{schema_name[1]});
                                cacheListener.resolveReference(out, schema_name[0], rh);
                                return;
                            case 1: {// case: synonym of the object in the current schema
                                RefHolder rh2 = new RefHolder(refType, "", new String[]{schema_name[0]});
                                resolve(out, rh2);
                                break;
                            }
                            default: // invalid synonym specification?
                                break;
                        }
                    }
                    case ContextPath.PACKAGE_SPEC: {
                        out.add(new PackageResolveHelperImpl(fullPath));
                        break;
                    }
                }
            }
        }
    }


    private ResolveDescriptor resolveCollectionItemAccessor(String path, String value) {
        Type t = TypeFactory.decodeType(value);
        if (t.typeId() == Type.USER_DEFINED) {
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            String[] names = t.typeName().split("\\.");
            resolve(out, new RefHolder(ContextPath.TYPE_REF, path, names));

            if (out.size() == 1) {
                return new CollectionItemAccessorResolveHelperImpl(path, out.get(0));
            }
        }
        return null;
    }


    public ResolveDescriptor createResolveHelper(String path, Type t) {
        switch (t.typeId()) {
            case Type.USER_DEFINED:
                List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
                String[] names = t.typeName().split("\\.");
                resolve(out, new RefHolder(ContextPath.TYPE_REF, path, names));
                return out.size() == 1 ? out.get(0) : null;
            case Type.ROWTYPE:
                return new RowtypeResolveHelper(path, (RowtypeType) t);
            case Type.TABLE_COLUMN_REF_TYPE:
                return new TableColumnResolveHelperImpl(path, t);
            default:
                return new ResolveHelperDefault(path, TypeFactory.encodeType(t)) {
                    public int getTargetType() {
                        return ResolveDescriptor.DEFAULT;
                    }
                };
        }
    }

    public void addListener(RefResolverListener cacheListener) {
        this.cacheListener = cacheListener;
    }

    public void removeListener(RefResolverListener cacheListener) {
        if (this.cacheListener == cacheListener) {
            this.cacheListener = null;
        }
    }

    private ResolveDescriptor[] resolveSubtype(Type t, String path, String name) {
        switch (t.typeId()) {
            case Type.USER_DEFINED:

                List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
                String[] names = t.typeName().split("\\.");
                resolve(out, new RefHolder(ContextPath.TYPE_REF, path, names));

                if (out.size() == 1) {
                    return out.get(0).resolve(name);
                } else {
                    // todo -- handle
                }
                return new ResolveDescriptor[0];
            case Type.ROWTYPE:
                return new RowtypeResolveHelper(path, (RowtypeType) t).resolve(name);
            case Type.TABLE_COLUMN_REF_TYPE:
                return new TableColumnResolveHelperImpl(path, t).resolve(name);
            default:
                return new ResolveDescriptor[0];
        }
    }

    private class TableColumnResolveHelperImpl extends ResolveHelperDefault implements TableColumnResolveHelper {
        Type type;

        public TableColumnResolveHelperImpl(String path, Type type) {
            super(path);
            this.type = type;
        }

        public ResolveDescriptor[] resolve(String name) {
            return resolveSubtype(type, ctxPath, name);
        }

        public int getTargetType() {
            return ResolveDescriptor.TABLE_COLUMN;
        }

        public Type getType() {
            return type;
        }

        public boolean isNotNull() {
            // todo -- implement me
            return false;
        }
    }

    private class ViewColumnResolveHelper extends ResolveHelperDefault {
        String column;
        Type t;

        public ViewColumnResolveHelper(String path, String column, Type t) {
            super(path);
            this.column = column;
            this.t = t;
        }

        public int getTargetType() {
            return ResolveDescriptor.VIEW_COLUMN;
        }

        public Type getType() {
            return t; //throw new NotSupportedException("NotSupported at the moment");
        }
    }


    private class RtItemResolveHelperImpl extends ResolveHelperDefault implements RtItemResolveHelper {
        public RtItemResolveHelperImpl(String path, String value) {
            super(path, value);
        }

        public ResolveDescriptor[] resolve(String name) {
            return resolveSubtype(TypeFactory.decodeType(value), ctxPath, name);
        }

        public int getTargetType() {
            return ResolveDescriptor.RECORD_ITEM;
        }


        public Type getType() {
            Type t = TypeFactory.decodeType(value);
            if (t instanceof UserDefinedType) {
                if (((UserDefinedType) t).getDefinitionPackage() == null) {
                    String pkg = ContextPathUtil.extractPackageName(ctxPath);
                    ((UserDefinedType) t).setDefinitionPackage(pkg);
                    int hh = 0;
                }
            }
            return t;
        }
    }

    private class VariableResolveHelper extends ResolveHelperDefault {
        public VariableResolveHelper(String path, String value) {
            super(path, value);
        }

        public ResolveDescriptor[] resolve(String name) {
            return resolveSubtype(TypeFactory.decodeType(value), ctxPath, name);
        }

        public int getTargetType() {
            return ResolveDescriptor.VARIABLE;
        }
    }

    private class LoopIndexResolveHelper extends ResolveHelperDefault {
        public LoopIndexResolveHelper(String path, String value) {
            super(path, value);
        }

        public int getTargetType() {
            return ResolveDescriptor.LOOP_INDEX;
        }

        public ResolveDescriptor[] resolve(String name) {
/*
todo -- FIX ME!
            Type t = TypeFactory.decodeType(value);
            if (t.typeId() == Type.REF_CURSOR) {
                String parentCtx = getParentCtx(ctxPath);
                String t0 = parentCtx + ContextPath.CURSOR_LOOP_SPEC_PRX + "..$";
                String val = itreeORIGIN.getContextPathValue(t0);

                return createSubqueryResolver(val, name);
            }
*/
            return new ResolveDescriptor[0];
        }

        public ResolveDescriptor[] resolve(int type, String name) {
            return resolve(name);
        }
    }

    private class ArgumentResolveHelper extends ResolveHelperDefault {
        public ArgumentResolveHelper(String path, String value) {
            super(path, value);
        }

        public ResolveDescriptor[] resolve(String name) {
            return resolveSubtype(TypeFactory.decodeType(value), ctxPath, name);
        }

        public int getTargetType() {
            return ResolveDescriptor.ARGUMENT;
        }
    }

    private class VarrayResolveHelper extends ResolveHelperDefault {
        public VarrayResolveHelper(String path, String value) {
            super(path, value);
        }

        public ResolveDescriptor[] resolve(String _name) {
            String name = _name.toLowerCase();
            if ("count".equals(name)) {
                return new ResolveDescriptor[]{new VarrayMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.INTEGER))};
            } else if ("last".equals(name)) {
                return new ResolveDescriptor[]{new VarrayMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.INTEGER))};
            } else if ("first".equals(name)) {
                return new ResolveDescriptor[]{new VarrayMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.INTEGER))};
            } else if ("extend".equals(name)) {
                return new ResolveDescriptor[]{new VarrayMethodResolveHelper(ctxPath, name, null)};
            } else if (ELEMENT_REF.equals(name)) {
                ResolveDescriptor r = createResolveHelper(ctxPath, TypeFactory.decodeType(value)); //elementType());
                return r == null ? new ResolveDescriptor[0] : new ResolveDescriptor[]{r};
//                return new ResolveHelper[]{new VarrayElemAccessorResolveHelperImpl(ctxPath, elementType())};
            }
            return new ResolveDescriptor[0];
        }

        public int getTargetType() {
            return ResolveDescriptor.VARRAY_TYPE;
        }

        public Type getType() {
            ContextPathParser2 ctxParser = new ContextPathParser2(ctxPath);
            String name = ctxParser.lastCtxName();
            int nbr = ctxParser.nbrParts();
            if (nbr > 1) {
                int parentCtxType = ctxParser.getCtxType(nbr - 2);
                if (parentCtxType == ContextPath.PACKAGE_SPEC) {
                    String pkg = ctxParser.getCtxName(nbr - 2);
                    return new UserDefinedType(pkg, name);
                } else if (parentCtxType == ContextPath.PACKAGE_BODY) {
                    String pkg = ctxParser.getCtxName(nbr - 2);
                    return new UserDefinedType(pkg, name);
                } else {
                    int jj = 0;
                }
            }

            return new UserDefinedType(null, name);
        }

    }


    private class VarrayElemAccessorResolveHelperImpl extends ResolveHelperDefault implements VarrayElemAccessorResolveHelper {
        Type type;

        public VarrayElemAccessorResolveHelperImpl(String path, Type type) {
            super(path);
            this.type = type;
        }

        public ResolveDescriptor[] resolve(String name) {
            return resolveSubtype(type, ctxPath, name);
        }

        public Type getType() {
            return type;
        }

        public int getTargetType() {
            return ResolveDescriptor.VARRAY_ELEM_ACCESSOR;
        }
    }


    private class CollectionItemAccessorResolveHelperImpl extends ResolveHelperDefault {
        ResolveDescriptor delegate;

        public CollectionItemAccessorResolveHelperImpl(String path, ResolveDescriptor delegate) {
            super(path);
            this.delegate = delegate;
        }

        public ResolveDescriptor[] resolve(String name) {
            // todo -- a chance to improve performance - move delegate UP
            ResolveDescriptor[] out = delegate.resolve(ELEMENT_REF);
            if (out.length == 1) {
                return out[0].resolve(name);
            }

            return new ResolveDescriptor[0]; //resolveSubtype(type, ctxPath, name);
//            return delegate.resolve(name);
        }

        public Type getType() {
            // todo -- a chance to improve performance - move delegate UP
            ResolveDescriptor[] out = delegate.resolve(ELEMENT_REF);
            if (out.length == 1) {
                return out[0].getType();
            }
//            return delegate.getType();
            return null;
        }

        public int getTargetType() {
//            return delegate.getTargetType(); //getType();
            return ResolveDescriptor.VARRAY_ELEM_ACCESSOR;
        }
    }

    public class VarrayMethodResolveHelper extends ResolveHelperDefault {
        String method;
        Type type;

        public VarrayMethodResolveHelper(String path, String method, Type type) {
            super(path);
            this.method = method;
            this.type = type;
        }

        public int getTargetType() {
            return ResolveDescriptor.VARRAY_METHOD;
        }

        public ResolveDescriptor[] resolve(String name) {
            return new ResolveDescriptor[0];
        }

        public Type getType() {
            return type;
        }
    }

    private class OtResolveHelper extends ResolveHelperDefault {
        String packageName;
        String typeName;

        public OtResolveHelper(String path, String packageName, String typeName, String value) {
            super(path, value);
            this.packageName = packageName;
            this.typeName = typeName;
        }

        public ResolveDescriptor[] resolve(String name) {
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();

            Matcher m = e12.matcher(name);
            if (m.find()) {
                String item = m.group(1);
                String index = m.group(2);
                if (index != null) {
                    // a_collection(i)
                    RefRes.this.resolve(out, new RefHolder(ContextPath.RECORD_ITEM_REF, ctxPath, new String[]{item}));
                    if (out.size() == 1) {
                        ResolveDescriptor rhlp = out.get(0);
                        String typeName = rhlp.getType().typeName();

                        List<ResolveDescriptor> out1 = new ArrayList<ResolveDescriptor>();
                        RefRes.this.resolve(out1, new RefHolder(ContextPath.TYPE_REF, rhlp.getCtxPath(), new String[]{typeName}));

                        return out1.toArray(new ResolveDescriptor[out1.size()]);
                    }
                } else {
                    // a_item
                    RefRes.this.resolve(out, new RefHolder(ContextPath.RECORD_ITEM_REF, ctxPath, new String[]{item}));
                }
                return out.toArray(new ResolveDescriptor[out.size()]);
            }

            return new ResolveDescriptor[0];
        }

        public int getTargetType() {
            return ResolveDescriptor.OBJECT_TYPE;
        }

        public Type getType() {
            return TypeFactory.createUserType(packageName, typeName);
        }

    }

    private class CollectionResolveHelperImpl extends ResolveHelperDefault implements CollectionResolveHelper {
        String packageName;
        String typeName;

        public CollectionResolveHelperImpl(String path, String packageName, String typeName, String value) {
            super(path, value);
            this.packageName = packageName;
            this.typeName = typeName;
        }

        public int getTargetType() {
            return ResolveDescriptor.COLLECTION_TYPE;
        }

        public ResolveDescriptor[] resolve(String _name) { //throws NameNotResolvedException {
            String name = _name.toLowerCase();
            if ("count".equals(name)) {
                return new ResolveDescriptor[]{new CollectionMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.INTEGER))};
            } else if ("last".equals(name)) {
                return new ResolveDescriptor[]{new CollectionMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.INTEGER))};
            } else if ("first".equals(name)) {
                return new ResolveDescriptor[]{new CollectionMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.INTEGER))};
            } else if ("next".equals(name)) {
                return new ResolveDescriptor[]{new CollectionMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.INTEGER))};
            } else if ("extend".equals(name)) {
                return new ResolveDescriptor[]{new CollectionMethodResolveHelper(ctxPath, name, null)};
            } else if ("exists".equals(name)) {
                return new ResolveDescriptor[]{new CollectionMethodResolveHelper(ctxPath, name, TypeFactory.createTypeById(Type.BOOLEAN))};
            } else if ("delete".equals(name)) {
                return new ResolveDescriptor[]{new CollectionMethodResolveHelper(ctxPath, name, null)};
            } else if (ELEMENT_REF.equals(name)) {
                ResolveDescriptor r = createResolveHelper(ctxPath, TypeFactory.decodeType(value)); //getElementType());
                return r == null ? new ResolveDescriptor[0] : new ResolveDescriptor[]{r};
//                return new ResolveHelper[]{new CollectionElemAccessorResolveHelperImpl(ctxPath, TypeFactory.decodeType(value))};
            }
            return new ResolveDescriptor[0];
        }

        public Type getType() {
//            return TypeFactory.decodeType(value);
            return TypeFactory.createUserType(packageName, typeName);
        }

        public Type getElementType() {
            return new CollectionElemAccessorResolveHelperImpl(ctxPath, TypeFactory.decodeType(value)).getType();
        }

        public ResolveDescriptor resolveType() {
            return createResolveHelper(ctxPath, TypeFactory.decodeType(value));
        }

        public boolean packageScope() {
            return packageName != null;
        }
    }


    private class CollectionElemAccessorResolveHelperImpl extends ResolveHelperDefault implements CollectionElemAccessorResolveHelper {
        Type type;

        public CollectionElemAccessorResolveHelperImpl(String path, Type type) {
            super(path);
            this.type = type;
        }

        public ResolveDescriptor[] resolve(String name) {
            return resolveSubtype(type, ctxPath, name);
        }

        public Type getType() {
            return type;
        }

        public int getTargetType() {
            return ResolveDescriptor.COLLECTION_ELEM_ACCESSOR;
        }
    }


    static final Pattern e12 = Pattern.compile("([a-zA-Z0-9_#\\$]+)(\\([a-z]+\\))?");


    private class RtResolveHelperImpl extends ResolveHelperDefault implements RtResolveHelper {
        Type type;

        public RtResolveHelperImpl(String path, Type type) {
            super(path, null);
            this.type = type;
        }

        public ResolveDescriptor[] resolve(String name) { //throws NameNotResolvedException {
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();

            Matcher m = e12.matcher(name);
            if (m.find()) {
                String item = m.group(1);
                String index = m.group(2);
                if (index != null) {
                    // a_collection(i)
                    RefRes.this.resolve(out, new RefHolder(ContextPath.RECORD_ITEM_REF, ctxPath, new String[]{item}));
                    if (out.size() == 1) {
                        ResolveDescriptor rhlp = out.get(0);
                        String typeName = rhlp.getType().typeName();

                        List<ResolveDescriptor> out1 = new ArrayList<ResolveDescriptor>();
                        RefRes.this.resolve(out1, new RefHolder(ContextPath.TYPE_REF, rhlp.getCtxPath(), new String[]{typeName}));

                        return out1.toArray(new ResolveDescriptor[out1.size()]);
                    }

                } else {
                    // a_item
                    RefRes.this.resolve(out, new RefHolder(ContextPath.RECORD_ITEM_REF, ctxPath, new String[]{item}));
                }
                return out.toArray(new ResolveDescriptor[out.size()]);
            }

            return new ResolveDescriptor[0];
        }

        public Type getType() {
            return type;
        }

        public int getTargetType() {
            return ResolveDescriptor.RECORD_TYPE;
        }
    }


    private class CollectionMethodResolveHelper extends ResolveHelperDefault {
        String method;
        Type type;

        public CollectionMethodResolveHelper(String path, String method, Type type) {
            super(path);
            this.method = method;
            this.type = type;
        }

        public int getTargetType() {
            return ResolveDescriptor.COLLECTION_METHOD;
        }

        public ResolveDescriptor[] resolve(String name) { //throws NameNotResolvedException {
            return new ResolveDescriptor[0];
        }

        public Type getType() {
            return type;
        }
    }


    private class RowtypeResolveHelper extends ResolveHelperDefault {
        RowtypeType type;

        public RowtypeResolveHelper(String path, RowtypeType type) {
            super(path, "");
            this.type = type;
        }

        public ResolveDescriptor[] resolve(String name) { //throws NameNotResolvedException {
            List<ResolveDescriptor> out1 = new ArrayList<ResolveDescriptor>();
            for (ResolveDescriptor rh : resolveTable(type.getTableName(), ctxPath)) {
                out1.addAll(Arrays.asList(rh.resolve(name)));
            }

//            String ctxPath = ContextPath.TABLE_DEF_PRX + "..$" + type.getTableName().toLowerCase();
//            NamesCacheImpl.this.resolve(out, new RefHolder(ContextPath.TABLE_COLUMN_REF, ctxPath, new String[]{name}));
            return out1.toArray(new ResolveDescriptor[out1.size()]);
        }

        public Type getType() {
            return type;
        }

        public int getTargetType() {
            return ResolveDescriptor.ROWTYPE;
        }
    }

    private class PackageResolveHelperImpl extends ResolveHelperDefault implements PackageResolveHelper {

        public PackageResolveHelperImpl(String mpath) {
            super(mpath, "");
        }

        public ResolveDescriptor[] resolve(String name) { //throws NameNotResolvedException {
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            RefRes.this.resolve(out, new RefHolder(ContextPath.GENERIC_NAME_REF, ctxPath, new String[]{name}));
            return out.toArray(new ResolveDescriptor[out.size()]);
        }

        public ResolveDescriptor[] resolve(int nameType, String name) { //throws NameNotResolvedException {
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            RefRes.this.resolve(out, new RefHolder(nameType, ctxPath, new String[]{name}));
            return out.toArray(new ResolveDescriptor[out.size()]);
        }

        public int getTargetType() {
            return ResolveDescriptor.PACKAGE_SPEC;
        }

        public Iterator<ContextItem> iterateOverChildren(int[] ctxTypes) {
            return new IteratorImpl(
                    itreeORIGIN.findNodeInContext(getCtxPath(), ctxTypes)
            );
        }
    }

    private class TableResolveHelperImpl extends ResolveHelperDefault implements TableResolveHelper {
        public TableResolveHelperImpl(String ctx, String value) {
            super(ctx, value);
        }

        public ResolveDescriptor[] resolve(String name) {
            if (name.equals("rowid")) {
                // pseudo column
                return new ResolveDescriptor[]{new RowidColumnResolveHelper(ctxPath)};
            } else {
                List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
                RefRes.this.resolve(out, new RefHolder(ContextPath.TABLE_COLUMN_REF, ctxPath, new String[]{name}));
                return out.toArray(new ResolveDescriptor[out.size()]);
            }
        }

        public int getTargetType() {
            return ResolveDescriptor.TABLE;
        }
    }

    private static RowidType ROWID_TYPE = new RowidType();

    private class RowidColumnResolveHelper extends ResolveHelperDefault {
        public RowidColumnResolveHelper(String ctxPath) {
            super(ctxPath);
        }

        public ResolveDescriptor[] resolve(String name) {
            return new ResolveDescriptor[0];
        }

        public int getTargetType() {
            return ResolveDescriptor.TABLE_COLUMN;
        }

        public Type getType() {
            return ROWID_TYPE;
        }

    }

    private class ViewResolveHelper extends ResolveHelperDefault {
        public ViewResolveHelper(String ctx, String value) {
            super(ctx, value);
        }

        public ResolveDescriptor[] resolve(String name) {
//            String value = RefResolverImpl.this.itree.getContextPathValue(ctxPath);
            if (value != null) {
                ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
                for (ArgumentSpec a : args) {
                    if (a.getName().equals(name)) {
                        return new ResolveDescriptor[]{new ViewColumnResolveHelper(ctxPath, name, a.getType())};
                    }
                }
            }
            return new ResolveDescriptor[0];
        }

        public int getTargetType() {
            return ResolveDescriptor.VIEW;
        }
    }

    private class CursorResolveHelper extends ResolveHelperDefault {
        public CursorResolveHelper(String ctx) {
            super(ctx, "");
        }

        public ResolveDescriptor[] resolve(String name) {
            String value = RefRes.this.itreeORIGIN.getContextPathValue(ctxPath);
            if (value != null) {
                List<ResolveDescriptor> out = resolveColumnInContext(value, new String[]{name});
                return out.toArray(new ResolveDescriptor[out.size()]);
            }
            return new ResolveDescriptor[0];
        }

        public int getTargetType() {
            return ResolveDescriptor.CURSOR_DECL;
        }
    }

    private class FunctionResolveHelper extends ResolveHelperDefault implements ExecutableResolveHelper {
        String name;
        boolean isBody;

        public FunctionResolveHelper(String lpath, String name, String value, boolean isBody) {
            super(lpath, value);
            this.name = name;
            this.isBody = isBody;
        }

        public Type getType() {
            return ContextPathUtil.extractRetType(value);
        }

        public ArgumentSpec[] getArgumentSpecification() {
            return ContextPathUtil.extractArguments(value);
        }

        public boolean isBody() {
            return isBody;
        }

        public FunctionValidator getValidator() {
            return null;
        }

        public int getTargetType() {
            return (isBody) ? ResolveDescriptor.FUNCTION_BODY : ResolveDescriptor.FUNCTION_SPEC;
        }

        public boolean isSpecificationOf(ExecutableResolveHelper helper) {
            ContextPathUtil.CtxPathParser p = new ContextPathUtil.CtxPathParser(ctxPath);
            ContextPathUtil.CtxPathParser _p = new ContextPathUtil.CtxPathParser(helper.getCtxPath());
            if (p.lastCtxName().equals(_p.lastCtxName())) {
                if (p.extractLastCtxType() == ContextPath.FUNCTION_SPEC && _p.extractLastCtxType() == ContextPath.FUNCTION_BODY) {
                    // check arguments
                    return getType().equals(helper.getType()) &&
                            ContextPathUtil.argumentEquals(getArgumentSpecification(), helper.getArgumentSpecification());
                }
            }

            return false;
        }
    }


    private class ProcedureResolveHelper extends ResolveHelperDefault implements ExecutableResolveHelper {
        String name;
        boolean isBody;

        public ProcedureResolveHelper(String lpath, String name, String value, boolean isBody) {
            super(lpath, value);
            this.name = name;
            this.isBody = isBody;
        }

        public ArgumentSpec[] getArgumentSpecification() {
            return ContextPathUtil.extractArguments(value);
        }

        public boolean isBody() {
            return isBody;
        }

        public FunctionValidator getValidator() {
            return null;
        }

        public int getTargetType() {
            return (isBody) ? ResolveDescriptor.PROCEDURE_BODY : ResolveDescriptor.PROCEDURE_SPEC;
        }

        public boolean isSpecificationOf(ExecutableResolveHelper helper) {
            ContextPathUtil.CtxPathParser p = new ContextPathUtil.CtxPathParser(ctxPath);
            ContextPathUtil.CtxPathParser _p = new ContextPathUtil.CtxPathParser(helper.getCtxPath());
            if (p.lastCtxName().equals(_p.lastCtxName())) {
                if (p.extractLastCtxType() == ContextPath.PROCEDURE_SPEC && _p.extractLastCtxType() == ContextPath.PROCEDURE_BODY) {
                    // check arguments
                    return ContextPathUtil.argumentEquals(getArgumentSpecification(), helper.getArgumentSpecification());
                }
            }

            return false;
        }
    }


    private class SysFuncResolveHelper extends ResolveHelperDefault implements ExecutableResolveHelper {
        String name;

        public SysFuncResolveHelper(String lpath, String name, String value) {
            super(lpath, value);
            this.name = name;
        }

        public Type getType() {
            return ContextPathUtil.extractRetType(value);
        }

        public ArgumentSpec[] getArgumentSpecification() {
            return ContextPathUtil.extractArguments(value);
        }

        public boolean isBody() {
            return false;
        }

        public FunctionValidator getValidator() {
            return sysFuncValidators.get(ctxPath);
        }

        public boolean isSpecificationOf(ExecutableResolveHelper helper) {
            // System Function has no specification
            return false;
        }

        public int getTargetType() {
            return ResolveDescriptor.SYSTEM_FUNC;
        }
    }

    private class SubqueryResolveHelper extends ResolveHelperDefault {

        String column;
        String field;

        public SubqueryResolveHelper(String ctxPath, String column, String field) {
            super(ctxPath);
            this.column = column;
            this.field = field;
        }

        public int getTargetType() {
            return ResolveDescriptor.SUBQUERY_FIELD;
        }
    }



    private class RefCursorResolveHelper extends ResolveHelperDefault {
        public RefCursorResolveHelper(String ctxPath) {
            super(ctxPath);
        }

        public int getTargetType() {
            return ResolveDescriptor.REF_CURSOR;
        }
    }


    private class SubqueryAliasResolveHelper extends ResolveHelperDefault {

        String subqueryCtxPath;
        String alias_name;

        public SubqueryAliasResolveHelper(String usageCtx, String subqueryCtxPath, String alias_name) {
            super(usageCtx);
            this.alias_name = alias_name;
            this.subqueryCtxPath = subqueryCtxPath;
        }

        public int getTargetType() {
            return ResolveDescriptor.SUBQUERY_ALIAS;
        }
    }

    // case: <table_name>.<column_name>
    // case: <table_alias>.<column_name>

    /**
     * Cases which should be supported:
     * - <table_name>.<column_name>
     * - <table_alias>.<column_name>
     */
    private class TableAliasResolveHelper extends ResolveHelperDefault {

        String tableName;
        String alias_name;

        public TableAliasResolveHelper(String usageCtx, String tableName, String alias_name) {
            super(usageCtx);
            this.tableName = tableName;
            this.alias_name = alias_name;

        }

        public int getTargetType() {
            return ResolveDescriptor.TABLE_ALIAS;
        }

        public ResolveDescriptor[] resolve(String name) {
            return resolveColumn(tableName, name);
//            RefHolder ref = new RefHolder(ContextPath.TABLE_REF, ctxPath, StringUtils.discloseDoubleQuotes(tableName));
//            List<ResolveHelper> out1 = new ArrayList<ResolveHelper>();
//            RefResolverImpl.this.resolve(out1, ref);
//
//            return out1.toArray(new ResolveHelper[0]);
        }
    }


    private abstract class ResolveHelperDefault implements ResolveDescriptor {

        protected String ctxPath;
        protected String value;

        public ResolveHelperDefault(String ctxPath, String value) {
            this.ctxPath = ctxPath;
            this.value = value;
        }

        public ResolveHelperDefault(String ctxPath) {
            this.ctxPath = ctxPath;
        }

        public ResolveDescriptor[] resolve(String name) {
            return new ResolveDescriptor[0];
        }

        public ResolveDescriptor[] resolve(int type, String name) {
            return resolve(name);
        }

        public String getCtxPath() {
            return ctxPath;
        }

        public String getValue() {
            return value;
        }

        public String getName() {
            return ctxPath != null ? ContextPathUtil.extractLastCtxName(ctxPath) : null;
        }

        public Type getType() {
            return TypeFactory.decodeType(value);
        }

        public int hashCode() {
            return (getTargetType() + ctxPath + value).hashCode();
        }

        public boolean equals(Object o) {
            if (o instanceof ResolveHelperDefault) {
                ResolveHelperDefault rd = (ResolveHelperDefault) o;
                return (getTargetType() + ctxPath + value).equals(rd.getTargetType() + rd.ctxPath + rd.value);
            } else {
                return false;
            }
        }

//        public String getUserName() {
//            return userName;
//        }

        public DbUrl getDbUrl(){
            return dbUrl;
        }


        public Iterator<ContextItem> iterateOverChildren() {
            final List<TreeNode> out = new ArrayList<TreeNode>();
            itreeORIGIN.findNodesInContextCommon(getCtxPath(), (String)null, new TreeNode.TreeNodeHandler(){
                public void handleNode(TreeNode node) {
                    out.add(node);
                }
            });

            return new IteratorImpl(
//                    itreeORIGIN.findNodeInContext(getCtxPath(), (String)null)
                    out.toArray(new TreeNode[out.size()])
            );
        }
    }


    private class IteratorImpl implements Iterator<ContextItem> {

        TreeNode[] nodes;
        int index = 0;

        public IteratorImpl(TreeNode[] nodes) {
            this.nodes = nodes;
        }

        public boolean hasNext() {
            return index < nodes.length;
        }

        public ContextItem next() {
            return (index < nodes.length) ? new ContextItemImpl(nodes[index++]) : null;
        }

        public void remove() {
        }
    }


    private class ContextItemImpl implements ContextItem {
        TreeNode n;
        public ContextItemImpl(TreeNode n) {
            this.n = n;
        }

        public String getCtxPath() {
            return n.getPath();
        }

        public String getValue() {
            return n.getValue();
        }
    }


    private class SequenceResolveHelperImpl extends ResolveHelperDefault {
        public SequenceResolveHelperImpl(String ctxPath, String value) {
            super(ctxPath);
            this.value = value;
        }

        public int getTargetType() {
            return ResolveDescriptor.SEQUENCE;
        }
    }


    public String searchContextTree_DownUp(String startCtxPath, final String name, final int refType, final TreeNodeHandler handler) {
        final List<TreeNode> out = new ArrayList<TreeNode>();
        final String[] retValue = {null};
        itreeORIGIN.getRoot().iterateThruContext(startCtxPath, new TreeNodeRoot.TreeNodeHandler2(){
            public boolean handleNode(int cnt, TreeNode node) {
                if(cnt == 0){
                    if( node.getType() == ContextPath.PACKAGE_BODY){
                        // build manually and save name of the package spec
                        final String encName = node.getName();
                        retValue[0] = ContextPath.PACKAGE_SPEC_PRX + encName.substring(4, encName.length());
                    }
                }
                if(node.getChildren().size() == 0){
                    // last context
                    // check type of the last context
                    switch (refType) {
                        case ContextPath.NESTED_CALL:
                            // search for the name in the context only
                            for (TreeNode d : node.findChildrenBySuffix(name)) {
                                handler.handleNode(d);
                            }
                            return false;
                        default:
                            switch (node.getType()) {
                                case ContextPath.COLUMN_DEF:
                                case ContextPath.RECORD_ITEM:
                                case ContextPath.VARIABLE_DECL:
                                case ContextPath.ARGUMENT:
                                case ContextPath.REF_CURSOR:
                                    // skip search in the context
                                    // todo -- more types expected
                                    return true;
                                default:
                                    break;
                            }
                    }
                } else {
                    // intermediate context, add to list
                }

                out.add(node);
                return true;
            }
        });

        for (int i = out.size() - 1; i >= 0; i--) {
            TreeNode n = out.get(i);
            for (TreeNode foundNode : n.findChildrenBySuffix(name)) {
                if (handler.handleNode(foundNode)) {
                    // iteration terminated by listener
                    return null;
                }
            }

//            switch(n.getType()){
//                case ContextPath.CURSOR_DECL:
//                    // Cursor decl: closed scope
//                    return;
//            }
        }

        return retValue[0]; //null;
    }

    

}
