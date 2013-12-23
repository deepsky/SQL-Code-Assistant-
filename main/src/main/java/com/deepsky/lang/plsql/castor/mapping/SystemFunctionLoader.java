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

package com.deepsky.lang.plsql.castor.mapping;

import com.deepsky.lang.plsql.ConfigurationException;
import com.deepsky.lang.plsql.struct.SystemFunctionDescriptor;
import com.deepsky.lang.plsql.resolver.FunctionValidator;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.deepsky.lang.plsql.resolver.validators.GenericValidator;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.ValidationException;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


public class SystemFunctionLoader {

    static final LoggerProxy log = LoggerProxy.getInstance("#SystemFunctionLoader");

    public static SystemFunctionDescriptor[] load(Reader reader) {
        try {
            FunctionList lst = null;
            lst = FunctionList.unmarshal(reader);

            List<SystemFunctionDescriptor> out = new ArrayList<SystemFunctionDescriptor>();
            for (Function f : lst.getFunction()) {
                String name = f.getName();
                String returnType = f.getReturnType();

                Type type = TypeFactory.createTypeByName(returnType);

                SystemFunctionDescriptor desc = new SystemFunctionDescriptor(name.toUpperCase(), type);
                for (ArgumentType a : f.getArg()) {
                    Type arg_type = TypeFactory.createTypeByName(a.getType());
                    desc.addParameter(arg_type, a.getName(), a.isOpt());
                }

                String validatorClass = f.getValidatorClass();

                // try to instantiate the validator
                if(validatorClass != null){
                    try {
                        Class validatorClazz = Class.forName(validatorClass);
                        Object o = validatorClazz.newInstance();
                        if(o instanceof FunctionValidator){
                            desc.setValidator((FunctionValidator) o);
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
                    GenericValidator validator = new GenericValidator();
                    validator.setSystemFunctionDesc(desc);
                    desc.setValidator(validator);
                }

                out.add(desc);
            }

            return out.toArray(new SystemFunctionDescriptor[out.size()]);
        } catch (MarshalException e) {
            throw new ConfigurationException("Could not load system function list!", e);
        } catch (ValidationException e) {
            throw new ConfigurationException("Could not load system function list!", e);
        }
    }
}
