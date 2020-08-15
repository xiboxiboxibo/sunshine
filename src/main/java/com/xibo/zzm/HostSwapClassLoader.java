package com.xibo.zzm;

/**
 * 为了多次载入执行类而加入的加载器
 * 把defineClass方法开放出来，只有外部显示调用的时候才会使用到loadByte方法
 * 由虚拟机调用，仍然按照原有的双亲委派规则使用loadClass方法进行类加载
 * @author zzm
 */
public class HostSwapClassLoader extends ClassLoader {

    public HostSwapClassLoader() {
        super(HostSwapClassLoader.class.getClassLoader());
    }

    public Class<?> loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }



}
