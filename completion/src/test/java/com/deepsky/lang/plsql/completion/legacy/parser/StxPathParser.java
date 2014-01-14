package com.deepsky.lang.plsql.completion.legacy.parser;

import antlr.Token;
import antlr.TokenStreamException;
import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.Step;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathLexer;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.SyntaxTreePathTokenTypes;
import com.deepsky.lang.plsql.completion.legacy.steps.ASTNodeStep;
import com.deepsky.lang.plsql.completion.legacy.steps.AllNodesStep;
import com.deepsky.lang.plsql.completion.legacy.steps.PsiElementStep;
import com.deepsky.lang.plsql.psi.PlSqlElement;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Stack;


public class StxPathParser {

    final static int INIT_LOCATION = 0;
    final static int LOCATION_STARTED = 1;
    final static int PARAMETER_REF = 2;
    final static int ASTNODE_IDENT = 3;
    final static int PSIELEMENT_IDENT = 4;


    final static private String[] PSI_ELEMENT_PKGS = new String[]{
            "com.deepsky.lang.plsql.psi",
            "com.deepsky.lang.plsql.psi.ctrl",
            "com.deepsky.lang.plsql.psi.ddl",
            "com.deepsky.lang.plsql.psi.names",
            "com.deepsky.lang.plsql.psi.ref",
            "com.deepsky.lang.plsql.psi.types"
    };

    final static private Class[] ASTNODE_TYPE_CLASSES = new Class[]{
            PlSqlElementTypes.class, PlSqlTokenTypes.class
    };


    @NotNull
    public static Step parse(@NotNull String stxPath) throws StxParseException {

        Reader r = new StringReader(stxPath);
        SyntaxTreePathLexer lexer = new SyntaxTreePathLexer(r);

        int state = INIT_LOCATION;
        int identType = PSIELEMENT_IDENT;
        int paramRef = -1;


        Stack<Step> stack = new Stack<Step>();
        try {
            while (true) {
                Token t = lexer.nextToken();
                if (t.getType() == SyntaxTreePathTokenTypes.EOF) {
                    break;
                }

                switch (t.getType()) {
                    case SyntaxTreePathTokenTypes.DOUBLE_SLASH:
                        if (state != INIT_LOCATION) {
                            throw new Exception();
                        }

                        stack.push(new AllNodesStep());
                        state = LOCATION_STARTED;
                        break;
                    case SyntaxTreePathTokenTypes.SLASH:
                        if (state != INIT_LOCATION) {
                            throw new Exception();
                        }
                        state = LOCATION_STARTED;
                        paramRef = -1;
                        break;
                    case SyntaxTreePathTokenTypes.NUMBER:
                        if (state != LOCATION_STARTED) {
                            throw new Exception();
                        }

                        paramRef = Integer.parseInt(t.getText());
                        state = PARAMETER_REF;
                        break;
                    case SyntaxTreePathTokenTypes.SHARP:
                        if (!(state == LOCATION_STARTED || state == PARAMETER_REF)) {
                            throw new Exception();
                        }

                        state = ASTNODE_IDENT;
                        identType = ASTNODE_IDENT;
                        break;
                    case SyntaxTreePathTokenTypes.DOLLAR:
                        if (!(state == LOCATION_STARTED || state == PARAMETER_REF)) {
                            throw new Exception();
                        }

                        state = PSIELEMENT_IDENT;
                        identType = PSIELEMENT_IDENT;
                        break;
                    case SyntaxTreePathTokenTypes.IDENTIFIER:
                        if (!(state == LOCATION_STARTED || state == ASTNODE_IDENT || state == PSIELEMENT_IDENT)) {
                            throw new Exception();
                        }

                        stack.push(createNamedStep(identType, paramRef, t.getText() ));
                        state = INIT_LOCATION;
                        break;
                }
            }

        } catch (TokenStreamException e) {
            throw new StxParseException(e);
        } catch (StxParseException e) {
            throw e;
        } catch (Exception e) {
            throw new StxParseException(e);
        }

        if(stack.size() == 0){
            throw new StxParseException("Not valid StxPath: " + stxPath);
        }

        Step last = stack.pop();
        while(stack.size() != 0){
            Step _last = stack.pop();
            _last.setChild(last);
            last = _last;
        }

        return last;
    }


    /**
     * Create ASTNodeStep or PsiElementStep
     * @param identType
     * @param paramRef
     * @param name
     * @return
     */
    private static Step createNamedStep(int identType, int paramRef, String name) throws StxParseException {

        if(identType == ASTNODE_IDENT){
            for(Class astClazz: ASTNODE_TYPE_CLASSES){
                try {
                    Field field = astClazz.getDeclaredField(name);
                    IElementType eType = (IElementType) field.get(null);
                    return new ASTNodeStep(eType, paramRef);
                } catch (NoSuchFieldException e) {
                    // Check ancestors
                    for(Class ancestor: astClazz.getInterfaces()) {
                        try {
                            Field field = ancestor.getDeclaredField(name);
                            IElementType eType = (IElementType) field.get(null);
                            return new ASTNodeStep(eType, paramRef);
                        } catch (NoSuchFieldException ignored) {
                        } catch (IllegalAccessException e1) {
                            e1.printStackTrace();
                        }
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                // keep searching
            }

            throw new StxParseException("There is no type with the name: " + name);
        } else {
            // PSIELEMENT_TYPE
            for(String pkg: PSI_ELEMENT_PKGS){
                try {
                    Class[] classes = getClasses(pkg);
                    for(Class clazz: classes){
                        if( clazz.getSimpleName().equals(name) && PlSqlElement.class.isAssignableFrom(clazz)){
                            return new PsiElementStep(clazz, paramRef);
                        }
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        throw new StxParseException("Cannot identify: name");
    }


    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<File>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class> classes = new ArrayList<Class>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[classes.size()]);
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
//            if (file.isDirectory()) {
//                assert !file.getName().contains(".");
//                classes.addAll(findClasses(file, packageName + "." + file.getName()));
//            } else
            if (file.getName().endsWith(".class")) {
                classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
            }
        }
        return classes;
    }

}
