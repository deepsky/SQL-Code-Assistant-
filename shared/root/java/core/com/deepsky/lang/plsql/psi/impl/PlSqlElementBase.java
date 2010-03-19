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

package com.deepsky.lang.plsql.psi.impl;

import com.deepsky.lang.common.PlSqlSupportLoader;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.resolve.*;
import com.deepsky.lang.plsql.psi.utils.PsiTreeHelpers;
import com.deepsky.lang.plsql.struct.PackageBodyDescriptor;
import com.deepsky.lang.plsql.struct.PackageDescriptor;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.project.Project;
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

    /// UTILITIES ---------------------------
    protected TableDescriptor describeTable(String name){
        return ResolveHelper.describeTable(getProject(), name);
    }

    protected PackageDescriptor resolve_Package(String name){
        return ResolveHelper.resolve_Package(getProject(), name);
    }

    protected PackageBodyDescriptor resolve_PackageBody(String name){
        return ResolveHelper.resolve_PackageBody(getProject(), name);        
    }
    /// UTILITIES ---------------------------


    @NotNull
    public PsiElement[] getChildren() {
      PsiElement psiChild = getFirstChild();
      if (psiChild == null) return EMPTY_ARRAY;

      List<PsiElement> result = new ArrayList<PsiElement>();
      while (psiChild != null) {
//        if (psiChild.getNode() instanceof CompositeElement) {
          result.add(psiChild);
//        }
        psiChild = psiChild.getNextSibling();
      }
      return result.toArray(new PsiElement[result.size()]);
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
                if (spec != null) {
                    for (PlSqlElement e : spec.getObjects()) {
                        if (e instanceof TypeDeclaration) {
                            out.add((TypeDeclaration) e);
                        }
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


    // [Contex Management Stuff] Start -------------------------------
    /**
     * Default implementation
     *
     * @return
     */
    public CtxPath getCtxPath() {
        PsiElement parent = this;
        do {
            parent = parent.getParent();
            if (parent instanceof PlSqlElement) {
                return ((PlSqlElement) parent).getCtxPath();
            }
        } while (parent != null);

        return null;
    }

    protected class CtxPathImpl implements CtxPath {
        private int sequence = 0;
        String path;
        public CtxPathImpl(String path){
            this.path = path;
        }
        public String getPath() {
            return path;
        }

        public String getSeqNEXT() {
            return ids[0xff & sequence++];
        }
    }

    final static String[] ids = {
            "00","01","02","03","04","05","06","07","08","09", "0A", "0B", "0C", "0D", "0E", "0F",
            "10","11","12","13","14","15","16","17","18","19", "1A", "1B", "1C", "1D", "1E", "1F",
            "20","21","22","23","24","25","26","27","28","29", "2A", "2B", "2C", "2D", "2E", "2F",
            "30","31","32","33","34","35","36","37","38","39", "3A", "3B", "3C", "3D", "3E", "3F",
            "40","41","42","43","44","45","46","47","48","49", "4A", "4B", "4C", "4D", "4E", "4F",
            "50","51","52","53","54","55","56","57","58","59", "5A", "5B", "5C", "5D", "5E", "5F",
            "60","61","62","63","64","65","66","67","68","69", "0A", "0B", "0C", "0D", "0E", "0F",
            "70","71","72","73","74","75","76","77","78","79", "7A", "7B", "7C", "7D", "7E", "7F",
            "80","81","82","83","84","85","86","87","88","89", "8A", "8B", "8C", "8D", "8E", "8F",
            "90","91","92","93","94","95","96","97","98","99", "9A", "9B", "9C", "9D", "9E", "9F",
            "A0","A1","A2","A3","A4","A5","A6","A7","A8","A9", "AA", "AB", "AC", "AD", "AE", "AF",
            "B0","B1","B2","B3","B4","B5","B6","B7","B8","B9", "BA", "BB", "BC", "BD", "BE", "BF",
            "C0","C1","C2","C3","C4","C5","C6","C7","C8","C9", "CA", "CB", "CC", "CD", "CE", "CF",
            "D0","D1","D2","D3","D4","D5","D6","D7","D8","D9", "DA", "DB", "DC", "DD", "DE", "DF",
            "E0","E1","E2","E3","E4","E5","E6","E7","E8","E9", "EA", "EB", "EC", "ED", "EE", "EF",
            "F0","F1","F2","F3","F4","F5","F6","F7","F8","F9", "FA", "FB", "FC", "FD", "FE", "FF"
    };

    // [Contex Management Stuff] End ---------------------------------


}
