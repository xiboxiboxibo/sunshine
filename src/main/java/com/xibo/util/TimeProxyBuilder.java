package com.xibo.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 方法计时代理类构造器
 * @author xihao.pan
 */
public final class TimeProxyBuilder {

    private TimeProxyBuilder() {
        throw new UnsupportedOperationException();
    }

    public static <T> T build(T source) {
        return (T) Proxy.newProxyInstance(
                source.getClass().getClassLoader(),
                source.getClass().getInterfaces(),
                TimeProxyHandler.of(source));
    }

    /**
     * 方法计时处理器
     * @author xihao.pan
     */
    private static class TimeProxyHandler implements InvocationHandler {

        private Object source;

        private TimeProxyHandler(Object source) {
            this.source = source;
        }

        public static TimeProxyHandler of(Object source) {
            return new TimeProxyHandler(source);
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.currentTimeMillis();
            Object result = method.invoke(source, args);
            System.out.printf("方法%s耗时%d毫秒\n", method.getName(), System.currentTimeMillis() - start);
            return result;
        }
    }

}
