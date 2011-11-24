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

import com.deepsky.lang.plsql.NotSupportedException;
import com.deepsky.lang.plsql.psi.ColumnSpec;
import com.deepsky.lang.plsql.psi.NameFragmentRef;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.struct.Type;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;

public class ColumnSpecImpl extends PlSqlCompositeNameBase implements ColumnSpec {
    public ColumnSpecImpl(ASTNode astNode) {
        super(astNode);
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitColumnSpec(this);
        } else {
            super.accept(visitor);
        }
    }

    public String getColumnNameRef() {
        NameFragmentRef[] refs = getNamePieces();
        return refs[refs.length - 1].getText();
    }

    public Type getColumnType() {
/*

        NameFragmentRef[] fragments = getNamePieces();
        NameFragmentRef last = fragments[fragments.length - 1];
*/

        // todo -- resolve stuff refactoring
        throw new NotSupportedException();
    }

/*
    @Nullable
    public String getQuickNavigateInfo() {
//        TableDefinition t = findParent(TableDefinition.class);
//        if (t != null) {
//            String tableName = t.getTableName();
//            TableDescriptor desc = t.describe();
//            if(desc != null){
//                desc.
//            }
// todo - throw exception
//            return "[Table] " + "dummy" //tableName.toLowerCase()
//                    + "\n [Column] " + getColumnNameRef().toLowerCase() + " "
//                    + getColumnType().toString().toUpperCase();
//        }
        return null;
    }
*/

}
