/*
 * Copyright (c) 2009,2014 Serhiy Kulyk
 * All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *      1. Redistributions of source code must retain the above copyright
 *        notice, this list of conditions and the following disclaimer.
 *      2. Redistributions in binary form must reproduce the above copyright
 *        notice, this list of conditions and the following disclaimer in the
 *        documentation and/or other materials provided with the distribution.
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

package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.processors;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generator.CodeGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.util.*;

@SupportedAnnotationTypes("com.deepsky.lang.plsql.completion.SyntaxTreePath")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SyntaxTreePathProcessor extends AbstractProcessor {

    // Class name to Method + Syntax TreePath mappings
    Map<String, List<String[]>> u1 = new HashMap<String, List<String[]>>();

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        System.err.println("[SyntaxTreePathProcessor] Hello");

        boolean looped = false;
        for (Element e : roundEnv.getElementsAnnotatedWith(SyntaxTreePath.class)) {

            looped = true;
            if (e.getKind() == ElementKind.METHOD) {
                ExecutableElement exeElement = (ExecutableElement) e;
                SyntaxTreePath treePath = exeElement.getAnnotation(SyntaxTreePath.class);

                List<String[]> lu = u1.get(exeElement.getEnclosingElement().toString());
                if (lu == null) {
                    lu = new ArrayList<String[]>();
                    u1.put(exeElement.getEnclosingElement().toString(), lu);
                }

//                String[] pair = new String[]{
//                        exeElement.getSimpleName().toString(),
//                        treePath.value()
//                };

                List<? extends VariableElement> params = exeElement.getParameters();
                String[] meth_path_params = new String[2+params.size()];
                meth_path_params[0] = exeElement.getSimpleName().toString();
                meth_path_params[1] = treePath.value();

                for(int i=0; i<params.size(); i++){
                    String paramName = params.get(i).getSimpleName().toString();
                    String clazzName = params.get(i).asType().toString();

                    processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, "Parameter:  " + paramName + " Class: " + clazzName);
                    meth_path_params[i+2] = clazzName;
                }

                lu.add(meth_path_params);

                String message = "annotation found in " + exeElement.getSimpleName() + " with path " + treePath.value();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
                System.err.println("[SyntaxTreePathProcessor] " + message);
                System.err.println("[SyntaxTreePathProcessor] enclosingElem: " + exeElement.getEnclosingElement());
            }
        }

        if (!looped) {
            String fqPkgName = "com.deepsky.lang.plsql.completion.syntaxTreePath.generated";
            String fqClassName = fqPkgName + ".CompletionProcessor2";

            System.err.println("[SyntaxTreePathProcessor] Complete processing");

            saveAnnotations(fqPkgName, u1);
            CodeGenerator codeGen = new CodeGenerator("CompletionProcessor2");

            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(fqClassName);
                loadAnnotations(new File(jfo.toUri()).getParent(), codeGen);

                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "creating source file: " + jfo.toUri());

                Writer writer = jfo.openWriter();

                codeGen.generate(writer);
                writer.write("\n");
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    private void loadAnnotations(String path, CodeGenerator codeGen) {
        File outputDir = new File(path);
        File[] children = outputDir.listFiles();
        for (File f : (children != null ? children : new File[0])) {
            if (f.getName().matches("^com\\.deepsky\\..*\\.txt$")) {
                // Looks like annotation mappings
                try {
                    Reader r = new FileReader(f);
                    CSVReader reader = new CSVReader(r);
                    while (true) {
                        String[] row = reader.readNext();
                        if (row == null) {
                            break;
                        }
                        try {
                            String className = f.getName().replaceFirst("\\.txt$", "");
                            codeGen.buildTree(className, row);
                        } catch (RecognitionException e1) {
                            e1.printStackTrace();
                        } catch (TokenStreamException e1) {
                            e1.printStackTrace();
                        }


                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void saveAnnotations(String pkgName, Map<String, List<String[]>> annots) {
        for (Map.Entry<String, List<String[]>> e : annots.entrySet()) {
            try {
                FileObject fObject = processingEnv.getFiler().createResource(StandardLocation.SOURCE_OUTPUT, pkgName, e.getKey() + ".txt");
                Writer w = fObject.openWriter();
                CSVWriter writer = new CSVWriter(w, ',', '"');

                for (String[] methodAnnotPair : e.getValue()) {
                    writer.writeNext(methodAnnotPair);
//                    writer.writeNext(new String[]{
//                            (String) methodAnnotPair[0],
//                            (String) methodAnnotPair[1]
//                    });
                }

                writer.close();
                w.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
