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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.database.SqlScriptManager;
import com.deepsky.database.fs.CachedVirtualFileSystem;
import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.utils.PsiTreeHelpers;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.RowtypeType;
import com.deepsky.lang.plsql.struct.types.TableColumnRefType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.utils.StringUtils;
import com.intellij.ide.DataManager;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileSystem;
import com.intellij.psi.PsiElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResolveHelper {


    public static TableDescriptor describeTable(Project project, String name) {
        String cutted = StringUtils.trimDoubleQuites(name);

        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
        DbObject[] objects = ocache //ObjectCacheFactory.getObjectCache()
                .findByNameForType(ObjectCache.TABLE | ObjectCache.VIEW, cutted);

        if (objects.length == 1 && objects[0] instanceof TableDescriptor) {
            return (TableDescriptor) objects[0];
        } else {
            return null;
        }
    }


    public static PlSqlElement[] getExecContext(ASTNode node) {

        List<PlSqlElement> out = new ArrayList<PlSqlElement>();
        Executable proc = (Executable) PsiTreeHelpers
                .findParentNode(
                        node,
                        new PlSqlElementType[]{
                                PlSqlElementTypes.PROCEDURE_BODY,
                                PlSqlElementTypes.FUNCTION_BODY}
                );

        if (proc != null) {
            out.addAll(Arrays.asList(proc.getArguments()));

            for (Declaration decl : proc.getDeclarationList()) {
                if (decl instanceof VariableDecl) {
                    out.add(decl);
                }
            }
        }

        return out.toArray(new PlSqlElement[out.size()]);
    }

    public static Executable[] identifyLocalFunction(PsiElement elem, String functionName) {
        PackageBody pkg =
                (PackageBody) PsiTreeHelpers
                        .findParentNode(
                                elem,
                                new PlSqlElementType[]{
                                        PlSqlElementTypes.PACKAGE_BODY}
                        );

        if (pkg != null) {
            Executable[] exec = pkg.findExecutableByName(functionName);
            return exec;
        }

        return new Executable[0];
    }

    public static PackageDescriptor resolve_Package(Project project, String name) {
        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
        DbObject[] objects = ocache //ObjectCacheFactory
                //.getObjectCache()
                .findByNameForType(ObjectCache.PACKAGE, name);

        if (objects.length == 1 && objects[0] instanceof PackageDescriptor) {
            return (PackageDescriptor) objects[0];
        } else {
            return null;
        }
    }


    public static PackageBodyDescriptor resolve_PackageBody(Project project, String name) {
        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
        DbObject[] objects = ocache //ObjectCacheFactory
                //.getObjectCache()
                .findByNameForType(ObjectCache.PACKAGE_BODY, name);

        if (objects.length == 1 && objects[0] instanceof PackageBodyDescriptor) {
            return (PackageBodyDescriptor) objects[0];
        } else {
            return null;
        }
    }

//    public static DbObject[] find_SysFunction(String name) {
//        return ObjectCacheFactory
//                .getObjectCache()
//                .findByNameForType(ObjectCache.EMBEDDED_FUNCTION, name);
//    }

    public static GenericTable resolve_TableRef(PlSqlElement alias) throws SyntaxTreeCorruptedException {
        // find the context (a statement)
        PsiElement stmt =
                PsiTreeHelpers
                        .findParentNode(
                                alias,
                                new PlSqlElementType[]{
//                                        PlSqlElementTypes.INSERT_SUBQUERY,
                                        PlSqlElementTypes.SELECT_EXPRESSION,
                                        PlSqlElementTypes.INSERT_COMMAND,
                                        PlSqlElementTypes.DELETE_COMMAND,
                                        PlSqlElementTypes.UPDATE_COMMAND}
                        );

        GenericTable[] tables = new GenericTable[0];
        if (stmt != null) {
            if (stmt instanceof SelectStatement) {
                FromClause from = ((SelectStatement) stmt).getFromClause();
                tables = from.getTableList();

            } else if (stmt instanceof InsertSubquery) {
                FromClause from = ((InsertSubquery) stmt).getFromClause();
                tables = from.getTableList();

            } else if (stmt instanceof InsertStatement) {
                tables = new GenericTable[1];
                tables[0] = ((InsertStatement) stmt).getIntoTable();
            } else if (stmt instanceof UpdateStatement) {
                tables = new GenericTable[1];
                tables[0] = ((UpdateStatement) stmt).getTargetTable();
            } else if (stmt instanceof DeleteStatement) {
                tables = new GenericTable[1];
                tables[0] = ((DeleteStatement) stmt).getTargetTable();
            } else {
                // TODO - what is the statement ?!!
                throw new SyntaxTreeCorruptedException();
            }
        }

        String text = alias.getStrippedText();
        for (GenericTable t : tables) {
            if (t.getAlias() != null) {
                if (text.equalsIgnoreCase(t.getAlias())) {
                    // prefix matches to alias
                    return t;
                }
            }

            // 2. is this a table name?
            if (t instanceof PlainTable) {
                if (((PlainTable) t).getTableName().equalsIgnoreCase(text)) {
                    // prefix matches to table name
                    return t;
                }
            }
        }

        return null;
    }

    public static GenericTable[] findTablesInScope(ASTNode column) throws SyntaxTreeCorruptedException {
        // find the context (a statement)
        PsiElement stmt =
                PsiTreeHelpers
                        .findParentNode(
                                column,
                                new PlSqlElementType[]{
//                                        PlSqlElementTypes.INSERT_SUBQUERY,
                                        PlSqlElementTypes.SELECT_EXPRESSION, //COMMAND,
                                        PlSqlElementTypes.INSERT_COMMAND,
                                        PlSqlElementTypes.DELETE_COMMAND,
                                        PlSqlElementTypes.UPDATE_COMMAND}
                        );

        GenericTable[] tables = new GenericTable[0];
        if (stmt != null) {
            if (stmt instanceof SelectStatement) {
                FromClause from = ((SelectStatement) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // TODO - FROM not found,syntax tree CORRUPTED ?!!
                    throw new SyntaxTreeCorruptedException();
                }

            } else if (stmt instanceof InsertSubquery) {
                FromClause from = ((InsertSubquery) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // TODO - FROM not found,syntax tree CORRUPTED ?!!
                    throw new SyntaxTreeCorruptedException();
                }

            } else if (stmt instanceof InsertStatement) {
                tables = new GenericTable[1];
                tables[0] = ((InsertStatement) stmt).getIntoTable();
            } else if (stmt instanceof UpdateStatement) {
                tables = new GenericTable[1];
                tables[0] = ((UpdateStatement) stmt).getTargetTable();
            } else if (stmt instanceof DeleteStatement) {
                tables = new GenericTable[1];
                tables[0] = ((DeleteStatement) stmt).getTargetTable();
            } else {
                // TODO - what is the statement ?!!
                throw new SyntaxTreeCorruptedException();
            }
        }

        return tables;
    }

//    public static UserDefinedTypeDescriptor resolve_Type(String typename) {
//        DbObject[] objects = ObjectCacheFactory
//                .getObjectCache()
//                .findByNameForType(ObjectCache.RECORD_TYPE | ObjectCache.COLLECTION_TYPE, typename);
//
//        if (objects.length == 1 && objects[0] instanceof RecordTypeDescriptor) {
//            return (UserDefinedTypeDescriptor) objects[0];
//        } else {
//            return null;
//        }
//    }

    public static UserDefinedTypeDescriptor resolve_Type(Project project,UserDefinedType type) {
        return resolve_Type(project, type, (String) null);
    }


    public static String getSurroundPackageName(PsiElement elem) {
        PsiElement pkg =
                PsiTreeHelpers
                        .findParentNode(
                                elem,
                                new PlSqlElementType[]{
                                        PlSqlElementTypes.PACKAGE_BODY,
                                        PlSqlElementTypes.PACKAGE_SPEC}
                        );

        if (pkg != null) {
            if (pkg instanceof PackageBody) {
                return ((PackageBody) pkg).getPackageName();
            } else if (pkg instanceof PackageSpec) {
                return ((PackageSpec) pkg).getPackageName();
            }
        }

        return null;
    }


    public static UserDefinedTypeDescriptor resolve_Type(Project project, UserDefinedType type, UsageContext uctx) {
        return resolve_Type(project, type, uctx.getPackageName());
    }

    public static UserDefinedTypeDescriptor resolve_Type(Project project, UserDefinedType type, String usedInPackage) {
        String defPkg = type.getDefinitionPackage();

        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);

        DbObject[] objects;
        if (defPkg == null) {
            // look for types defined in the schema scope
            objects = ocache
                    .findByNameForType(ObjectCache.USER_DEFINED_TYPE, type.getTypeName2());

            if (objects.length == 1 && objects[0] instanceof UserDefinedTypeDescriptor) {
                return (UserDefinedTypeDescriptor) objects[0];
            } else if (usedInPackage != null) {
                // type has been used inside a package, is the type defined inside the package also?
                objects = ocache
                        .findByNameForType(ObjectCache.PACKAGE, usedInPackage);

                if (objects.length == 1) {
                    DbObject[] dbos = ((PackageDescriptor) objects[0]).findObjectByName(type.getTypeName2());
                    if (dbos.length == 1 && dbos[0] instanceof UserDefinedTypeDescriptor) {
                        return (UserDefinedTypeDescriptor) dbos[0];
                    }
                }
            }

        } else {
            objects = ocache
                    .findByNameForType(ObjectCache.PACKAGE, defPkg);

            if (objects.length == 1) {
                DbObject[] dbos = ((PackageDescriptor) objects[0]).findObjectByName(type.getTypeName2());
                if (dbos.length == 1 && dbos[0] instanceof UserDefinedTypeDescriptor) {
                    return (UserDefinedTypeDescriptor) dbos[0];
                }
            }
        }

        return null;
    }

/*
    public static VariableDescriptor resolve_Variable(String packageName, String variableName) {
        DbObject[] objects = ObjectCacheFactory
                .getObjectCache()
                .findByNameForType(ObjectCache.PACKAGE, packageName);

        if (objects.length == 1 && objects[0] instanceof PackageDescriptor) {
            PackageDescriptor pdesc = (PackageDescriptor) objects[0];
            for (DbObject dbo : pdesc.findObjectByName(variableName)) {
                if (dbo instanceof VariableDescriptor) {
                    return (VariableDescriptor) dbo;
                }
            }
        }
        return null;
    }
*/

    public static Type resolve_TypeReference(Project project, Type t) throws NameNotResolvedException {
        if (t instanceof TableColumnRefType) {
            String table = ((TableColumnRefType) t).getTable();
            String column = ((TableColumnRefType) t).getColumn();

            ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
            DbObject[] objects = ocache
                    .findByNameForType(ObjectCache.TABLE, table);
            if (objects.length == 1) {
                ColumnDescriptor cdesc = ((TableDescriptor) objects[0]).getColumnDescriptor(column);
                if (cdesc == null) {
                    throw new NameNotResolvedException("Column '" + column + "' not found in the table " + table);
                } else {
                    return cdesc.getType();
                }
            } else {
                throw new NameNotResolvedException("Table '" + table + "' not found");
            }
        }
        throw new NameNotResolvedException("Type " + t.typeName() + " not resolved");
    }


    public static void validateType(Project project, Type t) throws NameNotResolvedException {
        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
        if (t instanceof TableColumnRefType) {
            String table = ((TableColumnRefType) t).getTable();
            String column = ((TableColumnRefType) t).getColumn();

            DbObject[] objects = ocache
                    .findByNameForType(ObjectCache.TABLE, table);
            if (objects.length == 1) {
                ColumnDescriptor cdesc = ((TableDescriptor) objects[0]).getColumnDescriptor(column);
                if (cdesc == null) {
                    throw new NameNotResolvedException("Column '" + column + "' not found in the table " + table);
                } else {
                    return;
                }
            } else {
                throw new NameNotResolvedException("Table '" + table + "' not found");
            }
        } else if (t instanceof RowtypeType) {
            String table = ((RowtypeType) t).getTableName();
            DbObject[] objects = ocache
                    .findByNameForType(ObjectCache.TABLE, table);

            if (objects.length == 1) {
                return;
            } else {
                throw new NameNotResolvedException("Table '" + table + "' not found");
            }

        } else if (t instanceof UserDefinedType) {
            throw new NameNotResolvedException("Type validation not supported at the moment");
        }
    }
/*
    public static void expandAsterisk(
            SelectStatement select, int asteriskPosition,
            List<String> outFields, List<String> displayedColumns) throws Exception {

        SelectFieldCommon[] fields = select.getSelectFieldList();
        if (fields.length <= asteriskPosition) {
            throw new Exception();
        } else if (!(fields[asteriskPosition] instanceof SelectFieldAsterisk)) {
            throw new Exception();
        }

        String tabRef = null;
        if (((SelectFieldAsterisk) fields[asteriskPosition]).getTableRef() != null) {
            tabRef = ((SelectFieldAsterisk) fields[asteriskPosition]).getTableRef();
        }

//        select r.id, r.*
//        from (
//        select *
//        from xdv_adp_import_status_t
//        ) r

//        List<String> names = new ArrayList<String>();
        GenericTable[] tables = select.getFromClause().getTableList();
        if (tabRef != null) {
            // search for specific table alias
            for (GenericTable t : tables) {
                if (t.getAlias() != null && t.getAlias().equalsIgnoreCase(tabRef)) {
                    // table found
                    TableDescriptorLegacy desc = t.describe();
                    for (String name : desc.getColumnNames()) {
//                        names.add(tabRef + "." + name);
                        outFields.add(tabRef + "." + name);
                        displayedColumns.add(name);
                    }

                    return;/// names.toArray(new String[names.size()]);
                }
            }

            // not resolved?
            throw new Exception();
        } else {
            for (GenericTable t : tables) {
                if (t.getAlias() != null && t.getAlias().length() > 0) {
                    tabRef = t.getAlias();
                } else {
                    // check for subquery vs plain table
                    if (t instanceof PlainTable) {
                        tabRef = ((PlainTable) t).getTableName();
                    } else {
                        // subquery !
                        //tabRef = "";
                    }
                }
                TableDescriptorLegacy desc = t.describe();
                for (String name : desc.getColumnNames()) {
                    if (tabRef != null) {
                        outFields.add(tabRef + "." + name);
                    } else {
                        outFields.add(name);
                    }
                    displayedColumns.add(name);
//                    names.add(tabRef + "." + name);
                }
            }
        }

//        return names.toArray(new String[names.size()]);
    }
*/

    public static CursorDecl resolveCursorRef(PsiElement cursorRef) {
        PsiElement psiElement =
                PsiTreeHelpers
                        .findParentNode(
                                cursorRef,
                                new PlSqlElementType[]{
//                                        PlSqlElementTypes.FUNCTION_BODY,
//                                        PlSqlElementTypes.PROCEDURE_BODY,
//                                        PlSqlElementTypes.DML_TRIGGER_CLAUSE
                                        PlSqlElementTypes.PLSQL_BLOCK
                                }
                        );

        if (psiElement instanceof PlSqlBlock) { //psiElement != null){
            PlSqlBlock block = (PlSqlBlock) psiElement;
            for (Declaration d : block.getDeclarations()) {
                if (d instanceof CursorDecl) {
                    String name = cursorRef.getText();
                    if (name.equalsIgnoreCase( d.getDeclName())) {
                        return (CursorDecl) d;
                    }
                }
            }

//            if( psiElement instanceof Executable){
//                Executable e = (Executable) psiElement;
//                for(Declaration d: e.getDeclarationList()){
//                    if(d instanceof CursorDecl){
//                        String name = cursorRef.getText();
//                        if(name.equalsIgnoreCase(((CursorDecl)d).getDeclName())){
//                            return (CursorDecl)d;
//                        }
//                    }
//                }
//
//            } else if( psiElement instanceof CreateTriggerDML){
//                CreateTriggerDML dml = (CreateTriggerDML) psiElement;
//                for(Declaration d: dml.getDeclarationList()){
//                    if(d instanceof CursorDecl){
//                        String name = cursorRef.getText();
//                        if(name.equalsIgnoreCase(((CursorDecl)d).getDeclName())){
//                            return (CursorDecl)d;
//                        }
//                    }
//                }
//            }
        }

        return null;
    }

    /**
     * Search for a sequence descriptor for the current user
     *
     * @param text - sequence name
     * @return - descriptor
     */
    public static DbObject[] resolve_Sequence(Project project, String text) {
        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
        String user = ocache.getCurrentUser();
        if(user == null){
            return new DbObject[0];
        } else {
            return ocache.findByNameForType(user, ObjectCache.TABLE, text);
        }
    }


    /**
     * Schema-wide Type Definition
     *
     * @param typeName -
     * @return - type descriptor
     */
    public static UserDefinedTypeDescriptor resolve_Type(Project project, String typeName) {

//        DbObject[] objects;
//        // is this type a schema scope?
//        objects = ObjectCacheFactory.getObjectCache()
//                .findByNameForType(ObjectCache.USER_DEFINED_TYPE, typeName);
//
//        if (objects.length == 1 && objects[0] instanceof UserDefinedTypeDescriptor) {
//            return (UserDefinedTypeDescriptor) objects[0];
//        }
//
//        return null;
        return resolve_Type(project, (String) null, typeName);
    }

    public static UserDefinedTypeDescriptor resolve_Type(Project project, String pkgName, String typeName) {

        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
        if (pkgName == null) {
            // type seems to be a schema-wide
            DbObject[] objects = ocache
                    .findByNameForType(ObjectCache.USER_DEFINED_TYPE, typeName);

            if (objects.length == 1 && objects[0] instanceof UserDefinedTypeDescriptor) {
                return (UserDefinedTypeDescriptor) objects[0];
            }
        } else {
            DbObject[] objects = ocache
                    .findByNameForType(ObjectCache.PACKAGE, pkgName);
            if (objects.length == 1) {
                PackageSpecDescriptor spec = (PackageSpecDescriptor) objects[0];
                DbObject[] candidates = spec.findObjectByName(typeName);
                for (DbObject dbo : candidates) {
                    if (dbo instanceof UserDefinedTypeDescriptor) {
                        return (UserDefinedTypeDescriptor) dbo;
                    }
                }
            }
        }
        return null;
    }

/*
    public static ResolveContext identifyNamePrefix(ASTNode node) throws NameNotResolvedException {
        // find the context (a statement)
        PsiElement stmt =
                SupportStuff
                        .findParentNode(
                                node,
                                new PlSqlElementType[]{
                                        PlSqlElementTypes.INSERT_SUBQUERY,
                                        PlSqlElementTypes.SELECT_COMMAND,
                                        PlSqlElementTypes.INSERT_COMMAND,
                                        PlSqlElementTypes.DELETE_COMMAND,
                                        PlSqlElementTypes.UPDATE_COMMAND}
                        );

        GenericTable[] tables;
        if (stmt != null) {
            if (stmt instanceof SelectStatement) {
                FromClause from = ((SelectStatement) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // FROM not found !!!
                    throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                }

            } else if (stmt instanceof InsertSubquery) {
                FromClause from = ((InsertSubquery) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // FROM not found !!!
                    throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                }

            } else if (stmt instanceof InsertStatement) {
                tables = new GenericTable[1];
                tables[0] = ((InsertStatement) stmt).getIntoTable();
            } else if (stmt instanceof UpdateStatement) {
                tables = new GenericTable[1];
                tables[0] = ((UpdateStatement) stmt).getTargetTable();
            } else if (stmt instanceof DeleteStatement) {
                tables = new GenericTable[1];
                tables[0] = ((DeleteStatement) stmt).getTargetTable();
            } else {
                // todo - what the hell is this statement?
                throw new NameNotResolvedException("Statement type not supported: " + stmt.getClass().getName());
            }

            // 1. is this a tableLegacy/subquery alias?
            for (GenericTable t : tables) {
                if (t.getAlias() != null) {
                    if (node.getText().equalsIgnoreCase(t.getAlias())) {
                        // prefix matches to alias, let describe the table
                        TableDescriptorLegacy td = t.describe(); //SupportStuff.describeTable(t);
                        if (td != null) {
                            return new TableContext(td);
                        } else {
                            return null;
                        }
                    }
                }

                // 2. is this a tableLegacy name?
                if (t instanceof PlainTable) {
                    if (((PlainTable) t).getTableName().equalsIgnoreCase(node.getText())) {
                        // prefix matches to table name, let describe the table
                        TableDescriptorLegacy td = t.describe(); //SupportStuff.describeTable(t);
                        if (td != null) {
                            return new TableContext(td);
                        } else {
                            return null;
                        }
                    }
                }
            }

            // prefix does not match any table in the scope
        } else {
            // todo
//            throw new NameNotResolvedException("Statement type not supported: " + node.getText());
        }

        // look for an object in the cache
        DbObject[] objects = ObjectCacheFactory
                .getObjectCache()
                .findByNameForType(ObjectCache.PACKAGE | ObjectCache.SEQUENCE, node.getText());
//                .findByName(node.getText());

        if (objects.length > 1) {
            // todo - duplication
            return null;
        } else if (objects.length == 1) {
            // todo - identify object
            if (objects[0] instanceof SequenceDescriptor) {
                // 3. this is a sequence name
                return new SequenceContext((SequenceDescriptor) objects[0]);
            } else if (objects[0] instanceof PackageDescriptor) {
                // 4. this is a package name
                return new PackageContext((PackageDescriptor) objects[0]);
            } else {
                throw new NameNotResolvedException(objects[0].getTypeName() + " could not be used as a reference name.");
            }
        }

        // 5. is this a collection?
        Procedure proc = (Procedure) SupportStuff
                .findParentNode(
                        node,
                        new PlSqlElementType[]{
                                PlSqlElementTypes.PROCEDURE_BODY}
                );

        Function func = (Function) SupportStuff
                .findParentNode(
                        node,
                        new PlSqlElementType[]{
                                PlSqlElementTypes.FUNCTION_BODY}
                );

        if (proc != null) {
            Declaration[] decl = proc.getDeclarationList();
            // todo

        } else if (func != null) {
            Declaration[] decl = func.getDeclarationList();
            // todo
        }

        // todo - element was not identified!
        throw new NameNotResolvedException("Not identified.");
    }
*/

    /*
        public static ResolveContext createPlainContext(ASTNode leaf) throws NameNotResolvedException {
            // find the context (a statement)
            PsiElement stmt = SupportStuff
                    .findParentNode(
                            leaf,
                            new PlSqlElementType[]{
                                    PlSqlElementTypes.INSERT_SUBQUERY,
                                    PlSqlElementTypes.SELECT_COMMAND,
                                    PlSqlElementTypes.INSERT_COMMAND,
                                    PlSqlElementTypes.DELETE_COMMAND,
                                    PlSqlElementTypes.UPDATE_COMMAND}
                    );

            GenericTable[] tables;
            if (stmt != null) {
                if (stmt instanceof SelectStatement) {
                    FromClause from = ((SelectStatement) stmt).getFromClause();
                    if (from != null) {
                        tables = from.getTableList();
                    } else {
                        // FROM not found !!!
                        throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                    }

                } else if (stmt instanceof InsertSubquery) {
                    FromClause from = ((InsertSubquery) stmt).getFromClause();
                    if (from != null) {
                        tables = from.getTableList();
                    } else {
                        // FROM not found !!!
                        throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                    }

                } else if (stmt instanceof InsertStatement) {
                    tables = new GenericTable[1];
                    tables[0] = ((InsertStatement) stmt).getIntoTable();
                } else if (stmt instanceof UpdateStatement) {
                    tables = new GenericTable[1];
                    tables[0] = ((UpdateStatement) stmt).getTargetTable();
                } else if (stmt instanceof DeleteStatement) {
                    tables = new GenericTable[1];
                    tables[0] = ((DeleteStatement) stmt).getTargetTable();
                } else {
                    // todo - what the hell is this statement?
                    throw new NameNotResolvedException("Statement type not supported: " + stmt.getClass().getName());
                }
            } else {
                // todo
                throw new NameNotResolvedException("Could not identify Statement");
            }

            // 1. is this a tableLegacy/subquery alias?
            List<TableDescriptorLegacy> ltd = new ArrayList<TableDescriptorLegacy>();
            for (GenericTable t : tables) {
                TableDescriptorLegacy dt = t.describe(); ///SupportStuff.describeTable(t);
                if (dt != null) {
                    // todo - add the tableLegacy descriptor
                    ltd.add(dt);
                }
            }

            // 5. is this a collection?
            Procedure proc = (Procedure) SupportStuff
                    .findParentNode(
                            leaf,
                            new PlSqlElementType[]{
                                    PlSqlElementTypes.PROCEDURE_BODY}
                    );

            Function func = (Function) SupportStuff
                    .findParentNode(
                            leaf,
                            new PlSqlElementType[]{
                                    PlSqlElementTypes.FUNCTION_BODY}
                    );

            if (proc != null) {
                Declaration[] decl = proc.getDeclarationList();
                // todo

            } else if (func != null) {
                Declaration[] decl = func.getDeclarationList();
                // todo
            }

            if (ltd.size() == 1) {
                return new TableContext(ltd.get(0));
            } else {
                // todo - element was not identified!
                String message = (ltd.size() == 0) ? "Not identified" : "Too many tables";
                throw new NameNotResolvedException(message);
            }
        }
    */

/*
    public static FunctionDescriptor identifyFunction(PsiElement elem, String functionName) throws NameNotResolvedException {

        FunctionNameDTO dto = ASTParseHelper.parseFunctionName(functionName);

        if (dto.getSchema().length() > 0) {
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
                    dto.getSchema(), ObjectCache.PACKAGE, dto.getPackage()
            );
            if (objects.length == 1) {
                PackageDescriptor desc = (PackageDescriptor) objects[0];
                DbObject[] d = desc.findObjectByName(dto.getName());
                if (d.length > 0 && d[0] instanceof FunctionDescriptor) {
                    return (FunctionDescriptor) d[0];
                }
            }
        } else if (dto.getPackage().length() > 0) {
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
                    ObjectCache.PACKAGE, dto.getPackage()
            );
            if (objects.length == 1) {
                PackageDescriptor desc = (PackageDescriptor) objects[0];
                DbObject[] d = desc.findObjectByName(dto.getName());
                if (d.length > 0 && d[0] instanceof FunctionDescriptor) {
                    return (FunctionDescriptor) d[0];
                }
            }
        } else {
            // three cases possible:
            //  1. this is an embedded function
            //  2. function defined outside any package
            //  3. function defined inside the package

            // Check the 3d case at first
            PackageBody pkg =
                    (PackageBody) SupportStuff
                            .findParentNode(
                                    elem,
                                    new PlSqlElementType[]{
                                            PlSqlElementTypes.PACKAGE_BODY}
                            );

            if (pkg != null) {
                Executable[] exec = pkg.findExecutableByName(dto.getName());
                if (exec.length > 0) {
//                    return (FunctionDescriptor) SupportStuff.describeExecutable(exec[0]);
                    return (FunctionDescriptor) exec[0].describe();
                }
            }

            // Function not defined in the package, so look up embedded and user fumnctions
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
                    ObjectCache.EMBEDDED_FUNCTION | ObjectCache.USER_FUNCTION, functionName
            );

            if (objects != null && objects.length == 1) {
                return (FunctionDescriptor) objects[0];
            }
        }

        throw new NameNotResolvedException("Function with name '" + functionName + "' not found");
//        DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
//                ObjectCache.EMBEDDED_FUNCTION | ObjectCache.USER_FUNCTION, functionName
//            );
//
//        if(objects.length == 0){
//            throw new NameNotResolvedException("Function with name '" + functionName + "' not found");
//        } else if(objects.length > 1){
//            throw new NameNotResolvedException("Found " + objects.length + " function with name ...");
//        }
//
//        return (FunctionDescriptor) objects[0];
    }
*/

/*
    public static ExecutableDescriptor[] resolveCallable(Callable exec) throws NameNotResolvedException {

        String name = exec.getFunctionName();
        FunctionNameDTO dto = ASTParseHelper.parseFunctionName(name);

        if (dto.getSchema().length() > 0) {
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
                    dto.getSchema(), ObjectCache.PACKAGE, dto.getPackage()
            );
            if (objects.length == 1) {
                PackageDescriptor desc = (PackageDescriptor) objects[0];
                DbObject[] d = desc.findObjectByName(dto.getName());
                if (d.length > 0 && d[0] instanceof FunctionDescriptor) {
//                    return (FunctionDescriptor) d;
                    return new ExecutableDescriptor[]{(FunctionDescriptor) d[0]};
                }
            }
        } else if (dto.getPackage().length() > 0) {
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
                    ObjectCache.PACKAGE, dto.getPackage()
            );
            if (objects.length == 1) {
                PackageDescriptor desc = (PackageDescriptor) objects[0];
                DbObject[] d = desc.findObjectByName(dto.getName());
                if (d.length > 0) {
                    if (d[0] instanceof FunctionDescriptor) {
                        return new ExecutableDescriptor[]{(FunctionDescriptor) d[0]};
                    } else if (d[0] instanceof ProcedureDescriptor) {
                        return new ExecutableDescriptor[]{(ProcedureDescriptor) d[0]};
                    }
                }
            }
        } else {
            // three cases possible:
            //  1. this is an embedded function
            //  2. function defined outside any package
            //  3. function defined inside the package

            // Check the 3d case at first
            PackageBody pkg =
                    (PackageBody) SupportStuff
                            .findParentNode(
                                    exec,
                                    new PlSqlElementType[]{
                                            PlSqlElementTypes.PACKAGE_BODY}
                            );

            if (pkg != null) {
                Executable[] exec1 = pkg.findExecutableByName(dto.getName());
                ExecutableDescriptor[] edesc = new ExecutableDescriptor[exec1.length];
                for (int i = 0; i < exec1.length; i++) {
                    edesc[i] = exec1[i].describe();
                }

                return edesc;
//                if (exec1.length > 0) {
//                    return SupportStuff.describeExecutable(exec1);
//                }
            }

            // Function not defined in the package, so look up embedded and user fumnctions
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
                    ObjectCache.EMBEDDED_FUNCTION | ObjectCache.USER_FUNCTION | ObjectCache.USER_PROCEDURE, name
            );

            if (objects != null && objects.length > 0) {
                ExecutableDescriptor[] edescs = new ExecutableDescriptor[objects.length];
                for (int i = 0; i < objects.length; i++) {
                    edescs[i] = (ExecutableDescriptor) objects[i];
                }
                return edescs;
            }
        }

        throw new NameNotResolvedException("Function with name '" + name + "' not found");
    }
*/

/*
    public static ResolveContext[] resolveName(ASTNode context, String name) throws NameNotResolvedException {
        // find the context (a statement)
        PsiElement stmt =
                SupportStuff
                        .findParentNode(
                                context,
                                new PlSqlElementType[]{
                                        PlSqlElementTypes.INSERT_SUBQUERY,
                                        PlSqlElementTypes.SELECT_COMMAND,
                                        PlSqlElementTypes.INSERT_COMMAND,
                                        PlSqlElementTypes.DELETE_COMMAND,
                                        PlSqlElementTypes.UPDATE_COMMAND}
                        );

        GenericTable[] tables = new GenericTable[0];
        if (stmt != null) {
            if (stmt instanceof SelectStatement) {
                FromClause from = ((SelectStatement) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // FROM not found !!!
                    throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                }

            } else if (stmt instanceof InsertSubquery) {
                FromClause from = ((InsertSubquery) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // FROM not found !!!
                    throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                }

            } else if (stmt instanceof InsertStatement) {
                tables = new GenericTable[1];
                tables[0] = ((InsertStatement) stmt).getIntoTable();
            } else if (stmt instanceof UpdateStatement) {
                tables = new GenericTable[1];
                tables[0] = ((UpdateStatement) stmt).getTargetTable();
            } else if (stmt instanceof DeleteStatement) {
                tables = new GenericTable[1];
                tables[0] = ((DeleteStatement) stmt).getTargetTable();
            } else {
                // todo - what the hell is this statement?
                throw new NameNotResolvedException("Statement type not supported: " + stmt.getClass().getName());
            }
        }

        // Possibles type of names:
        // 1. "abc.name"
        //  - table_name.column
        //  - table_alias.column
        //  - package.(function/procedure)  (without arguments or arguments with default values)
        // 2. "name"
        //  - column in one of existing tables in scope
        //  - function/procedure of the current package or SYS's (without arguments or arguments with default values)
        //  - variable

        // what the name is kind of? ("abc.name" or "name")
        String[] parts = name.split("\\.");
        boolean nameComplex = false;
        if (parts.length == 2) {
            // "abc.name" case 1
            nameComplex = true;
        } else if (parts.length == 1) {
            // "name" case 2
        } else {
            throw new NameNotResolvedException("Name is empty or too complex!");
        }

        List<ResolveContext> out = new ArrayList<ResolveContext>();
        if (nameComplex && tables.length > 0) {
            //  - table_name.column
            //  - table_alias.column

            // 1. is this a table/subquery alias?
            for (GenericTable t : tables) {
                if (t.getAlias() != null) {
                    if (parts[0].equalsIgnoreCase(t.getAlias())) {
                        // prefix matches to alias, let describe the table
                        TableDescriptorLegacy td = SupportStuff.describeTable(t);
                        if (td != null) {
                            out.add(new TableContext(td));
                        } else {
                            // todo - problem with describing of the table??
                        }
                    }
                }

                // 2. is this a table name?
                if (t instanceof PlainTable) {
                    if (((PlainTable) t).getTableName().equalsIgnoreCase(parts[0])) {
                        // prefix matches to table name, let describe the table
                        TableDescriptorLegacy td = SupportStuff.describeTable(t);
                        if (td != null) {
                            out.add(new TableContext(td));
                        } else {
                            // todo - problem with describing of the table??
                        }
                    }
                }
            }
        } else if (!nameComplex && tables.length > 0) {
            //  - column in one of existing tables in scope
            for (GenericTable t : tables) {
                TableDescriptorLegacy td = SupportStuff.describeTable(t);
                if (td != null) {
                    // search "name" thru the all columns of the tables
                    for (String col : td.getColumnNames()) {
                        if (col.equalsIgnoreCase(parts[0])) {
                            out.add(new TableContext(td));
                            break;
                        }
                    }
                } else {
                    // todo - problem with describing of the table??
                }
            }
        }

        if (nameComplex) {
            //  - package.(function/procedure)
            DbObject[] objects = ObjectCacheFactory
                    .getObjectCache()
                    .findByNameForType(ObjectCache.PACKAGE, parts[0]);

            if (objects.length > 0) {
                for (DbObject dbo : objects) {
                    if (dbo instanceof PackageDescriptor) {
                        if (((PackageDescriptor) dbo).findObjectByName(parts[1]) != null) {
                            out.add(new PackageContext((PackageDescriptor) dbo));
                        }
                    }
                }
            }
        } else {
            // !nameComplex
            // todo -
            // - function/procedure of the current package or SYS's
            // - variable
        }

        return out.toArray(new ResolveContext[out.size()]);
    }
*/

/*
    public static ResolveContext[] resolve_AsExecutableRef(ASTNode context, String name) throws NameNotResolvedException {
        List<ResolveContext> out = new ArrayList<ResolveContext>();

        // what the name is kind of? ("abc.name" or "name")
        String[] parts = name.split("\\.");
        boolean nameComplex = false;
        if (parts.length == 2) {
            // "abc.name" case 1
            nameComplex = true;
        } else if (parts.length == 1) {
            // "name" case 2
        } else {
            throw new NameNotResolvedException("Name is empty or too complex!");
        }

        if (nameComplex) {
            //  - package.(function/procedure)
            DbObject[] objects = ObjectCacheFactory
                    .getObjectCache()
                    .findByNameForType(ObjectCache.PACKAGE, parts[0]);

            if (objects.length > 0) {
                for (DbObject dbo : objects) {
                    if (dbo instanceof PackageDescriptor) {
                        if (((PackageDescriptor) dbo).findObjectByName(parts[1]) != null) {
                            out.add(new PackageContext((PackageDescriptor) dbo));
                        }
                    }
                }
            }
        } else {
            // !nameComplex
            // todo -
            // - function/procedure of the current package or SYS's
            // - variable
        }

        return out.toArray(new ResolveContext[out.size()]);
    }
*/

/*
    public static ResolveContext[] resolve_AsColumnRef(ASTNode context, String name) throws NameNotResolvedException {
        // find the context (a statement)
        PsiElement stmt =
                PsiTreeHelpers
                        .findParentNode(
                                context,
                                new PlSqlElementType[]{
//                                        PlSqlElementTypes.INSERT_SUBQUERY,
                                        PlSqlElementTypes.SELECT_EXPRESSION, //COMMAND,
                                        PlSqlElementTypes.INSERT_COMMAND,
                                        PlSqlElementTypes.DELETE_COMMAND,
                                        PlSqlElementTypes.UPDATE_COMMAND}
                        );

        GenericTable[] tables = new GenericTable[0];
        if (stmt != null) {
            if (stmt instanceof SelectStatement) {
                FromClause from = ((SelectStatement) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // FROM not found !!!
                    throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                }

            } else if (stmt instanceof InsertSubquery) {
                FromClause from = ((InsertSubquery) stmt).getFromClause();
                if (from != null) {
                    tables = from.getTableList();
                } else {
                    // FROM not found !!!
                    throw new NameNotResolvedException("FROM clause not found in SELECT statement.");
                }

            } else if (stmt instanceof InsertStatement) {
                tables = new GenericTable[1];
                tables[0] = ((InsertStatement) stmt).getIntoTable();
            } else if (stmt instanceof UpdateStatement) {
                tables = new GenericTable[1];
                tables[0] = ((UpdateStatement) stmt).getTargetTable();
            } else if (stmt instanceof DeleteStatement) {
                tables = new GenericTable[1];
                tables[0] = ((DeleteStatement) stmt).getTargetTable();
            } else {
                // todo - what the hell is this statement?
                throw new NameNotResolvedException("Statement type not supported: " + stmt.getClass().getName());
            }
        }

        if (tables.length == 0) {
            return new ResolveContext[0];
        }

        // Possibles type of names:
        // 1. "abc.name"
        //  - table_name.column
        //  - table_alias.column
        // 2. "name"
        //  - column in one of existing tables in scope

        // what the name is kind of? ("abc.name" or "name")
        String[] parts = name.split("\\.");
        boolean nameComplex = false;
        if (parts.length == 2) {
            // "abc.name" case 1
            nameComplex = true;
        } else if (parts.length == 1) {
            // "name" case 2
        } else {
            throw new NameNotResolvedException("Name is empty or too complex!");
        }

        List<ResolveContext> out = new ArrayList<ResolveContext>();
        if (nameComplex) {
            //  - table_name.column
            //  - table_alias.column

            // 1. is this a table/subquery alias?
            for (GenericTable t : tables) {
                if (t.getAlias() != null) {
                    if (parts[0].equalsIgnoreCase(t.getAlias())) {
                        // prefix matches to alias, let describe the table
                        TableDescriptorLegacy td = t.describe(); ///SupportStuff.describeTable(t);
                        if (td != null) {
                            out.add(new TableContext(td));
                        } else {
                            // todo - problem with describing of the table??
                        }
                    }
                }

                // 2. is this a table name?
                if (t instanceof PlainTable) {
                    if (((PlainTable) t).getTableName().equalsIgnoreCase(parts[0])) {
                        // prefix matches to table name, let describe the table
                        TableDescriptorLegacy td = t.describe(); //SupportStuff.describeTable(t);
                        if (td != null) {
                            out.add(new TableContext(td));
                        } else {
                            // todo - problem with describing of the table??
                        }
                    }
                }
            }
        } else {
            //  - column in one of existing tables in scope
            for (GenericTable t : tables) {
                TableDescriptorLegacy td = t.describe(); ///SupportStuff.describeTable(t);
                if (td != null) {
                    // search "name" thru the all columns of the tables
                    for (String col : td.getColumnNames()) {
                        if (col.equalsIgnoreCase(parts[0])) {
                            out.add(new TableContext(td));
                            break;
                        }
                    }
                } else {
                    // todo - problem with describing of the table??
                }
            }
        }

        return out.toArray(new ResolveContext[out.size()]);
    }
*/

    public static PackageBody resolve_PackageBody(Project project, VirtualFileSystem vfs, String packageName) {

        if (vfs.getProtocol().equals(CachedVirtualFileSystem.PROTOCOL)) {
            PackageBodyDescriptor pdesc = resolve_PackageBody(project, packageName);
            if (pdesc != null) {
                //Project project = LangDataKeys.PROJECT.getData(DataManager.getInstance().getDataContext());
                PlSqlElement elem = SqlScriptManager.mapToPsiTree(project, pdesc);
                if (elem instanceof PackageBody) {
                    return (PackageBody) elem;
                }
            }
        }
        // todo ---
        return null;
    }
}


/*
        public static ResolveContext createProcFuncContext(ASTNode node) {

            Procedure proc = (Procedure) SupportStuff
                    .findParentNode(
                            node,
                            new PlSqlElementType[]{
                                    PlSqlElementTypes.PROCEDURE_BODY}
                    );

            if (proc != null) {
                ExecBodyResolveCtx ctx = new ExecBodyResolveCtx();
                for (Argument arg : proc.getArgumentList()) {
                    ctx.addDecl(arg.getArgumentName(), arg.getType());
                }

                for (Declaration decl : proc.getDeclarationList()) {
                    if (decl instanceof VariableDecl) {
                        VariableDecl var = (VariableDecl) decl;
                        ctx.addDecl(var.getRecordItemName(), var.getType());
                    }
                }
                return ctx;
            }


            Function func = (Function) SupportStuff
                    .findParentNode(
                            node,
                            new PlSqlElementType[]{
                                    PlSqlElementTypes.FUNCTION_BODY}
                    );

            if (func != null) {
                ExecBodyResolveCtx ctx = new ExecBodyResolveCtx();
                for (Argument arg : func.getArgumentList()) {
                    ctx.addDecl(arg.getArgumentName(), arg.getType());
                }

                for (Declaration decl : func.getDeclarationList()) {
                    if (decl instanceof VariableDecl) {
                        VariableDecl var = (VariableDecl) decl;
                        ctx.addDecl(var.getRecordItemName(), var.getType());
                    }
                }
                return ctx;
            }

            return null;
        }

        public static ResolveContext discoverExecContext(ASTNode node) {

            Executable proc = (Executable) SupportStuff
                    .findParentNode(
                            node,
                            new PlSqlElementType[]{
                                    PlSqlElementTypes.PROCEDURE_BODY,
                                    PlSqlElementTypes.FUNCTION_BODY}
                    );

            if (proc != null) {
                ExecBodyResolveCtx ctx = new ExecBodyResolveCtx();
                for (Argument arg : proc.getArgumentList()) {
                    ctx.addDecl(arg.getArgumentName(), arg.getType());
                }

                for (Declaration decl : proc.getDeclarationList()) {
                    if (decl instanceof VariableDecl) {
                        VariableDecl var = (VariableDecl) decl;
                        ctx.addDecl(var.getRecordItemName(), var.getType());
                    }
                }
                return ctx;
            }

            return null;
        }
    */
