package com.xibo.helloworld;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟任务
 * @author xihao.pan
 */
public class DelayedTask implements Delayed {

    int id;
    long milliseconds;

    public DelayedTask(int id, long milliseconds) {
        this.id = id;
        this.milliseconds = System.nanoTime() + TimeUnit.NANOSECONDS.convert(milliseconds, TimeUnit.MILLISECONDS);
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(milliseconds - System.nanoTime(), TimeUnit.NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        DelayedTask tar = (DelayedTask)o;
        if (milliseconds > tar.milliseconds) return 1;
        if (milliseconds < tar.milliseconds) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return "id：" + id + "  milliseconds：" + milliseconds;
    }
}
