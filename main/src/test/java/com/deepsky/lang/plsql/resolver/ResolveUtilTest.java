package com.deepsky.lang.plsql.resolver;

import com.deepsky.lang.plsql.psi.Argument;
import com.deepsky.lang.plsql.resolver.utils.ArgumentSpec;
import com.deepsky.lang.plsql.resolver.utils.ContextPathUtil;
import com.deepsky.lang.plsql.struct.Type;
import com.deepsky.lang.plsql.struct.TypeFactory;
import junit.framework.TestCase;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ResolveUtilTest extends TestCase {

    public void test_encodeArgumentsReturnType(){
        Argument f = (Argument) Proxy.newProxyInstance(
                Argument.class.getClassLoader(),
                new Class[]{Argument.class},
                new ArgumentProxy("name1", TypeFactory.createTypeById(Type.INTEGER), true));

        Argument[] args = new Argument[]{f};
        String encoded = ContextPathUtil.encodeArguments(args);

        assertEquals("name1:1|INTEGER.0:0!", encoded);

        ArgumentSpec[] args1 =  ContextPathUtil.extractArguments(encoded);
        assertEquals(1, args1.length);
    }


    public void test_encodeArgumentsReturnType_2(){
        Argument f = (Argument) Proxy.newProxyInstance(
                Argument.class.getClassLoader(),
                new Class[]{Argument.class},
                new ArgumentProxy("name1", TypeFactory.createTypeById(Type.INTEGER), true));
        Argument f2 = (Argument) Proxy.newProxyInstance(
                Argument.class.getClassLoader(),
                new Class[]{Argument.class},
                new ArgumentProxy("name2", TypeFactory.createTypeById(Type.DATE), true));

        Argument[] args = new Argument[]{f, f2};
        String encoded = ContextPathUtil.encodeArguments(args);

        assertEquals("name1:1|INTEGER.0:0,name2:7|DATE:0!", encoded);

        ArgumentSpec[] args1 =  ContextPathUtil.extractArguments(encoded);
        assertEquals(2, args1.length);
    }


    public void test_encodeArgumentsReturnType_3(){
        Argument f = (Argument) Proxy.newProxyInstance(
                Argument.class.getClassLoader(),
                new Class[]{Argument.class},
                new ArgumentProxy("name1", TypeFactory.createTypeById(Type.INTEGER), true));
        Argument f2 = (Argument) Proxy.newProxyInstance(
                Argument.class.getClassLoader(),
                new Class[]{Argument.class},
                new ArgumentProxy("name2", TypeFactory.createTypeById(Type.DATE), true));

        Argument[] args = new Argument[]{f, f2};
        String encoded = ContextPathUtil.encodeArgumentsReturnType(args, TypeFactory.createTypeById(Type.DATE));

        assertEquals("name1:1|INTEGER.0:0,name2:7|DATE:0!7|DATE", encoded);

        ArgumentSpec[] args1 =  ContextPathUtil.extractArguments(encoded);
        assertEquals(2, args1.length);

        Type ret = ContextPathUtil.extractRetType(encoded);
        assertNotNull(ret);
        assertEquals(TypeFactory.createTypeById(Type.DATE), ret);

    }

    public void test_extractArguments(){
        String value = "a_min_log_lvl_id:23|xdv_prd_log_t.lvl_id%type:1,a_def_log_section:23|xdv_prd_log_t.lsection%type:1,a_use_ssec_prc:10|BOOLEAN:1,a_show_callstack:10|BOOLEAN:1,a_show_user:5|NUMBER.2200:1,a_use_auto_trans:10|BOOLEAN:1,a_disp_logtable:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_file:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_output:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_pipe:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_alert:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_trace:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_email:13|xdv_logger_pkg.r_dispatcher:1,a_disp_tcp_sock:13|xdv_logger_pkg.r_dispatcher:1,a_dbms_pipe_name:2|VARCHAR2.0:1,a_pipe_to_log4j:10|BOOLEAN:1,a_dbms_output_lsize:26|PLS_INTEGER.0:1,a_dbms_output_headr:10|BOOLEAN:1,a_utl_file_name:2|VARCHAR2.0:1,a_utl_file_dir:2|VARCHAR2.0:1,a_utl_email_user:2|VARCHAR2.0:1,a_utl_email_subj:2|VARCHAR2.0:1,a_utl_email_recs:2|VARCHAR2.0:1!13|xdv_logger_pkg.r_lcontext";
        String value2 = "a_min_log_lvl_id:23|xdv_prd_log_t.lvl_id%type:1,a_def_log_section:23|xdv_prd_log_t.lsection%type:1,a_use_ssec_prc:10|BOOLEAN:1,a_show_callstack:10|BOOLEAN:1,a_show_user:5|NUMBER.2200:1,a_use_auto_trans:10|BOOLEAN:1,a_disp_logtable:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_file:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_output:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_pipe:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_alert:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_trace:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_email:13|xdv_logger_pkg.r_dispatcher:1,a_disp_tcp_sock:13|xdv_logger_pkg.r_dispatcher:1,a_dbms_pipe_name:2|VARCHAR2.0:1,a_pipe_to_log4j:10|BOOLEAN:1,a_dbms_output_lsize:26|PLS_INTEGER.0:1,a_dbms_output_headr:10|BOOLEAN:1,a_utl_file_name:2|VARCHAR2.0:1,a_utl_file_dir:2|VARCHAR2.0:1,a_utl_email_user:2|VARCHAR2.0:1,a_utl_email_subj:2|VARCHAR2.0:1,a_utl_email_recs:2|VARCHAR2.0:1!";
        String value3 = "!";
        String value4 = "!13|xdv_prd_excrec_t.id";

        // value
        ArgumentSpec[] args1 =  ContextPathUtil.extractArguments(value);
        assertEquals(23, args1.length);

        assertEquals("a_min_log_lvl_id", args1[0].getName());
        assertTrue(args1[0].getType().equals(TypeFactory.decodeType("23|xdv_prd_log_t.lvl_id%type")));

        assertEquals("a_def_log_section", args1[1].getName());
        assertTrue(args1[1].getType().equals(TypeFactory.decodeType("23|xdv_prd_log_t.lsection%type")));

        assertEquals("a_utl_email_recs", args1[22].getName());
        assertTrue(args1[22].getType().equals(TypeFactory.decodeType("2|VARCHAR2.0")));

        // value 2
        args1 =  ContextPathUtil.extractArguments(value2);
        assertEquals(23, args1.length);

        assertEquals("a_min_log_lvl_id", args1[0].getName());
        assertTrue(args1[0].getType().equals(TypeFactory.decodeType("23|xdv_prd_log_t.lvl_id%type")));

        assertEquals("a_def_log_section", args1[1].getName());
        assertTrue(args1[1].getType().equals(TypeFactory.decodeType("23|xdv_prd_log_t.lsection%type")));

        assertEquals("a_utl_email_recs", args1[22].getName());
        assertTrue(args1[22].getType().equals(TypeFactory.decodeType("2|VARCHAR2.0")));

        // value 3
        args1 =  ContextPathUtil.extractArguments(value3);
        assertEquals(0, args1.length);

        // value 4
        args1 =  ContextPathUtil.extractArguments(value4);
        assertEquals(0, args1.length);
    }

    public void test_extractReturnType(){
        String value = "a_min_log_lvl_id:23|xdv_prd_log_t.lvl_id%type:1,a_def_log_section:23|xdv_prd_log_t.lsection%type:1,a_use_ssec_prc:10|BOOLEAN:1,a_show_callstack:10|BOOLEAN:1,a_show_user:5|NUMBER.2200:1,a_use_auto_trans:10|BOOLEAN:1,a_disp_logtable:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_file:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_output:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_pipe:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_alert:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_trace:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_email:13|xdv_logger_pkg.r_dispatcher:1,a_disp_tcp_sock:13|xdv_logger_pkg.r_dispatcher:1,a_dbms_pipe_name:2|VARCHAR2.0:1,a_pipe_to_log4j:10|BOOLEAN:1,a_dbms_output_lsize:26|PLS_INTEGER.0:1,a_dbms_output_headr:10|BOOLEAN:1,a_utl_file_name:2|VARCHAR2.0:1,a_utl_file_dir:2|VARCHAR2.0:1,a_utl_email_user:2|VARCHAR2.0:1,a_utl_email_subj:2|VARCHAR2.0:1,a_utl_email_recs:2|VARCHAR2.0:1!13|xdv_logger_pkg.r_lcontext";
        String value2 = "a_min_log_lvl_id:23|xdv_prd_log_t.lvl_id%type:1,a_def_log_section:23|xdv_prd_log_t.lsection%type:1,a_use_ssec_prc:10|BOOLEAN:1,a_show_callstack:10|BOOLEAN:1,a_show_user:5|NUMBER.2200:1,a_use_auto_trans:10|BOOLEAN:1,a_disp_logtable:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_file:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_output:13|xdv_logger_pkg.r_dispatcher:1,a_disp_dbms_pipe:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_alert:13|xdv_logger_pkg.r_dispatcher:1,a_disp_log_trace:13|xdv_logger_pkg.r_dispatcher:1,a_disp_utl_email:13|xdv_logger_pkg.r_dispatcher:1,a_disp_tcp_sock:13|xdv_logger_pkg.r_dispatcher:1,a_dbms_pipe_name:2|VARCHAR2.0:1,a_pipe_to_log4j:10|BOOLEAN:1,a_dbms_output_lsize:26|PLS_INTEGER.0:1,a_dbms_output_headr:10|BOOLEAN:1,a_utl_file_name:2|VARCHAR2.0:1,a_utl_file_dir:2|VARCHAR2.0:1,a_utl_email_user:2|VARCHAR2.0:1,a_utl_email_subj:2|VARCHAR2.0:1,a_utl_email_recs:2|VARCHAR2.0:1!";
        String value3 = "!";
        String value4 = "!13|xdv_prd_excrec_t.id";

        Type t = ContextPathUtil.extractRetType(value);
        assertNotNull(t);

        t = ContextPathUtil.extractRetType(value2);
        assertNotNull(t);
        assertEquals(Type.UNKNOWN, t.typeId());

        t = ContextPathUtil.extractRetType(value3);
        assertNotNull(t);
        assertEquals(Type.UNKNOWN, t.typeId());

        t = ContextPathUtil.extractRetType(value4);
        assertNotNull(t);
    }


    private class ArgumentProxy implements InvocationHandler {

        String name;
        Type type;
        boolean hasDefault;

        public ArgumentProxy(String name, Type type, boolean hasDefault){
            this.name = name;
            this.type = type;
            this.hasDefault = hasDefault;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("getArgumentName")) {
                return name;
            } else if (method.getName().equals("getType")) {
                return type;
            } else if (method.getName().equals("getDefaultExpr")) {
                return null;
            }
            return null;
        }
    }
}
