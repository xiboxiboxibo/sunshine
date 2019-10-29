package com.xibo.util;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.AllPermission;
import java.security.Permissions;
import java.security.ProtectionDomain;

/**
 * 自定义类加载器
 * @author xihao.pan
 */
public final class MyClassLoader extends ClassLoader {

    private final static ProtectionDomain PROTECTION_DOMAIN;
    private String path;

    static {
        Permissions ps = new Permissions();
        ps.add(new AllPermission());
        PROTECTION_DOMAIN = new ProtectionDomain(null, ps);
    }

    public MyClassLoader(String path) {
        this.path = path;
    }

    public Class<?> getClass(String name) throws IOException {
        try {
            byte[] bytes = getClassBytes(path, name);
            Method method = ClassLoader.class.getDeclaredMethod("defineClass1", String.class, byte[].class, int.class, int.class, ProtectionDomain.class, String.class);
            method.setAccessible(true);
            Object obj = method.invoke(this, name, bytes, 0, bytes.length, PROTECTION_DOMAIN, null);
            return (Class<?>) obj;
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = getClassBytes(path, name);
            Class<?> cl = defineClass(name, bytes, 0, bytes.length);
            return cl;
        }
        catch (IOException e) {
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }

    private static byte[] getClassBytes(String path, String name) throws IOException {
        StringBuilder sb = new StringBuilder(path).append(name.replaceAll("\\.", "\\\\")).append(".class");
        byte[] bytes = Files.readAllBytes(Paths.get(sb.toString()));
        return bytes;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
