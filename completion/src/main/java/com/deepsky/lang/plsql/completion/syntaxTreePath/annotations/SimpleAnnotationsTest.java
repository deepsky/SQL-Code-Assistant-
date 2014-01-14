package com.deepsky.lang.plsql.completion.syntaxTreePath.annotations;

@Complexity(Complexity.Level.VERY_SIMPLE)
public class SimpleAnnotationsTest {

    public SimpleAnnotationsTest() {
        super();
    }

    @Complexity(Complexity.Level.MEDIUM) // this annotation type applies also to methods
    // the default value 'ComplexityLevel.MEDIUM' is assumed
    public void theMethod() {
        System.out.println("console output");
    }
}
