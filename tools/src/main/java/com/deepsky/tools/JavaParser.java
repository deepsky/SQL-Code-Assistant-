package com.deepsky.tools;

import antlr.ASTFactory;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.collections.AST;
import com.deepsky.tools.generated.j15.JavaLexer;
import com.deepsky.tools.generated.j15.JavaRecognizer;
import com.deepsky.tools.generated.j15.JavaTokenTypes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Java source parser and class method iterator
 */
public class JavaParser {

    private AST root;
    private String packageName;
    private String className;

    private List<AST> methods = new ArrayList<AST>();

    private JavaParser(AST root){
        this.root = root;
        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {

            int type = c.getType();

            switch (type) {

                case JavaTokenTypes.PACKAGE_DEF:
                    packageName = parseImport(c);
                    packageName = packageName.substring(0, packageName.length()-1);
                    break;
                case JavaTokenTypes.CLASS_DEF:
                    //there should only be one class per file
                    className = parseClass(c);
                    break;
            }

            c = c.getNextSibling();

        } //end for each child
    }


    /**
     * Recursively parses an IMPORT node for the full name of the package
     * @param root AST
     * @return String
     */
    private static String parseImport(AST root) {

        String returner = "";
        AST c = root.getFirstChild();

        while (c != null) {
            int type = c.getType();
            if (type == JavaTokenTypes.DOT)
                returner = parseImport(c);
            else {
                if (!c.getText().equals(""))
                    returner += c.getText() + ".";
            }
            c = c.getNextSibling();
        }

        return returner;
    }


    public int methods(){
        return methods.size();
    }

    /**
     * Parses the given CLASS_DEF node, which contains the following nodes:
     * MODIFIERS, EXTENDS_CLAUSE, IMPLEMENTS_CLAUSE, OBJBLOCK, IDENT
     * @param root AST
     * @return String
     */
    private String parseClass(AST root) {

        String className = "";

        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {
            int type = c.getType();

            switch (type) {
                case JavaTokenTypes.IDENT:
                    className = c.getText();
                    break;
                case JavaTokenTypes.EXTENDS_CLAUSE:
                    //parseImplementsOrExtensions(c, is_a);
                    break;
                case JavaTokenTypes.IMPLEMENTS_CLAUSE:
                    //parseImplementsOrExtensions(c, realizes);
                    break;
                case JavaTokenTypes.OBJBLOCK:
                    parseObject(c);
                    break;
            }

            c = c.getNextSibling();

        } //end for each child

        return className;

    }


    private void parseObject(AST c1) {

        AST c = c1.getFirstChild();

        //for each child...
        while (c != null) {
            int type = c.getType();

            switch (type) {
                case JavaTokenTypes.VARIABLE_DEF:
                    //parseVariableDef(c, has_a);
                    break;
                case JavaTokenTypes.METHOD_DEF:
//                    parseMethodDef(c.getFirstChild());
                    methods.add(c.getFirstChild());
                    break;
            }

            c = c.getNextSibling();
        }
    }

/*
    private void parseMethodDef(AST c) {
        while (c != null) {
            int type = c.getType();
            switch (type) {
                case JavaTokenTypes.MODIFIERS:
                    String[] modifiers = parseMethodModifiers(c.getFirstChild());
                    int h = 0;
                    break;
                case JavaTokenTypes.TYPE:
                    //parseMethodDef(c.getFirstChild());
                    System.out.println("Method type: " + parseMethodType(c.getFirstChild()));
                    break;
                case JavaTokenTypes.IDENT:
                    // method name
                    System.out.println("Method name: " + c.getText());
                default:
                    int h1 =0;
                    break;
            }

            c = c.getNextSibling();
        }
    }
*/

    private String parseMethodType(AST c) {
        String type1 = null;
        boolean array = false;
        while (c != null) {
            int type = c.getType();
            switch (type) {
                case JavaTokenTypes.ARRAY_DECLARATOR:
                    array = true;
                    c = c.getFirstChild();
                    continue;
                default:
                    type1 = c.getText();
                    break;
            }
            c = c.getNextSibling();
        }

        return type1 + ((array)? "[]": "");
    }

    private String[] parseMethodModifiers(AST c) {
        List<String> out = new ArrayList<String>();
        while (c != null) {
            int type = c.getType();
            switch (type) {
                default:
                    out.add(c.getText());
                    break;
            }
            c = c.getNextSibling();
        }

        return out.toArray(new String[out.size()]);
    }

    public static JavaParser parse(File file) throws FileNotFoundException, TokenStreamException, RecognitionException {

        BufferedReader reader = new BufferedReader(new FileReader(file));

        //Create a scanner that reads from the input stream passed to us
        JavaLexer lexer = new JavaLexer(reader);
        lexer.setFilename(file.getPath());

        //Create a parser that reads from the scanner
        JavaRecognizer parser = new JavaRecognizer(lexer);
        parser.setFilename(file.getPath());

        //start parsing at the compilationUnit rule
        parser.compilationUnit();

//        ((CommonAST) parser.getAST()).setVerboseStringConversion(true, tokenNames);
        ASTFactory factory = new ASTFactory();
        AST r = factory.create(0, "AST ROOT");
        r.setFirstChild(parser.getAST());

        return new JavaParser(r);
    }

    public String getClassName(){
        return className;
    }

    public String getPackage(){
        return packageName;
    }

    /**
     * Iterate over class methods
     * @param processor
     */
    public void iterateOverMethods(ClassMethodProcessor processor){

        for(AST ast: methods){
            final List<String> throwsList = new ArrayList<String>();

            String[] modifiers = new String[0];
            ParameterDesc[] params = new ParameterDesc[0];
            String returnType = null;
            String methodName = null;
            AST c = ast;
            while (c != null) {
                int type = c.getType();
                switch (type) {
                    case JavaTokenTypes.MODIFIERS:
                        modifiers = parseMethodModifiers(c.getFirstChild());
                        break;
                    case JavaTokenTypes.TYPE:
                        returnType = parseMethodType(c.getFirstChild());
                        break;
                    case JavaTokenTypes.IDENT:
                        // method name
                        methodName = c.getText();
                        break;
                    case JavaTokenTypes.PARAMETERS:
                        params = parseParameters(c.getFirstChild());
                        break;
                    case JavaTokenTypes.THROWS_CLAUSE:
                        AST throws1 = c.getFirstChild();
                        while(throws1 != null){
                            throwsList.add(throws1.getText());
                            throws1 = throws1.getNextSibling();
                        }
                        break;
                    default:
                        break;
                }

                c = c.getNextSibling();
            }

            final String finalMethodName = methodName;
            final String finalReturnType = returnType;
            final String[] finalModifiers = modifiers;
            final ParameterDesc[] finalParams = params;
            processor.process(new MethodDesc() {
                public String name() {
                    return finalMethodName;
                }

                public String type() {
                    return finalReturnType;
                }

                public String[] modifiers() {
                    return finalModifiers;
                }

                public ParameterDesc[] params() {
                    return finalParams;
                }

                public String[] throwsExc() {
                    return throwsList.toArray(new String[throwsList.size()]);
                }
            });
        }
    }

    private ParameterDesc[] parseParameters(AST ast) {

        List<ParameterDesc> out = new ArrayList<ParameterDesc>();
        while(ast != null){
            if(ast.getType() == JavaTokenTypes.PARAMETER_DEF){
                String[] modifiers = null;
                String type = null;
                String name = null;
                AST child = ast.getFirstChild();
                while(child != null){
                    switch(child.getType()){
                        case JavaTokenTypes.MODIFIERS:
                            modifiers = parseMethodModifiers(child.getFirstChild());
                            break;
                        case JavaTokenTypes.TYPE :
                            type = parseMethodType(child.getFirstChild());
                            break;
                        case JavaTokenTypes.IDENT:
                            name = child.getText();
                            break;
                    }

                    child = child.getNextSibling();
                }

                out.add(new ParameterDescImpl(modifiers, type, name));
            }

            ast = ast.getNextSibling();
        }
        return out.toArray(new ParameterDesc[out.size()]);
    }

    public interface ClassMethodProcessor {
//        void process(String name, String[] modifiers, String returnType);
        void process(MethodDesc method);
    }

    public interface MethodDesc {
        String name();
        String type();
        String[] modifiers();
        ParameterDesc[] params();
        String[] throwsExc();
    }

    public interface ParameterDesc {
        String[] modifiers();
        String type();
        String name();
    }

    private class ParameterDescImpl implements ParameterDesc {

        String[] modifiers;
        String type;
        String name;

        public ParameterDescImpl(String[] modifiers, String type, String name){
            this.modifiers = modifiers;
            this.type = type;
            this.name = name;
        }

        public String[] modifiers() {
            return modifiers;
        }

        public String type() {
            return type;
        }

        public String name() {
            return name;
        }
    }
}
