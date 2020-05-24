package com.xibo.zzm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * JavaClass执行工具
 * @author zzm
 */
public class JavaClassExecuter {

    /**
     * 执行外部传过来的的代表一个Java类的byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     * @param classByte 字节码
     * @return 执行结果
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "com/xibo/zzm/HackSystem");
        HostSwapClassLoader loader = new HostSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try {
            Method method = clazz.getMethod("main", new Class[] {String[].class});
            method.invoke(null, new String[] {null});
            write(modiBytes);
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }

    private static void write(byte[] bytes) throws IOException {
        Files.write(Path.of("/Users/xibo/MyWorld/sunshine/x.class"), bytes);
    }

}
