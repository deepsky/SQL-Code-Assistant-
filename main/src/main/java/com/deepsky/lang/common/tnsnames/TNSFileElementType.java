package com.deepsky.lang.common.tnsnames;

import com.deepsky.lang.common.PlSqlLanguage;
import com.deepsky.lang.plsql.workarounds.LoggerProxy;
import com.intellij.lang.*;
import com.intellij.openapi.project.Project;
import com.intellij.psi.tree.IFileElementType;

public class TNSFileElementType  extends IFileElementType {

    static final LoggerProxy log = LoggerProxy.getInstance("#TNSFileElementType");

    public TNSFileElementType() {
        super("FILE", Language.findInstance(PlSqlLanguage.class));
    }

    public ASTNode parseContents(ASTNode chameleon) {
        final Project project = chameleon.getPsi().getProject();
        long start = System.currentTimeMillis();
        try {
            final PsiBuilderFactory factory = PsiBuilderFactory.getInstance();
            final PsiBuilder builder = factory.createBuilder(
                    project,
                    chameleon,
                    null,
                    getLanguage(),
                    chameleon.getChars()
            );

            final PsiParser parser = LanguageParserDefinitions.INSTANCE.forLanguage(getLanguage()).createParser(project);
            ASTNode root = parser.parse(this, builder);

            return root.getFirstChildNode();
        } finally {
            log.info("#parseContent: END [time: " + (System.currentTimeMillis() - start) + "]");
        }
    }
}

