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

import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.TriggerColumnNameRef;
import com.deepsky.lang.plsql.psi.resolve.*;
import com.deepsky.lang.plsql.psi.resolve.impl.*;
import com.deepsky.lang.plsql.psi.ddl.CreateTrigger;
import com.deepsky.lang.plsql.psi.ddl.CreateTriggerDML;
//import com.deepsky.lang.plsql.psi.resolve.*;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class TriggerColumnNameRefImpl extends PlSqlCompositeNameBase implements TriggerColumnNameRef {

    final private static TokenSet tokens = TokenSet.create(
                // todo -- must be revise -- mixed types!
                PLSqlTypesAdopted.NAME_FRAGMENT,
                PlSqlTokenTypes.COLON_NEW,
                PlSqlTokenTypes.COLON_OLD);

    public TriggerColumnNameRefImpl(ASTNode astNode) {
        super(astNode);
    }

    @NotNull
    public ResolveContext777 resolve(NameFragmentRef namePart) throws NameNotResolvedException {

        NameFragmentRef[] pieces = getNamePieces();
        int pos = namePart == null ? pieces.length - 1 : getFragmentIndex(namePart);

        if (pos == -1) {
            String text = namePart == null ? getText() : namePart.getText();
            throw new NameNotResolvedException("Could not resolve name: " + text);
        }

        ResolveContext777 ctx = getResolveContext();
        return ctx.resolve(pieces[pos]);
//        int i = 1;
//        try {
//            for (; --pos >= 0; i++) {
//                ctx = ctx.resolve(pieces[i]);
//            }
//        } catch (NameNotResolvedException e) {
//             throw new NameNotResolvedException(pieces[i], "Name not resolved");
//        }
//        return ctx;
    }

    @NotNull
    public ResolveContext777 getResolveContext() throws NameNotResolvedException {
        final TableDescriptor[] tdesc = new TableDescriptor[]{null};
        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new TriggerNodeHandler() {
            protected void handleTriggerDefintion(CreateTrigger trigger) {
                if (trigger instanceof CreateTriggerDML) {
                    tdesc[0] = describeTable(
                            ((CreateTriggerDML) trigger).getTableName()
                    );
                }
            }
        });
        runner.process(getNode());

        if (tdesc[0] != null) {
            return new com.deepsky.lang.plsql.psi.resolve.impl.TableContext(getProject(), tdesc[0]);
        } else {
            throw new NameNotResolvedException("");
        }
    }

    @NotNull
    public Type getExpressionType() {

        final Type[] out = new Type[]{null};

        ASTNode[] nodes = getNode().getChildren(tokens);

        if(nodes == null || nodes.length != 1){
            return null;
        }

        final String column = nodes[0].getText();

        ASTTreeProcessor runner = new ASTTreeProcessor();
        runner.add(new TriggerNodeHandler() {
            protected void handleTriggerDefintion(CreateTrigger trigger) {
                if (trigger instanceof CreateTriggerDML) {
                    TableDescriptor tdesc = describeTable(
                            ((CreateTriggerDML) trigger).getTableName()
                    );

                    if(tdesc != null){
                        out[0] = tdesc.getColumnType(column);
                    }
                }
            }
        });
        runner.process(getNode());

        return out[0];
    }

    @NotNull
    public NameFragmentRef[] getNamePieces() {
        ASTNode[] nodes = getNode().getChildren(tokens);
        NameFragmentRef[] out = new NameFragmentRef[nodes != null ? nodes.length : 0];
        for (int i = 0; i < out.length; i++) {
            out[i] = (NameFragmentRef) nodes[i].getPsi();
        }

        return out;
    }

    public int getFragmentIndex(@NotNull NameFragmentRef fragment) {
        ASTNode[] nodes = getNode().getChildren(tokens);
        if (nodes == null) {
            return -1;
        } else {
            for (int i = 0; i < nodes.length; i++) {
                if (nodes[i].getPsi() == fragment) {
                    return i;
                }
            }

            return -1;
        }
    }



/*
    @NotNull
    public NameFragmentRef[] getNamePieces() {
//        ASTNode[] nodes = getNode().getChildren(TokenSet.create(PLSqlTypesAdopted.NAME_FRAGMENT, PlSqlTokenTypes.COLON_OLD));
//        NameFragmentRef[] out = new NameFragmentRef[nodes != null ? nodes.length : 0];
//        for (int i = 0; i < out.length; i++) {
//            out[i] = (NameFragmentRef) nodes[i].getPsi();
//        }

        //return out;
        // todo -- must be revised
        throw new NotSupportedException("");
    }
*/

}
