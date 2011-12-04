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

package com.deepsky.lang.plsql.resolver;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ref.SequenceRef;
import com.deepsky.lang.plsql.psi.ref.TableRef;
import com.deepsky.lang.plsql.resolver.factory.PlSqlElementLocator;
import com.deepsky.lang.plsql.resolver.index.IndexEntriesWalkerInterruptable;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.index.TreeNode;
import com.deepsky.lang.plsql.resolver.utils.*;
import com.deepsky.lang.plsql.sqlIndex.AbstractSchema;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.types.TableColumnRefType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.validation.ValidationException;
import com.deepsky.utils.StringUtils;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Computable;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResolveFacadeImpl implements ResolveFacade {

    protected ResolveHelper resolver;
    protected AbstractSchema index;

    public ResolveFacadeImpl(AbstractSchema index) {
        this.index = index;
        this.resolver = index.getResolveHelper();
        if (this.resolver == null) {
            throw new ConfigurationException("Resolver not available: " + index.getName());
        }
    }


    public AbstractSchema getSimpleIndex() {
        return index;
    }

    public Type resolveType(Expression expression) {
        if (expression instanceof ObjectReference) {
            ObjectReference ref = (ObjectReference) expression;

            ResolveDescriptor rhlp = resolve(ref);
            return rhlp.getType();
        } else if (expression instanceof CollectionMethodCall) {
            CollectionMethodCall ref = (CollectionMethodCall) expression;
            return resolve(ref).getType();
        } else if (expression instanceof FunctionCall) {
            FunctionCall call = (FunctionCall) expression;
            return resolve(call).getType();
        }

        throw new ValidationException("Not supported: " + expression.getClass().getName());
//        return resolver.resolveType(expression);
    }

    protected ResolveDescriptor resolve(ObjectReference node) {
        String path = node.getCtxPath1().getPath();
        int refType = node.isPlSqlVarRef() ? ContextPath.PLSQL_NAME_REF : ContextPath.GENERIC_NAME_REF;
        RefHolder ref = new RefHolder(refType, path, node.getText());
        ResolveDescriptor rhlp = resolver.resolveReference(ref);
        if (rhlp == null) {
            throw new ValidationException("Cannot resolve object reference: " + node.getText());
        } else {
            return rhlp;
        }
    }


    public ResolveDescriptor resolve(FunctionCall call) {
        String path = call.getCtxPath1().getPath();
        CallArgument[] args = call.getCallArguments();
        ResolveDescriptor rhlp = resolver.resolveCallable(
                ContextPath.FUNC_CALL, path, call.getCompositeName().getText(), args
        );

        if (rhlp != null) {
            return rhlp;
        }

        throw new ValidationException("Cannot resolve function call: " + call.getText());
    }

    public ResolveDescriptor resolve(CollectionMethodCall elem) {

        ResolveDescriptor hlp = ResolveUtil.resolve(elem, new NameFragmentCallback() {
            //            public ResolveDescriptor resolve(ResolveDescriptor hlp, CompositeElementExt node) {
            public ResolveDescriptor resolve(ResolveDescriptor hlp, PlSqlElement node) {
                if (node.getNode().getElementType() == PlSqlElementTypes.NAME_FRAGMENT) {
                    if (hlp != null) {
                        return resolver.resolveReference(hlp, node.getText());
                    } else {
                        RefHolder rholder = new RefHolder(
                                ContextPath.GENERIC_NAME_REF, node.getCtxPath1().getPath(), node.getText());
                        return resolver.resolveReference(rholder);
                    }
                } else if (node.getNode().getElementType() == PlSqlElementTypes.EXEC_NAME_REF) {
                    ExecNameRef execName = (ExecNameRef) node; //(ExecNameRef) node.getPsi();
                    CallArgument[] args = execName.getCallArgumentList();
                    return resolver.resolveCallable(hlp,
                            ContextPath.FUNC_CALL, node.getCtxPath1().getPath(), node.getText(), args);
                } else if (node.getNode().getElementType() == PlSqlElementTypes.C_RECORD_ITEM_REF) {
                    if (hlp != null) {
                        return resolver.resolveReference(hlp, node.getText());
                    } else {
                        RefHolder rholder = new RefHolder(
                                ContextPath.GENERIC_NAME_REF, node.getCtxPath1().getPath(), node.getText());
                        return resolver.resolveReference(rholder);
                    }
                }
                return null;
            }
        });

        if (hlp == null)
            throw new ValidationException("Cannot resolve collection method call: " + elem.getText());

        return hlp; //!= null? locatePsiElement(ref.getProject(), hlp): null;
    }


    public Type resolveType(TableColumnRefType t) {
        return resolver.resolveType(t);
    }

    public PsiElement resolveCallable(Callable callable) {
        int ctype = callable instanceof FunctionCall ? ContextPath.FUNC_CALL : ContextPath.PROC_CALL;
        String ctxType = callable.getCtxPath1().getPath();
        ResolveDescriptor rhlp = resolver.resolveCallable(
                ctype, ctxType, callable.getFunctionName(), callable.getCallArguments()
        );

        return rhlp != null ? locatePsiElement(callable, rhlp) : null;
    }

    public PsiElement[] resolveCallableWithOverloads(Callable callable) {
        int ctype = callable instanceof FunctionCall ? ContextPath.FUNC_CALL : ContextPath.PROC_CALL;
        String ctxType = callable.getCtxPath1().getPath();
        ResolveDescriptor[] rhlps = resolver.resolveCallable(
                ctype, ctxType, callable.getFunctionName()
        );

        PsiElement[] out = new PsiElement[rhlps.length];
        for (int i = 0; i < rhlps.length; i++) {
            out[i] = locatePsiElement(callable, rhlps[i]);
        }
        return out;
    }

    public PsiElement resolveTable(TableRef table) {
//        String path = ((CompositeElementExt) table.getNode()).getCtxPath1().getPath();
        String path = table.getCtxPath1().getPath();
        String text = table.getText();

        RefHolder ref = new RefHolder(ContextPath.TABLE_REF, path, StringUtils.discloseDoubleQuotes(text));
        //ResolveHelper rhlp = resolver.resolveReference(ref);
        ResolveDescriptor[] rhlp = resolver.resolveReferencePoly(ref);
        if (rhlp.length == 1) {
            return locatePsiElement(table, rhlp[0]);
        } else if (rhlp.length == 0) {
            return null;
        } else {
            // resolved more then to one reference, additional resolving needed
            return null; //resolvingPolyRef(table.getProject(), rhlp);
        }
    }

    public PsiElement resolveSequence(SequenceRef ref) {
        String text = ref.getSequenceName();

        RefHolder holder = new RefHolder(ContextPath.SEQUENCE_REF, "", StringUtils.discloseDoubleQuotes(text));
        ResolveDescriptor rhlp = resolver.resolveReference(holder);
        if (rhlp != null) {
            return locatePsiElement(ref, rhlp);
        }
        return null;
    }


    public PsiElement resolveObjectReference(ObjectReference ref) {
        String path = ref.getCtxPath1().getPath();
        String text = ref.getText();

        int refType = ref.isPlSqlVarRef() ? ContextPath.PLSQL_NAME_REF : ContextPath.GENERIC_NAME_REF;
        RefHolder holder = new RefHolder(refType, path, text);
        ResolveDescriptor out = resolver.resolveReference(holder);
        if (out != null) {
            return locatePsiElement(ref, out);
        }

        return null;
    }

    public PsiElement resolveColumnNameRef(ColumnNameRef ref) {
        ResolveDescriptor out = ResolveUtil.resolveColumnNameRef(resolver, ref);
        if (out != null) {
            return locatePsiElement(ref, out);
        }

        return null;
    }

    public PsiElement resolveCRecordItemRef(CRecordItemRef ref) {
        CollectionMethodCall parent = (CollectionMethodCall) ref.getParent();
        try {
            ResolveDescriptor rhlp = resolve(parent);
            return locatePsiElement(ref.getProject(), rhlp);
        } catch (ValidationException e) {
            return null;
        }
    }

/*
    private PsiElement resolvingPolyRef(Project project, ResolveDescriptor[] rhlp) {
        // 1. get native domain
        DbUrl dbUrl = resolver.getSqlIndex().getUID();
        // 2. compare with resolvers' domains, choose resolver with appropriate domain
        List<ResolveDescriptor> filtered = new ArrayList<ResolveDescriptor>();
        for (ResolveDescriptor r : rhlp) {
            if (r.getUserName().equalsIgnoreCase(dbUrl.getUser())) {
                filtered.add(r);
            }
        }

        // 3. if several resolvers are matched or none - do more resolutions
        if (filtered.size() == 1) {
            return locatePsiElement(project, filtered.get(0));
        } else {
            // todo ... implement me
        }

        return null;
    }

    private PsiElement resolvingPolyRef(Project project, List<ResolveDescriptor> rhlp) {
        // 1. get native domain
        DbUrl dbUrl = resolver.getSqlIndex().getUID();
        // 2. compare with resolvers' domains, choose resolver with appropriate domain
        List<ResolveDescriptor> filtered = new ArrayList<ResolveDescriptor>();
        for (ResolveDescriptor r : rhlp) {
            if (r.getUserName().equalsIgnoreCase(dbUrl.getUser())) {
                filtered.add(r);
            }
        }

        // 3. if several resolvers are matched or none - do more resolutions
        if (filtered.size() == 1) {
            return locatePsiElement(project, filtered.get(0));
        } else {
            // todo ... implement me
        }

        return null;
    }
*/

    public PsiElement resolveFragment(NameFragmentRef fragment) {
        PsiElement parent = fragment.getParent();

        if (parent instanceof ObjectReference
                || parent instanceof ColumnSpec) {
            // ObjectReference
//            String path = ((CompositeElementExt) parent.getNode()).getCtxPath1().getPath();
            String path = ((PlSqlElement) parent).getCtxPath1().getPath();
            String text = parent.getText();

            int refType = ContextPath.GENERIC_NAME_REF;
            if (parent instanceof ObjectReference) {
                refType = ((ObjectReference) parent).isPlSqlVarRef() ? ContextPath.PLSQL_NAME_REF : ContextPath.GENERIC_NAME_REF;
            }
//            int refType = ((ObjectReference) parent).isPlSqlVarRef() ? ContextPath.PLSQL_NAME_REF : ContextPath.GENERIC_NAME_REF;
            RefHolder ref = new RefHolder(refType, path, text);
            ResolveDescriptor[] out = resolver.resolveFrontPoly(ref);

            int nameIndex = ((CompositeName) parent).getFragmentIndex(fragment);
            if (nameIndex == 0) {
                // the first part of the name
                switch (out.length) {
                    case 0:
                        return null;
                    case 1:
                        return locatePsiElement(fragment, out[0]);
                    default:
                        return null;//resolvingPolyRef(fragment.getProject(), out);
                }
            } else {
                // the name consists of several parts and the choosen part is not the first in the name
                List<ResolveDescriptor> out2 = new ArrayList<ResolveDescriptor>();
                for (ResolveDescriptor r : out) {
                    for (int cnt = 1; cnt <= nameIndex; cnt++) {
                        String text1 = ((CompositeName) parent).getFragmentByPos(cnt).getText();
                        ResolveDescriptor[] out1 = r.resolve(text1.toLowerCase());
                        out2.addAll(Arrays.asList(out1));
                    }
                }
                // final reference resolving
                switch (out2.size()) {
                    case 0:
                        return null;
                    case 1:
                        return locatePsiElement(fragment, out2.get(0));
                    default:
                        return null; //resolvingPolyRef(fragment.getProject(), out2);
                }
            }

        } else if (parent instanceof CallableCompositeName) {
            // CallableCompositeName
            Callable callable = ((CallableCompositeName) parent).getCallable();
            String path = callable.getCtxPath1().getPath();
            int callType = callable instanceof FunctionCall ? ContextPath.FUNC_CALL : ContextPath.PROC_CALL;
            RefHolder rholder = new RefHolder(callType, path, callable.getFunctionName());
            int nameIndex = ((CompositeName) parent).getFragmentIndex(fragment);
            try {
                CallArgument[] args = callable.getCallArguments();
                ResolveDescriptor rhlp = resolver.resolveCallable(rholder, args, nameIndex);
                return rhlp != null ? locatePsiElement(fragment, rhlp) : null;

            } catch (ValidationException e) {
                //failed++;
                System.out.println("Cannot resolve: " + rholder);
            } catch (Throwable e) {
                //failed++;
                System.out.println("Cannot resolve: " + rholder);
            }

        } else if (parent instanceof TypeNameReference) {
            // TypeNameReference
            // todo -- does not work for the type name consists of several parts
//            String path = ((CompositeElementExt) parent.getNode()).getCtxPath1().getPath();
            String path = ((TypeNameReference) parent).getCtxPath1().getPath();
            Type t = ((TypeNameReference) parent).getType();

            UserDefinedType udt = (UserDefinedType) t;
            RefHolder ref = new RefHolder(ContextPath.TYPE_REF, path, udt.typeName());
            ResolveDescriptor rhlp = resolver.resolveReference(ref);
            if (rhlp == null) {
//                System.out.println("Cannot resolve: " + ref);
            } else {
                return locatePsiElement(fragment, rhlp);
            }

        } else {
            // todo
            // TriggerColumnNameRef
            // ParameterReference
        }

        return null;
    }


    private String findPackageSpecCtxPath(IndexTree itree, final String packageName) {
        final String[] targetCtx = {null};
        itree.iterateTopNodes(packageName, new IndexEntriesWalkerInterruptable() {
            public boolean process(String ctxPath, String value) {
                ContextPathUtil.CtxPathParser p = new ContextPathUtil.CtxPathParser(ctxPath);
                if (p.extractLastCtxType() == ContextPath.PACKAGE_SPEC) {
                    if (p.lastCtxName().equals(packageName)) {
                        targetCtx[0] = ctxPath;
                        return false;
                    }
                }
                return true;
            }
        });

        return targetCtx[0];
    }

    public PackageSpec findPackageSpecification(PackageBody body) {
        IndexTree itree = index.getIndexTree();
        String ctxPath = findPackageSpecCtxPath(itree, body.getPackageName().toLowerCase());

        return ctxPath != null ? (PackageSpec) locatePsiElement(body, ctxPath) : null;
    }


    private boolean signEquals(Executable exec, String value) {
        ArgumentSpec[] args = ContextPathUtil.extractArguments(value);
        String v = ContextPathUtil.encodeArguments(exec.getArguments());
        return ContextPathUtil.argumentEquals(args, ContextPathUtil.extractArguments(v));
    }

    public ExecutableSpec findSpecificationFor(Executable exec) {

        final IndexTree itree = index.getIndexTree();
        String ctxPath = exec.getCtxPath1().getPath();
        ContextPathUtil.CtxPathParser p = new ContextPathUtil.CtxPathParser(ctxPath);
        String parentCtx = p.getParentCtx();
        String specCtxPath = null;
        if (parentCtx != null) {
            final String execName = exec.getEName().toLowerCase();
            ContextPathUtil.CtxPathParser pp = new ContextPathUtil.CtxPathParser(parentCtx);
            if (pp.extractLastCtxType() == ContextPath.PACKAGE_BODY) {
                String pCtxPath = findPackageSpecCtxPath(itree, pp.lastCtxName().toLowerCase());
                if (pCtxPath != null) {
                    TreeNode[] nodes = itree.findNodeInContext(pCtxPath, execName);
                    for (TreeNode n : nodes) {
                        if (n.getType() == ContextPath.FUNCTION_SPEC && exec instanceof Function) {
                            Type t = ContextPathUtil.extractRetType(n.getValue());
                            if (((Function) exec).getReturnType().equals(t) && signEquals(exec, n.getValue())) {
                                specCtxPath = n.getPath();
                                break;
                            }
                        } else if (n.getType() == ContextPath.PROCEDURE_SPEC && exec instanceof Procedure) {
                            if (signEquals(exec, n.getValue())) {
                                specCtxPath = n.getPath();
                                break;
                            }
                        }
                    }
                }
            }
        }

        return specCtxPath != null ? (ExecutableSpec) locatePsiElement(exec, specCtxPath) : null;
    }

    public ResolveHelper getLLResolver() {
        return resolver;
    }


    public boolean callArgumentListMatchesArgumentList(CallArgumentList callArgs, ArgumentList argList) {
        List<String> errors = new ArrayList<String>();
        new CallArgumentResolver(resolver).validate(argList, callArgs.getArguments(), errors);
        return errors.size() == 0;
    }

    protected PsiElement locatePsiElement(final Project project, @NotNull ResolveDescriptor rhlp) {
        String filePath = index.getSourceFile(rhlp).getPath();
        if (filePath != null) {
            final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            for (VirtualFile v : fileEditorManager.getOpenFiles()) {
                if (v.getPath().equals(filePath)) {
                    //
                    PsiFile file = PsiManager.getInstance(project).findFile(v);
                    if (file != null) {
                        return new PlSqlElementLocator().locate(file, rhlp.getCtxPath());
                    }
                    return file;
                }
            }

            final VirtualFile vf = index.getSourceFile(rhlp);
            if (vf != null) {
                // todo -- the case for system functions, etc - is there a way to fix it?
                PsiFile file = ApplicationManager.getApplication().runReadAction(new Computable<PsiFile>() {
                    public PsiFile compute() {
                        return PsiManager.getInstance(project).findFile(vf);
                    }
                });

                if (file != null) {
                    return new PlSqlElementLocator().locate(file, rhlp.getCtxPath());
                }
            }
        }

        return null;
    }


    protected PsiElement locatePsiElement(Project project, @NotNull String ctxPath) {
        String filePath = index.getSourceFile(ctxPath).getPath();
        if (filePath != null) {
            final FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
            for (VirtualFile v : fileEditorManager.getOpenFiles()) {
                if (v.getPath().equals(filePath)) {
                    //
                    PsiFile file = PsiManager.getInstance(project).findFile(v);
                    if (file != null) {
                        return new PlSqlElementLocator().locate(file, ctxPath);
                    }
                    return file;
                }
            }

            VirtualFile vf = index.getSourceFile(ctxPath); //SqlFile(ctxPath);
            PsiFile file = PsiManager.getInstance(project).findFile(vf);
            if (file != null) {
                return new PlSqlElementLocator().locate(file, ctxPath);
            }
        }

        return null;
    }

    protected PsiElement locatePsiElement(@NotNull PlSqlElement target, @NotNull String ctxPath) {
        String fPath = ContextPathUtil.extractFilePath(target.getCtxPath1().getPath());
        if (fPath.equals(ContextPathUtil.extractFilePath(ctxPath))) {
            return new PlSqlElementLocator().locate(target.getContainingFile(), ctxPath);
        } else { //if (filePath != null) {
            return locatePsiElement(target.getProject(), ctxPath);
        }
    }

    protected PsiElement locatePsiElement(@NotNull PlSqlElement target, @NotNull ResolveDescriptor rhlp) {
        String fPath = ContextPathUtil.extractFilePath(target.getCtxPath1().getPath());
        if (fPath.equals(ContextPathUtil.extractFilePath(rhlp.getCtxPath()))) {
            return new PlSqlElementLocator().locate(target.getContainingFile(), rhlp.getCtxPath());
        } else {
            return locatePsiElement(target.getProject(), rhlp);
        }
    }

}
