package antlrtest;

// Standard I/O
import java.io.IOException;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
// ANTLR Library
import antlr.collections.AST;
import antlr.debug.misc.ASTFrame;
import antlr.RecognitionException;
import antlr.TokenStreamException;
import antlr.CommonAST;
import antlr.ASTFactory;
import com.deepsky.tools.generated.j15.JavaLexer;
import com.deepsky.tools.generated.j15.JavaRecognizer;
import com.deepsky.tools.generated.j15.JavaTokenTypes;
// Events
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
// Data
import java.util.ArrayList;

/**
 * <p>Title: Main</p>
 *
 * <p>Description: Takes a Java source code file as an argument, parses it,
 * displays it content in a tree, and determines its imports and relationships
 * with other classes.</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * @author John Valentino II
 * @version 1.0
 */
public class Main implements JavaTokenTypes {

    /**
     * Entry points for the program
     * @param args String[]
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Usage: main <java file name>");
            System.exit(0);
        }

        try {

            ArrayList<String> imports = new ArrayList<String>();
            ArrayList<String> is_a = new ArrayList<String>();
            ArrayList<String> realizes = new ArrayList<String>();
            ArrayList<String> has_a = new ArrayList<String>();

            //Open the given file
            File file = new File(args[0]);
            String fileName = file.getName();
            BufferedReader reader = new BufferedReader(new FileReader(file));

            //Create a scanner that reads from the input stream passed to us
            JavaLexer lexer = new JavaLexer(reader);
            lexer.setFilename(fileName);

            //Create a parser that reads from the scanner
            JavaRecognizer parser = new JavaRecognizer(lexer);
            parser.setFilename(fileName);

            //start parsing at the compilationUnit rule
            parser.compilationUnit();

            //Get the imports and relationships for this Java file and returns
            //the full class name
            String fullClassName = parseTree(parser.getAST(),
                    parser.getTokenNames(),
                    imports, is_a, realizes, has_a);

            System.out.println("Full Class Name: " + fullClassName);

            //display imports
            System.out.println("Imports:");
            for (int i = 0; i < imports.size(); i++) {
                System.out.println("\t" + imports.get(i));
            }

            //display the classes that this class inherits from
            System.out.println("Extends (Is A):");
            for (int i = 0; i < is_a.size(); i++) {
                System.out.println("\t" + is_a.get(i));
            }

            //display the "has a" relationships of this class
            System.out.println("Has A: ");
            for (int i = 0; i < has_a.size(); i++) {
                System.out.println("\t" + has_a.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (RecognitionException re) {
            re.printStackTrace();
        } catch (TokenStreamException tse) {
            tse.printStackTrace();
        }
    }


    /**
     * Parses the AST Root Node for a Java class. The AST Root node contains
     * the following nodes: PACKAGE_DEF, IMPORT, CLASS_DEF
     * @param t AST
     * @param tokenNames String[]
     * @param imports ArrayList
     * @param is_a ArrayList
     * @param realizes ArrayList
     * @param has_a ArrayList
     * @return String
     */
    private static String parseTree(AST t, String[] tokenNames,
                                    ArrayList<String> imports,
                                    ArrayList<String> is_a,
                                    ArrayList<String> realizes,
                                    ArrayList<String> has_a) {

        String packageName = "";
        String className = "";

        ((CommonAST) t).setVerboseStringConversion(true, tokenNames);
        ASTFactory factory = new ASTFactory();
        AST r = factory.create(0, "AST ROOT");
        r.setFirstChild(t);

        displayFrame(r);

        AST c = r.getFirstChild();

        //for each child...
        while (c != null) {

            int type = c.getType();

            switch (type) {

                case PACKAGE_DEF:
                    packageName = getFormattedImport(c);
                    break;
                case IMPORT:
                    //get the import in the format "java.io.File"
                    String importText = getFormattedImport(c);
                    //add this import
                    imports.add(importText);
                    break;
                case CLASS_DEF:
                    //there should only be one class per file
                    className = parseClass(c, is_a, realizes, has_a);
                    break;
            }

            c = c.getNextSibling();

        } //end for each child

        return packageName + "." + className;

    }

    /**
     * Displays the tree in a frame
     * @param r AST
     */
    private static void displayFrame(AST r) {
        final ASTFrame frame = new ASTFrame("Java AST", r);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.setVisible(false);
                frame.dispose();
                System.exit(0);
            }
        });
    }

    /**
     * Parses an IMPORT node and returns the full name
     * of the package
     * @param root AST
     * @return String
     */
    private static String getFormattedImport(AST root) {

        //get the import in the format "java.io.File."
        String importText = parseImport(root);

        //trim off the last character making it "java.io.File"
        importText = importText.substring(0, importText.length() - 1);

        return importText;

    }

    /**
     * Recursively parses an IMPORT node for the full name of the package
     * @param root AST
     * @return String
     */
    private static String parseImport(AST root) {

        String returner = "";

        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {
            int type = c.getType();
            if (type == DOT)
                returner = parseImport(c);
            else {
                if (!c.getText().equals(""))
                    returner += c.getText() + ".";
            }
            c = c.getNextSibling();
        } //end for each child

        return returner;

    }

    /**
     * Parses the given CLASS_DEF node, which contains the following nodes:
     * MODIFIERS, EXTENDS_CLAUSE, IMPLEMENTS_CLAUSE, OBJBLOCK, IDENT
     * @param root AST
     * @param is_a ArrayList
     * @param realizes ArrayList
     * @param has_a ArrayList
     * @return String
     */
    private static String parseClass(AST root,
                                     ArrayList<String> is_a,
                                     ArrayList<String> realizes,
                                     ArrayList<String> has_a) {

        String className = "";

        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {
            int type = c.getType();

            switch (type) {
                case IDENT:
                    className = c.getText();
                    break;
                case EXTENDS_CLAUSE:
                    parseImplementsOrExtensions(c, is_a);
                    break;
                case IMPLEMENTS_CLAUSE:
                    parseImplementsOrExtensions(c, realizes);
                    break;
                case OBJBLOCK:
                    parseObject(c, has_a);
                    break;
            }

            c = c.getNextSibling();

        } //end for each child

        return className;

    }

    /**
     * Parses the given IMPLEMENTS_CLAUSE or EXTENDS_CLAUSE to determine the
     * classes that are implements or extended
     * @param root AST
     * @param realizes ArrayList
     */
    private static void parseImplementsOrExtensions(AST root,
                                                    ArrayList<String> realizes) {

        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {

            int type = c.getType();

            switch (type) {
                case IDENT:
                    realizes.add(c.getText());
                    break;
            }

            c = c.getNextSibling();

        } //end for each child

    }

    /**
     * Parses the given OBJBLOCK node, which contains the following nodes:
     * VARIABLE_DEF, METHOD_DEF, CLASS_DEF, CTOR_DEF, IDENT
     * @param root AST
     * @param has_a ArrayList
     */
    private static void parseObject(AST root, ArrayList<String> has_a) {

        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {
            int type = c.getType();

            switch (type) {
                case VARIABLE_DEF:
                    parseVariableDef(c, has_a);
                    break;
            }

            c = c.getNextSibling();

        } //end for each child

    }

    /**
     * Parses the given VARIABLE_DEF node, which contains the following nodes:
     * MODIFIERS, TYPE, IDENT
     * @param root AST
     * @param has_a ArrayList
     */
    private static void parseVariableDef(AST root, ArrayList<String> has_a) {
        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {

            int type = c.getType();

            switch (type) {
                case TYPE:
                    parseType(c, has_a);
                    break;
            }
            c = c.getNextSibling();

        } //end for each child

    }

    /**
     * Parses the given TYPE node for its class names
     * @param root AST
     * @param has_a ArrayList
     */
    private static void parseType(AST root, ArrayList<String> has_a) {
        AST c = root.getFirstChild();

        //for each child...
        while (c != null) {

            int type = c.getType();

            switch (type) {
                case IDENT:
                    has_a.add(c.getText());
                    break;
            }
            c = c.getNextSibling();

        } //end for each child

    }

}