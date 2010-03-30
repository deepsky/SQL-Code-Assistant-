package test.deepsky.lang.common.lang.parser;

import antlr.CommonAST;

public class CommonASTExt extends CommonAST {
    Object o;
    public CommonASTExt(Object o){
        this.o = o;
    }

    public CommonASTExt(){
    }

    public antlr.Token tkn;
    public CommonASTExt(antlr.Token token) {
        super(token);
        tkn = token;
    }

    public void initialize(antlr.Token token) {
        super.initialize(token);
        tkn = token;
    }
}
