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
 *     3. The name of the author may not be used to endorse or promote
 *       products derived from this software without specific prior written
 *       permission from the author.
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

package com.deepsky.lang.plsql.folding;

import com.intellij.lang.folding.FoldingBuilder;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.util.TextRange;
import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;
import com.deepsky.lang.common.PlSqlTokenTypes;

import java.util.List;
import java.util.ArrayList;

public class PlSqlCodeFoldingBuilder implements FoldingBuilder {

    public FoldingDescriptor[] buildFoldRegions(ASTNode node, Document document) {
        List<FoldingDescriptor> descriptors = new ArrayList<FoldingDescriptor>();
        appendDescriptors(node, document, descriptors);
        return descriptors.toArray(new FoldingDescriptor[descriptors.size()]);
    }

    private void appendDescriptors(final ASTNode node, final Document document, final List<FoldingDescriptor> descriptors) {
        if (node.getElementType() == PLSqlTypesAdopted.FUNCTION_BODY) {
            if (node.getTreeParent().getElementType() != PlSqlTokenTypes.FILE && suitableForFolding(node.getText())) {
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
        } else if (node.getElementType() == PLSqlTypesAdopted.PROCEDURE_BODY) {
            if (node.getTreeParent().getElementType() != PlSqlTokenTypes.FILE && suitableForFolding(node.getText())) {
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
//        } else if (node.getElementType() == PLSqlTypesAdopted.PLSQL_BLOCK) {
//            if(suitableForFolding(node.getText())){
//                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
//            }
        } else if (node.getElementType() == PLSqlTypesAdopted.SELECT_EXPRESSION){ //COMMAND) {
            if(suitableForFolding(node.getText())){
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
        } else if (node.getElementType() == PLSqlTypesAdopted.INSERT_COMMAND) {
            if(suitableForFolding(node.getText())){
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
        } else if (node.getElementType() == PLSqlTypesAdopted.DELETE_COMMAND) {
            if(suitableForFolding(node.getText())){
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
        } else if (node.getElementType() == PLSqlTypesAdopted.UPDATE_COMMAND) {
            if(suitableForFolding(node.getText())){
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
        } else if (node.getElementType() == PlSqlTokenTypes.ML_COMMENT) {
            if(suitableForFolding(node.getText())){
                descriptors.add(new FoldingDescriptor(node, node.getTextRange()));
            }
        }

        ASTNode child = node.getFirstChildNode();
        while (child != null) {
            appendDescriptors(child, document, descriptors);
            child = child.getTreeNext();
        }
    }


    boolean suitableForFolding(String text) {
        return text.indexOf("\n") != -1; 
    }

    public String getPlaceholderText(ASTNode node) {

        if (node.getElementType() == PLSqlTypesAdopted.FUNCTION_BODY) {
            return cutText(node.getText());
        } else if (node.getElementType() == PLSqlTypesAdopted.PROCEDURE_BODY) {
            return cutText(node.getText());
//        } else if (node.getElementType() == PLSqlTypesAdopted.PLSQL_BLOCK) {
//            return cutText(node.getText());
        } else if (node.getElementType() == PLSqlTypesAdopted.SELECT_EXPRESSION){ //COMMAND) {
            return cutText(node.getText());
        } else if (node.getElementType() == PLSqlTypesAdopted.INSERT_COMMAND) {
            return cutText(node.getText());
        } else if (node.getElementType() == PLSqlTypesAdopted.DELETE_COMMAND) {
            return cutText(node.getText());
        } else if (node.getElementType() == PLSqlTypesAdopted.UPDATE_COMMAND) {
            return cutText(node.getText());
        } else if (node.getElementType() == PlSqlTokenTypes.ML_COMMENT) {
            return "/*...*/";
        }

//    if (node.getElementType() == JSTokenTypes.DOC_COMMENT) {
//      return "/**...*/";
//    }
//    else if (node.getElementType() == JSTokenTypes.C_STYLE_COMMENT) {
//      return "/*...*/";
//    }
//    else if (node.getElementType() == JSElementTypes.BLOCK) {
//      return "{...}";
//    }
        return null;
    }

    String cutText(String text){
        int ssize = text.indexOf("\n");
        if (ssize > 0) {
            return text.substring(0, ssize) + " ...";
        } else {
            return text;
        }
    }


    public boolean isCollapsedByDefault(ASTNode node) {
        return false; //node.getElementType() == JSTokenTypes.DOC_COMMENT;
    }
}

