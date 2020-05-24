package com.xibo;

import com.xibo.zzm.HackSystem;
import com.xibo.zzm.JavaClassExecuter;

import java.nio.file.Files;
import java.nio.file.Path;

public class Test {

    public static void main(String[] args) throws Exception {
        byte[] bytes = Files.readAllBytes(Path.of("/Users/xibo/MyWorld/sunshine/out/production/sunshine/com/xibo/zzm/TestClass.class"));
        JavaClassExecuter.execute(bytes);
        System.err.println(HackSystem.getBufferString());
    }

}