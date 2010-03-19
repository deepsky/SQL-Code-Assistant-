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

package com.deepsky.lang.plsql.psi.impl.types;

import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.impl.PlSqlCompositeNameBase;
import com.deepsky.lang.plsql.psi.resolve.*;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiPackageSpecContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiPackageBodyContext;
import com.deepsky.lang.plsql.psi.resolve.impl.*;
import com.deepsky.lang.plsql.psi.utils.PsiTreeHelpers;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TypeNameReferenceImpl extends PlSqlCompositeNameBase implements TypeNameReference {

    public TypeNameReferenceImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    protected VariantsProcessor777 createVariantsProcessorFront() throws NameNotResolvedException {
        return new VariantsProcessor777Front(this);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitTypeNameReference(this);
        } else {
            super.accept(visitor);
        }
    }


    public Type getType() {
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
        if (nodes == null || nodes.length < 1 || nodes.length > 2) {
            throw new SyntaxTreeCorruptedException();
        } else if (nodes.length == 1) {
            return new UserDefinedType(null, nodes[0].getText());
        } else {
            int len = nodes.length;
            return new UserDefinedType(nodes[len - 2].getText(), nodes[len - 1].getText());
        }
    }

    public void validate() {
        NameFragmentRef[] fragments = getNamePieces();
        NameFragmentRef last = fragments[fragments.length - 1];

        try {
            // todo -- TIME CONSUMING TASK!!! PERFORMANCE ISSUE
            ResolveContext777 ctx = last.resolveContext();
        } catch (SyntaxTreeCorruptedException e1) {
            throw new ValidationException("Type '" + getText() + "' not resolved", getNode());
        } catch (NotSupportedException e) {
            throw new ValidationException("Type '" + getText() + "' not resolved", getNode());
        } catch (NameNotResolvedException e1) {
            throw new ValidationException(e1.getMessage(), e1.getCauseElement()); //"Type '" + getText() + "' not resolved", getNode()), ;
        }
    }


    @NotNull
    public ResolveContext777 resolveType() throws NameNotResolvedException {
        // 1. "abc.name"
        //  - package.type_name
        // 2. "name"
        //  - type_name
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
/*
        if(nodes.length == 0){
            return getResolveContext();
        } else {
            ResolveContext777 ctx = getResolveContext();
            return ctx.resolve(nodes[0].getPsi());
        }
*/
        if(nodes.length == 1){
            return getResolveContext();
        } else if(nodes.length == 2) {
            ResolveContext777 ctx = getResolveContext();
            return ctx.resolve(nodes[1].getPsi());
        }

        throw new NameNotResolvedException("Can not resolve " + getText());
    }

    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        // 1. "abc.name"
        //  - package.type_name
        // 2. "name"
        //  - type_name
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));

        List<ResolveContext777> collisions = new ArrayList<ResolveContext777>();
        // 1. package name (usage: sql statement, function/procedure definition)
        if (nodes.length == 2) {
            PackageDescriptor pdesc = resolve_Package(nodes[0].getText());
            if (pdesc != null) {
                collisions.add(new PackageContext(pdesc, getProject())); //.resolve(nodes[1].getPsi()));
            } else {
                throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(), "Package not found: " + nodes[0].getText());
            }
        } else if (nodes.length == 1) {
            // is the type schema-wide?
            UserDefinedTypeDescriptor udt = ResolveHelper.resolve_Type(getProject(), nodes[0].getText());
            if (udt != null) {
                return UserDefinedTypeHelper.createResolveContext(getProject(), udt);
            } else {
//                PlSqlElement context = getUsageContext(TokenSet.create(
//                        PlSqlElementTypes.PACKAGE_BODY, PlSqlElementTypes.PACKAGE_SPEC)
//                );

                SyntaxTreeContext context = getUsagePlace(this);
                if (context.getPackageBody() != null){ // instanceof PackageBody) {

                    try {
                        // 1. Type defined inside package body
                        collisions.add(
                                new PsiPackageBodyContext(context.getPackageBody()).resolve(nodes[0].getPsi())
                        );
                    } catch (NameNotResolvedException e) {
                    }

                    // 2. Type defined inside package specification
                    String pkgName = context.getPackageBody().getPackageName();
                    UserDefinedTypeDescriptor udt2 = ResolveHelper.resolve_Type(getProject(), pkgName, nodes[0].getText());
                    if(udt2 != null){
                        try {
                            collisions.add(
                                    UserDefinedTypeHelper.createResolveContext(getProject(), udt2)
                            );
                        } catch (NameNotResolvedException e) {
                        }
                    }

                    // 3. Type defined in DECLARE section of the executable
                    Executable exec = context.getExecutable();
                    if(exec != null){
                        for(Declaration d: exec.getDeclarationList()){
                            if(d instanceof TypeDeclaration){
                                TypeDeclaration type = (TypeDeclaration)d;
                                if( type.getDeclName().equalsIgnoreCase(nodes[0].getText())){
                                    collisions.add(
                                        UserDefinedTypeHelper.createResolveContext(type)
                                    );
                                }
                            }
                        }
                    }

                } else if (context.getPackageSpec() != null){ // instanceof PackageSpec) {
                    try {
                        collisions.add(
                                new PsiPackageSpecContext(context.getPackageSpec()).resolve(nodes[0].getPsi())
                        );
                    } catch (NameNotResolvedException e) {
                    }
                }

                // 2. type defined in Decl section (Function/Procedure)
                DeclarationList decl = (DeclarationList) PsiTreeHelpers
                        .findParentNode(this, new PlSqlElementType[]{PlSqlElementTypes.DECLARE_LIST});
                if (decl != null) {
                    for (Declaration d : decl.getDeclList()) {
                        if (d instanceof TypeDeclaration) {
                            TypeDeclaration td = (TypeDeclaration) d;
                            if (nodes[0].getText().equalsIgnoreCase(td.getDeclName())) {
                                // todo - create a Context for the found type and add it to collision list
                                // collisions.add(...);
                                break;
                            }
                        }
                    }
                }
            }
        }

        if (collisions.size() == 1) {
            return collisions.get(0);
        } else if (collisions.size() == 0) {
            // search the schema name
            throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(), "Name not resolved: " + nodes[0].getText());
        } else {
            // name collision?
            throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(), "Name duplication found: " + nodes[0].getText());
        }
    }


    class SyntaxTreeContext {

        private PackageBody body;
        private PackageSpec spec;
        private Executable exec;

        public PackageBody getPackageBody(){
            return body;
        }

        public PackageSpec getPackageSpec(){
            return spec;
        }

        public Executable getExecutable(){
            return exec;            
        }

        public void setBody(PackageBody body) {
            this.body = body;
        }

        public void setSpec(PackageSpec spec) {
            this.spec = spec;
        }

        public void setExec(Executable exec) {
            this.exec = exec;
        }
    }


    public SyntaxTreeContext getUsagePlace(PsiElement elem){

        ASTTreeProcessor runner = new ASTTreeProcessor();
        PackageBody body = null;
        PackageSpec spec = null;
        Executable  exec = null;

        final SyntaxTreeContext context = new SyntaxTreeContext();

        runner.add(new ContextEnumerator(){
            public void handleASTNode(@NotNull ASTNode node) {
                if(node.getElementType() == PlSqlElementTypes.PACKAGE_BODY){
                    context.setBody((PackageBody) node.getPsi());
                } else if(node.getElementType() == PlSqlElementTypes.PACKAGE_SPEC){
                    context.setSpec((PackageSpec) node.getPsi());
                } else if(node.getElementType() == PlSqlElementTypes.PROCEDURE_BODY){
                    context.setExec((Executable) node.getPsi());
                } else if(node.getElementType() == PlSqlElementTypes.FUNCTION_BODY){
                    context.setExec((Executable) node.getPsi());
                }
            }
        });

        runner.process(elem.getNode());
        return context;
    }

//    private PlSqlElement getUsageContext(final TokenSet set) {
//        // [start] search definition context --------------------
//
//        final PlSqlElement[] elem = new PlSqlElement[1];
//        ASTTreeProcessor runner = new ASTTreeProcessor();
//        runner.add(new ASTNodeHandler() {
//            @NotNull
//            public TokenSet getTokenSet() {
//                return set;
//            }
//
//            public boolean handleNode(@NotNull ASTNode node) {
//                elem[0] = (PlSqlElement) node.getPsi();
//                return false;
//            }
//        });
//        runner.process(getNode());
//        // [end] search definition context ------------------------
//        return elem[0];
//    }


    @NotNull
    public UsagePlace getUsagePlace() {
        PsiElement pkg =
                PsiTreeHelpers
                        .findParentNode(
                                this,
                                new PlSqlElementType[]{
                                        PlSqlElementTypes.PACKAGE_BODY,
                                        PlSqlElementTypes.PACKAGE_SPEC}
                        );

        if (pkg != null) {
            String pkgName = null;
            if (pkg instanceof PackageBody) {
                pkgName = ((PackageBody) pkg).getPackageName();
                return new UsagePlaceImpl(pkgName, true);
            } else if (pkg instanceof PackageSpec) {
                pkgName = ((PackageSpec) pkg).getPackageName();
                return new UsagePlaceImpl(pkgName, true);
            }
        }

        return new UsagePlaceImpl(null, false);
    }

//    @NotNull
//    public Type resolveType() {
//        NameFragmentRef[] fragments = getNamePieces();
//        NameFragmentRef last = fragments[fragments.length - 1];
//
//        try {
//            ResolveContext777 ctx = last.resolveContext();
//            return ctx.getType();
//        } catch (SyntaxTreeCorruptedException e1) {
//            throw new ValidationException("Type '" + getText() + "' not resolved", getNode());
//        } catch (NotSupportedException e) {
//            throw new ValidationException("Type '" + getText() + "' not resolved", getNode());
//        } catch (NameNotResolvedException e1) {
//            throw new ValidationException(e1.getMessage(), e1.getCauseElem()); //"Type '" + getText() + "' not resolved", getNode()), ;
//        }
//    }

    class UsagePlaceImpl implements UsagePlace {

        String name;
        boolean isPackage;

        public UsagePlaceImpl(String name, boolean isPackage) {
            this.name = name;
            this.isPackage = isPackage;
        }

        public String getName() {
            return name;
        }

        public boolean isPackage() {
            return isPackage;
        }
    }

    class VariantsProcessor777Front implements VariantsProcessor777 {

        PsiElement elem;

        public VariantsProcessor777Front(PsiElement elem) {
            this.elem = elem;
        }

        public String[] getVariants(String prefix) {
            List<String> out = new ArrayList<String>();

            String[] datatypes = TypeFactory.listDataTypes();
            List<String> udt = new ArrayList<String>();
            UsageContext ctx = getUsageContext();
//            UsagePlace usage = getUsagePlace();
            // todo -- needs to support UDT defined inside Procedure or Function
            PackageDescriptorAggregate pkg = ctx.getPackage();
            if (pkg != null) {
                for (TypeDeclaration e : pkg.getUdtDecl()) {
                    udt.add(e.getDeclName());
                }
            }

//            if (usage.isPackage()) {
//                PackageDescriptor pdesc = ResolveHelper.resolve_Package(usage.getName());
//                if (pdesc != null) {
//                    for (DbObject dbo : pdesc.getObjects()) {
//                        if (dbo instanceof UserDefinedTypeDescriptor) {
//                            udt.add(((UserDefinedTypeDescriptor) dbo).getType().typeName());
//                        }
//                    }
//                }
//            } else {
//                // todo -- get list of schema-wide user types?
//            }

            if (prefix.length() == 0) {
                udt.addAll(Arrays.asList(datatypes));
                return udt.toArray(new String[udt.size()]);
            } else {
                for (String t : datatypes) {
                    if (t.toUpperCase().startsWith(prefix.toUpperCase())) {
                        out.add(t);
                    }
                }
                for (String t : udt) {
                    if (t.toUpperCase().startsWith(prefix.toUpperCase())) {
                        out.add(t);
                    }
                }

                return out.toArray(new String[out.size()]);
            }
        }
    }

}
