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

package com.deepsky.lang.plsql.psi.utils;


import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.resolve.ResolveHelper;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.TokenType;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import org.jetbrains.annotations.NotNull;

public class PsiTreeHelpers {

    private static final Logger log = Logger.getInstance("#SupportStuff");

    static int indent = 2;


    public static <T> T findParent(@NotNull PsiElement psi, Class clazz) {
        PsiElement n = psi;
        while (n != null) {
            Class class1 = n.getClass();
            if (class1 == clazz) {
                return (T) n;
            } else {
                if (isTheSame(class1, clazz)) {
                    return (T) n;
                }

                if (class1.getGenericSuperclass() == clazz) {
                    return (T) n;
                }
            }
            n = n.getContext(); //getTreeParent();
        }

        return null;
    }

    static boolean isTheSame(Class probe, Class target) {
        Class[] interfaces = probe.getInterfaces();
        for (Class t : interfaces) {
            if (t == target) {
                return true;
            } else {
                return isTheSame(t, target);
            }
        }
        return false;
    }

/*
    public static TableDescriptorLegacy[] describeTablesFor(SelectStatement select) {

        List<TableDescriptorLegacy> desc = new ArrayList<TableDescriptorLegacy>();
        FromClause from = select.getFromClause();
        if (from != null) {
            for (GenericTable t : from.getTableList()) {
                if (t instanceof PlainTable) {
                    DbObject[] r =
                            ObjectCacheFactory
                                    .getObjectCache()
                                    .findByNameForType(ObjectCache.TABLE | ObjectCache.VIEW,
                                            ((PlainTable) t).getTableName());
                    if (r.length > 0) {
                        for (DbObject o : r) {
                            TableDescriptor rt = (TableDescriptor) o;
                            desc.add(new TableDescriptorForRegular(rt, t.getAlias()));
                        }
                    } else {
                        // table not found!
                    }

                } else if (t instanceof Subquery) {
                    Subquery sub = (Subquery) t;

                    List<String> columns = new ArrayList<String>();
                    List<Type> types = new ArrayList<Type>();

                    for (SelectFieldCommon f : sub.getSelectStatement().getSelectFieldList()) {
                        if (f instanceof SelectField) {
                            SelectField s = (SelectField) f;
                            columns.add((s.getAlias() == null) ? "" : s.getAlias());
                            // TODO real type needed
                            types.add(new TypeBase());
                        } else if (f instanceof SelectFieldAsterisk) {
                            SelectFieldAsterisk a = (SelectFieldAsterisk) f;
                            // TODO not supported !
                        } else {
                            // TODO not supported !
                        }
                    }

                    TableDescriptorLegacy tdesc = new TableDescriptorForSubquery(
                            "",
                            t.getAlias(),
                            columns.toArray(new String[columns.size()]),
                            types.toArray(new Type[types.size()])
                    );
                    desc.add(tdesc);
                } else {
                    // impossible !
                }
            }
        }

        TableDescriptorLegacy[] _desc = removeDuplicates(desc.toArray(new TableDescriptorLegacy[desc.size()]));

        return _desc;
    }
*/

//    public static TableDescriptorLegacy[] describeTablesFor(SelectStatement select) {
//
//        List<TableDescriptorLegacy> desc = new ArrayList<TableDescriptorLegacy>();
//        FromClause from = select.getFromClause();
//        if (from != null) {
//            for (GenericTable t : from.getTableList()) {
//                TableDescriptorLegacy tdesc = t.describe(); ///describeTable(t);
//                if (tdesc != null) {
//                    desc.add(tdesc);
//                }
//            }
//        }
//
//        return desc.toArray(new TableDescriptorLegacy[desc.size()]);
//    }

    /*
        public static TableDescriptorLegacy[] removeDuplicates(TableDescriptorLegacy[] descs) {
    //        List<TableDescriptorLegacy> out  = new ArrayList<TableDescriptorLegacy>(descs.length);
    //        for(TableDescriptorLegacy d: descs){
    //            if(d.getAlias() != null){
    //                Collections.
    //            }
    //        }
            // TODO
            return descs;
        }
    */
    public static String[] removeDuplicates(String[] strings) {

        List<String> out = new ArrayList<String>(strings.length);
        Arrays.sort(strings);
        String prev = "$$111$$222!!!";
        for (String s : strings) {
            if (!s.equals(prev)) {
                out.add(s);
            }
            prev = s;
        }

        return out.toArray(new String[out.size()]);
    }

    public static PsiElement findParentNode(ASTNode node, PlSqlElementType etype) {

        int _indent = 2;
        //String clazzName = clazz.getName();
        ASTNode n = node;
        while (n != null) {
//            log.info("..." + getIndent(_indent) + n.getElementType());
            _indent += indent;
            if (n.getElementType() == etype) {
                return n.getPsi();
            }
            n = n.getTreeParent();
        }

        return null;
    }


    public static PsiElement findParentNode(ASTNode node, PlSqlElementType[] etypes) {

        int _indent = 2;
        //String clazzName = clazz.getName();
        ASTNode n = node;
        while (n != null) {
//            log.info("..." + getIndent(_indent) + n.getElementType());
            _indent += indent;
            for (PlSqlElementType etype : etypes) {
                if (n.getElementType() == etype) {
                    return n.getPsi();
                }
            }
            n = n.getTreeParent();
        }

        return null;
    }

    public static PsiElement findParentNode(PsiElement elem, PlSqlElementType[] etypes) {

        int _indent = 2;
        //String clazzName = clazz.getName();
        ASTNode n = elem.getNode();
        while (n != null) {
//            log.info("..." + getIndent(_indent) + n.getElementType());
            _indent += indent;
            for (PlSqlElementType etype : etypes) {
                if (n.getElementType() == etype) {
                    return n.getPsi();
                }
            }
            n = n.getTreeParent();
        }

        return null;
    }


    public static String getIndent(int indent) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            b.append(' ');
        }
        return b.toString();
    }


    static final String IntellijIdeaRulezzz = "IntellijIdeaRulezzz";

    public static String stripText(String text) {
        int idx = text.indexOf(IntellijIdeaRulezzz);
        if (idx >= 0) {
            if (idx == 0) {
                return "";
//                if (text.length() == IntellijIdeaRulezzz.length()) {
//                    return "";
//                } else {
//                    // there is a need throw away trailing whitespace
//                    return text.substring(IntellijIdeaRulezzz.length() + 1, text.length());
//                }
            } else {
                return text.substring(0, idx);
            }

        } else {
            return text;
        }
    }


    public static ASTNode findPrevNode(ASTNode node) {
        ASTNode parent = node.getTreeParent();
        ASTNode t = parent.getFirstChildNode();
        ASTNode prev = null;
        while (t != node) {
            if (t.getElementType() != TokenType.WHITE_SPACE) {
                prev = t;
            }
            t = t.getTreeNext();
        }

        if (prev == null) {
            // go higher
            return findPrevNode(node.getTreeParent());
        }
        return prev;
    }

    public static ASTNode findPrevNode(ASTNode node, PlSqlElementType elem) {

        ASTNode n = node;
        while (n != null) {
//            log.info("..." + getIndent(indent) + n.getElementType());
            indent += 2;
            if (n.getElementType() == elem) {

                ASTNode parent = n;
                ASTNode t = parent.getFirstChildNode();
                ASTNode prev = null;
                while (t != node) {
                    if (t.getElementType() != TokenType.WHITE_SPACE) {
                        prev = t;
                    }
                    t = t.getTreeNext();
                }

                return prev;
            }
            n = n.getTreeParent();
        }

        return null;
    }

//    public static TableDescriptor describeTable(String name) {
//        DbObject[] objects = ObjectCacheFactory
//                .getObjectCache()
//                .findByNameForType(ObjectCache.TABLE, name);
//
//        if (objects.length == 1 && objects[0] instanceof TableDescriptor) {
//            return (TableDescriptor) objects[0];
//        } else {
//            return null;
//        }
//    }


    public static TableDescriptorLegacy describeTable(GenericTable tab) {
        if (tab instanceof PlainTable) {
            String name = ((PlainTable) tab).getTableName();
            TableDescriptor _tdesc = ResolveHelper.describeTable(tab.getProject(), name);

            if (_tdesc != null) {
                return new TableDescriptorForRegular(_tdesc, tab.getAlias());
            } else {
                return null;
            }

        } else if (tab instanceof Subquery) {
            List<String> columns = new ArrayList<String>();
            List<Type> types = new ArrayList<Type>();

            SelectStatement stmt = ((Subquery) tab).getSelectStatement();
//            if (stmt != null) {
            for (SelectFieldCommon f : stmt.getSelectFieldList()) {
                if (f instanceof SelectFieldExpr) {
                    SelectFieldExpr s = (SelectFieldExpr) f;
                    String aliasName = s.getAlias();
                    Expression expr = s.getExpression();

                    if (aliasName != null && aliasName.length() > 0) {
                        columns.add(aliasName);
                    } else {
                        // special case - alias not specified!
                        String columnRef = expr.getText();

                        if (isColumnNameCorrect(columnRef)) {
                            columns.add(columnRef);
                        } else {
                            // todo -- column not referred by the name!!! 
                            columns.add(columnRef);
                            // this is not a valid name (might be a numeric/string literal, arithmetic expression)
                            // ORA-00904: : invalid identifier
//                            int i = 0;
//                            continue;
                        }

                    }
                    Type t = null;
                    try {
                        t = expr.getExpressionType();
                    } catch (ValidationException e) {
                        t = TypeFactory.createTypeById(Type.ANY);
                    }
                    types.add(t);

                } else if (f instanceof SelectFieldIdentAsterisk) {
                    SelectFieldIdentAsterisk a = (SelectFieldIdentAsterisk) f;
                    String alias = a.getTableRef();
                    for (GenericTable t1 : stmt.getFromClause().getTableList()) {
                        if (alias == null || (t1.getAlias() != null && t1.getAlias().equalsIgnoreCase(alias))) {
                            TableDescriptorLegacy tdesc = t1.describe(); ///SupportStuff.describeTable(t1);
                            if (tdesc == null) {
                                // todo - cache is not fresh??
                                break;
                            }
                            for (String s : tdesc.getColumnNames()) {
                                columns.add(s);
                                types.add(tdesc.getColumnType(s));
                            }

                            break;
                        }
                    }
                } else {
                    // TODO not supported !
                }
            }
            TableDescriptorLegacy tdesc = new TableDescriptorForSubquery(
                    "",
                    tab.getAlias(),
                    columns.toArray(new String[columns.size()]),
                    types.toArray(new Type[types.size()])
            );

            return tdesc;
//            } else {
//                throw new SyntaxTreeCorruptedException();
//            }
        } else {
            // impossible !
        }

        return null;
    }
/*
    public static TableDescriptorLegacy describeTable(GenericTable tab) {

        // look into the 2Level Cache
        TableDescriptorLegacy cached = findIn_2ndCache(tab);
        if (cached != null) {
            return cached;
        }


        if (tab instanceof PlainTable) {
            DbObject[] r =
                    ObjectCacheFactory
                            .getObjectCache()
                            .findByNameForType(ObjectCache.TABLE | ObjectCache.VIEW,
                                    ((PlainTable) tab).getTableName());
            if (r.length > 0) {
                // todo - possible issue with multiply tables?
                for (DbObject o : r) {
                    TableDescriptor rt = (TableDescriptor) o;
                    TableDescriptorLegacy tdesc = new TableDescriptorForRegular(rt, tab.getAlias());
                    putIn_2ndCache(tab, tdesc);
                    return tdesc;
                }
            } else {
                // table not found!
            }                                

        } else if (tab instanceof Subquery) {
            Subquery sub = (Subquery) tab;

            List<String> columns = new ArrayList<String>();
            List<Type> types = new ArrayList<Type>();

            SelectStatement stmt = sub.getSelectStatement();
            if (stmt != null) {
                for (SelectFieldCommon f : stmt.getSelectFieldList()) {
                    if (f instanceof SelectField) {
                        SelectField s = (SelectField) f;
                        String aliasName = s.getAlias();
                        Expression expr = s.getExpression();

                        if (aliasName != null) {
                            columns.add(aliasName);
                        } else {
                            // special case - alias not specified!
                            String columnRef = expr.getText();
                            if (isColumnNameCorrect(columnRef)) {
                                columns.add(columnRef);
                            } else {
                                // this is not a valid name (might be a numeric/string literal, arithmetic expression)
                                // ORA-00904: : invalid identifier
                                int i = 0;
                                continue;
                            }
                        }
                        Type t = null;
                        try {
                            t = expr.getExpressionType();
                        } catch (ValidationException e) {
                            t = TypeFactory.createTypeById(Type.ANY);
                        }
                        types.add(t);

                    } else if (f instanceof SelectFieldAsterisk) {
                        SelectFieldAsterisk a = (SelectFieldAsterisk) f;
                        String alias = a.getTableRef();
                        for (GenericTable t1 : stmt.getFromClause().getTableList()) {
                            if (alias == null || (t1.getAlias() != null && t1.getAlias().equalsIgnoreCase(alias))) {
                                TableDescriptorLegacy tdesc = SupportStuff.describeTable(t1);
                                if (tdesc == null) {
                                    // todo - cache is not fresh??
                                    break;
                                }
                                for (String s : tdesc.getColumnNames()) {
                                    columns.add(s);
                                    types.add(tdesc.getColumnType(s));
                                }

                                break;
                            }
                        }
                    } else {
                        // TODO not supported !
                    }
                }
            } else {
                int hh = 0;
            }

            TableDescriptorLegacy tdesc = new TableDescriptorForSubquery(
                    "",
                    tab.getAlias(),
                    columns.toArray(new String[columns.size()]),
                    types.toArray(new Type[types.size()])
            );

            putIn_2ndCache(tab, tdesc);
            return tdesc;
        } else {
            // impossible !
        }

        return null;
    }
*/

//    private static void putIn_2ndCache(GenericTable tab, TableDescriptorLegacy tdesc) {
//        PlSqlFile plsqlFile = (PlSqlFile) tab.getContainingFile();
//        plsqlFile.putIn_2ndCache(tab, tdesc);
//    }

//    private static TableDescriptorLegacy findIn_2ndCache(GenericTable tab) {
//        PlSqlFile plsqlFile = (PlSqlFile) tab.getContainingFile();
//        return plsqlFile.findIn_2ndCache(tab);
//    }

    public static boolean isColumnNameCorrect(String columnRef) {
        return columnRef.matches("^[a-zA-Z][a-zA-Z0-9_]*");
    }

/*
    public static ExecutableDescriptor describeExecutable(Executable exec) {

        if (exec instanceof Procedure) {
            String name = exec.getEName();
            ProcedureDescriptorImpl fdesc = new ProcedureDescriptorImpl(name);
            Argument[] args = exec.getArgumentList();
            for (Argument a : args) {
                fdesc.addParameter(a.getType(), a.getArgumentName(), a.getDefaultExpr() != null);
            }

            return fdesc;
        } else if (exec instanceof Function) {
            String name = exec.getEName();
            Type type = ((Function) exec).getReturnType();
            FunctionDescriptorImpl fdesc = new FunctionDescriptorImpl(null, name, type, false);
            Argument[] args = exec.getArgumentList();
            for (Argument a : args) {
                fdesc.addParameter(a.getType(), a.getArgumentName(), a.getDefaultExpr() != null);
            }

            return fdesc;
        }

        return null;
    }

    public static ExecutableDescriptor[] describeExecutable(Executable[] exec) {
        List<ExecutableDescriptor> out = new ArrayList<ExecutableDescriptor>();
        for (Executable e : exec) {
            if (e instanceof Procedure) {
                String name = e.getEName();
                ProcedureDescriptorImpl fdesc = new ProcedureDescriptorImpl(name);
                Argument[] args = e.getArgumentList();
                for (Argument a : args) {
                    fdesc.addParameter(a.getType(), a.getArgumentName(), a.getDefaultExpr() != null);
                }

                out.add(fdesc);
            } else if (e instanceof Function) {
                String name = e.getEName();
                Type type = ((Function) e).getReturnType();
                FunctionDescriptorImpl fdesc = new FunctionDescriptorImpl(name, type, false);
                Argument[] args = e.getArgumentList();
                for (Argument a : args) {
                    fdesc.addParameter(a.getType(), a.getArgumentName(), a.getDefaultExpr() != null);
                }

                out.add(fdesc);
            }
        }

        return out.toArray(new ExecutableDescriptor[0]);
    }
*/
}

