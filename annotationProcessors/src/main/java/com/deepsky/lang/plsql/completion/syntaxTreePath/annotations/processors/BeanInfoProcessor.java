package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.processors;

import com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.BeanInfo;
//import org.apache.velocity.Template;
//import org.apache.velocity.VelocityContext;
//import org.apache.velocity.app.VelocityEngine;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


@SupportedAnnotationTypes("com.deepsky.lang.plsql.completion.syntaxTreePath.annotations.BeanInfo")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class BeanInfoProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        String fqClassName = null;
        String className = null;
        String packageName = null;
        Map<String, VariableElement> fields = new HashMap<String, VariableElement>();
        Map<String, ExecutableElement> methods = new HashMap<String, ExecutableElement>();

        for (Element e : roundEnv.getElementsAnnotatedWith(BeanInfo.class)) {

            if (e.getKind() == ElementKind.CLASS) {

                TypeElement classElement = (TypeElement) e;
                PackageElement packageElement = (PackageElement) classElement.getEnclosingElement();

                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "annotated class: " + classElement.getQualifiedName(), e);

                fqClassName = classElement.getQualifiedName().toString();
                className = classElement.getSimpleName().toString();
                packageName = packageElement.getQualifiedName().toString();

            } else if (e.getKind() == ElementKind.FIELD) {

                VariableElement varElement = (VariableElement) e;

                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "annotated field: " + varElement.getSimpleName(), e);

                fields.put(varElement.getSimpleName().toString(), varElement);

            } else if (e.getKind() == ElementKind.METHOD) {

                ExecutableElement exeElement = (ExecutableElement) e;

                processingEnv.getMessager().printMessage(
                        Diagnostic.Kind.NOTE,
                        "annotated method: " + exeElement.getSimpleName(), e);

                methods.put(exeElement.getSimpleName().toString(), exeElement);
            }

//            if (fqClassName != null) {
//                try {
//                    Properties props = new Properties();
//                    URL url = this.getClass().getClassLoader().getResource("velocity.properties");
//                    props.load(url.openStream());
//
//                    VelocityEngine ve = new VelocityEngine(props);
//                    ve.init();
//
//                    VelocityContext vc = new VelocityContext();
//
//                    vc.put("className", className);
//                    vc.put("packageName", packageName);
//                    vc.put("fields", fields);
//                    vc.put("methods", methods);
//
//                    Template vt = ve.getTemplate("beaninfo.vm");
//                    JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
//                            fqClassName + "BeanInfo");
//
//                    processingEnv.getMessager().printMessage(
//                            Diagnostic.Kind.NOTE,
//                            "creating source file: " + jfo.toUri());
//
//                    Writer writer = jfo.openWriter();
//
//                    processingEnv.getMessager().printMessage(
//                            Diagnostic.Kind.NOTE,
//                            "applying velocity template: " + vt.getName());
//
//                    vt.merge(vc, writer);
//
//                    writer.close();
//                } catch (IOException ee) {
//                    // TODO
//                }
//            }
        }
        return true;
    }
}
