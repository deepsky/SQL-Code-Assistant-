package com.deepsky.lang.common.tnsnames;

import com.deepsky.lang.parser.tns.TNSTypesAdopted;
import com.deepsky.lang.tnsnames.psi.AddressElementImpl;
import com.deepsky.lang.tnsnames.psi.AddressImpl;
import com.deepsky.lang.tnsnames.psi.NetServiceDescImpl;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class TNSParserDefinition implements ParserDefinition {

    @NotNull
    public Lexer createLexer(Project project) {
        return new TNSPsiLexer();
    }

    public IFileElementType getFileNodeType() {
        return TNSPsiTokenTypes.FILE;
    }

    private static TokenSet ws = TokenSet.create(
            TNSPsiTokenTypes.WS,
            TNSPsiTokenTypes.LF,
            TokenType.WHITE_SPACE);

    @NotNull
    public TokenSet getWhitespaceTokens() {
        return ws;
    }

    @NotNull
    public TokenSet getCommentTokens() {
        return TNSPsiTokenTypes.COMMENTS;
    }

    @NotNull
    public TokenSet getStringLiteralElements() {
        return TNSPsiTokenTypes.STRING_LITERALS;
    }


    @NotNull
    public PsiParser createParser(final Project project) {
        return new TNSPsiParser();
    }

    public PsiFile createFile(FileViewProvider fileViewProvider) {
        return new TNSFileBase(fileViewProvider, Language.findLanguageByID("PL/SQL"));
    }

    public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public static PsiElement createElement2(@NotNull ASTNode f) {

        if(f.getElementType() == TNSTypesAdopted.NET_SERVICE_DESC){
            return new NetServiceDescImpl(f);
        } else if(f.getElementType() == TNSTypesAdopted.ADDRESS){
            return new AddressImpl(f);
        } else if(f.getElementType() == TNSTypesAdopted.ADDRESS_ELEMENT){
            return new AddressElementImpl(f);
        }
        return null;
    }

    @NotNull
    public PsiElement createElement(ASTNode node) {
        return new ASTWrapperPsiElement(node);
    }


}
