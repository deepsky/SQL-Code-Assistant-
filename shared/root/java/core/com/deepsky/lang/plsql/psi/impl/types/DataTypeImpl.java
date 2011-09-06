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

package com.deepsky.lang.plsql.psi.impl.types;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.psi.PlSqlElementVisitor;
import com.deepsky.lang.plsql.psi.impl.PlSqlElementBase;
import com.deepsky.lang.plsql.psi.types.DataType;
import com.deepsky.lang.plsql.psi.utils.ASTNodeIterator;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class DataTypeImpl extends PlSqlElementBase implements DataType {

    private static TokenSet DATATYPE_KEYWORDS = TokenSet.create(
//         PlSqlTokenTypes.NUMBER,

         PlSqlTokenTypes.KEYWORD_BINARY_INTEGER,
         PlSqlTokenTypes.KEYWORD_NATURAL,
         PlSqlTokenTypes.KEYWORD_POSITIVE,
         PlSqlTokenTypes.KEYWORD_NUMBER,
         PlSqlTokenTypes.KEYWORD_CHAR,
         PlSqlTokenTypes.KEYWORD_LONG,
            PlSqlTokenTypes.KEYWORD_RAW,
            PlSqlTokenTypes.KEYWORD_BOOLEAN,
            PlSqlTokenTypes.KEYWORD_DATE,
            PlSqlTokenTypes.KEYWORD_TIMESTAMP,
            PlSqlTokenTypes.KEYWORD_WITH,
            PlSqlTokenTypes.KEYWORD_LOCAL,
            PlSqlTokenTypes.KEYWORD_TIME,
            PlSqlTokenTypes.KEYWORD_ZONE,
            PlSqlTokenTypes.KEYWORD_INTERVAL,
            PlSqlTokenTypes.KEYWORD_YEAR,
            PlSqlTokenTypes.KEYWORD_DAY,
            PlSqlTokenTypes.KEYWORD_TO,
            PlSqlTokenTypes.KEYWORD_MONTH,
            PlSqlTokenTypes.KEYWORD_SECOND,
            PlSqlTokenTypes.KEYWORD_SMALLINT,
            PlSqlTokenTypes.KEYWORD_REAL,
            PlSqlTokenTypes.KEYWORD_NUMERIC,
            PlSqlTokenTypes.KEYWORD_INT,
            PlSqlTokenTypes.KEYWORD_INTEGER,
            PlSqlTokenTypes.KEYWORD_PLS_INTEGER,
            PlSqlTokenTypes.KEYWORD_DOUBLE,
            PlSqlTokenTypes.KEYWORD_PRECISION,
            PlSqlTokenTypes.KEYWORD_FLOAT,
            PlSqlTokenTypes.KEYWORD_DECIMAL,
            PlSqlTokenTypes.KEYWORD_VARCHAR,
            PlSqlTokenTypes.KEYWORD_VARCHAR2,
            PlSqlTokenTypes.KEYWORD_NVARCHAR,
            PlSqlTokenTypes.KEYWORD_NVARCHAR2,
            PlSqlTokenTypes.KEYWORD_CHARACTER,
            PlSqlTokenTypes.KEYWORD_ROWID,
            PlSqlTokenTypes.KEYWORD_BLOB,
            PlSqlTokenTypes.KEYWORD_CLOB,
            PlSqlTokenTypes.KEYWORD_BFILE
    );

    public DataTypeImpl(ASTNode astNode) {
        super(astNode);
    }

    public Type getType() {

/*
       ASTNode[] keywords = getNode().getChildren(DATATYPE_KEYWORDS);

       for(int i = 0; i<keywords.length; i++){
          DATATYPE_KEYWORDS.

       }
*/

        ASTNodeIterator iterator = new ASTNodeIterator(getNode().getFirstChildNode());

        String typeName = null;
        String length = null;
        while (iterator.hasNext()) {
            ASTNode node = iterator.next();
            if (typeName == null) {
                typeName = node.getText();
            } else if (node.getElementType() == PlSqlTokenTypes.NUMBER) {
                //
                length = node.getText();
                node = iterator.next();
                if (node.getText().equalsIgnoreCase("char")) {
                    // todo - process 'char'
                } else if (node.getText().equalsIgnoreCase("byte")) {
                    // todo - process 'byte'
                }
            }
        }

        if (length != null) {
            try {
                return TypeFactory.createTypeByName(typeName, Integer.parseInt(length));
            }catch (NumberFormatException e){
                try {
                    return TypeFactory.createTypeByName(typeName, (int)Float.parseFloat(length));
                } catch(Throwable e1){
                    return TypeFactory.createTypeByName(typeName);
                }
            }
        } else {
            return TypeFactory.createTypeByName(typeName);
        }
    }

    public boolean isTypeValid() {
        // valid always by syntax
        return true;
    }


    public PsiElement[] getTypeName() {
       ASTNode[] keywords = getNode().getChildren(DATATYPE_KEYWORDS);
       PsiElement[] out = new PsiElement[keywords.length];
       for(int i =0;i <keywords.length; i++){
         out[i] = keywords[i].getPsi();
       }

        return out;//this.getFirstChild();
    }

    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof PlSqlElementVisitor) {
            ((PlSqlElementVisitor) visitor).visitDatatype(this);
        } else {
            super.accept(visitor);
        }
    }

}
