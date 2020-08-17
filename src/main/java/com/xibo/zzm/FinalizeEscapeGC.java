package com.xibo.zzm;

import java.util.concurrent.TimeUnit;

/**
 * @author zzm
 */
public class FinalizeEscapeGC {

    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.err.println("yes, i am still alive :)");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.err.println("finalize method executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws Exception {
        SAVE_HOOK = new FinalizeEscapeGC();

        // 对象第一次拯救自己
        test();

        // 第二次
        test();
    }

    public static void test() throws Exception {
        SAVE_HOOK = null;
        System.gc();
        // 因为Finalizer方法优先级很低，暂停0.5秒，以等待它
        TimeUnit.MILLISECONDS.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.err.println("no, i am dead :(");
        }
    }

}
