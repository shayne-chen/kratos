package com.shaw.kratos.common.lock;

import java.util.concurrent.atomic.AtomicInteger;

public class SpinLock {

    private static AtomicInteger state = new AtomicInteger(0);

    private static final int tryCount = 10;

    public static void addLock() {
        int currentCount = 0;
        while (!state.compareAndSet(0, 1)) {
            if (currentCount < tryCount) {
                currentCount++;
            } else {
                Thread.yield();
            }
        }
    }

    public static void unLock() {
        state.set(0);
    }
}
