package com.xibo.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 自定义类加载器
 * @author xihao.pan
 */
public class MyClassLoader extends ClassLoader {

    private String path;

    public MyClassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = getClassBytes(path, name);
            return defineClass(name, bytes, 0, bytes.length);
        }
        catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

    private static byte[] getClassBytes(String path, String name) throws IOException {
        String classPath = path + name.replaceAll("\\.", "/") + ".class";
        return Files.readAllBytes(Paths.get(classPath));
    }

}
