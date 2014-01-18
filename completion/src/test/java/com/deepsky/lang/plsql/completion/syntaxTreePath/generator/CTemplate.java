package com.deepsky.lang.plsql.completion.syntaxTreePath.generator;


import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.deepsky.lang.plsql.completion.syntaxTreePath.logic.EOFException;
import com.deepsky.lang.plsql.psi.SelectStatement;
import com.intellij.lang.ASTNode;

import java.util.Iterator;
import java.util.List;

/*
 /
    ERROR_TOKEN_A /
        IDENTIFIER
        SELECT IDENTIFIER IDENTIFIER
    SelectStatement /
        ..
            EXPR_COLUMN
                / .. ALIAS_NAME / ALIAS_IDENT / IDENTIFIER
                // ALIAS_IDENT
            TABLE_REFERENCE_LIST_FROM .. ORDER IDENTIFIER
            GROUP IDENTIFIER
        SELECT .. GROUP IDENTIFIER
    UPDATE_COMMAND // UPDATE .. TABLE_ALIAS / TABLE_REF
 //
    SELECT
        ASTERISK IDENTIFIER
        .. EXPR_COLUMN COMMA ERROR_TOKEN_A / IDENTIFIER
    ERROR_TOKEN_A / SELECT .. EXPR_COLUMN COMMA ERROR_TOKEN_A / IDENTIFIER
    SelectStatement IDENTIFIER
    UPDATE TABLE_ALIAS SET ERROR_TOKEN_A / IDENTIFIER

 */
public abstract class CTemplate {

    public boolean process() {
        Object o = peek();
        if (o instanceof SlashNode) {
            int lexerState = getState();
            if (processS0()) {
                return true;
            }
            setState(lexerState);
        }

        // DoubleSlash case
        // TODO - implement me

        return false;
    }


    /*
 /
    ERROR_TOKEN_A /
        IDENTIFIER
        SELECT IDENTIFIER IDENTIFIER
    SelectStatement /
        ..
            EXPR_COLUMN
                / .. ALIAS_NAME / ALIAS_IDENT / IDENTIFIER
                // ALIAS_IDENT
            TABLE_REFERENCE_LIST_FROM .. ORDER IDENTIFIER
            GROUP IDENTIFIER
        SELECT .. GROUP IDENTIFIER
    UPDATE_COMMAND // UPDATE .. TABLE_ALIAS / TABLE_REF
     */
    private boolean processS0() {
        try {
            consume(SlashNode.class);
            ASTNode n = peek(ASTNode.class);
            if (n.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
                return processS0_ERROR_TOKEN_A();
            }

            if (n.getPsi() instanceof SelectStatement) {
                return processS0_SelectStatement();
            }

            if (n.getElementType() == PlSqlElementTypes.UPDATE_COMMAND) {
                return processS0_UPDATE_COMMAND();
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean processS0_UPDATE_COMMAND() {
        return false;
    }

    /*
    SelectStatement /
        ..
            EXPR_COLUMN
                / .. ALIAS_NAME / ALIAS_IDENT / IDENTIFIER
                // ALIAS_IDENT
            TABLE_REFERENCE_LIST_FROM .. ORDER IDENTIFIER
            GROUP IDENTIFIER
        SELECT .. GROUP IDENTIFIER
     */
    private boolean processS0_SelectStatement() {
        try {
            consume(ASTNode.class);
            Object o = peek();
            if (o instanceof SlashNode) {
                int lexerState = getState();
                if (processS0_SelectStatement_S0()) {
                    return true;
                }
                setState(lexerState);
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }

        return false;
    }

    /*
    /
        ..
            EXPR_COLUMN
                / .. ALIAS_NAME / ALIAS_IDENT / IDENTIFIER
                // ALIAS_IDENT
            TABLE_REFERENCE_LIST_FROM .. ORDER IDENTIFIER
            GROUP IDENTIFIER
        SELECT .. GROUP IDENTIFIER
     */
    private boolean processS0_SelectStatement_S0() {
        try {
            consume(SlashNode.class);
            ASTNode n = peek(ASTNode.class);
            if (processS0_SelectStatement_S0_DD()) {
                return true;
            }
            if (n.getElementType() == PlSqlTokenTypes.KEYWORD_SELECT) {
                return processS0_SelectStatement_S0_SELECT();
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
        SELECT .. GROUP IDENTIFIER
     */
    private boolean processS0_SelectStatement_S0_SELECT() {
        try {
            consume(ASTNode.class);
            while (true) {
                Object o = next();
                if (o instanceof SlashNode) {
                    break;
                }
                ASTNode n = (ASTNode) o;

                if (n.getElementType() == PlSqlTokenTypes.KEYWORD_GROUP) {
                    int lexerState = getState();
                    n = next(ASTNode.class);
                    if (n.getElementType() == PlSqlTokenTypes.IDENTIFIER) {
                        // TODO - process result
                        return true;
                    }
                    setState(lexerState);
                }
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
        return false;
    }


    /*
        ..
            EXPR_COLUMN
                / .. ALIAS_NAME / ALIAS_IDENT / IDENTIFIER
                // ALIAS_IDENT
            TABLE_REFERENCE_LIST_FROM .. ORDER IDENTIFIER
            GROUP IDENTIFIER
     */
    private boolean processS0_SelectStatement_S0_DD() {
        try {
            while (true) {
                Object o = next();
                if (o instanceof SlashNode) {
                    break;
                }

                ASTNode n = (ASTNode) o;
                if (n.getElementType() == PlSqlElementTypes.EXPR_COLUMN) {
                    int lexerState = getState();
                    if (processS0_SelectStatement_S0_DD_EXPR_COLUMN()) {
                        return true;
                    }
                    setState(lexerState);
                } else if (n.getElementType() == PlSqlElementTypes.TABLE_REFERENCE_LIST_FROM) {
                    int lexerState = getState();
                    if (processS0_S1_D0_D0()) {
                        return true;
                    }
                    setState(lexerState);
                } else if (n.getElementType() == PlSqlTokenTypes.KEYWORD_GROUP) {
                    int lexerState = getState();
                    n = next(ASTNode.class);
                    if (n.getElementType() == PlSqlTokenTypes.IDENTIFIER) {
                        // TODO - process result
                        return true;
                    }
                    setState(lexerState);
                }
            }
        } catch (EOFException e) {

        }
        return true;
    }

    /*
           // ALIAS_IDENT
     */
    private boolean processS0_SelectStatement_S0_DD_EXPR_COLUMN_DS() {
        try {
            while (true) {
                Object o = next();
                if (o instanceof SlashNode) {
                    ASTNode n = next(ASTNode.class);
                    if (n.getElementType() == PlSqlElementTypes.ALIAS_IDENT) {
                        // TODO - process result
                        return true;
                    }
                }
            }
        } catch (EOFException e) {
        }
        return false;
    }

    /*
            EXPR_COLUMN
                / .. ALIAS_NAME / ALIAS_IDENT / IDENTIFIER
                // ALIAS_IDENT
     */
    private boolean processS0_SelectStatement_S0_DD_EXPR_COLUMN() {
        try {
            consume(ASTNode.class);  // consume EXPR_COLUMN
            Object o = peek();
            if(o instanceof SlashNode){
                int lexerState = getState();
                if (processS0_SelectStatement_S0_DD_EXPR_COLUMN_S0()) {
                    return true;
                }
                setState(lexerState);
            }

            if(processS0_SelectStatement_S0_DD_EXPR_COLUMN_DS()){
                return false;
            }

        } catch (EOFException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean processS0_SelectStatement_S0_DD_EXPR_COLUMN_S0() {
        return false;
    }


    /*
        .. ORDER IDENTIFIER
     */
    private boolean processS0_S1_D0_D0() {
        try {
            while (true) {
                Object o = next();
                if (o instanceof SlashNode) {
                    break;
                }

                ASTNode n = (ASTNode) o;
                if (n.getElementType() == PlSqlTokenTypes.KEYWORD_ORDER) {
                    int lexerState = getState();
                    n = next(ASTNode.class);
                    if (n.getElementType() == PlSqlTokenTypes.IDENTIFIER) {
                        // TODO - process result
                        return true;
                    }
                    setState(lexerState);
                }
            }
        } catch (EOFException e) {

        }
        return false;
    }



    /*
    /
        IDENTIFIER
        SELECT IDENTIFIER IDENTIFIER
     */
    private boolean processS0_ERROR_TOKEN_A() {
        try {
            consume(ASTNode.class);
            Object o = peek();
            if (o instanceof SlashNode) {
                processS0_ERROR_TOKEN_A_S0();
            }
            next(SlashNode.class);
            ASTNode n1 = next(ASTNode.class);
            if (n1.getElementType() == PlSqlTokenTypes.IDENTIFIER) {
                return true; // TODO - process result
            }
            if (n1.getElementType() == PlSqlTokenTypes.KEYWORD_SELECT) {

                ASTNode n2 = next(ASTNode.class);
                if (n2.getElementType() != PlSqlTokenTypes.IDENTIFIER) {
                    return false;
                }

                ASTNode n3 = next(ASTNode.class);
                if (n3.getElementType() != PlSqlTokenTypes.IDENTIFIER) {
                    return false;
                }

                int gg = 0;
                // TODO - process result
                return true;
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean processS0_ERROR_TOKEN_A_S0() {
        try {
            consume(SlashNode.class);
            Object o = peek();
            ASTNode n1 = next(ASTNode.class);
            if (n1.getElementType() == PlSqlTokenTypes.IDENTIFIER) {
                return true; // TODO - process result
            } else if (n1.getElementType() == PlSqlTokenTypes.KEYWORD_SELECT) {

                ASTNode n2 = next(ASTNode.class);
                if (n2.getElementType() != PlSqlTokenTypes.IDENTIFIER) {
                    return false;
                }

                ASTNode n3 = next(ASTNode.class);
                if (n3.getElementType() != PlSqlTokenTypes.IDENTIFIER) {
                    return false;
                }

                int gg = 0;
                // TODO - process result
                return true;
            }
        } catch (EOFException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean process1234() {
//        next(SlashNode.class);
//        ASTNode n = next(ASTNode.class);
//        if (n.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A) {
//            next(SlashNode.class);
//            ASTNode n1 = next(ASTNode.class);
//            if (n1.getElementType() == PlSqlTokenTypes.IDENTIFIER) {
//                return true; // TODO - process result
//            } else if (n1.getElementType() == PlSqlTokenTypes.KEYWORD_SELECT) {
//
//
//                return true; // TODO - process result
//            }
//
//        } else if(n.getPsi() instanceof SelectStatement){
//
//        }


//        Iterator<List<ASTNode>> pathIt = iterator();
//        while(pathIt.hasNext()){
//            List<ASTNode> l = pathIt.next();
//        }
//        if(t.equals("/")){
//
//        } else if(t.equals("//")){
//
//        }
        return false;
    }

    protected abstract int getState();

    protected abstract void setState(int lexerState);


//    void processLevel1(Iterator<List<ASTNode>> pathIt){
//        if(pathIt.hasNext()){
//            List<ASTNode> l = pathIt.next();
//        }
//        if(pathIt.hasNext()){
//            processLevel2(pathIt);
//        }
//    }
//    void processLevel2(Iterator<List<ASTNode>> pathIt){
//        if(pathIt.hasNext()){
//            List<ASTNode> l = pathIt.next();
//        }
//
//        if(pathIt.hasNext()){
//            processLevel3(pathIt);
//        }
//    }
//
//    private void processSlash1() {
//        Object t = next();
//        if(t instanceof SlashNode){
//            t = next();
//            if(t instanceof ASTNode){
//                ASTNode n = (ASTNode) t;
//                if(n.getElementType() == PlSqlElementTypes.ERROR_TOKEN_A){
//                    processSlash();
//                }
//            }
//        }
//    }

    abstract Iterator<List<ASTNode>> iterator();


    abstract Object next();

    abstract Object peek();

    <T> T next(Class<T> e) throws EOFException {
        return null;
    }

    <T> T peek(Class<T> e) throws EOFException {
        return null;
    }

    <T> void consume(Class<T> e) throws EOFException {
        // TODO - implement me
    }

    interface SlashNode {

    }
}
