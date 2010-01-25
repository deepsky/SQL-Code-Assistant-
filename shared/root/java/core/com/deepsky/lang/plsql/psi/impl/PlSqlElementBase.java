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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.common.PlSqlSupportLoader;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.deepsky.lang.plsql.psi.resolve.PackageDescriptorAggregate;
import com.deepsky.lang.plsql.psi.resolve.UsageContext;
import com.deepsky.lang.plsql.psi.utils.PsiTreeHelpers;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PlSqlElementBase extends ASTWrapperPsiElement implements PlSqlElement {

    public PlSqlElementBase(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public Language getLanguage() {
        return PlSqlSupportLoader.PLSQL.getLanguage();
    }

    @Nullable
    public String getQuickNavigateInfo() {
        return null;
    }

    public String getStrippedText() {
        return PsiTreeHelpers.stripText(this.getText()).trim();
    }

    public void process(Visitor proc) {
        // todo -
    }

    /**
     * Default implementation
     *
     * @return
     */
    public String getCtxPath() {
        PsiElement parent = this;
        do {
            parent = parent.getParent();
            if (parent instanceof PlSqlElement) {
                return ((PlSqlElement) parent).getCtxPath();
            }
        } while (parent != null);
        return "";
    }

    protected PlSqlElement getUsageContext(final TokenSet set) {
        // [start] search definition context --------------------
        final PlSqlElement[] elem = new PlSqlElement[1];
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return set;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                elem[0] = (PlSqlElement) node.getPsi();
                return false;
            }
        });
        runner.process(getNode());
        // [end] search definition context ------------------------

        return elem[0];
    }


    public <T> T findParent(Class clazz) {
        ASTNode n = getNode();
        while (n != null) {

            if (n.getPsi().getClass() == clazz) {
                return (T) n.getPsi();
            } else {
                java.lang.reflect.Type[] interfaces = n.getPsi().getClass().getGenericInterfaces();
                for (java.lang.reflect.Type t : interfaces) {
                    if (t == clazz) {
                        return (T) n.getPsi();
                    }
                }

                if (n.getPsi().getClass().getGenericSuperclass() == clazz) {
                    return (T) n.getPsi();
                }
            }
            n = n.getTreeParent();
        }

        return null;
    }

    final protected void __ASSERT_NOT_NULL__(Object o) {
        if (o == null) {
            throw new SyntaxTreeCorruptedException();
        }
    }

//    public void accept(@NotNull PsiElementVisitor visitor) {
//        if (visitor instanceof PlSqlElementVisitor) {
//            ((PlSqlElementVisitor) visitor).visitPlSqlElement(this);
//        } else {
//            super.accept(visitor);
//        }
//    }

    static private TokenSet CONTEXT = TokenSet.create(
            PlSqlElementTypes.PACKAGE_BODY,
            PlSqlElementTypes.PACKAGE_SPEC,
            PlSqlElementTypes.CREATE_TRIGGER,
            PlSqlElementTypes.FUNCTION_BODY,
            PlSqlElementTypes.PROCEDURE_BODY
    );


    @NotNull
    public UsageContext getUsageContext() {

        final UsageContextImpl usage = new UsageContextImpl();
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new ASTNodeHandler() {
            @NotNull
            public TokenSet getTokenSet() {
                return CONTEXT;
            }

            public boolean handleNode(@NotNull ASTNode node) {
                if (node.getElementType() == PlSqlElementTypes.PACKAGE_BODY) {
                    usage.setPackageBody((PackageBody) node.getPsi());
                } else if (node.getElementType() == PlSqlElementTypes.PACKAGE_SPEC) {
                    usage.setPackageSpec((PackageSpec) node.getPsi());
                } else if (node.getElementType() == PlSqlElementTypes.CREATE_TRIGGER) {
                    usage.setTrigger((CreateTrigger) node.getPsi());
                } else if (node.getElementType() == PlSqlElementTypes.FUNCTION_BODY) {
                    usage.setFunctionBody((Function) node.getPsi());
                } else if (node.getElementType() == PlSqlElementTypes.PROCEDURE_BODY) {
                    usage.setProcedureBody((Procedure) node.getPsi());
                }
                return false;
            }
        });

        runner.process(getNode());
        return usage;
    }
//    public UsageContext getUsageContext() {
//
//        final UsageContextImpl usage = new UsageContextImpl();
//        ASTTreeProcessor runner = new ASTTreeProcessor();
//        runner.add(new PackageTriggerHandler() {
//            public void handleTriggerBody(CreateTrigger trigger) {
//                usage.setTrigger(trigger);
//            }
//
//            public void handlePackageBody(PackageBody pkg) {
//                usage.setPackageBody(pkg);
//            }
//
//            public void handlePackageSpec(PackageSpec pkg) {
//                usage.setPackageSpec(pkg);
//            }
//        });
//
//        runner.process(getNode());
//        return usage;
//    }


    class UsageContextImpl implements UsageContext {

        PackageSpec spec;
        PackageBody body;
        CreateTrigger trigger;
        Procedure procedure;
        Function function;

        public String getPackageName() {
            if (spec != null) {
                return spec.getPackageName();
            } else if (body != null) {
                return body.getPackageName();
            }
            return null;
        }

        public PackageDescriptorAggregate getPackage() {
            if (spec != null) {
                return new PackageDescriptorAggregateImpl(spec);
            } else if (body != null) {
                return new PackageDescriptorAggregateImpl(body);
            }
            return null;
        }

        public PackageBody getPackageBody() {
            return body;
        }

        public PackageSpec getPackageSpec() {
            return spec;
        }

        public CreateTrigger getTrigger() {
            return trigger;
        }

        @NotNull
        public PlSqlElement[] searchForDeclWithName(String name) {
            List<PlSqlElement> out = new ArrayList<PlSqlElement>();
            if (trigger != null) {
                // todo --
            } else {
                if (function != null || procedure != null) {

                    Executable exec = function != null ? function : procedure;
                    for (Argument a : exec.getArguments()) {
                        if (a.getArgumentName().equalsIgnoreCase(name)) {
                            out.add(a);
                        }
                    }

                    for (Declaration d : exec.getDeclarationList()) {
                        if (d.getDeclName().equalsIgnoreCase(name)) {
                            out.add(d);
                        }
                    }
                }

                if (body != null) {
                    out.addAll(Arrays.asList(body.findObjectByName(name)));
                } else if (spec != null) {
                    out.addAll(Arrays.asList(spec.findObjectByName(name)));
                }
            }

            return out.toArray(new PlSqlElement[out.size()]);
        }

        public void setPackageSpec(PackageSpec spec) {
            this.spec = spec;
        }

        public void setPackageBody(PackageBody body) {
            this.body = body;
        }

        public void setTrigger(CreateTrigger trigger) {
            this.trigger = trigger;
        }

        public void setFunctionBody(Function function) {
            this.function = function;
        }

        public void setProcedureBody(Procedure procedure) {
            this.procedure = procedure;
        }
    }


    class PackageDescriptorAggregateImpl implements PackageDescriptorAggregate {
        PackageSpec spec;
        PackageBody body;

        public PackageDescriptorAggregateImpl(PackageSpec spec) {
            this.spec = spec;
        }

        public PackageDescriptorAggregateImpl(PackageBody body) {
            this.body = body;
        }

        public PlSqlElement[] getObjects() {
            if (spec != null) {
                // search specification only
                return spec.getObjects();
            } else {
                // search specification and body
                PackageSpec spec = body.getPackageSpecification();
                PlSqlElement[] elems1 = spec.getObjects();
                List<PlSqlElement> out = new ArrayList<PlSqlElement>(Arrays.asList(elems1));
                PlSqlElement[] elems2 = body.getObjects();
                out.addAll(Arrays.asList(elems2));
                return out.toArray(new PlSqlElement[out.size()]);
            }
        }

        public PlSqlElement[] findObjectsByName(String name) {
            if (spec != null) {
                return spec.findObjectByName(name);
            } else {
                PackageSpec spec = body.getPackageSpecification();
                if (spec != null) {
                    PlSqlElement[] elems1 = spec.findObjectByName(name);
                    List<PlSqlElement> out = new ArrayList<PlSqlElement>(Arrays.asList(elems1));
                    PlSqlElement[] elems2 = body.findObjectByName(name);
                    out.addAll(Arrays.asList(elems2));
                    return out.toArray(new PlSqlElement[out.size()]);
                } else {
                    return new PlSqlElement[0];
                }
            }
        }

        public TypeDeclaration[] getUdtDecl() {
            List<TypeDeclaration> out = new ArrayList<TypeDeclaration>();
            if (spec != null) {
                for (PlSqlElement e : spec.getObjects()) {
                    if (e instanceof TypeDeclaration) {
                        out.add((TypeDeclaration) e);
                    }
                }
            } else {
                PackageSpec spec = body.getPackageSpecification();
                for (PlSqlElement e : spec.getObjects()) {
                    if (e instanceof TypeDeclaration) {
                        out.add((TypeDeclaration) e);
                    }
                }

                for (PlSqlElement e : body.getObjects()) {
                    if (e instanceof TypeDeclaration) {
                        out.add((TypeDeclaration) e);
                    }
                }
            }
            return out.toArray(new TypeDeclaration[out.size()]);
        }
    }
}
