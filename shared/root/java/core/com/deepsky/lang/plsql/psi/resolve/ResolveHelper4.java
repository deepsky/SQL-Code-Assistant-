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
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.resolve.impl.ArgumentContext;
import com.deepsky.lang.plsql.psi.resolve.impl.ExecutableContext;
import com.deepsky.lang.plsql.psi.resolve.impl.PackageContext;
import com.deepsky.lang.plsql.psi.resolve.impl.TableColumnContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.*;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import com.deepsky.lang.plsql.struct.PackageDescriptor;
import com.deepsky.lang.plsql.struct.SystemFunctionDescriptor;
import com.deepsky.lang.plsql.struct.TableDescriptorLegacy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ResolveHelper4 {

    @NotNull
    public static ResolveContext777 resolveContext2(final NameFragmentRef elem, final int argc) throws NameNotResolvedException {

        final List<ResolveContext777> collisions = new ArrayList<ResolveContext777>();
        final Project project = elem.getProject();
//        if(++counter777 % 200 == 0){
//            log.info("error --- counter777: " + counter777);
//        }
        ASTTreeProcessor runner = new ASTTreeProcessor();

        // 2. the name of variable or argument (usage: function/procedure definition)
        runner.add(new ExecCtxDeclarationEnumerator() {
            public void handleArgument(Argument arg) {
                if (arg.getArgumentName().equalsIgnoreCase(elem.getFragmentText())) {
//                    collisions.add(new PsiArgumentContext(arg));
                    collisions.add(new ArgumentContext(arg));
                }
            }

//            public void handleDecl(Declaration decl) {
//                if (decl instanceof VariableDecl) {
//                    if (((VariableDecl) decl).getDeclName().equalsIgnoreCase(elem.getFragmentText())) {
//                        collisions.add(new PsiVariableContext((VariableDecl) decl));
//                    }
//                }
//            }
        });

        // name of variable defined in the DECLARE section of the block 
        runner.add(new BeginBlockContext() {
            public void handleDecl(Declaration decl) {
                if (decl instanceof VariableDecl) {
                    if (((VariableDecl) decl).getDeclName().equalsIgnoreCase(elem.getFragmentText())) {
                        collisions.add(new PsiVariableContext((VariableDecl) decl));
                    }
                }
            }
        });

        // name used in "select" statement references CURSOR PARAMETERS 
        runner.add(new CursorDefinitionContext() {
            public void handleArgument(Argument argument) {
                if (argument.getArgumentName().equalsIgnoreCase(elem.getText())) {
                    collisions.add(new ArgumentContext(argument));
                }
            }
        });

        runner.add(new PackageTriggerHandler() {
            public void handleTriggerBody(CreateTrigger trigger) {
                // look up among system fumnctions without arguments
                List<String> errors = new ArrayList<String>();
                ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(project);
                for (SystemFunctionDescriptor sdesc : ocache.findSystemFunction(elem.getText())) {
                    if (sdesc.getValidator().validate(new CallArgument[0], errors)) {
                        collisions.add(new ExecutableContext(sdesc, project));
                    }
                }

//                DbObject[] objects = objects = ObjectCacheFactory.getObjectCache().findByNameForType(
//                        ObjectCache.EMBEDDED_FUNCTION, elem.getText()
//                );
//
//                for (DbObject object : objects) {
//                    if (object instanceof SystemFunctionDescriptor) {
//                        SystemFunctionDescriptor edesc = (SystemFunctionDescriptor) object;
//                        if (edesc.getValidator().validate(new CallArgument[0], errors)) {
//                            collisions.add(new ExecutableContext(edesc, elem.getProject()));
//                        }
//                    }
//                }
            }

            public void handlePackageBody(PackageBody pkg) {
                // name was occurred in the Package Body
//                if (argc == 1) {
                // Name of the object defined inside the package body
                PlSqlElement[] objects = pkg.findObjectByName(elem.getFragmentText());
                for (PlSqlElement e : objects) {
                    if (e instanceof Executable) {
                        // 4. the name of function/procedure (without arguments or arguments with default values)
                        ExecutableDescriptor edesc = ((Executable) e).describe();

                        if (elem.getParent() instanceof CallableCompositeName) {
                            // executable with arguments
                            Callable exec = ((CallableCompositeName) elem.getParent()).getCallable();
                            CallArgument[] callArgs = exec.getCallArgumentList();
                            List<String> errors = new ArrayList<String>();
                            if (ResolveHelper3.validateCallArgumentList(edesc, callArgs, errors)) {
                                collisions.add(new ExecutableContext(edesc, project));
                            }

                        } else if (elem.getParent() instanceof ObjectReference) {
                            // executable without arguments
                            collisions.add(new ExecutableContext(edesc, project));
                        } else {
                            // skip --
                        }
                    } else if (e instanceof VariableDecl) {
                        // 5. name of variable defined in the package body
                        collisions.add(new PsiVariableContext((VariableDecl) e));
                    }
                }

//                    PlSqlElement[] findings = pkg.findObjectByName(elem.getText());
//                    for (PlSqlElement e : findings) {
//                        if (e instanceof VariableDecl) {
//                            collisions.add(new PsiVariableContext((VariableDecl) e));
//                        }
//                    }

                // 6. name of variable defined in the package spec
                PackageSpec spec = pkg.getPackageSpecification(); //ResolveHelper.resolve_Package(pkg.getPackageName());
                if (spec != null) {
                    for (PlSqlElement dbo : spec.findObjectByName(elem.getText())) {
                        if (dbo instanceof VariableDecl) {
                            collisions.add(new PsiVariableContext((VariableDecl) dbo));
                        }
                    }
                }

/*
                    Executable[] exec1 = pkg.findExecutableByName(elem.getFragmentText());
                    for (int i = 0; i < exec1.length; i++) {
                        ExecutableDescriptor edesc = exec1[i].describe();

                        if (elem.getParent() instanceof CallableCompositeName) {
                            // executable with arguments
                            Callable exec = ((CallableCompositeName) elem.getParent()).getCallable();
                            List<String> errors = new ArrayList<String>();
                            if (ResolveHelper3.validateCallArgumentList(edesc, exec, errors)) {
                                collisions.add(new ExecutableContext(edesc, elem.getProject()));
                            }

                        } else if (elem.getParent() instanceof ObjectReference) {
                            // executable without arguments
                            collisions.add(new ExecutableContext(edesc, elem.getProject()));
                        } else {
                            // skip --
                        }
                    }
*/
//                } else {
//                    // todo --
//                }
            }

            @Override
            public void handlePackageSpec(PackageSpec pkg) {
                // 7. the name of variable defined in the package spec
                PlSqlElement[] findings = pkg.findObjectByName(elem.getText());
                for (PlSqlElement e : findings) {
                    if (e instanceof VariableDecl) {
                        collisions.add(new PsiVariableContext((VariableDecl) e));
                    }
                }
            }
        }
        );


        if (argc == 1) {
            // 5. column's name in one of existing tables (usage: sql statement)
            runner.add(new TableEnumerator() {
                public void handleTable(GenericTable t) {
                    TableDescriptorLegacy tdesc = t.describe();
                    if (tdesc != null && tdesc.getColumnType(elem.getFragmentText()) != null) {
                        try {
                            collisions.add(new TableColumnContext(t, tdesc, elem.getFragmentText()));
                        } catch (NameNotResolvedException e) {
                        }
                    } else {
                        // TODO - table definition not found
                    }
                }
            });

            // 6. column alias referenced from ORDER_CLAUSE, migh be a column name
            runner.add(new NamesInOrderByResolver() {
                public void handle(SelectStatement select) {
                    SelectFieldExpr field = select.findSelectFieldByAlias(elem.getText());
                    if (field != null) {
                        // TODO --- DIRTY HACK!!! FIX ME
                        if (collisions.size() == 1 && collisions.get(0) instanceof TableColumnContext) {
                            collisions.clear();
                        }
                        // TODO ---
                        collisions.add(new PsiSelectFieldContext(field));
                    }
                }
            });


        } else if (argc == 2) {
            // the name is composed with more then one fragment

            // 1. the name of the table alias, search among table's aliases (usage: sql statement)
            runner.add(new TableAliasHandler(elem) {
                public void handleTable(GenericTable t) {
                    collisions.add(new PsiTableContext(t));
                }
            });

/*
            // 2. reference to a variable or cursor thru LOOP index
            runner.add(new LoopHandler(elem) {
                public void handleNumericLoopSpec(ASTNode node) {
                    // todo -- implement me
                }

                public void handleCursorRefLoopSpec(ASTNode cursorRef) {
                    CursorDecl cursor = ResolveHelper.resolveCursorRef(cursorRef.getPsi());
                    if (cursor != null) {
                        collisions.add(new PsiCursorDeclContext(cursor));
                    }
                }

                public void handleCursorFromSelectLoopSpec(SelectStatement select) {
                    // todo -- implement me
                }
            });
*/

            // 3. package name (usage: sql statement, function/procedure definition)
            //  <package_name>.<variable>
            //  <package_name>.<function_without_args>
            PackageDescriptor pdesc = ResolveHelper.resolve_Package(project, elem.getFragmentText());
            if (pdesc != null) {
                collisions.add(new PackageContext(pdesc, project));
            }
        } else if (argc == 3) {
            // 4. package name (usage: sql statement, function/procedure definition)
            //  <package_name>.<variable_table_collection_type>.{FIRST|LAST}
            PackageDescriptor pdesc = ResolveHelper.resolve_Package(project, elem.getFragmentText());
            if (pdesc != null) {
                collisions.add(new PackageContext(pdesc, elem.getProject()));
            }
        }


        // 2. reference to a variable or cursor thru LOOP index
        runner.add(new LoopHandler(elem) {
            @Override
            protected void handleForALLLoopSpec(ASTNode loopSpec) {
                //todo --
            }

            public void handleNumericLoopSpec(ASTNode node) {
                ASTNode lindex = node.findChildByType(PlSqlElementTypes.LOOP_INDEX);
                if (lindex != null && lindex.getPsi() instanceof LoopIndex) {
                    collisions.add(new PsiLoopIndexContext((LoopIndex) lindex.getPsi()));
                }
            }

            public void handleCursorRefLoopSpec(ASTNode cursorRef) {
                CursorDecl cursor = ResolveHelper.resolveCursorRef(cursorRef.getPsi());
                if (cursor != null) {
                    collisions.add(new PsiCursorDeclContext(cursor));
                }
            }

            public void handleCursorFromSelectLoopSpec(SelectStatement select) {
                // todo -- implement me
            }
        });


        runner.process(elem.getNode());

        // 8. the name of schema (scope: sql statement, function/procedure definition)
        // todo ...

        // 9. Schema-wide type definition
//        UserDefinedTypeDescriptor udt = ResolveHelper.resolve_Type(elem.getText());
//        if(udt != null){
//            if(udt instanceof RecordTypeDescriptor){
//                collisions.add(new RecordTypeContext((RecordTypeDescriptor) udt));
//            } else if(udt instanceof TableCollectionDescriptor){
//                // todo --
//                collisions.add(new TableCollectionDescriptorContext(elem.getProject(), (TableCollectionDescriptor)udt, null));
//            } else {
//                throw new NameNotResolvedException(elem, "Type resolving for " + udt.getClass().getSimpleName() + " not supported");
//            }
//        }

        if (collisions.size() == 1) {
            return collisions.get(0);
        } else if (collisions.size() == 0) {
            // search the schema name
            throw new NameNotResolvedException(elem, "Name not resolved: " + elem.getFragmentText());
        } else {
            // name collision?
            throw new NameNotResolvedException(elem, "Name duplication found: " + elem.getFragmentText());
        }
    }

}
