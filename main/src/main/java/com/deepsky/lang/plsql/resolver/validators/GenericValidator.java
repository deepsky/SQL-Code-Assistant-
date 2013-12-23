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

package com.deepsky.lang.plsql.resolver.validators;

import com.deepsky.lang.plsql.resolver.FunctionValidator;
import com.deepsky.lang.plsql.struct.SystemFunctionDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.psi.CallArgument;
//import com.deepsky.lang.plsql.psi.resolve.TypeNotResolvedException;
import com.deepsky.lang.validation.TypeValidationHelper;
import com.deepsky.lang.validation.ValidationException;

import java.util.List;

// todo -- subject to review and delete
public class GenericValidator implements FunctionValidator {

    SystemFunctionDescriptor edesc;

    public boolean validate(CallArgument[] args, List<String> errors) {

        if (args.length < edesc.numberOfReqArguments()) {
            errors.add(edesc.getName() + ": arguments less then expected");
            return false;
        } else if (args.length > edesc.getArgumentNames().length) {
            errors.add(edesc.getName() + ": too many arguments");
            return false;
        }

        for (int i = 0; i < args.length; i++) {
            CallArgument arg = args[i];
            String name = edesc.getArgumentName(i);
            if (name == null) {
                errors.add(edesc.getName() + ": number of formal and real parameters mismatched");
                return false;
            }
            Type t = edesc.getArgumentType(name);
            try {
                Type t1 = arg.getExpression().getExpressionType();
                if (!TypeValidationHelper.canBeAssigned(arg, t, t1)) {
                    errors.add(edesc.getName() + ": type of argument does not fit a formal parameter, position: " + (i + 1));
                    return false;
                }
            } catch (ValidationException e) {
                // exception on type discovering ... lets pretend that checks pass
                continue;
/*
            } catch (TypeNotResolvedException e) {
                // exception on type discovering ... lets pretend that checks pass
                continue;
*/
            } catch (Throwable e) {
                int hh = 0;
            }
        }

        return true;
    }

    public void setSystemFunctionDesc(SystemFunctionDescriptor edesc) {
        this.edesc = edesc;
    }
}
