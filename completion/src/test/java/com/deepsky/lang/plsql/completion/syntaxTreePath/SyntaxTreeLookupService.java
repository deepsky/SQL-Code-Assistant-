package com.deepsky.lang.plsql.completion.syntaxTreePath;

import com.deepsky.lang.common.PlSqlTokenTypes;
import com.deepsky.lang.parser.plsql.PlSqlElementTypes;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxTreeLookupService {

    final static private String[] PSI_ELEMENT_PKGS = new String[]{
            "com.deepsky.lang.plsql.psi",
            "com.deepsky.lang.plsql.psi.ctrl",
            "com.deepsky.lang.plsql.psi.ddl",
            "com.deepsky.lang.plsql.psi.names",
            "com.deepsky.lang.plsql.psi.ref",
            "com.deepsky.lang.plsql.psi.types"
    };

    final static private String PKG_BEING_PARSED =
            "com.deepsky.lang.plsql.completion.processors";

    final static private Class[] ASTNODE_TYPE_CLASSES = new Class[]{
            PlSqlElementTypes.class, PlSqlTokenTypes.class
    };



    public static SyntaxTreeLookupService getInstance(Project project) {
        return ServiceManager.getService(project, SyntaxTreeLookupService.class);
    }

    public SyntaxTreePathWrap[] getPaths(){
        // TODO - implement me
        return null;
    }

    // Samples:
    //      /1$RowtypeType/2$TableRef
    //      /1#IDENTIFIER
    //      //TableRef

    static private Pattern ONE_ELEMENT_PATH = Pattern.compile("^/([^/]+)$");
    static private Pattern TWO_ELEMENT_PATH = Pattern.compile("^/([^/]*)/([^/]+)$");
    static private Pattern THREE_ELEMENT_PATH = Pattern.compile("^/([^/]*)/([^/]*)/([^/]+)$");
    static private Pattern FOUR_ELEMENT_PATH = Pattern.compile("^/([^/]*)/([^/]*)/([^/]*)/([^/]+)$");


    public SyntaxTreePathWrap parsePath(@NotNull String syntaxTreePath) {

        Matcher m = ONE_ELEMENT_PATH.matcher(syntaxTreePath);
        if (m.find()) {
            String _1st = m.group(1);
            validate(_1st);
        } else {
            m = TWO_ELEMENT_PATH.matcher(syntaxTreePath);
            if (m.find()) {
                String _1st = m.group(1);
                String _2nd = m.group(2);
            } else {
                m = THREE_ELEMENT_PATH.matcher(syntaxTreePath);
                if (m.find()) {
                    String _1st = m.group(1);
                    String _2nd = m.group(2);
                    String _3d = m.group(3);
                } else {
                    m = FOUR_ELEMENT_PATH.matcher(syntaxTreePath);
                    if (m.find()) {
                        String _1st = m.group(1);
                        String _2nd = m.group(2);
                        String _3d = m.group(3);
                        String _4th = m.group(4);
                    } else {
                        // TODO error: cannot parse
                    }
                }
            }
        }

        return null;
    }

    private void validate(String st) {
        //To change body of created methods use File | Settings | File Templates.
    }


}
