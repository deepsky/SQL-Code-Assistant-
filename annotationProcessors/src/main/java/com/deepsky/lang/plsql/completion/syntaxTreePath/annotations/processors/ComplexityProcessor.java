package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.processors;

import com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.Complexity;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.util.Set;

@SupportedAnnotationTypes("com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.Complexity")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class ComplexityProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        System.err.println("[AnnotProc] Hello");

        for (Element elem : roundEnv.getElementsAnnotatedWith(Complexity.class)) {
            Complexity complexity = elem.getAnnotation(Complexity.class);
            String message = "annotation found in " + elem.getSimpleName()
                    + " with complexity " + complexity.value();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.NOTE, message);
            System.err.println("[AnnotProc] " + message);


            System.err.println("[AnnotProc] enclosingElem: " + elem.getEnclosingElement());
            System.err.println("[AnnotProc] kind: " + elem.getKind());
            System.err.println("[AnnotProc] EnclosedElements: " + elem.getEnclosedElements());
            System.err.println("[AnnotProc] SimpleName: " + elem.getSimpleName());

        }
        return true; // no further processing of this annotation type
    }
}
