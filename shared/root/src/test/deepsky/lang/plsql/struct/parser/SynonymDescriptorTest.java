package test.deepsky.lang.plsql.struct.parser;

import junit.framework.TestCase;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.lang.reflect.Method;

import com.intellij.lang.PsiBuilder;
import com.deepsky.lang.plsql.struct.SynonymDescriptor;
import com.deepsky.lang.plsql.struct.ViewDescriptor;
import com.deepsky.lang.plsql.struct.TableDescriptor;
import com.deepsky.lang.plsql.struct.DbObject;


public class SynonymDescriptorTest extends TestCase {

    public void test1(){
        InvocationHandler handler = new MyInvocationHandler();
//        Class proxyClass = Proxy.getProxyClass(
//            this.getClass().getClassLoader(), new Class[] { PsiBuilder.class });

        ViewDescriptor f = (ViewDescriptor) Proxy.newProxyInstance(SynonymDescriptor.class.getClassLoader(),
                                             new Class[] { ViewDescriptor.class },
                                             handler);

        assertTrue(f instanceof DbObject);
        assertTrue(f instanceof TableDescriptor);
        assertTrue(f instanceof ViewDescriptor);

        String name = f.getName();
    }


    class MyInvocationHandler implements InvocationHandler {

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            return null;
        }
    }
}
