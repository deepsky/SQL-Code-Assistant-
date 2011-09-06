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

package com.deepsky.navigation;

import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.psi.PackageBody;
import com.deepsky.lang.plsql.psi.PackageSpec;
import com.deepsky.lang.plsql.psi.PlSqlElement;
//import com.deepsky.lang.plsql.psi.resolve.ASTNodeHandler;
//import com.deepsky.lang.plsql.psi.resolve.ASTTreeProcessor;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class PlSqlPackageUtil {

    static private TokenSet CONTEXT = TokenSet.create(
            PlSqlElementTypes.PACKAGE_BODY,
            PlSqlElementTypes.PACKAGE_SPEC
    );

    public static String findPackageNameForElement(PsiElement elem){
        if(elem == null){
            return null;
        } else {
            final String[] out = {null};
/*
todo -- resolve stuff refactoring
            ASTTreeProcessor runner = new ASTTreeProcessor();
            runner.add(new ASTNodeHandler() {
                @NotNull
                public TokenSet getTokenSet() {
                    return CONTEXT;
                }

                public boolean handleNode(@NotNull ASTNode node) {
                    if (node.getElementType() == PlSqlElementTypes.PACKAGE_BODY) {
                        out[0] = ((PackageBody) node.getPsi()).getPackageName();
                    } else if (node.getElementType() == PlSqlElementTypes.PACKAGE_SPEC) {
                        out[0] = ((PackageSpec) node.getPsi()).getPackageName();
                    }
                    return false;
                }
            });

            runner.process(elem.getNode());
*/
            return out[0];
        }
    }


    public static PlSqlElement findPackageForElement(PsiElement elem){
        if(elem == null){
            return null;
        } else {
            final PlSqlElement[] out = {null};
/*
todo -- resolve stuff refactoring

            ASTTreeProcessor runner = new ASTTreeProcessor();
            runner.add(new ASTNodeHandler() {
                @NotNull
                public TokenSet getTokenSet() {
                    return CONTEXT;
                }

                public boolean handleNode(@NotNull ASTNode node) {
                    if (node.getElementType() == PlSqlElementTypes.PACKAGE_BODY) {
                        out[0] = (PackageBody) node.getPsi();
                    } else if (node.getElementType() == PlSqlElementTypes.PACKAGE_SPEC) {
                        out[0] = (PackageSpec) node.getPsi();
                    }
                    return false;
                }
            });

            runner.process(elem.getNode());
*/
            return out[0];
        }
    }

}
