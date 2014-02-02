package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.processors;

import antlr.RecognitionException;
import antlr.TokenStreamException;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import com.deepsky.lang.plsql.completion.SyntaxTreePath;
import com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CodeGenerator;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import java.io.*;
import java.util.*;

@SupportedAnnotationTypes("com.deepsky.lang.plsql.completion.SyntaxTreePath")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SyntaxTreePathProcessor extends AbstractProcessor {

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

//                try {
//                    codeGen.buildTree(treePath.value());
//                } catch (RecognitionException e1) {
//                    e1.printStackTrace();
//                } catch (TokenStreamException e1) {
//                    e1.printStackTrace();
//                }


                List<String[]> lu = u1.get(exeElement.getEnclosingElement().toString());
                if (lu == null) {
                    lu = new ArrayList<>();
                    u1.put(exeElement.getEnclosingElement().toString(), lu);
                }

                String[] pair = new String[]{
                        exeElement.getSimpleName().toString(),
                        treePath.value()
                };
                lu.add(pair);

                String message = "annotation found in " + exeElement.getSimpleName()
                        + " with path " + treePath.value();
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
                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "applying velocity template: " + "Hello");

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
                            codeGen.buildTree(className, row[0], row[1]);
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
//                Writer w = new FileWriter(new File(fObject.toUri()));
                CSVWriter writer = new CSVWriter(w, ',', '"');

                for (String[] methodAnnotPair : e.getValue()) {
                    writer.writeNext(new String[]{
                            methodAnnotPair[0],
                            methodAnnotPair[1]
                    });
                }

                writer.close();
                w.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }
}
