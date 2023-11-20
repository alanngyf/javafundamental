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
 *
 * Thread common API
 * # start();
 * # run();
 * # currentThread();
 * # getName();
 * # setName("_threadName");
 *
 * # public static void sleep(long millis);
 * Thread.sleep(1000); // sleep for 1 second
 *
 * # public static void yield();
 * Thread.yield(); //A hint to the scheduler that the current thread is willing to yield its current use of a processor.
 *
 * # public final void join() throws InterruptedException
 * t1.join(); // Waits for this thread to die.
 *
 * # public final boolean isAlive()
 * // Tests if this thread is alive
 *
 * # public final int getPriority()
 * // return thread's priority, (1-10) MAX_PRIORITY: 10; NORM_PRIORITY: 5; MIN_PRIORITY: 1;
 *
 * # public final void setPriority(int newPriority)
 *
 *
 * ### Thread Lifecycle before jdk 1.5
 * # new
 * # Runnable
 * # Running
 * # Blocked
 * # Terminated
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
