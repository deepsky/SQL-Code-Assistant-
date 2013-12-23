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

package com.deepsky.lang.plsql.resolver.res_newlook;

import com.deepsky.database.ora.DbUrl;
import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.castor.mapping.ArgumentType;
import com.deepsky.lang.plsql.castor.mapping.Function;
import com.deepsky.lang.plsql.castor.mapping.FunctionList;
import com.deepsky.lang.plsql.resolver.*;
import com.deepsky.lang.plsql.resolver.index.IndexTree;
import com.deepsky.lang.plsql.resolver.index.IndexTreeBase;
import com.deepsky.lang.plsql.resolver.utils.SysFuncCtxPathBuilder;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

public class CompositeRefResolver extends RefRes {

    static LoggerProxy log = LoggerProxy.getInstance("CompositeRefResolver");

    private NameResolver external;

    public CompositeRefResolver(DbUrl userName, IndexTreeBase itree) {
        super(userName, itree, null);
        this.setContextLevelResolver(new ContextLevelResolverImpl());

        // load SYSTEM functions
        InputStream istream = this.getClass().getClassLoader().getResourceAsStream("sysfunc_list.xml");
        if (istream != null) {
            Reader r = new InputStreamReader(istream);
            loadSystemFunctions(r, itree);
        } else {
            log.error("Cannot load a list of embedded functions, i.e. SUBSTR, TO_NUMBER, etc");
        }
    }

    public void setExternalResolver(NameResolver external) {
        this.external = external;
    }

    private class ContextLevelResolverImpl implements NameResolver {

        public void resolveForRelContext(List<ResolveDescriptor> out, String ctxPath, String name, int refType) {
            if (external != null) {
                int before = out.size();
                external.resolveForRelContext(out, ctxPath, name, refType);
                if (before < out.size()) {
                    return;
                }
            } else {
                // todo -- CompositeRefResolver.this.resolveForRelContext(out, ctxPath, name, refType);
            }
            CompositeRefResolver.this.resolveForRelContext(out, ctxPath, name, refType);
        }
    }


    private void loadSystemFunctions(Reader reader, IndexTree itree) {
        try {
            FunctionList lst = FunctionList.unmarshal(reader);
            SysFuncCtxPathBuilder ctxBld = new SysFuncCtxPathBuilder(ContextPath.FILE_CTX_PRX + "..$system");

            for (Function f : lst.getFunction()) {
                ctxBld.start();
                String name = f.getName();
                ctxBld.setName(name);
                String returnType = f.getReturnType();
                Type type = TypeFactory.createTypeByName(returnType);
                ctxBld.setReturnType(type);

                for (ArgumentType a : f.getArg()) {
                    Type arg_type = TypeFactory.createTypeByName(a.getType());
                    String arg_name = a.getName();
                    boolean isDef = a.isOpt();

                    ctxBld.addArgParams(arg_name, arg_type, isDef);
                }

                String ctxPath = ctxBld.buildPath();

//                String validatorClass = null; // todo -- f.getValidatorClass();
                String validatorClass = f.getValidatorClass();
                // try to instantiate the validator
                if (validatorClass != null) {
                    try {
                        Class validatorClazz = Class.forName(validatorClass);
                        Object o = validatorClazz.newInstance();
                        if (o instanceof FunctionValidator) {
                            sysFuncValidators.put(ctxPath, (FunctionValidator) o);
                        } else {
                            log.warn("Configuration exception: validator class is not instance of SystemFunctionValidator, " + o.getClass());
                        }
                    } catch (ClassNotFoundException e) {
                        log.warn("Could not find validator class: " + e.getMessage());
                    } catch (IllegalAccessException e) {
                        log.warn("Could not find validator class: " + e.getMessage());
                    } catch (InstantiationException e) {
                        log.warn("Could not instantiate validator class: " + e.getMessage());
                    }
                } else {
                    // use Generic Validator
                }

                itree.addContextPath(ctxPath, ctxBld.getEncodedArguments());
            }

        } catch (MarshalException e) {
            throw new ConfigurationException("Could not load system function list!", e);
        } catch (ValidationException e) {
            throw new ConfigurationException("Could not load system function list!", e);
        }
    }

}
