package com.shaw.kratos.common.lock;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @author shaw
 * @date 2021/7/2
 */
public class KratosCustomizeLock {

    private static class Sync extends AbstractQueuedSynchronizer {

        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        @Override
        protected boolean tryAcquire(int arg) {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
    }

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unLock() {
        sync.release(0);
    }

    public boolean tryUnLock() {
        return sync.tryRelease(0);
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }
}
