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

package com.deepsky.lang.plsql.psi.resolve;

import com.deepsky.database.ObjectCache;
import com.deepsky.database.ObjectCacheFactory;
import com.deepsky.integration.PlSqlElementType;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.resolve.collection.TableCollectionItemAccessorCtx;
import com.deepsky.lang.plsql.psi.resolve.collection.VarrayItemAccessorCtx;
import com.deepsky.lang.plsql.psi.utils.Formatter;
import com.deepsky.lang.plsql.psi.utils.PsiTreeHelpers;
import com.deepsky.lang.plsql.struct.*;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.validation.TypeValidationHelper;
import com.deepsky.lang.validation.ValidationException;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ResolveHelper3 {

    @NotNull
    public static ExecutableDescriptor resolveCallable(Callable exec) throws NameNotResolvedException {

        CallableCompositeName ref = exec.getCompositeName();
        CallableCompositeName.CallableNameDesc ndesc = ref.describe();

//        ASTNode schema = ref.getNode().findChildByType(PLSqlTypesAdopted.SCHEMA_NAME);
//        ASTNode tab_ref = ref.getNode().findChildByType(PLSqlTypesAdopted.TABLE_REF);
//        ASTNode column = ref.getNode().findChildByType(PLSqlTypesAdopted.COLUMN_NAME_REF);
        ASTNode schema = (ndesc.getSchemaName() != null) ? ndesc.getSchemaName().getNode() : null;
        ASTNode pkgRef = (ndesc.getPackageName() != null) ? ndesc.getPackageName().getNode() : null;
        ASTNode execName = ndesc.getExecName().getNode();

        boolean isFunctionCall = exec instanceof FunctionCall;

        List<String> errors = new ArrayList<String>();
        List<ExecutableDescriptor> out = new ArrayList<ExecutableDescriptor>();
        CallArgument[] callArgs = exec.getCallArgumentList();

        PackageDescriptor desc = null;
        if (schema != null) {
            // case 1: schema.package.name(....)
            for (ExecutableDescriptor dbo : getExecutableByName(schema.getText(), pkgRef.getText(), execName.getText())) {
                if (dbo instanceof ProcedureDescriptor && exec instanceof ProcedureCall) {
                    if (validateCallArgumentList(dbo, callArgs, errors)) {
                        out.add(dbo);
                    }
                } else if (dbo instanceof FunctionDescriptor && exec instanceof FunctionCall) {
                    if (validateCallArgumentList(dbo, callArgs, errors)) {
                        out.add(dbo);
                    }
                }
            }

        } else if (pkgRef != null) {
            // case 2: package.name(....)
            for (ExecutableDescriptor dbo : getExecutableByName(pkgRef.getText(), execName.getText())) {
                if (!isFunctionCall && dbo instanceof ProcedureDescriptor) {
                    if (validateCallArgumentList(dbo, callArgs, errors)) {
                        out.add(dbo);
                    }
                } else if (isFunctionCall && dbo instanceof FunctionDescriptor) {
                    if (validateCallArgumentList(dbo, callArgs, errors)) {
                        out.add(dbo);
                    }
                }
            }

        } else {
            // case 3: name(....)
            // three cases possible:
            //  1. this is an embedded function
            //  2. function defined outside any package
            //  3. function defined inside the package

            // Check the 3d case first
            PackageBody pkg =
                    (PackageBody) PsiTreeHelpers
                            .findParentNode(
                                    exec,
                                    new PlSqlElementType[]{
                                            PlSqlElementTypes.PACKAGE_BODY}
                            );


            if (pkg != null) {
//                for(PlSqlElement elem: pkg.findObjectByName(execName.getText())){
//                    if (elem instanceof Procedure && exec instanceof ProcedureCall) {
//                        Procedure p = (Procedure) elem;
//                        if (validateCallArgumentList(p.getArgumentList(), exec.getCallArgumentList(), errors)) {
//                            out.add(p.describe());
//                        }
//                    } else if (elem instanceof Function && exec instanceof FunctionCall) {
//                        Function f = (Function) elem;
//                        if (validateCallArgumentList(f.getArgumentList(), exec.getCallArgumentList(), errors)) {
//                            out.add(f.describe());
//                        }
//                    } else if (elem instanceof VariableDecl ) {
//                        VariableDecl var = (VariableDecl) elem;
//                        Type t = var.getType();
//                        TypeSpec ts = var.getTypeSpec();
//                    }
//                }

                Executable[] exec1 = pkg.findExecutableByName(execName.getText());
                for (Executable e : exec1) {
//                    ExecutableDescriptor edesc = e.describe();
                    if (!isFunctionCall && e instanceof Procedure ){ //desc instanceof ProcedureDescriptor) {
                        if (validateCallArgumentList(e.getArgumentList(), callArgs, errors)) {
                            out.add(e.describe());
                        }
                    } else if (isFunctionCall && e instanceof Function){ //desc instanceof FunctionDescriptor) {
                        if (validateCallArgumentList(e.getArgumentList(), callArgs, errors)) {
                            out.add(e.describe());
                        }
                    }
                }
            }

            // Function not defined in the package, so look up among user fumnctions
            DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(
                    ObjectCache.USER_FUNCTION | ObjectCache.USER_PROCEDURE,
                    execName.getText()
            );

            for (DbObject object : objects) {
                if (object instanceof ExecutableDescriptor) {
                    ExecutableDescriptor edesc = (ExecutableDescriptor) object;
                    if (!isFunctionCall && edesc instanceof ProcedureDescriptor) {
                        if (validateCallArgumentList(edesc, callArgs, errors)) {
                            out.add(edesc);
                        }
                    } else if (isFunctionCall && edesc instanceof FunctionDescriptor) {
                        if (validateCallArgumentList(edesc, callArgs, errors)) {
                            out.add(edesc);
                        }
                    }
                }
            }

            // look up among system functions
            for (SystemFunctionDescriptor sdesc : ObjectCacheFactory.getObjectCache().findSystemFunction(execName.getText())) {
                if (sdesc.getValidator().validate(callArgs, errors)) {
                    out.add(sdesc);
                }
            }
        }

        String callableType = isFunctionCall ? "Function" : "Procedure";
        switch (out.size()) {
            case 0: // error: callable not resolved
                String sign = "";
                try {
                    // create a function signature based on real argument list
                    sign = Formatter.formatSignature(exec);
                    sign = sign.length() > 60 ? sign.substring(0, 66) + " ..." : sign;
                } catch (Throwable e) {
                    throw new NameNotResolvedException((PlSqlElement) execName.getPsi(), callableType + " not found: " + execName.getText());
                }
                throw new NameNotResolvedException((PlSqlElement) execName.getPsi(), callableType + " " + sign + " not found");
            case 1: // success
                return out.get(0);
            default: // error: resolved more then one
                throw new NameNotResolvedException((PlSqlElement) execName.getPsi(), "Collision on " + callableType + " resolving: " + execName.getText());
        }

    }


    public static ResolveContext777[] resolveCollectionCall(Callable callable) {
        final String fname = callable.getFunctionName();
        final List<ResolveContext777> ctx = new ArrayList<ResolveContext777>();

        final UsageContext uctx = callable.getUsageContext();
        // 2. the name of variable or argument (usage: function/procedure definition)
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new ExecCtxDeclarationEnumerator() {
            public void handleArgument(Argument arg) {
                if (arg.getArgumentName().equalsIgnoreCase(fname)) {
                    Type t = arg.getType();
                    if (t instanceof UserDefinedType) {
//                        ctx.add( new PsiArgumentContext(arg));
                        UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) t, uctx);
                        if (tdesc instanceof TableCollectionDescriptor) {
                            TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                            ctx.add(new TableCollectionItemAccessorCtx(arg, cdesc));
                        } else if( tdesc instanceof VarrayCollectionDescriptor){
                            VarrayCollectionDescriptor vdesc = (VarrayCollectionDescriptor) tdesc;
                            ctx.add(new VarrayItemAccessorCtx(arg, vdesc));
                        }
                    }
                }
            }

            public void handleDecl(Declaration decl) {
                if (decl instanceof VariableDecl) {
                    VariableDecl var = (VariableDecl) decl;
                    if (var.getDeclName().equalsIgnoreCase(fname)) {
                        Type t = var.getType();
                        if (t instanceof UserDefinedType) {
//                            ctx.add( new PsiVariableContext(var));
                            UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) t, uctx);
                            if (tdesc instanceof TableCollectionDescriptor) {
                                TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                                ctx.add(new TableCollectionItemAccessorCtx(var, cdesc));
                            } else if( tdesc instanceof VarrayCollectionDescriptor){
                                VarrayCollectionDescriptor vdesc = (VarrayCollectionDescriptor) tdesc;
                                ctx.add(new VarrayItemAccessorCtx(var, vdesc));
                            }
                        }
                    }
                }
            }
        });

        runner.add(new PackageTriggerHandler() {
            @Override
            public void handleTriggerBody(CreateTrigger trigger) {
            }

            @Override
            public void handlePackageBody(PackageBody pkg) {
                for (PlSqlElement o : pkg.findObjectByName(fname)) {
                    if (o instanceof VariableDecl ){ //&& ((VariableDecl) o).getVariableName().equalsIgnoreCase(text)) {
                        VariableDecl var = (VariableDecl) o;
                        Type t = var.getType();
                        if (t instanceof UserDefinedType) {
//                            ctx.add( new PsiVariableContext(var));

                            UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) t, uctx);
                            if (tdesc instanceof TableCollectionDescriptor) {
                                TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                                ctx.add(new TableCollectionItemAccessorCtx(var, cdesc));
                            } else if( tdesc instanceof VarrayCollectionDescriptor){
                                VarrayCollectionDescriptor vdesc = (VarrayCollectionDescriptor) tdesc;
                                ctx.add(new VarrayItemAccessorCtx(var, vdesc));
                            }
                        }
                    }
                }
            }

            @Override
            public void handlePackageSpec(PackageSpec pkg) {
                for (PlSqlElement o : pkg.findObjectByName(fname)) {
                    if (o instanceof VariableDecl ){ //&& ((VariableDecl) o).getVariableName().equalsIgnoreCase(text)) {
                        VariableDecl var = (VariableDecl) o;
                        Type t = var.getType();
                        if (t instanceof UserDefinedType) {
//                            ctx.add( new PsiVariableContext(var));

                            UserDefinedTypeDescriptor tdesc = ResolveHelper.resolve_Type((UserDefinedType) t, uctx);
                            if (tdesc instanceof TableCollectionDescriptor) {
                                TableCollectionDescriptor cdesc = (TableCollectionDescriptor) tdesc;
                                ctx.add(new TableCollectionItemAccessorCtx(var, cdesc));
                            } else if( tdesc instanceof VarrayCollectionDescriptor){
                                VarrayCollectionDescriptor vdesc = (VarrayCollectionDescriptor) tdesc;
                                ctx.add(new VarrayItemAccessorCtx(var, vdesc));
                            }
                        }
                    }
                }
            }
        });

        runner.process(callable.getNode());
        return ctx.toArray(new ResolveContext777[ctx.size()]);
    }


    static private ExecutableDescriptor[] getExecutableByName(String schema, String packageName, String execName) throws NameNotResolvedException {
        List<ExecutableDescriptor> out = new ArrayList<ExecutableDescriptor>();
        DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(schema, ObjectCache.PACKAGE, packageName);

        if (objects.length == 0) {
            throw new NameNotResolvedException("Package with name '" + packageName + "' not found");
        }
        for (DbObject dbo : objects) {
            PackageDescriptor pdesc = (PackageDescriptor) dbo;
            for (DbObject d : pdesc.findObjectByName(execName)) {
                if (d instanceof ExecutableDescriptor) {
                    // add item to "out" with filtering of duplicates
                    addDescriptorWithFiltering(out, (ExecutableDescriptor) d);
                }
            }
        }
        objects = ObjectCacheFactory.getObjectCache().findByNameForType(schema, ObjectCache.PACKAGE_BODY, packageName);
        for (DbObject dbo : objects) {
            PackageDescriptor pdesc = (PackageDescriptor) dbo;
            for (DbObject d : pdesc.findObjectByName(execName)) {
                if (d instanceof ExecutableDescriptor) {
                    // add item to "out" with filtering of duplicates
                    addDescriptorWithFiltering(out, (ExecutableDescriptor) d);
                }
            }
        }

        return out.toArray(new ExecutableDescriptor[out.size()]);
    }

    static private ExecutableDescriptor[] getExecutableByName(String packageName, String execName) throws NameNotResolvedException {
        List<ExecutableDescriptor> out = new ArrayList<ExecutableDescriptor>();
        DbObject[] objects = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.PACKAGE, packageName);

        if (objects.length == 0) {
            throw new NameNotResolvedException("Package with name '" + packageName + "' not found");
        }
        for (DbObject dbo : objects) {
            PackageDescriptor pdesc = (PackageDescriptor) dbo;
            for (DbObject d : pdesc.findObjectByName(execName)) {
                if (d instanceof ExecutableDescriptor) {
                    // add item to "out" with filtering of duplicates
                    addDescriptorWithFiltering(out, (ExecutableDescriptor) d);
                }
            }
        }
        objects = ObjectCacheFactory.getObjectCache().findByNameForType(ObjectCache.PACKAGE_BODY, packageName);
        for (DbObject dbo : objects) {
            PackageDescriptor pdesc = (PackageDescriptor) dbo;
            for (DbObject d : pdesc.findObjectByName(execName)) {
                if (d instanceof ExecutableDescriptor) {
                    // add item to "out" with filtering of duplicates
                    addDescriptorWithFiltering(out, (ExecutableDescriptor) d);
                }
            }
        }

        return out.toArray(new ExecutableDescriptor[out.size()]);
    }


    static private void addDescriptorWithFiltering(List<ExecutableDescriptor> l, ExecutableDescriptor what) {
        for (ExecutableDescriptor e : l) {
            if (e.signatureEquals(what)) {
                // attempt to add a duplicate, skip adding 
                return;
            }
        }
        l.add(what);
    }

    static public boolean argumentsEquals(Argument[] args1, Argument[] args2) {
        if (args1.length != args2.length) {
            return false;
        } else {
            for (Argument a : args1) {
                Argument arg = findArgumentByName(a.getArgumentName(), args2);
                if (arg == null) {
                    return false;
                } else {
                    // compare arguments
                    if (a.getType().equals(arg.getType()) && a.isIn() == arg.isIn() && a.isOut() == arg.isOut()) {
                        // good
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }


    static private Argument findArgumentByName(String name, Argument[] args) {
        for (Argument a : args) {
            if (a.getArgumentName().equals(name)) {
                return a;
            }
        }

        return null;
    }

    /**
     * Argument List validation: Validate formal argument types vs real ones
     *
     * @param edesc
     * @param args
     * @param errors
     * @return - result of the validation
     */
    static public boolean validateCallArgumentList(ExecutableDescriptor edesc, CallArgument[] args, List<String> errors) {

        if (args.length < edesc.numberOfReqArguments()) {
            errors.add("Arguments less then expected");
            return false;
        } else if (args.length > edesc.getArgumentNames().length) {
            errors.add("Too many arguments");
            return false;
        }

        if (args.length == 0) {
            if (edesc.getArgumentNames().length == 0) {
                // it's ok
            } else {
                // there is possible a case when all of arguments have default values, check it out ...
                for (String name : edesc.getArgumentNames()) {
                    if (!edesc.hasDefault(name)) {
                        // Error: mismatch number of formal and real parameters!
                        errors.add("Function: " + "!!!!" + ", number of formal and real parameters mismatched");
                        return false;
                    }
                }
            }
        } else {
            for (int i = 0; i < args.length; i++) {
                CallArgument arg = args[i];
                String var = arg.getVariableName();
                if (var != null) {
                    // binding parameter by name
                    Type left = edesc.getArgumentType(var);
                    if (left == null) {
                        errors.add("Parameter with name not found: " + var);
                        return false;
                    }
                    try {
                        Type right = arg.getExpression().getExpressionType();
//                        if (right instanceof UserDefinedType && !((UserDefinedType)right).isFQN()) {
//                            UserDefinedType udt = (UserDefinedType) right;
//                            udt.setDefinitionPackage(arg.getUsageContext().getPackageName());
//                        }
                        if (!TypeValidationHelper.canBeAssigned(left, right)) {
                            errors.add("Formal type and real type mismatched, parameter: " + i);
                            return false;
                        }
                    } catch (ValidationException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (TypeNotResolvedException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (Throwable e) {
                        int hh = 0;
                    }
                    /*
                    // TODO - there is no need to check real agrument type in case of "binding parameters by name"
                    else if (!TypeValidationHelper.canBeAssigned(
                            t.typeId(),
                            arg.getExpression().getExpressionType().typeId())) {
                        errors.add("Formal type and real type mismatched, parameter: " + i);
                        return false;
                    }   */
                } else {
                    // ... by position
                    String name = edesc.getArgumentName(i);
                    if (name == null) {
                        errors.add("Function: " + "!!!!!" + ", number of formal and real parameters mismatched");
                        return false;
                    }
                    Type t = edesc.getArgumentType(name);
                    try {
                        Type t1 = arg.getExpression().getExpressionType();
                        if (t1 == null || t == null) {
                            int hh = 0;
                        }
                        if (!TypeValidationHelper.canBeAssigned(t, t1)) {
                            errors.add("Function: " + "!!!!!" + ", type of argument does not fit a formal parameter, position: " + (i + 1));
                            return false;
                        }
                    } catch (ValidationException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (TypeNotResolvedException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (Throwable e) {
                        int hh = 0;
                    }
                }
            }
        }

        return true;
    }


    static public boolean validateCallArgumentList(ArgumentList argList, CallArgument[] args, List<String> errors) {

        if (argList == null) {
            return args == null || args.length == 0;
        }

        Argument[] aa = argList.getArguments();

        if (args.length < argList.numberOfReqArguments()) {
            errors.add("Arguments less then expected");
            return false;
        } else if (args.length > aa.length) {
            errors.add("Too many arguments");
            return false;
        }

        if (args.length == 0) {
            if (aa.length == 0) {
                // it's ok
            } else {
                // there is possible a case when all of arguments have default values, check it out ...
                for (Argument a : aa) {
                    //String name
                    if (a.getDefaultExpr() == null) { //!edesc.hasDefault(name)) {
                        // Error: mismatch number of formal and real parameters!
                        errors.add("Function: " + "!!!!" + ", number of formal and real parameters mismatched");
                        return false;
                    }
                }
            }
        } else {
            for (int i = 0; i < args.length; i++) {
                CallArgument arg = args[i];
                String var = arg.getVariableName();
                if (var != null) {
                    // binding parameter by name
                    Argument a = argList.getArgumentByName(var);
                    if (a == null) {
                        errors.add("Parameter with name not found: " + var);
                        return false;
                    }
                    Type t = a.getType(); //edesc.getArgumentType(var);
//                    if (t == null) {
//                        errors.add("Parameter with name not found: " + var);
//                        return false;
//                    }
                    try {
                        Type t1 = arg.getExpression().getExpressionType();
                        if (t1 == null) {
                            int hh = 0;
                        }
                        if (!TypeValidationHelper.canBeAssigned(t, t1)) {
                            errors.add("Formal type and real type mismatched, parameter: " + i);
                            return false;
                        }
                    } catch (ValidationException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (TypeNotResolvedException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (Throwable e) {
                        int hh = 0;
                    }
                    /*
                    // TODO - there is no need to check real agrument type in case of "binding parameters by name"
                    else if (!TypeValidationHelper.canBeAssigned(
                            t.typeId(),
                            arg.getExpression().getExpressionType().typeId())) {
                        errors.add("Formal type and real type mismatched, parameter: " + i);
                        return false;
                    }   */
                } else {
                    // ... by position
//                    String name = edesc.getArgumentName(i);
//                    if (name == null) {
//                        errors.add("Function: " + "!!!!!" + ", number of formal and real parameters mismatched");
//                        return false;
//                    }
//                    Type t = edesc.getArgumentType(name);
                    Type t = argList.getArgumentByPos(i).getType();
                    try {
                        Type t1 = arg.getExpression().getExpressionType();
                        if (t1 == null || t == null) {
                            int hh = 0;
                        }
                        if (!TypeValidationHelper.canBeAssigned(t, t1)) {
                            errors.add("Function: " + "!!!!!" + ", type of argument does not fit a formal parameter, position: " + (i + 1));
                            return false;
                        }
                    } catch (ValidationException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (TypeNotResolvedException e) {
                        // exception on type discovering ... lets pretend that checks pass
                        continue;
                    } catch (Throwable e) {
                        int hh = 0;
                    }
                }
            }
        }

        return true;
    }
}
