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

package com.deepsky.lang.plsql.resolver.factory;

import com.deepsky.lang.plsql.psi.CallArgument;
import com.deepsky.lang.plsql.psi.Callable;
import com.deepsky.lang.plsql.psi.FunctionCall;
import com.deepsky.lang.plsql.resolver.*;
import com.deepsky.lang.plsql.resolver.helpers.CollectionResolveHelper;
import com.deepsky.lang.plsql.resolver.helpers.ExecutableResolveHelper;
import com.deepsky.lang.plsql.resolver.helpers.PackageResolveHelper;
import com.deepsky.lang.plsql.resolver.utils.CallArgumentResolver;
import com.deepsky.lang.plsql.sqlIndex.ResolveHelper;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.struct.types.TableColumnRefType;
import com.deepsky.lang.plsql.struct.types.UserDefinedType;
import com.deepsky.lang.validation.ValidationException;
import com.deepsky.utils.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResolveHelperImpl implements ResolveHelper {

    private static final CallArgument[] EMPTY_ARGS = new CallArgument[0];

    RefResolver refResolver;
    long resolveTime;
    volatile int locked = 0;

    public ResolveHelperImpl(RefResolver refResolver) { //}, SqlDomainIndex sqlIndex) {
        this.refResolver = refResolver;
    }

    public long getSpentTime() {
        return resolveTime;
    }

    public void clearStatistics() {
        resolveTime = 0;
    }

    public ResolveDescriptor resolveCallable(int callableType, String path, String callableName, CallArgument[] args) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            List<ResolveDescriptor> filtered = new ArrayList<ResolveDescriptor>();
            RefHolder rholder = new RefHolder(callableType, path, callableName);
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            refResolver.resolve(out, rholder);
            if (out.size() != 0) {
                // check real arguments against formal
                CallArgumentResolver validator = new CallArgumentResolver(this);
                for (ResolveDescriptor rhlp : out) {
                    ResolveDescriptor response = dispatcher(rhlp, validator, args);
                    if (response != null) {
                        filtered.add(response);
                    }
                }
            }

            switch (filtered.size()) {
                case 0:
                    return null;
                case 1:
                    return filtered.get(0);
                case 2: {
                    // choose specification
//                    return chooseSpec(filtered);
                    return chooseSpec(filtered.get(0), filtered.get(1));
                }
                default:
                    return null;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    @NotNull
    public ResolveDescriptor[] resolveCallable(int callableType, String path, String callableName) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder rholder = new RefHolder(callableType, path, callableName);
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            refResolver.resolve(out, rholder);
            if (out.size() != 0) {
                // separate specification from body
                List<ResolveDescriptor> specs = new ArrayList<ResolveDescriptor>();
                List<ResolveDescriptor> bodies = new ArrayList<ResolveDescriptor>();

                for (ResolveDescriptor d : out) {
                    if (d instanceof ExecutableResolveHelper) {
                        ExecutableResolveHelper ehlp = (ExecutableResolveHelper) d;
                        if (ehlp.isBody()) {
                            bodies.add(ehlp);
                        } else {
                            specs.add(ehlp);
                        }
                    }
                }

                if (specs.size() == 0) {
                    return bodies.toArray(new ResolveDescriptor[bodies.size()]);
                } else {
                    return specs.toArray(new ResolveDescriptor[specs.size()]);
                }
            }
            return new ResolveDescriptor[0];
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public ResolveDescriptor resolveCallable(Callable callable) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            return resolveCallable(
                    callable instanceof FunctionCall ? ContextPath.FUNC_CALL : ContextPath.PROC_CALL,
                    callable.getCtxPath1().getPath(),
                    callable.getFunctionName(),
                    callable.getCallArguments());
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }


    public ResolveDescriptor resolveCallable(ResolveDescriptor ctx, int callableType, String path, String callableName, CallArgument[] args) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            List<ResolveDescriptor> filtered = new ArrayList<ResolveDescriptor>();

            if (ctx != null) {
                out = Arrays.asList(ctx.resolve(callableType, callableName.toLowerCase()));
            } else {
                RefHolder rholder = new RefHolder(callableType, path, callableName.toLowerCase());
                refResolver.resolve(out, rholder);
            }
            if (out.size() != 0) {
                // check real arguments against formal
                CallArgumentResolver validator = new CallArgumentResolver(this);
                for (ResolveDescriptor rhlp : out) {
                    ResolveDescriptor response = dispatcher(rhlp, validator, args);
                    if (response != null) {
                        filtered.add(response);
                    }
                }
            }

            switch (filtered.size()) {
                case 0:
                    return null;
                case 1:
                    return filtered.get(0);
                case 2: {
                    // choose specification
//                    return chooseSpec(filtered);
                    return chooseSpec(filtered.get(0), filtered.get(1));
                }
                default:
                    return null;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }


    @Nullable
    public ResolveDescriptor resolveReference(ResolveDescriptor ctx, String nameToResolve) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            List<ResolveDescriptor> filtered = Arrays.asList(ctx.resolve(nameToResolve));

            switch (filtered.size()) {
                case 0:
                    return null;
                case 1:
                    return filtered.get(0);
                case 2: {
                    // choose specification
//                    return chooseSpec(filtered);
                    return chooseSpec(filtered.get(0), filtered.get(1));
                }
                default:
                    return null;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public ResolveDescriptor resolveTableRef(String tableName) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder ref = new RefHolder(ContextPath.TABLE_REF, "", StringUtils.discloseDoubleQuotes(tableName).toLowerCase());
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            refResolver.resolve(out, ref);
            return out.size() == 1 ? out.get(0) : null;
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public ResolveDescriptor resolveSequenceRef(String text) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder ref = new RefHolder(ContextPath.SEQUENCE_REF, "", StringUtils.discloseDoubleQuotes(text).toLowerCase());
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            refResolver.resolve(out, ref);
            return out.size() == 1 ? out.get(0) : null;
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public PackageResolveHelper resolvePackage(String pkgName) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder ref = new RefHolder(ContextPath.PACKAGE_REF, "", StringUtils.discloseDoubleQuotes(pkgName).toLowerCase());
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            refResolver.resolve(out, ref);
            return out.size() == 1 ? (PackageResolveHelper) out.get(0) : null;
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }


    public ResolveDescriptor[] resolveNames(RefHolder rholder, int position) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            if (position >= rholder.getNames().length || position < 0) {
                throw new ValidationException("Requested name position out of bound: " + position);
            }

            ResolveDescriptor[] out = refResolver.resolveFront(rholder);
            if (position > 0 && out.length > 0) {
                // go deep
                List<ResolveDescriptor> out2 = new ArrayList<ResolveDescriptor>();
                for (ResolveDescriptor r : out) {
                    ResolveDescriptor[] out1 = new ResolverM(rholder.getNames(), position).resolveDeep(r, 1);
                    out2.addAll(Arrays.asList(out1));
                }
                return out2.toArray(new ResolveDescriptor[out2.size()]);
            } else {
                return out;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    private class ResolverM {
        public String[] names;
        public int position;

        private ResolverM(String[] names, int position) {
            this.names = names;
            this.position = position;
        }

        private ResolveDescriptor[] resolveDeep(ResolveDescriptor r, int current) {
            ResolveDescriptor[] r1 = r.resolve(names[current]);
            if (current < position) {
                List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
                for (ResolveDescriptor r2 : r1) {
                    ResolveDescriptor[] out1 = resolveDeep(r2, current + 1);
                    out.addAll(Arrays.asList(out1));
                }
                return out.toArray(new ResolveDescriptor[out.size()]);
            } else {
                return r1;
            }
        }
    }


    public ResolveDescriptor resolveCallable(RefHolder rholder, CallArgument[] args, int position) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            ResolveDescriptor[] out = resolveNames(rholder, position);
            if (out.length == 0) {
                return null;
            }
            switch (out[0].getTargetType()) {
                case ResolveDescriptor.FUNCTION_BODY:
                case ResolveDescriptor.FUNCTION_SPEC:
                case ResolveDescriptor.PROCEDURE_BODY:
                case ResolveDescriptor.PROCEDURE_SPEC:
                    // need resolving based on call arguments
                    break;
                default:
                    return out.length == 1 ? out[0] : null;

            }

            List<ResolveDescriptor> filtered = new ArrayList<ResolveDescriptor>();
            // check real arguments against formal
            CallArgumentResolver validator = new CallArgumentResolver(this);

            for (ResolveDescriptor rhlp : out) {
                ResolveDescriptor response = dispatcher(rhlp, validator, args);
                if (response != null) {
                    filtered.add(response);
                }
            }

            switch (filtered.size()) {
                case 0:
                    return null;
                case 1:
                    return filtered.get(0);
                case 2: // choose specification
//                return chooseSpec(out);
                    return chooseSpec(filtered.get(0), filtered.get(1));
                default:
                    return null;
            }
//            return filtered.toArray(new ResolveHelper[filtered.size()]);

        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public Type resolveType(TableColumnRefType type) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder ref = new RefHolder(ContextPath.TABLE_REF, "", type.getTable().toLowerCase());
            ResolveDescriptor[] out = refResolver.resolveFront(ref);
            if (out.length == 1) {
                ResolveDescriptor[] out2 = out[0].resolve(type.getColumn().toLowerCase());
                if (out2.length == 1) {
                    return out2[0].getType();
                }
            }
            throw new ValidationException("Table not resolved: " + type.getTable());
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }


    public UserDefinedType resolveUDT(String usageContext, String typeName) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder rholder = new RefHolder(ContextPath.TYPE_REF, usageContext, typeName);
            Type t = resolveTypeInternal(rholder);
            // todo -- exception?
            return t instanceof UserDefinedType ? (UserDefinedType) t : null;
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public ResolveDescriptor[] resolveReferencePoly(RefHolder rholder) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
            //cache.resolve(out, rholder);
            //List<ResolveHelper> out =
            refResolver.resolve(out, rholder);
            int collisions = out.size();
            if (collisions > 0) {
                List<ResolveDescriptor> filtered = new ArrayList<ResolveDescriptor>();
                // check real arguments against formal
                CallArgumentResolver validator = new CallArgumentResolver(this);

                for (ResolveDescriptor rhlp : out) {
                    int rhlpType = rhlp.getTargetType();
                    switch (rhlpType) {
                        case ResolveDescriptor.PROCEDURE_BODY:
                        case ResolveDescriptor.PROCEDURE_SPEC:
                        case ResolveDescriptor.FUNCTION_BODY:
                        case ResolveDescriptor.FUNCTION_SPEC:
                        case ResolveDescriptor.SYSTEM_FUNC:
                            List errors = new ArrayList();
                            ExecutableResolveHelper ehlp = (ExecutableResolveHelper) rhlp;
                            FunctionValidator funcSpecValidator = ehlp.getValidator();
                            if (funcSpecValidator != null && funcSpecValidator.validate(EMPTY_ARGS, errors)) {
                                // hitted
                                filtered.add(ehlp);
                            } else if (funcSpecValidator == null && validator.validate(ehlp, EMPTY_ARGS, errors)) {
                                // hitted
                                filtered.add(ehlp);
                            } else {
                                // arguments mismatched, skip
                            }
                            break;
                        default:
                            filtered.add(rhlp);
                    }
                }

                return filtered.toArray(new ResolveDescriptor[filtered.size()]);
            } else {
                return new ResolveDescriptor[0];
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }


    public ResolveDescriptor resolveGenericReference(String name, String ctxPath) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder rholder = new RefHolder(ContextPath.GENERIC_NAME_REF, ctxPath, name);
            ResolveDescriptor[] out = resolveReferencePoly(rholder);
            switch (out.length) {
                case 0:
                    return null;
                case 1:
                    return out[0];
                case 2: // choose specification
                    return chooseSpec(out[0], out[1]);
                default:
                    return null;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }


    public ResolveDescriptor resolvePlSqlVariable(String name, String ctxPath) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            RefHolder rholder = new RefHolder(ContextPath.PLSQL_NAME_REF, ctxPath, name);
            ResolveDescriptor[] out = resolveReferencePoly(rholder);
            switch (out.length) {
                case 0:
                    return null;
                case 1:
                    return out[0];
                case 2: // choose specification
                    return chooseSpec(out[0], out[1]);
                default:
                    return null;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }


    public ResolveDescriptor resolveReference(RefHolder rholder) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            ResolveDescriptor[] out = resolveReferencePoly(rholder);
            switch (out.length) {
                case 0:
                    return null;
                case 1:
                    return out[0];
                case 2: // choose specification
                    return chooseSpec(out[0], out[1]);
                default:
                    return null;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public ResolveDescriptor resolveFront(RefHolder rholder) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            ResolveDescriptor[] out = refResolver.resolveFront(rholder);
            if (out.length == 1) {
                return out[0];
            } else {
                return null;
            }
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    public ResolveDescriptor[] resolveFrontPoly(RefHolder rholder) {
        long ms0 = System.currentTimeMillis();
        try {
            locked++;
            return refResolver.resolveFront(rholder);
        } finally {
            if (--locked == 0) {
                resolveTime += (System.currentTimeMillis() - ms0);
            }
        }
    }

    private ResolveDescriptor chooseSpec(ResolveDescriptor _1st, ResolveDescriptor _2nd) {
        if (_1st instanceof ExecutableResolveHelper && _2nd instanceof ExecutableResolveHelper) {
            ExecutableResolveHelper ehlp1 = (ExecutableResolveHelper) _1st;
            ExecutableResolveHelper ehlp2 = (ExecutableResolveHelper) _2nd;
            if (ehlp1.isSpecificationOf(ehlp2)) {
                return ehlp1;
            } else if (ehlp2.isSpecificationOf(ehlp1)) {
                return ehlp2;
            }
        } else if (_1st instanceof CollectionResolveHelper && _2nd instanceof CollectionResolveHelper) {
            CollectionResolveHelper c1 = (CollectionResolveHelper) _1st;
            CollectionResolveHelper c2 = (CollectionResolveHelper) _2nd;
            // choose a closer context
            if (c1.packageScope() && !c2.packageScope()) {
                return c1;
            } else if (!c1.packageScope() && c2.packageScope()) {
                return c2;
            }
        }
        return null;
    }

    private ResolveDescriptor dispatcher(ResolveDescriptor rhlp, CallArgumentResolver validator, CallArgument[] args) {
        int rhlpType = rhlp.getTargetType();
        switch (rhlpType) {
            case ResolveDescriptor.PROCEDURE_BODY:
            case ResolveDescriptor.PROCEDURE_SPEC:
            case ResolveDescriptor.FUNCTION_BODY:
            case ResolveDescriptor.FUNCTION_SPEC:
            case ResolveDescriptor.SYSTEM_FUNC:
                List errors = new ArrayList();
                ExecutableResolveHelper ehlp = (ExecutableResolveHelper) rhlp;
                FunctionValidator funcSpecValidator = ehlp.getValidator();
                if (funcSpecValidator != null && funcSpecValidator.validate(args, errors)) {
                    // hitted
                    return ehlp;
                } else if (funcSpecValidator == null && validator.validate(ehlp, args, errors)) {
                    // hitted
                    return ehlp;
                } else {
                    // arguments mismatched, skip
                    return null;
                }
            case ResolveDescriptor.COLLECTION_TYPE:
            case ResolveDescriptor.COLLECTION_METHOD:
            case ResolveDescriptor.COLLECTION_ELEM_ACCESSOR:
            case ResolveDescriptor.VARRAY_METHOD: // Varray method call
            case ResolveDescriptor.VARRAY_TYPE: // ctor of the Varray type?
            case ResolveDescriptor.VARRAY_ELEM_ACCESSOR: // addressing of the element of Varray
                return rhlp;
            case ResolveDescriptor.OBJECT_TYPE: // ctor of the Object type?
                return rhlp;
            case ResolveDescriptor.RECORD_TYPE:
                return rhlp;
            case ResolveDescriptor.RECORD_ITEM:
                if (args.length == 1) {
                    // access to elem of collection
                    Type t = rhlp.getType();
                    ResolveDescriptor rhlp1 = resolveTypeInternal(rhlp.getCtxPath(), t.typeName());
                    if (rhlp1 != null && rhlp1.getTargetType() == ResolveDescriptor.COLLECTION_TYPE) {
                        CollectionResolveHelper rhlp2 = (CollectionResolveHelper) rhlp1;
                        //Type elemType = rhlp2.getElementType();
                        //refResolver.createResolveHelper(rhlp2.getCtxPath(), elemType);
                        return rhlp2.resolveType();
                    }
                    return null;
                } else if (args.length == 0) {
                    // collection ctor?
                    return rhlp;
                }
        }

        return null;
    }


    private ResolveDescriptor resolveTypeInternal(String usageContext, String typeName) {
        RefHolder rholder = new RefHolder(ContextPath.TYPE_REF, usageContext, typeName);

        List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
        //cache.resolve(out, rholder);
        //List<ResolveHelper> out =
        refResolver.resolve(out, rholder);
        if (out.size() == 1) {
            return out.get(0);
        }
        return null;
    }


    private Type resolveTypeInternal(RefHolder rholder) {
        List<ResolveDescriptor> out = new ArrayList<ResolveDescriptor>();
        refResolver.resolve(out, rholder);
        if (out.size() != 1) {
            // todo --
            if (rholder.getFullName().equalsIgnoreCase("i")) {
                return TypeFactory.createTypeById(Type.INTEGER);
            }
            // todo ---

            throw new ValidationException("Not resolved: " + rholder.getFullName());
        }
        return out.get(0).getType();
    }

}
