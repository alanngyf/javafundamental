package com.gbacoder.boot.juc.add;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author alanulog
 * @create 2023-12-03
 *
 * Semaphore: 信號量
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        // 允許線程數量， 停車位; 限流
        Semaphore semaphore = new Semaphore(3);

        for (int i = 1; i <= 11; i++) {
            new Thread(() -> {
                // acquire
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " entered parking!");
                    TimeUnit.SECONDS.sleep(3);
                    System.out.println(Thread.currentThread().getName() + " left parking!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // release
                    semaphore.release();
                }


            }, String.valueOf(i)).start();
        }
    }
}
