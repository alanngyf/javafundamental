package com.gbacoder.boot.juc.volatiletest;

import java.util.concurrent.TimeUnit;

/**
 * @author alanulog
 * @create 2023-12-07
 */
public class JMMDemo {
    private volatile static int num = 0; // 不加 volatile 程序會死循環
    public static void main(String[] args) {
        new Thread(() -> { // 線程A 對 主內存的變化是不知道的
            int count = 0;
            while (num == 0) {
                System.out.println(Thread.currentThread().getName() + " " + (count++));
            }
            System.out.println(Thread.currentThread().getName() + " completed");
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
