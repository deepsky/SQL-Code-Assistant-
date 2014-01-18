package com.deepsky.lang.plsql.completion.legacy.logic

import org.junit.Test


// http://groovy.codehaus.org/Regular+Expressions
class Test1 {

    @Test
    public void test1(){
        def m = "foobarfoo" =~ /o(b.*r)f/
        assert m[0] == ["obarf", "bar"]
        assert m[0][1] == "bar"

    }


    @Test
    public void test2(){
        def m = "Firefox25.98.2" =~ /.*Firefox([0-9]+)\.([0-9]+).*/
        assert m[0] == ["Firefox25.98.2", "25", "98"]
        assert m[0][1] == "25"
        assert m[0][2] == "98"
    }

    @Test
    public void test3(){

        def m = "Firefox25.98.2" =~ /.*Firefox([0-9]+)\.([0-9]+).*/; println m.matches()? Integer.parseInt(m[0][1]) < 29: false

        assert ("Firefox25.98.2" =~ /.*Firefox([0-9]+)\.([0-9]+).*/)[0][1] == "25"
//        assert m[0] == ["Firefox25.98.2", "25", "98"]
//        assert m[0][1] == "25"
//        assert m[0][2] == "98"
    }

}
