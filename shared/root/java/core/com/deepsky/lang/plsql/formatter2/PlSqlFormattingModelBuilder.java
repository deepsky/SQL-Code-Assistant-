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

package com.deepsky.lang.plsql.formatter2;

import com.deepsky.lang.common.PlSqlLanguage;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.plsql.formatter2.settings.PlSqlCodeStyleSettings;
import com.intellij.formatting.Block;
import com.intellij.formatting.FormattingModel;
import com.intellij.formatting.FormattingModelBuilder;
import com.intellij.formatting.Indent;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CommonCodeStyleSettings;
import com.intellij.psi.formatter.FormatterUtil;
import com.intellij.psi.formatter.FormattingDocumentModelImpl;
import com.intellij.psi.formatter.PsiBasedFormattingModel;
import com.intellij.psi.impl.source.tree.TreeUtil;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

public class PlSqlFormattingModelBuilder implements FormattingModelBuilder {
    @NotNull
    public FormattingModel createModel(PsiElement element, CodeStyleSettings settings) {
        ASTNode node = element.getNode();
        assert node != null;
        PsiFile containingFile = element.getContainingFile().getViewProvider().getPsi(Language.findInstance(PlSqlLanguage.class));
        assert containingFile != null : element.getContainingFile();
        ASTNode astNode = containingFile.getNode();
        assert astNode != null;
        CommonCodeStyleSettings plsqlSettings = settings.getCommonSettings(Language.findInstance(PlSqlLanguage.class));
        PlSqlCodeStyleSettings customSettings = settings.getCustomSettings(PlSqlCodeStyleSettings.class);
        final PlSqlBlock block = new PlSqlBlock(astNode, null, Indent.getAbsoluteNoneIndent(), null, plsqlSettings, customSettings);
        return new PlSqlFormattingModel(containingFile, block, FormattingDocumentModelImpl.createOn(containingFile));
    }

    public TextRange getRangeAffectingIndent(PsiFile file, int offset, ASTNode elementAtOffset) {
        return null;
    }



    public class PlSqlFormattingModel extends PsiBasedFormattingModel {
        public PlSqlFormattingModel(PsiFile file, @NotNull Block rootBlock, FormattingDocumentModelImpl documentModel) {
            super(file, rootBlock, documentModel);
        }

        @Override
        protected String replaceWithPsiInLeaf(TextRange textRange, String whiteSpace, ASTNode leafElement) {
            if (!myCanModifyAllWhiteSpaces) {
                if (PlSqlTokenTypes.WS_TOKENS.contains(leafElement.getElementType())) return null;
            }

            IElementType elementTypeToUse = TokenType.WHITE_SPACE;
            ASTNode prevNode = TreeUtil.prevLeaf(leafElement);
            if (prevNode != null && PlSqlTokenTypes.WS_TOKENS.contains(prevNode.getElementType())) {
                elementTypeToUse = prevNode.getElementType();
            }
            FormatterUtil.replaceWhiteSpace(whiteSpace, leafElement, elementTypeToUse, textRange);
            return whiteSpace;
        }
    }

}
