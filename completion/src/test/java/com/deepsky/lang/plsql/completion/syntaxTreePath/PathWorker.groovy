package com.deepsky.lang.plsql.completion.syntaxTreePath

class PathWorker extends GroovyTestCase {

    void setUp() {
    }

    void tearDown() {
    }

    void test1(){
        /*
        * /apbcd/jjka/uyeuye => aapbcd, jjka, uyeuye
        *
        *
        * /apbcd/2#jjka//1$uyeuye => apbcd, 2#jjka, //, 1$uyeuye
        *
        */

        def path = ['select', 'columnExpr', 'nameRef']
        def pattern = [["head": "/", "sym": "select"], ["head": "/", "sym": "columnExpr", "pos": 2], ["head": "//", "sym": "functionCall"], ["head": "/", "sym": "argument"]]

        assertFalse(tt(path, pattern));

        def path1 = ['select', 'columnExpr', 'functionCall', 'argument']
        def pattern1 = [["head": "/", "sym": "select"], ["head": "/", "sym": "columnExpr", "pos": 2], ["head": "//", "sym": "functionCall"], ["head": "/", "sym": "argument"]]

        assertTrue(tt(path1, pattern1));

        def path2 = ['select', 'columnExpr', 'trunk_func', 'functionCall', 'argument']
        def pattern2 = [["head": "/", "sym": "select"], ["head": "/", "sym": "columnExpr", "pos": 2], ["head": "//", "sym": "functionCall"], ["head": "/", "sym": "argument"]]

        assertTrue(tt(path2, pattern2));
    }

    boolean tt(List path, List pattern) {

        Iterator it = path.iterator();
        Iterator itPat = pattern.iterator();
        while (itPat.hasNext() && it.hasNext()) {
            Object patElem = itPat.next();

            while (it.hasNext()) {
                Object pathElem = it.next();

                if (patElem.sym.equals(pathElem)) {
                    // Elements equals
                    // TODO - do element saving?

                    if (!it.hasNext() && !itPat.hasNext()) {
                        // Chain done
                        return true;
                    }

                    break;
                } else {
                    if (patElem.head == '//') {
                        // go to the next element in the chain
                    } else {
                        return false;
                    }
                }
            }

        }

        // Matching was unsuccessful!
        return false;
    }

}
