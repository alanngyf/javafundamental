package com.gbacoder.boot.juc.lock;

import lombok.SneakyThrows;

import java.util.concurrent.TimeUnit;

/**
 * @author alanulog
 * @create 2023-12-17
 *
 * 如何排查死鎖?
 * java bin 下有許多工具
 * 1. 使用 'jps -l' 定位進程號
 * 2. 使用 'jstack 進程號' 查看進程信息
 *
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new MyThread(lockA, lockB), "T1").start();
        new Thread(new MyThread(lockB, lockA), "T2").start();
    }
}

class MyThread implements Runnable {

    private String lockA;
    private String lockB;

    public MyThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + " lock: " + lockA + " get " + lockB);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + " lock: " + lockB + " get " + lockA);
            }
        }
    }
}
