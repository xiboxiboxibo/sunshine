package com.xibo.zzm;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * VM Args: -XX:MetaspaceSize=10M
 * @author zzm
 */
public class JavaMethodAreaOOM {

    static class OOMObject {

    }

    public static void main(final String[] args) throws Exception {
        TimeUnit.SECONDS.sleep(5);
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args));
            enhancer.create();
        }
    }

}
