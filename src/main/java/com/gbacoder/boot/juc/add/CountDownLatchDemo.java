package com.gbacoder.boot.juc.add;

import java.util.concurrent.CountDownLatch;

/**
 * @author alanulog
 * @create 2023-12-03
 *
 * CountDownLatch: 減法計數器
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 必須執行的任務時再使用
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + ": job completed");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await(); //等待計數器歸零 再向下執行

        System.out.println("all job completed");
    }
}
