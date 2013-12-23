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

package test.deepsky.lang.plsql;

import com.deepsky.lang.plsql.tree.MarkupGeneratorEx2;
import com.deepsky.utils.StringUtils;
import com.intellij.lang.ASTNode;
import com.intellij.mock.MockApplication;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.testFramework.UsefulTestCase;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class PlSqlParser_AbstractTest extends TestCase {

    private File base;
    private String baseDir;
    private String fileExt = ".sql";

    public void setUp() throws URISyntaxException {
        URL url = this.getClass().getClassLoader().getResource(baseDir);
        if(url == null){
            System.out.println("Specified directory does not exist: " + baseDir + ", will be used default one.");
            base = new File("./");
        } else {
            base = new File(url.toURI());
        }
    }


    protected PlSqlParser_AbstractTest(String baseDir, String fileExtension){
        this.baseDir = baseDir;
        this.fileExt = fileExtension;

        ApplicationManager.setApplication(
                new MockApplication(new Disposable() {
                    public void dispose() {
                    }
                }),
                new Disposable() {
                    public void dispose() {
                    }
                }
        );
    }
    protected PlSqlParser_AbstractTest(String baseDir){
        this(baseDir, ".sql");
    }

    protected ASTNode parseScript(String file) throws IOException {
        String content = StringUtils.file2string(new File(getBase(), file));
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        return generator.parse(content);
    }

    protected ASTNode parseScript(File file) throws IOException {
        String content = StringUtils.file2string(file);
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        return generator.parse(content);
    }

    protected ASTNode parseString(String content) {
        MarkupGeneratorEx2 generator = new MarkupGeneratorEx2();
        return generator.parse(content);
    }

    protected File getBase(){
        return base;
    }

    protected File getFilePath() {
        return new File(getTestDataPath(), getTestName(true).replaceAll("\\$", "/") + fileExt);
    }

    protected File getFilePathFor(String fileName) {
        return new File(getTestDataPath(), fileName + fileExt);
    }

    protected File getTestDataPath() {
        URL url = this.getClass().getClassLoader().getResource(baseDir);
        if (url == null) {
            return new File("./");
        } else {
            try {
                return new File(url.toURI());
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected String getTestName(boolean lowercaseFirstLetter) {
        String name = getName();
        return UsefulTestCase.getTestName(name, lowercaseFirstLetter);
    }


}
