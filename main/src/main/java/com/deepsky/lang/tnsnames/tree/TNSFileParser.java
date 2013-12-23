package com.deepsky.lang.tnsnames.tree;


import com.deepsky.lang.common.tnsnames.TNSPsiParser;
import com.deepsky.lang.common.tnsnames.TNSPsiTokenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class TNSFileParser {

    private final PsiParser parser = new TNSPsiParser();

    private String filePath = null;
    private VirtualFile virtualFile = null;

    public TNSFileParser() {
    }

    public TNSFileParser(String filePath) {
        this.filePath = filePath;
    }

    public TNSFileParser(VirtualFile virtualFile) {
        this.filePath = virtualFile.getPath();
        this.virtualFile = virtualFile;
    }

    @NotNull
    public ASTNode parse(String input) {
        TNSNodeBuilder builder =
                virtualFile != null ?
                        new TNSNodeBuilder(virtualFile, input)
                        : new TNSNodeBuilder(filePath, input);

        return parser.parse(TNSPsiTokenTypes.FILE, builder);
    }

}
