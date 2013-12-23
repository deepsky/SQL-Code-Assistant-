package com.deepsky.integration.large_scripts;

import com.deepsky.lang.lexer.PlSqlPsiLexer;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiFile;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class ParsingBigFilesTest {

    private File base;


    @Before
    public void setUp() throws URISyntaxException {

        URL url = this.getClass().getClassLoader().getResource("large_scripts");
        base = new File(url.toURI());
    }

    @Test
    public void test_lexer_100k() throws IOException {

        long ms1 = System.currentTimeMillis();
        File f = new File(base, "100k_inserts.sqlx");
        String content = StringUtils.file2string(f);
        long ms2 = System.currentTimeMillis();
        System.out.println("Load 100k_inserts into string, spent: " + (ms2-ms1));

        PlSqlPsiLexer lexer = new PlSqlPsiLexer();
        lexer.start(content);

        long tokenCounter =0;
        while(lexer.getTokenType() != null){
            lexer.advance();
            tokenCounter++;
        }
        long ms3 = System.currentTimeMillis();
        System.out.println("Lexer, time spent: " + (ms3-ms2));

        assertEquals(4458818L, tokenCounter);
    }


    @Test
    public void test_parser_100k() throws IOException {

        long ms1 = System.currentTimeMillis();
        File f = new File(base, "100k_inserts.sqlx");
        String content = StringUtils.file2string(f);
        long ms2 = System.currentTimeMillis();
        System.out.println("Load 100k_inserts into string, spent: " + (ms2-ms1));


        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2(f.getPath());
        ASTNode root = generator.parse(content);
        PsiFile psi = (PsiFile) root.getPsi();

        long ms3 = System.currentTimeMillis();
        System.out.println("Parser, time spent: " + (ms3-ms2));

        // Count children of root
        long nodeCounter =0;
        ASTNode curr = root.getFirstChildNode();
        while(curr != null){
            nodeCounter++;
            curr = curr.getTreeNext();
        }

        assertEquals(524768L, nodeCounter);
    }
}
