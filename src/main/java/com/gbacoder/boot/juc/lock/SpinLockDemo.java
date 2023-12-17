package com.gbacoder.boot.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author alanulog
 * @create 2023-12-17
 *
 * 自旋鎖 Spin lock
 *
 */
public class SpinLockDemo {

    // 默認值 int 0; Thread null;
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    // 加鎖
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ==> mylock");
        // 自旋鎖
        while (!atomicReference.compareAndSet(null, thread)) {

        }
    }

    // 解鎖
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ==> myunlock");
        // 自旋鎖
        atomicReference.compareAndSet(thread, null);
    }
}
