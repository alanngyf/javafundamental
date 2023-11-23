package com.gbacoder.boot.thread.lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author alanulog
 * @create 2023-11-20
 *
 * extends Thread
 *
 * possible issue:
 * - must add use "static" to ensure all threads share the same copy of data
 * - resell the same ticket via different vendors
 * - oversell the ticket
 *
 * 2 solutions (synchronized block, synchronized method):
 *
 * # method 1, synchronized block
 * synchronized(monitor) {
 *     // code that needed to be synchronized, code that manipulate shared data
 *     // whichever thread has the monitor, the thread has the access to the synchronized block of code
 *     // monitor must be the same object for every thread
 * }
 *
 * # method 2, synchronized method
 * synchronized method: code that manipulate shared data are all within synchronized method
 *
 * method 3 Lock, ReentrantLock
 * Lock implementations provide more extensive locking operations
 * than can be obtained using synchronized methods and statements.
 * They allow more flexible structuring, may have quite different properties,
 * and may support multiple associated Condition objects.
 *
 *
 */
public class ThreadSafeLockDemoTest {
    public static void main(String[] args) {
        TicketVendor2 t1 = new TicketVendor2();
        TicketVendor2 t2 = new TicketVendor2();
        TicketVendor2 t3 = new TicketVendor2();

        t1.setName("Vendor 1");
        t2.setName("Vendor 2");
        t3.setName("Vendor 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketVendor2 extends Thread {
    static int ticket = 100; // number of tickets, add static to make sure only 1 copy of data across multiple-thread

    private static final ReentrantLock lock = new ReentrantLock(); // make it private, so cannot obtain outside the class.

    @Override
    public void run() {
        while(true) {
            try {
                lock.lock();
                if (ticket > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(Thread.currentThread().getName() + ": ticket# " + ticket);
                    ticket--;
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }

        }
    }
}
