package com.gbacoder.boot.juc.lock8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock (Summary)
 * 1. When multiple threads attempt to access the shared resource simultaneously,
 *    the thread that successfully acquires the lock associated with
 *    that resource gains the right to execute its code.
 *
 * 2.
 *
 * @author alanulog
 * @create 2023-12-02
 */
public class Test1 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {


    // whichever Thread get the monitor, it will execute the block of code first
    public synchronized void sendSms() { // 'this' instance is the monitor and serves as lock
//        try {
//            wait(2000); // this release the monitor
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        try {
            TimeUnit.SECONDS.sleep(2); // this does not release the monitor
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("texting...");
    }

    public synchronized void call() {
        System.out.println("calling...");
    }
}
