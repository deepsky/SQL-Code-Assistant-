package com.deepsky.lang.plsql.completion.legacy.parser;


public class StxParseException extends Exception {


    public StxParseException(Exception e) {
        super(e);
    }

    public StxParseException(String message) {
        super(message);
    }
}
