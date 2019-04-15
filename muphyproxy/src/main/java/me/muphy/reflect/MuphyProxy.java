package me.muphy.reflect;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 2019/4/12
 * 莫非
 */
public class MuphyProxy implements Serializable {
    protected MuphyInvocationHandler h;

    protected MuphyProxy(MuphyInvocationHandler h){
        this.h = h;
    }

    public static Object newProxyInstance(MuphyClassLoader loader, Class<?>[] interfaces, MuphyInvocationHandler h){
        //生成文件
        String src = generateCode(interfaces);
        //输出到磁盘
        String path = MuphyProxy.class.getResource("").getPath();
        File file = new File(path + "$MuphyProxy0.java");
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fileWriter != null){
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //编译
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(file);
        JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, fileObjects);
        task.call();
        try {
            fileManager.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //加载
        try {
            Class<?> muphyProxy0 = loader.findClass("$MuphyProxy0");
            Constructor<?> constructor = muphyProxy0.getConstructor(MuphyInvocationHandler.class);
            return constructor.newInstance(h);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return  null;
    }

    private static String generateCode(Class<?>[] interfaces) {
        StringBuilder sb = new StringBuilder();

        sb.append("package me.muphy.reflect;" + "\n");
        sb.append("" + "\n");
        sb.append("import me.muphy.reflect.MuphyClassLoader;" + "\n");
        sb.append("import me.muphy.reflect.MuphyInvocationHandler;" + "\n");
        sb.append("import me.muphy.reflect.MuphyProxy;" + "\n");
        sb.append("" + "\n");
        sb.append("import java.lang.reflect.Method;" + "\n");
        sb.append("" + "\n");
        sb.append("/**" + "\n");
        sb.append(" * 2019/4/12" + "\n");
        sb.append(" * 莫非" + "\n");
        sb.append(" */" + "\n");
        sb.append("public class $MuphyProxy0  implements " + interfaces[0].getName() + "{" + "\n");
        sb.append("private MuphyInvocationHandler h;" + "\n");

        sb.append("public $MuphyProxy0(MuphyInvocationHandler h){" + "\n");
        sb.append(" this.h = h;" + "\n");
        sb.append("}" + "\n");

        for (Method method : interfaces[0].getMethods()) {
            sb.append("public final " + method.getReturnType().getName() + " " +  method.getName() + "(){" + "\n");
            sb.append("        try {" + "\n");
            sb.append("             Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + method.getName() + "\", new Class[]{});" + "\n");
            sb.append("             this.h.invoke(this, m, null);" + "\n");
            sb.append("        }catch (Throwable e){" + "\n");
            sb.append("            e.printStackTrace();" + "\n");
            sb.append("        }" + "\n");
            sb.append("}" + "\n");
        }
        sb.append("}" + "\n");

        return sb.toString();
    }
}


