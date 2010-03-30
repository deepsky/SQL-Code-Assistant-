package test.deepsky.utils;

import junit.framework.TestCase;
import com.deepsky.utils.PluginPathProcessorException;
import com.deepsky.utils.PluginPathProcessor;


public class PluginPathProcessorTest extends TestCase {

    String[] set0 = new String[]{
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant/lib/antlr-2.7.5-patched.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant/lib/castor-1.2.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant/lib/commons-logging-1.1.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant/lib/const_storage.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant/lib/ehcache-1.6.0-beta5.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant/lib/plsql-plugin-0.9.0.jar"
    };

    String[] set1 = new String[]{
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/system/plugins-sandbox/plugins/PlSqlPlugin/lib/antlr-2.7.5-patched.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/system/plugins-sandbox/plugins/PlSqlPlugin/lib/castor-1.2.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/system/plugins-sandbox/plugins/PlSqlPlugin/lib/commons-logging-1.1.jar",
            "/C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/system/plugins-sandbox/plugins/PlSqlPlugin/classes/"
    };


    public void test1() throws PluginPathProcessorException {

        PluginPathProcessor proc = new PluginPathProcessor(set1[0]);
        assertEquals("PlSqlPlugin", proc.getPluginName());
        assertEquals("antlr-2.7.5-patched.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/system/plugins-sandbox/plugins/PlSqlPlugin", proc.getPluginHomeDir());

        proc = new PluginPathProcessor(set1[1]);
        assertEquals("PlSqlPlugin", proc.getPluginName());
        assertEquals("castor-1.2.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/system/plugins-sandbox/plugins/PlSqlPlugin", proc.getPluginHomeDir());

        proc = new PluginPathProcessor(set1[2]);
        assertEquals("PlSqlPlugin", proc.getPluginName());
        assertEquals("commons-logging-1.1.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/system/plugins-sandbox/plugins/PlSqlPlugin", proc.getPluginHomeDir());

        try {
            proc = new PluginPathProcessor(set1[3]);
            assertTrue(false);
        } catch (PluginPathProcessorException e) {
            assertTrue(true);
        }
    }


    public void test0() throws PluginPathProcessorException {

        PluginPathProcessor proc = new PluginPathProcessor(set0[0]);
        assertEquals("plsql-assistant", proc.getPluginName());
        assertEquals("antlr-2.7.5-patched.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant", proc.getPluginHomeDir());

        proc = new PluginPathProcessor(set0[1]);
        assertEquals("plsql-assistant", proc.getPluginName());
        assertEquals("castor-1.2.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant", proc.getPluginHomeDir());

        proc = new PluginPathProcessor(set0[2]);
        assertEquals("plsql-assistant", proc.getPluginName());
        assertEquals("commons-logging-1.1.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant", proc.getPluginHomeDir());

        proc = new PluginPathProcessor(set0[3]);
        assertEquals("plsql-assistant", proc.getPluginName());
        assertEquals("const_storage.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant", proc.getPluginHomeDir());

        proc = new PluginPathProcessor(set0[4]);
        assertEquals("plsql-assistant", proc.getPluginName());
        assertEquals("ehcache-1.6.0-beta5.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant", proc.getPluginHomeDir());

        proc = new PluginPathProcessor(set0[5]);
        assertEquals("plsql-assistant", proc.getPluginName());
        assertEquals("plsql-plugin-0.9.0.jar", proc.getJarName());
        assertEquals("C:/Documents and Settings/serhiy.kulyk/.IntelliJIdea70/config/plugins/plsql-assistant", proc.getPluginHomeDir());
    }

}
