package com.gbacoder.boot.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author alanulog
 * @create 2023-12-17
 *
 * Synchronized <- 默認是非公平鎖
 */
public class Demo02 {
    public static void main(String[] args) {
        Phone2 phone = new Phone2();

        new Thread(() -> {
            phone.sms();
        }, "A").start();

        new Thread(() -> {
            phone.sms();
        }, "B").start();
    }
}

class Phone2 {
    Lock lock = new ReentrantLock();
    public void sms() {
        /*
        * lock鎖 必須配對 否則就死鎖
        * */
        lock.lock(); // 細節 這裡有一把鎖
        lock.lock(); // 細節 這裡有一把鎖

        try {
            System.out.println(Thread.currentThread().getName() + " sms()...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            call(); // 這裡也有鎖
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            lock.unlock();
        }


    }

    public void call() {
        lock.lock(); // 細節 這裡是另外一把鎖
        try {
            System.out.println(Thread.currentThread().getName() + " call()...");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
}
