package com.deepsky.tools;

import antlr.RecognitionException;
import antlr.TokenStreamException;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class PsiAdapterGenerator {

    String targetPackage;
    File javaSource;

    //File target;
    public PsiAdapterGenerator(String targetPackage, File javaSource){
        this.targetPackage = targetPackage;
        this.javaSource = javaSource;
    }

    public void process() throws IOException, RecognitionException, TokenStreamException {
        JavaParser parser = JavaParser.parse(javaSource);
        String className = parser.getClassName();
        String packageName = parser.getPackage();
        System.out.println("Info: Target package: " + targetPackage);
        System.out.println("Info: Java class source was parsed: " + packageName + "." + className);
        System.out.println("Info: Java class after generation: " + packageName + "." + className + "Ext");
        System.out.println("Info: Number of methods found in class: " + parser.methods());

        System.out.println("Run PsiBuilder adapter generator ...");


        FileOutputStream out = new FileOutputStream(new File(className + "Ext.java"));
        extractClass(parser, out);
        out.flush();
        out.close();
        System.out.println("Done");
    }

    static public void main(String[] args) throws IOException, RecognitionException, TokenStreamException {

        if(args == null || args.length != 2){
            System.out.println("Error: check arguments");
            System.out.println("Usage: java PsiAdapterGenerator <Target Package> <Path to the class being processed>");
            System.exit(-1);
        }

        File cp = new File(args[1]);
        if(!cp.exists()){
            System.out.println("Error: Java source does not exist: " + cp);
            System.exit(-1);
        }

        PsiAdapterGenerator extr = new PsiAdapterGenerator(args[0], cp);
        extr.process();
    }


    public void extractClass(JavaParser parser, final OutputStream output) {

        try {
            output.write(("package " + targetPackage + ";\n\n").getBytes());
            output.write("import com.intellij.lang.PsiBuilder;\n".getBytes());
            output.write("import antlr.*;\n\n".getBytes());

            String header = "public abstract class " + parser.getClassName() + "Ext extends "
                    + parser.getPackage() + "." + parser.getClassName() + " {\n";
            output.write(header.getBytes());
            output.write("\tPsiBuilder builder;\n".getBytes());

            StringBuilder bb0 = new StringBuilder();
            bb0.append("\tabstract public int getPredicting();\n\n");
            output.write(bb0.toString().getBytes());


            StringBuilder bb = new StringBuilder();
            bb.append("\tpublic ").append(parser.getClassName() + "Ext").append("(TokenStream t, PsiBuilder b){\n");
            bb.append("\t\tsuper(t);\n");
            bb.append("\t\tbuilder = b;\n");
            bb.append("\t}\n\n");

            output.write(bb.toString().getBytes());

            final int[] cnt = {0};
            final int[] skipped = new int[1];
            parser.iterateOverMethods(new JavaParser.ClassMethodProcessor(){
                public void process(JavaParser.MethodDesc method) {
                    Set<String> modifiers = new HashSet<String>(Arrays.asList(method.modifiers()));

                    if(!method.name().startsWith("__") && modifiers.contains("public")){
                        String s = createFuncCallWrapper(method) + "\n\n";
                        try {
                            output.write(s.getBytes());
                            output.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                            System.exit(-1);
                        }
                        cnt[0]++;
                    } else {
                        skipped[0]++;
                    }
                }
            });

            System.out.println("Number of processed methods: " + cnt[0] + ", skipped:  " + skipped[0]);
/*
            // read and write mappings PlSqlTokenTypes -> PlSqlElementTypes
            // TODO !!!! - InterfaceExtractor class should be in the same package as GrammarConvertor class
            File base = new File(this.getClass().getClassLoader().getResource(this.getClass().getPackage().getName().replace(".", "/")).toURI());
            if(!new File(base, "mapping.txt").exists()){
                System.out.println("File mapping - mapping.txt does not exist!");
                System.exit(-1);
            }

            BufferedReader rd = new BufferedReader(new FileReader(new File(base, "mapping.txt")));
            String s;
            while((s=rd.readLine()) != null){
                String s1 = s + "\n";
                output.write(s1.getBytes());
            }

*/
            String tail = "}\n";
            output.write(tail.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String getReturnType(Method m) {
        return getTypeName(m.getReturnType());
    }

    String[] getParamTypes(Method m) {
        List<String> out = new ArrayList<String>();
        Class[] params = m.getParameterTypes(); // avoid clone
        for (int j = 0; j < params.length; j++) {
            out.add(getTypeName(params[j]));
        }

        return out.toArray(new String[out.size()]);
    }



    String createFuncCallWrapper(JavaParser.MethodDesc method) {
        // antlr.CommonToken function_call(antlr.collections.AST arg0) throws antlr.RecognitionException,antlr.TokenStreamException {
        //    Builder.Marker m = builder.mark()
        //    antlr.CommonToken ret = super.function_call(arg0);
        //    if(returnType > 0 ){
        //      m.done( findPlSqlElemType(returnType) );
        //      returnType = -1;
        //    } else {
        //      m.rollbackTo();
        //    }
        //    return ret;
        // }
        StringBuilder b = new StringBuilder();
        b.append("\t").append(getFuncSignature(method)).append("{\n");
        if (!method.type().equals("void")) {
            b.append("\t\t").append(method.type()).append(" ret;\n");
        }
        b.append("\t\tif (getPredicting() == 0) {\n");
        b.append("\t\t\tPsiBuilder.Marker m = builder.mark();\n");
        b.append("\t\t\ttry {\n");

        if (method.type().equals("void")) {
            b.append("\t\t\tsuper.").append(method.name()).append("(");
        } else {
            b.append("\t\t\tret =");
            b.append("super.").append(method.name()).append("(");
        }

        for (int i=0; i<method.params().length; i++) {
            if (i > 0) {
                b.append(", ");
            }
            b.append("arg").append(i++);
        }
        b.append(");\n");

        b.append("\t\t\tif(returnType > 0 ){\n");
        b.append("\t\t\t\tm.done( ANTLRType2AdoptedType.type2etype[returnType] );\n");
        b.append("\t\t\t} else {\n");
        b.append("\t\t\t\tm.drop();\n");
        b.append("\t\t\t}\n");

        b.append("\t\t\t} catch(antlr.RecognitionException ex){\n");
        b.append("\t\t\t\tm.drop();\n");
        b.append("\t\t\t\tthrow ex;\n");
        b.append("\t\t\t}\n");

        b.append("\t\t} else {\n");
        if (!method.type().equals("void")) {
            b.append("\t\tret = ");
            b.append("super.").append(method.name()).append("(");
            for (int i=0; i<method.params().length; i++) {
                if (i > 0) {
                    b.append(", ");
                }
                b.append("arg").append(i++);
            }
            b.append(");\n");
        } else {
            b.append("\t\t\tsuper.").append(method.name()).append("(");
            for (int i=0; i<method.params().length; i++) {
                if (i > 0) {
                    b.append(", ");
                }
                b.append("arg").append(i++);
            }
            b.append(");\n");
        }

        b.append("\t\t}\n");
        b.append("\t\treturnType = -1;\n");

        if (!method.type().equals("void")){
            b.append("\t\treturn ret;\n");
        }

        b.append("\t}");
        return b.toString();
    }

    private String getFuncSignature(JavaParser.MethodDesc method) {
        try {
            StringBuilder sb = new StringBuilder();
            int mod = method.modifiers().length;
            if (mod != 0) {
                if(new HashSet<String>(Arrays.asList(method.modifiers())).contains("public")){
                    sb.append("public ");
                }
            }
            sb.append(method.type() + " ");
            sb.append(method.name() + "(");
            JavaParser.ParameterDesc[] params = method.params();
            for(int j =0; j<params.length; j++){
                sb.append(params[j].type()).append(" arg" + j);
                if (j < (params.length - 1))
                    sb.append(",");
            }
            sb.append(")");

            String[] throwsList = method.throwsExc();
            if(throwsList.length > 0){
                sb.append(" throws ");
                for (int k = 0; k < throwsList.length; k++) {
                    sb.append(throwsList[k]);
                    if (k < (throwsList.length - 1))
                        sb.append(",");
                }
            }

            return sb.toString();
        } catch (Exception e) {
            return "<" + e + ">";
        }
    }


    String createFuncCallWrapper(Method method) {
        // antlr.CommonToken function_call(antlr.collections.AST arg0) throws antlr.RecognitionException,antlr.TokenStreamException {
        //    Builder.Marker m = builder.mark()
        //    antlr.CommonToken ret = super.function_call(arg0);
        //    if(returnType > 0 ){
        //      m.done( findPlSqlElemType(returnType) );
        //      returnType = -1;
        //    } else {
        //      m.rollbackTo();
        //    }
        //    return ret;
        // }
        StringBuilder b = new StringBuilder();
        b.append("\t").append(getFuncSignature(method)).append("{\n");
        if (!getReturnType(method).equals("void")) {
            b.append("\t\t").append(getReturnType(method)).append(" ret;\n");
        }
        b.append("\t\tif (getPredicting() == 0) {\n");
//        b.append("\t\t\tint local = depth++;\n");
        b.append("\t\t\tPsiBuilder.Marker m = builder.mark();\n");
        b.append("\t\t\ttry {\n");

        if (getReturnType(method).equals("void")) {
            b.append("\t\t\tsuper.").append(method.getName()).append("(");
        } else {
//            b.append("\exec\exec").append(getReturnType(m)).append(" ret = ");
            b.append("\t\t\tret =");
            b.append("super.").append(method.getName()).append("(");
        }

        int i = 0;
        for (String s : getParamTypes(method)) {
            if (i > 0) {
                b.append(", ");
            }
            b.append("arg").append(i++);
        }
        b.append(");\n");

        b.append("\t\t\tif(returnType > 0 ){\n");
        b.append("\t\t\t\tm.done( ANTLRType2AdoptedType.type2etype[returnType] );\n");
        b.append("\t\t\t} else {\n");
        b.append("\t\t\t\tm.drop();\n");
        b.append("\t\t\t}\n");

        b.append("\t\t\t} catch(antlr.RecognitionException ex){\n");
        b.append("\t\t\t\tm.drop();\n");
        b.append("\t\t\t\tthrow ex;\n");
        b.append("\t\t\t}\n");

        b.append("\t\t} else {\n");
        if (!getReturnType(method).equals("void")) {
//            b.append("\exec\exec").append(getReturnType(m)).append(" ret = ");
            b.append("\t\tret = ");
            b.append("super.").append(method.getName()).append("(");
            i = 0;
            for (String s : getParamTypes(method)) {
                if (i > 0) {
                    b.append(", ");
                }
                b.append("arg").append(i++);
            }
            b.append(");\n");
        } else {
            b.append("\t\t\tsuper.").append(method.getName()).append("(");
            i = 0;
            for (String s : getParamTypes(method)) {
                if (i > 0) {
                    b.append(", ");
                }
                b.append("arg").append(i++);
            }
            b.append(");\n");
        }

        b.append("\t\t}\n");
        b.append("\t\treturnType = -1;\n");

        if (!getReturnType(method).equals("void")){
            b.append("\t\treturn ret;\n");
        }

        b.append("\t}");
        return b.toString();
    }

    private String getFuncSignature(Method method) {
        try {
            StringBuilder sb = new StringBuilder();
            int mod = method.getModifiers();
            if (mod != 0) {
                if (Modifier.toString(mod).contains("public")) {
                    sb.append("public ");
                }
            }
            sb.append(getTypeName(method.getReturnType()) + " ");
            sb.append(method.getName() + "(");
            Class[] params = method.getParameterTypes(); // avoid clone
            for (int j = 0; j < params.length; j++) {
                sb.append(getTypeName(params[j])).append(" arg" + j);
                if (j < (params.length - 1))
                    sb.append(",");
            }
            sb.append(")");
            Class[] exceptions = method.getExceptionTypes(); // avoid clone
            if (exceptions.length > 0) {
                sb.append(" throws ");
                for (int k = 0; k < exceptions.length; k++) {
                    sb.append(exceptions[k].getName());
                    if (k < (exceptions.length - 1))
                        sb.append(",");
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "<" + e + ">";
        }
    }



/*
* Utility routine to paper over array type names
*/

    static String getTypeName(Class type) {
        if (type.isArray()) {
            try {
                Class cl = type;
                int dimensions = 0;
                while (cl.isArray()) {
                    dimensions++;
                    cl = cl.getComponentType();
                }
                StringBuffer sb = new StringBuffer();
                sb.append(cl.getName());
                for (int i = 0; i < dimensions; i++) {
                    sb.append("[]");
                }
                return sb.toString();
            } catch (Throwable e) { /*FALLTHRU*/ }
        }
        return type.getName();
    }


}
