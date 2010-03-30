package test.deepsky.utils.jar;

import junit.framework.TestCase;

import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.ByteArrayOutputStream;
import java.net.URL;

import org.jetbrains.annotations.NotNull;
import com.deepsky.utils.CustomClassLoader;


public class CustomClassLoaderTest extends TestCase {


    public void setUp() {

    }

    class YY {
        int hh;
    }

    public void test1() throws IOException, ClassNotFoundException {

        String clazz = "oracle.jdbc.OracleCallableStatement";
        String _interface = "java.sql.Driver";

        t2("resources/jars/ojdbc14.jar", _interface);
    }

    void t2(String jarPath, String clazz) throws IOException, ClassNotFoundException {
        JarFile zip = new JarFile(jarPath);
        Enumeration enumm = zip.entries();

        URL url = new URL("file://" + new File(jarPath).getAbsolutePath());
//        URLClassLoader loader = new URLClassLoader(new URL[]{url});

        ClassLoader l = this.getClass().getClassLoader();
        CustomClassLoader loader = new CustomClassLoader( l, new File(jarPath).getAbsolutePath());

        Class target = Class.forName(clazz);
        int counter = 0;
        while (enumm.hasMoreElements()) {
            JarEntry o = (JarEntry) enumm.nextElement();
            if (!o.isDirectory() && o.getName().endsWith(".class")) {
                // class entry found
                //InputStream in = zip.getInputStream(o);
                try {
                    String clazzName = o.getName().substring(0, o.getName().length() - 6);
                    clazzName = clazzName.replace('/', '.');
                    Class c = loader.loadClass(clazzName);
                    boolean res = isClassDerivedFrom(c, target);

                    int hh = 0;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                //Thread.currentThread().ge
/*
                String name = o.getName();
                String clazz_ = clazz.replace('.', '/') + ".class";
                if(name.equals(clazz_)){
                    InputStream in = zip.getInputStream(o);
                    try {
                        Class c = loader.loadClass(clazz);
                        Class[] parents = c.getInterfaces();
                        int hh =0;

//                        if(in instanceof java.sql.Driver){
//
//                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
*/
            }
            int kk = 0;
            counter++;
        }
    }

    boolean isClassDerivedFrom(Class probe, Class target) {
        Class[] ancestors = probe.getInterfaces();
        for (Class ancestor : ancestors) {
            if (target.getName().equals(ancestor.getName())) {
                return true;
            } else {
                if (isClassDerivedFrom(ancestor, target)) {
                    return true;
                } else {
                    //
                }
            }
        }

        return false;
    }


    public class MyClassLoader extends ClassLoader {
        private static final int BUFFER_SIZE = 8192;

        JarFile zip;

        public MyClassLoader(@NotNull ClassLoader parent, String path) throws IOException {
            super(parent);
            zip = new JarFile(path);
        }

        InputStream getStream(String className) {
            Enumeration enumm = zip.entries();
            String clsFile = className.replace('.', '/') + ".class";
            while (enumm.hasMoreElements()) {
                JarEntry o = (JarEntry) enumm.nextElement();
                if (!o.isDirectory() && o.getName().equals(clsFile)) {
                    try {
                        return zip.getInputStream(o);
                    } catch (IOException e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }

            return null;
        }

        protected synchronized Class loadClass(String className, boolean resolve) throws ClassNotFoundException {
            // 1. is this class already loaded?
            Class cls = findLoadedClass(className);
            if (cls != null) {
                return cls;
            }

            // 3. get bytes for class
            byte[] classBytes = null;
            InputStream in = getStream(className);
            if (in != null) {
                try {
                    byte[] buffer = new byte[BUFFER_SIZE];
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    int n = -1;
                    while ((n = in.read(buffer, 0, BUFFER_SIZE)) != -1) {
                        out.write(buffer, 0, n);
                    }
                    classBytes = out.toByteArray();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (classBytes == null) {
                return super.loadClass(className);
            }

            // 4. turn the byte array into a Class
            try {
                cls = defineClass(className, classBytes, 0, classBytes.length);
                if (resolve) {
                    resolveClass(cls);
                }
            }
            catch (SecurityException e) {
                // loading core java classes such as java.lang.String
                // is prohibited, throws java.lang.SecurityException.
                // delegate to parent if not allowed to load class
                cls = super.loadClass(className, resolve);
            }

            return cls;
        }
    }

}
