package com.xibo.util;

import java.io.*;
import java.util.Optional;

/**
 * 序列化器
 * @author xihao.pan
 */
public final class Serializer {

    private Serializer() {
        throw new UnsupportedOperationException();
    }

    public static boolean writeObject(Object object, String path) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(object);
            return true;
        }
        catch (IOException e) {
            return false;
        }
    }

    public static <T> Optional<T> readObject(String path) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
            return Optional.of((T) in.readObject());
        }
        catch (ClassNotFoundException | IOException e) {
            return Optional.empty();
        }
    }

}
