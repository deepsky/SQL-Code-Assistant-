package com.deepsky.tools;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PsiAdapterGeneratorTest extends TestCase {

    public void test0() throws RecognitionException, TokenStreamException, IOException {

        URL url = getClass().getClassLoader().getResource("PLSqlParserAdopted4Gen.java.txt");
        PsiAdapterGenerator gen = new PsiAdapterGenerator("com.company.xxx", new File(url.getPath()));
        gen.process();
    }

}
