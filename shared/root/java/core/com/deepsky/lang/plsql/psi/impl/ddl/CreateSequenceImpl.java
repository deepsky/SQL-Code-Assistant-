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

package com.deepsky.lang.plsql.psi.impl.ddl;

import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.plsql.SyntaxTreeCorruptedException;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.ddl.CreateSequence;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.lang.plsql.resolver.ContextPath;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;


public class CreateSequenceImpl extends PlSqlElementBase implements CreateSequence {

    public CreateSequenceImpl(ASTNode astNode) {
        super(astNode);
    }

    public String getSequenceName() {
        ASTNode oname = getNode().findChildByType(PLSqlTypesAdopted.OBJECT_NAME);
        if (oname == null) {
            throw new SyntaxTreeCorruptedException();
        }

        String name = oname.getText();
        int start = name.indexOf('.');
        if (start < 0) {
            return name;
        } else {
            return name.substring(start + 1);
        }
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitCreateSequence(this);
        } else {
            super.accept(visitor);
        }
    }

/*
    // [Contex Management Stuff] Start -------------------------------
    CtxPath cachedCtxPath = null;

    public CtxPath getCtxPath() {
        if (cachedCtxPath != null) {
            return cachedCtxPath;
        } else {
            CtxPath parent = super.getCtxPath();
            cachedCtxPath = new ContextPathUtil.CtxPathImpl(
                    parent.getPath() + ContextPathUtil.encodeCtx(ContextPath.SEQUENCE, "..$" + getSequenceName().toLowerCase()));
        }
        return cachedCtxPath;
    }
    // [Contex Management Stuff] End ---------------------------------
*/

}
