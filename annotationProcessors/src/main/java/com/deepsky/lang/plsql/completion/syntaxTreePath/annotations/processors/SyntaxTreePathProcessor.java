package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.processors;

import antlr.RecognitionException;
import antlr.TokenStreamException;
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
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SupportedAnnotationTypes("com.deepsky.lang.plsql.completion.SyntaxTreePath")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class SyntaxTreePathProcessor extends AbstractProcessor {

    CodeGenerator codeGen = new CodeGenerator("CompletionProcessor2");

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        System.err.println("[SyntaxTreePathProcessor] Hello");

        boolean looped = false;
        for (Element e : roundEnv.getElementsAnnotatedWith(SyntaxTreePath.class)) {

            looped = true;
            if (e.getKind() == ElementKind.METHOD) {
                ExecutableElement exeElement = (ExecutableElement) e;
                SyntaxTreePath treePath = exeElement.getAnnotation(SyntaxTreePath.class);

                try {
                    codeGen.buildTree(treePath.value());
                } catch (RecognitionException e1) {
                    e1.printStackTrace();
                } catch (TokenStreamException e1) {
                    e1.printStackTrace();
                }

                String message = "annotation found in " + exeElement.getSimpleName()
                        + " with path " + treePath.value();
                processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
                System.err.println("[SyntaxTreePathProcessor] " + message);
                System.err.println("[SyntaxTreePathProcessor] enclosingElem: " + exeElement.getEnclosingElement());
            }
        }

        if (!looped) {


            String fqClassName = "com.deepsky.lang.plsql.completion.syntaxTreePath.generated.CompletionProcessor2";

            System.err.println("[SyntaxTreePathProcessor] Complete processing");


            try {
                JavaFileObject jfo = processingEnv.getFiler().createSourceFile(fqClassName);

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
}
