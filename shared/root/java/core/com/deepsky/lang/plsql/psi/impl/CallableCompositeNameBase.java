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

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.lang.common.PluginKeys;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.resolve.*;
import com.deepsky.lang.plsql.psi.resolve.collection.TableCollectionItemAccessorCtx;
import com.deepsky.lang.plsql.psi.resolve.collection.VarrayItemAccessorCtx;
import com.deepsky.lang.plsql.psi.resolve.impl.ExecutableContext;
import com.deepsky.lang.plsql.psi.resolve.impl.PackageContext2;
import com.deepsky.lang.plsql.psi.resolve.impl.SystemFunctionProxy;
import com.deepsky.lang.plsql.psi.resolve.impl.VarrayCollectionContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiArgumentContext;
import com.deepsky.lang.plsql.psi.resolve.psibased.PsiVariableContext;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.*;


public class CallableCompositeNameBase extends PlSqlCompositeNameBase implements CallableCompositeName {

    private static final LoggerProxy LOG = LoggerProxy.getInstance("#CallableCompositeNameBase");

    public CallableCompositeNameBase(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    protected VariantsProcessor777 createVariantsProcessorFront() throws NameNotResolvedException {
        if (getNamePieces().length > 1) {
            // "name1.name2" case, it is assumed the first piece of the name is a package name
            int type = ObjectCache.PACKAGE;
            return new SchemaWideExecutableVariantProcessor(type);
        } else {
            // "name1" case, procedure/function name ?
            int type = ObjectCache.USER_PROCEDURE;
            if (this.getCallableType() == CallableCompositeName.CallableType.FUNCTION) {
                type = ObjectCache.USER_FUNCTION | ObjectCache.EMBEDDED_FUNCTION;
            }
            return new SchemaWideExecutableVariantProcessor(type);
        }
    }

    class SchemaWideExecutableVariantProcessor implements VariantsProcessor777 {

        int type;

        public SchemaWideExecutableVariantProcessor(int type) {
            this.type = type;
        }

        public String[] getVariants(String prefix) {
            ObjectCache cache = PluginKeys.OBJECT_CACHE.getData(getProject()); //ObjectCacheFactory.getObjectCache();

            String user = cache.getCurrentUser();
            String[] objects = cache.findByNamePrefix2(user, type, prefix);
            if (objects.length == 0) {
                // try to search among SYS's schema objects
                objects = cache.findByNamePrefix2("SYS", type, prefix);
            }
            return objects; //extractNames(objects);
        }
    }


    private String[] extractNames(DbObject[] objs) {
        String[] out = new String[objs.length];
        for (int i = 0; i < objs.length; i++) {
            out[i] = objs[i].getName();
        }

        return out;
    }

    public String getCName() {
        StringBuffer out = new StringBuffer();
        ASTNode[] names = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));

        for (ASTNode n : names) {
            if (out.length() > 0) {
                out.append(".");
            }
            out.append(n.getText());
        }

//        ASTNodeIterator iterator = new ASTNodeIterator(getNode());
//        while (iterator.hasNext()) {
//            out.append(iterator.next().getText());
//        }
        return out.toString();
    }

    public CallableNameDesc describe() {
        ASTNode[] names = getNode().getChildren(
                TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT)
        );

        switch (names.length) {
            case 1:
                return new CallableNameDescImpl(null, null, names[0].getPsi());
            case 2:
                return new CallableNameDescImpl(null, names[0].getPsi(), names[1].getPsi());
            case 3:
                return new CallableNameDescImpl(names[0].getPsi(), names[1].getPsi(), names[2].getPsi());
            default: // todo --
                break;
        }
        return null;
    }

    @NotNull
    public CallableType getCallableType() {
        if (getParent() instanceof FunctionCall) {
            return CallableType.FUNCTION;
        } else if (getParent() instanceof ProcedureCall) {
            return CallableType.PROCEDURE;
        } else {
            throw new SyntaxTreeCorruptedException();
        }
    }

    @NotNull
    public Callable getCallable() {
        return (Callable) getParent();
    }


    // todo - dirty hack which should be fixed asap
    static public Map<String, RefElement> resolveCache = new HashMap<String, RefElement>();

    static class RefElement {
        public ResolveContext777 ctx;

        public RefElement(ResolveContext777 ctx) {
            this.ctx = ctx;
        }
    }


    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        RefElement elem = resolveCache.get(this.toString());
        if (elem != null) {
            if (elem.ctx == null) {
                throw new NameNotResolvedException("Name not resolved: " + getText());
            }
            return elem.ctx;
        }

        try {
            ResolveContext777 ctx = getResolveContextInternal();
            RefElement ref = new RefElement(ctx);
            resolveCache.put(this.toString(), ref);
            return ctx;
        } catch (NameNotResolvedException e) {
            RefElement ref = new RefElement(null);
            resolveCache.put(this.toString(), ref);

            throw e;
        }
    }

    public void subtreeChanged() {
        super.subtreeChanged();
        LOG.info("subtreeChanged: " + getText());
    }


    @NotNull
    private ResolveContext777 getResolveContextInternal() throws NameNotResolvedException {

        PsiElement praparent = getParent().getParent();
        // 4. Collection Type ctor or method call?
        if (praparent instanceof CollectionMethodCall) {
            CollectionMethodCall cmethod = (CollectionMethodCall) praparent;
            return cmethod.getResolveContext();
        }
        ObjectCache ocache = PluginKeys.OBJECT_CACHE.getData(getProject());
        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT));
        Callable callable = getCallable();
        boolean isFunctionCall = callable instanceof FunctionCall;
        CallArgument[] callArgs = callable.getCallArgumentList();

        List<ResolveContext777> collisions = new ArrayList<ResolveContext777>();

        if (nodes.length == 1) {
            String packageName = getUsageContext().getPackageName();
            if (packageName != null) {
                // 2. the name of function/procedure in the current package or SYS's
                // (without arguments or arguments with default values)

                PackageDescriptorAggregate2 pa = new PackageDescriptorAggregate2(ocache, packageName);
                for (PlSqlObject plsql : pa.findObjectByName(nodes[0].getText())) {
                    if (isFunctionCall && plsql instanceof FunctionDescriptor) {
                        ExecutableDescriptor edesc = (ExecutableDescriptor) plsql;
                        // executable with arguments
                        List<String> errors = new ArrayList<String>();
                        if (ResolveHelper3.validateCallArgumentList(edesc, callArgs, errors)) {
                            collisions.add(new ExecutableContext(edesc, getProject()));
                        }
                    } else if (!isFunctionCall && plsql instanceof ProcedureDescriptor) {
                        ExecutableDescriptor edesc = (ExecutableDescriptor) plsql;
                        // executable with arguments
                        List<String> errors = new ArrayList<String>();
                        if (ResolveHelper3.validateCallArgumentList(edesc, callArgs, errors)) {
                            collisions.add(new ExecutableContext(edesc, getProject()));
                        }
                    } else if (plsql instanceof VarrayCollectionDescriptor) {
                        VarrayCollectionDescriptor vdesc = (VarrayCollectionDescriptor) plsql;
                        collisions.add(new VarrayCollectionContext(getProject(), vdesc));
                    }
                }
            }

            // 3. System function
            for (SystemFunctionDescriptor sdesc : ocache.findSystemFunction(nodes[0].getText())) {
                List<String> errors = new ArrayList<String>();
                if(sdesc.getValidator().validate(callArgs, errors)){
                    collisions.add(new SystemFunctionProxy(sdesc, getProject()));
                }
            }

            if (collisions.size() == 0) {
                // check for collection case
                //collisions.addAll(Arrays.asList(ResolveHelper3.resolveCollectionCall((Callable) getParent())));
                UsageContext ctx = getUsageContext();
                for (PlSqlElement e : ctx.searchForDeclWithName(nodes[0].getText())) {
                    if (e instanceof VariableDecl) {
                        VariableDecl var = (VariableDecl) e;
                        Type t = var.getType();
                        if (t instanceof UserDefinedType) {
                            UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type(getProject(), (UserDefinedType) t, ctx);
                            if (tdesc instanceof TableCollectionDescriptor) {
                                TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                                collisions.add(new TableCollectionItemAccessorCtx(var, cdesc));
                            } else if( tdesc instanceof VarrayCollectionDescriptor){
                                VarrayCollectionDescriptor vdesc = (VarrayCollectionDescriptor) tdesc;
                                collisions.add(new VarrayItemAccessorCtx(var, vdesc));
                            }
                        }
                    } else if (e instanceof Argument) {
                        Argument arg = (Argument) e;
                        Type t = arg.getType();
                        if (t instanceof UserDefinedType) {
                            UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type(getProject(),(UserDefinedType) t, ctx);
                            if (tdesc instanceof TableCollectionDescriptor) {
                                TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                                collisions.add(new TableCollectionItemAccessorCtx(arg, cdesc));
                            } else if( tdesc instanceof VarrayCollectionDescriptor){
                                VarrayCollectionDescriptor vdesc = (VarrayCollectionDescriptor) tdesc;
                                collisions.add(new VarrayItemAccessorCtx(arg, vdesc));
                            }
                        }
                    }
                }
            }

        } else {
            // 1. package name (usage: sql statement, function/procedure definition)
            DbObject[] objects = ocache.findByNameForType(ObjectCache.PACKAGE, nodes[0].getText());
            if (objects.length == 1 && objects[0] instanceof PackageSpecDescriptor) {
                try {
                    collisions.add(
                            new PackageContext2(
                                    new PackageDescriptorAggregate2(ocache, (PackageSpecDescriptor) objects[0]), getProject())//.resolve(nodes[1].getPsi())
                    );
                } catch (NameNotResolvedException e) {
                    // ignore
                }
            } else {
                // 2. Table Collection method
                UsageContext ctx = getUsageContext();
                for (PlSqlElement e : ctx.searchForDeclWithName(nodes[0].getText())) {
                    if (e instanceof VariableDecl) {
                        VariableDecl var = (VariableDecl) e;
                        if (var.getTypeSpec().getType() instanceof UserDefinedType) {
                            // it is supposed to be Collection type
                            collisions.add(new PsiVariableContext(var));//.resolve(nodes[1].getPsi()));
                        }
                    } else if (e instanceof Argument) {
                        Argument arg = (Argument) e;
                        if (arg.getTypeSpec().getType() instanceof UserDefinedType) {
                            collisions.add(new PsiArgumentContext(arg));
                        }
                    }
                }

            }
        }


        String callableType = isFunctionCall ? "Function" : "Procedure";
        switch (collisions.size()) {
            case 0: // error: callable not resolved
                String sign = "";
                try {
                    // create a function signature based on real argument list
                    sign = Formatter.formatSignature(callable);
                    sign = sign.length() > 60 ? sign.substring(0, 66) + " ..." : sign;
                } catch (Throwable e) {
                    throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(), callableType + " not found: " + nodes[0].getText());
                }
                throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(), callableType + " " + sign + " not found");
            case 1: // success
                return collisions.get(0);
            default: // error: resolved more then one
                throw new NameNotResolvedException((PlSqlElement) nodes[0].getPsi(), "Collision on " + callableType + " resolving: " + nodes[0].getText());
        }
    }


    class CallableNameDescImpl implements CallableNameDesc {

        PsiElement schema;
        PsiElement pkg;
        PsiElement exec;

        public CallableNameDescImpl(PsiElement schema, PsiElement pkg, PsiElement exec) {
            this.schema = schema;
            this.pkg = pkg;
            this.exec = exec;
        }

        public PsiElement getSchemaName() {
            return schema;
        }

        public PsiElement getPackageName() {
            return pkg;
        }

        public PsiElement getExecName() {
            return exec;
        }
    }


    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCallableCompositeName(this);
        } else {
            super.accept(visitor);
        }
    }

}
