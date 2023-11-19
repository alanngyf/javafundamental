package com.gbacoder.boot.thread;

/**
 * @author alanulog
 * @create 2023-11-18
 *
 * before JDK 5.0, there are 2 ways creating Thread
 * note that t.start() can be used for only 1 time
 *
 * one way
 * 1. create a class extends from Thread, and override run method
 * 2. new an instance (i.e t) of that class
 * 3. call t.start() method
 *
 * another way
 * 1. create a class implements Runnable, and override the run method
 * 2. new an instance of (i.e. p) that class
 * 3. new an instance of Thread class (t2) and pass the instance p as parameter
 * 4. call t2.start() method
 */
public class ThreadTest {
    public static void main(String[] args) {

        //
        new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }.start();



        // 1. create a class implements Runnable, and override the run method
        class printNum implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 100; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + ": " + i);
                    }
                }
            }
        }

        // 2. new a printNum instance
        printNum printNum = new printNum();
        Thread t2 = new Thread(printNum);
        t2.start();


        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
            if (i == 20) {
                try {
                    t2.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
