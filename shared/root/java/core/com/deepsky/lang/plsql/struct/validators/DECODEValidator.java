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

package com.deepsky.lang.plsql.struct.validators;

import com.deepsky.lang.plsql.psi.CallArgument;
import com.deepsky.lang.plsql.psi.resolve.TypeNotResolvedException;
import com.deepsky.lang.plsql.struct.ExecutableDescriptor;
import com.deepsky.lang.plsql.struct.SystemFunctionValidator;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.validation.TypeValidationHelper;
import com.deepsky.lang.validation.ValidationException;

import java.util.List;


public class DECODEValidator implements SystemFunctionValidator {

    ExecutableDescriptor edesc;

/*
DECODE (warehouse_id, 1, 'Southlake',
                      2, 'San Francisco',
                      3, 'New Jersey',
                      4, 'Seattle',
                         'Non domestic'
       ) 
*/

    public boolean validate(CallArgument[] args, List<String> errors) {

        if (args.length < 3) {
            errors.add("Arguments less then expected");
            return false;
        }

        Type exprType = null;
        Type evenType = null;
        int len = args.length;
        for (int i = 0; i < len; i++) {
            try {
                if (i == 0) {
                    // defined type of expression
                    exprType = args[i].getExpression().getExpressionType();
                } else if (i >= 3 && i == len - 1) {
                    // pass validation if type of any arguments is NULL
//                    if(evenType.typeId() == Type.NULL){
//                        continue;
//                    }
                    // check type of the default argument
                    Type t = args[i].getExpression().getExpressionType();
//                    if(t.typeId() == Type.NULL){
//                        continue;
//                    }
                    if (!TypeValidationHelper.canBeAssigned(args[i], evenType, t)) {
                        errors.add("DECODE: different types in the 'result' argument, position: " + (i + 1));
                        return false;
                    }
                } else {
                    if (i % 2 != 0) {
                        // odd position, check argument typa against exprType
                        Type t = args[i].getExpression().getExpressionType();
                        if (!TypeValidationHelper.canBeAssigned(args[i], exprType, t)) {
                            errors.add("DECODE: argument type does not fit the formal parameter, position: " + (i + 1));
                            return false;
                        }

                    } else {
                        // even position, make sure all of arguments in even position have the same type
                        if (evenType != null) {
                            Type t = args[i].getExpression().getExpressionType();
                            if (!TypeValidationHelper.canBeAssigned(args[i], evenType, t)) {
                                errors.add("DECODE: different types in the 'result' argument, position: " + (i + 1));
                                return false;
                            }
                        } else {
                            evenType = args[i].getExpression().getExpressionType();
                        }
                    }
                }

            } catch (ValidationException e) {
                // exception on type discovering ... lets pretend that checks pass
                continue;
            } catch (TypeNotResolvedException e) {
                // exception on type discovering ... lets pretend that checks pass
                continue;
            } catch (Throwable e) {
                // todo --
            }
        }

        return true;
    }
}
