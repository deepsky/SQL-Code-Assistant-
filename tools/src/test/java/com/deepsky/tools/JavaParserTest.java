package com.deepsky.tools;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import junit.framework.TestCase;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class JavaParserTest extends TestCase {

    public void test_PLSqlParserAdopted() throws FileNotFoundException, RecognitionException, TokenStreamException {
        URL url = getClass().getClassLoader().getResource("PLSqlParserAdopted.java.txt");
        JavaParser parser = JavaParser.parse(new File(url.getFile()));

        assertEquals("PLSqlParserAdopted", parser.getClassName());
        assertEquals("com.deepsky.generated.plsql.adopted", parser.getPackage());

        final int[] cnt = {0};
        parser.iterateOverMethods(new JavaParser.ClassMethodProcessor() {

            public void process(JavaParser.MethodDesc method) {

                switch(cnt[0]){
                    case 0:
                        assertEquals("Mismatch in method name", "__markRule", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 0, method.throwsExc().length);
                        break;
                    case 1:
                        assertEquals("Mismatch in method name", "process_wrapped_package", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "protected", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 0, method.throwsExc().length);
                        break;
                    case 2:
                        assertEquals("Mismatch in method name", "isTypeName", method.name());
                        assertEquals("Mismatch in method type", "boolean", method.type());
                        assertEquals("Mismatch in modifiers", 0, method.modifiers().length);
                        assertEquals("Mismatch in throws", 0, method.throwsExc().length);
                        break;
                    case 3:
                        assertEquals("Mismatch in method name", "recoverErrorAndCheckEOF", method.name());
                        assertEquals("Mismatch in method type", "boolean", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "protected", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "MismatchedTokenException", method.throwsExc()[1]);
                        break;
                    case 4:
                        assertEquals("Mismatch in method name", "no_one_should_call_me", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "RecognitionException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[1]);
                        break;
                    case 5:
                        assertEquals("Mismatch in method name", "identifier", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "RecognitionException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[1]);
                        break;
                    case 6:
                        assertEquals("Mismatch in method name", "start_rule", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "RecognitionException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[1]);
                        break;
                    case 7:
                        assertEquals("Mismatch in method name", "start_rule_inner", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "RecognitionException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[1]);
                        break;
                    case 8:
                        assertEquals("Mismatch in method name", "create_or_replace", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "RecognitionException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[1]);
                        break;
                    case 9:
                        assertEquals("Mismatch in method name", "package_body", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "RecognitionException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[1]);
                        break;
                    case 10:
                        assertEquals("Mismatch in method name", "package_spec", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "public", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 2, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "RecognitionException", method.throwsExc()[0]);
                        assertEquals("Mismatch in throws", "TokenStreamException", method.throwsExc()[1]);
                        break;
                    case 36:
                        assertEquals("Mismatch in method name", "object_name", method.name());
                        break;

                    case 699:
                        assertEquals("Mismatch in method name", "mk_tokenSet_325", method.name());
                        assertEquals("Mismatch in method type", "long[]", method.type());
                        assertEquals("Mismatch in modifiers", 3, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "private", method.modifiers()[0]);
                        assertEquals("Mismatch in modifiers", "static", method.modifiers()[1]);
                        assertEquals("Mismatch in modifiers", "final", method.modifiers()[2]);
                        assertEquals("Mismatch in throws", 0, method.throwsExc().length);
                        break;

                    case 700:
                        assertEquals("Mismatch in method name", "mk_tokenSet_326", method.name());
                        assertEquals("Mismatch in method type", "long[]", method.type());
                        assertEquals("Mismatch in modifiers", 3, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "private", method.modifiers()[0]);
                        assertEquals("Mismatch in modifiers", "static", method.modifiers()[1]);
                        assertEquals("Mismatch in modifiers", "final", method.modifiers()[2]);
                        assertEquals("Mismatch in throws", 0, method.throwsExc().length);
                        break;

                    case 701:
                        assertEquals("Mismatch in method name", "just_for_checking", method.name());
                        assertEquals("Mismatch in method type", "void", method.type());
                        assertEquals("Mismatch in modifiers", 1, method.modifiers().length);
                        assertEquals("Mismatch in modifiers", "static", method.modifiers()[0]);
                        assertEquals("Mismatch in throws", 1, method.throwsExc().length);
                        assertEquals("Mismatch in throws", "Exception", method.throwsExc()[0]);

                        assertEquals("Mismatch in params", 3, method.params().length);

                        assertEquals("Mismatch in params", 0, method.params()[0].modifiers().length);
                        assertEquals("Mismatch in params", "arg1", method.params()[0].name());
                        assertEquals("Mismatch in params", "String", method.params()[0].type());

                        assertEquals("Mismatch in params", 1, method.params()[1].modifiers().length);
                        assertEquals("Mismatch in params", "final", method.params()[1].modifiers()[0]);
                        assertEquals("Mismatch in params", "arg2", method.params()[1].name());
                        assertEquals("Mismatch in params", "String", method.params()[1].type());

                        assertEquals("Mismatch in params", 0, method.params()[2].modifiers().length);
                        assertEquals("Mismatch in params", "arg3", method.params()[2].name());
                        assertEquals("Mismatch in params", "long[]", method.params()[2].type());
                        break;
                }
                cnt[0]++;
            }
        });

        assertEquals(702, cnt[0]);

    }
}
