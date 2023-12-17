package com.gbacoder.boot.juc.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author alanulog
 * @create 2023-12-17
 *
 * Synchronized <- 默認是非公平鎖
 */
public class Demo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone {
    public synchronized void sms() {
        System.out.println(Thread.currentThread().getName() + " sms()...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        call(); // 這裡也有鎖
    }

    public synchronized void call() {
        System.out.println(Thread.currentThread().getName() + " call()...");
    }
}
