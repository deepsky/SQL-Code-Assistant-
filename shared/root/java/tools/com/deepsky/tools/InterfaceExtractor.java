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

package com.deepsky.tools;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.net.URISyntaxException;

public class InterfaceExtractor {

    File target;
    public InterfaceExtractor(File target){
        this.target = target;
    }

    static public void main(String[] args) throws IOException {

        if(args == null || args.length == 0){
            System.out.println("A target directory was not defined");
            System.exit(-1);
        }

        File f = new File(args[0]);
        if(!f.exists()){
            System.out.println("A target directory does not exist: " + f);
            System.exit(-1);
        }

        InterfaceExtractor extr = new InterfaceExtractor(f);
//        extr.extractInterface("com.deepsky.generated.plsql.adopted.PLSqlParserAdopted", System.out);

        System.out.println("Run interface extractor");
        FileOutputStream out = new FileOutputStream(new File(f, "PLSqlParserAdoptedExt.java"));
        extr.extractClass("com.deepsky.generated.plsql.adopted.PLSqlParserAdopted", out);
        out.close();
        System.out.println("Done");
    }

    public void printMethodSign(String clazz, OutputStream output) {

        try {
            Class target = Class.forName(clazz);

            Method[] methods = target.getMethods();
            for (Method m : methods) {
                String s = toString(m);
                s += "\n";
                output.write(s.getBytes());

            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void extractInterface(String clazz, OutputStream output) {

        try {
            Class target = Class.forName(clazz);

            String header = "public interface " + target.getSimpleName() + "Generic {\n";
            output.write(header.getBytes());
            Method[] methods = target.getMethods();
            for (Method m : methods) {
                if (m.getDeclaringClass().getName().equals(clazz)) {
                    String s = "\t" + toString(m, clazz) + ";";
                    s += "\n";
                    output.write(s.getBytes());
                }
            }
            String tail = "}\n";
            output.write(tail.getBytes());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void extractClass(String clazz, OutputStream output) {

        try {
            Class target = Class.forName(clazz);

            output.write("package com.deepsky.lang.parser.plsql;\n\n".getBytes());
            output.write("import com.intellij.lang.PsiBuilder;\n".getBytes());
            output.write("import com.deepsky.generated.plsql.PLSqlTokenTypes;\n".getBytes());
            output.write("import com.deepsky.lang.common.PlSqlTokenTypes;\n\n".getBytes());
            output.write("import antlr.TokenStream;\n".getBytes());
            output.write("import java.util.Map;\n".getBytes());
            output.write("import java.util.HashMap;\n".getBytes());
            output.write("import com.intellij.psi.tree.IElementType;\n".getBytes());
            output.write("import com.deepsky.lang.parser.plsql.PLSqlTypesAdopted;\n\n".getBytes());


            String header = "public abstract class " + target.getSimpleName() + "Ext extends " + clazz + " {\n";
            output.write(header.getBytes());
            output.write("\tPsiBuilder builder;\n".getBytes());
//            output.write("\tint returnType = -1;\n\n".getBytes());

            StringBuilder bb0 = new StringBuilder();
            bb0.append("\tabstract public int getPredicting();\n\n");
            output.write(bb0.toString().getBytes());

//            IElementType elem = type2etype.get(exec);
//            if(elem != null){
//                return elem;
//            } else {
//                return PlSqlTokenTypes.KEYWORD;
//            }

/*
            bb0.append("\tpublic IElementType findPlSqlElemType(int t){\n");
            bb0.append("\t\tIElementType elem = type2etype.get(t);\n");
            bb0.append("\t\tif(elem != null){\n");
            bb0.append("\t\t\treturn elem;\n");
            bb0.append("\t\t} else {\n");
            bb0.append("\t\t\treturn PlSqlTokenTypes.PROXY_KEYWORD;\n");
            bb0.append("\t\t}\n");
            bb0.append("\t}\n\n");
            output.write(bb0.toString().getBytes());
*/

            StringBuilder bb = new StringBuilder();
            bb.append("\tpublic ").append(target.getSimpleName() + "Ext").append("(TokenStream t, PsiBuilder b){\n");
            bb.append("\t\tsuper(t);\n");
            bb.append("\t\tbuilder = b;\n");
            bb.append("\t}\n\n");

            output.write(bb.toString().getBytes());
            Method[] methods = target.getMethods();
            for (Method m : methods) {
                if (m.getDeclaringClass().getName().equals(clazz)) {
                    if (!m.getName().startsWith("__")) {
                        String s = createFuncCallWrapper(m, clazz) + "\n\n";
                        output.write(s.getBytes());
                    }
                }
            }

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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
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

    String createFuncCallWrapper(Method m, String clazz) {
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
        b.append("\t").append(getFuncSign(m)).append("{\n");
        if (!getReturnType(m).equals("void")) {
            b.append("\t\t").append(getReturnType(m)).append(" ret;\n");
        }
        b.append("\t\tif (getPredicting() == 0) {\n");
//        b.append("\t\t\tint local = depth++;\n");
        b.append("\t\t\tPsiBuilder.Marker m = builder.mark();\n");
        b.append("\t\t\ttry {\n");

        if (getReturnType(m).equals("void")) {
            b.append("\t\t\tsuper.").append(m.getName()).append("(");
        } else {
//            b.append("\exec\exec").append(getReturnType(m)).append(" ret = ");
            b.append("\t\t\tret =");
            b.append("super.").append(m.getName()).append("(");
        }

        int i = 0;
        for (String s : getParamTypes(m)) {
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
        if (!getReturnType(m).equals("void")) {
//            b.append("\exec\exec").append(getReturnType(m)).append(" ret = ");
            b.append("\t\tret = ");
            b.append("super.").append(m.getName()).append("(");
            i = 0;
            for (String s : getParamTypes(m)) {
                if (i > 0) {
                    b.append(", ");
                }
                b.append("arg").append(i++);
            }
            b.append(");\n");
        } else {
            b.append("\t\t\tsuper.").append(m.getName()).append("(");
            i = 0;
            for (String s : getParamTypes(m)) {
                if (i > 0) {
                    b.append(", ");
                }
                b.append("arg").append(i++);
            }
            b.append(");\n");
        }

        b.append("\t\t}\n");
        b.append("\t\treturnType = -1;\n");

        if (!getReturnType(m).equals("void")){
            b.append("\t\treturn ret;\n");
        }

        b.append("\t}");
        return b.toString();
    }

    private String getFuncSign(Method m) {
        try {
            StringBuffer sb = new StringBuffer();
            int mod = m.getModifiers();
            if (mod != 0) {
                if (Modifier.toString(mod).contains("public")) {
                    sb.append("public ");
                }
            }
            sb.append(getTypeName(m.getReturnType()) + " ");
//                sb.append(typeName(m.getDeclaringClass()) + ".");
            sb.append(m.getName() + "(");
            Class[] params = m.getParameterTypes(); // avoid clone
            for (int j = 0; j < params.length; j++) {
                sb.append(getTypeName(params[j])).append(" arg" + j);
                if (j < (params.length - 1))
                    sb.append(",");
            }
            sb.append(")");
            Class[] exceptions = m.getExceptionTypes(); // avoid clone
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

    public String toString(Method m, String clazz) {

        try {
            StringBuffer sb = new StringBuffer();
//                int mod = m.getModifiers();
//                if (mod != 0) {
//                    sb.append(Modifier.toString(mod) + " ");
//                }
            sb.append(getTypeName(m.getReturnType()) + " ");
//                sb.append(typeName(m.getDeclaringClass()) + ".");
            sb.append(m.getName() + "(");
            Class[] params = m.getParameterTypes(); // avoid clone
            for (int j = 0; j < params.length; j++) {
                sb.append(getTypeName(params[j])).append(" arg" + j);
                if (j < (params.length - 1))
                    sb.append(",");
            }
            sb.append(")");
            Class[] exceptions = m.getExceptionTypes(); // avoid clone
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

    public String toString(Method m) {
        try {
            StringBuffer sb = new StringBuffer();
            int mod = m.getModifiers();
            if (mod != 0) {
                sb.append(Modifier.toString(mod) + " ");
            }
            sb.append(getTypeName(m.getReturnType()) + " ");
            sb.append(getTypeName(m.getDeclaringClass()) + ".");
            sb.append(m.getName() + "(");
            Class[] params = m.getParameterTypes(); // avoid clone
            for (int j = 0; j < params.length; j++) {
                sb.append(getTypeName(params[j]));
                if (j < (params.length - 1))
                    sb.append(",");
            }
            sb.append(")");
            Class[] exceptions = m.getExceptionTypes(); // avoid clone
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
