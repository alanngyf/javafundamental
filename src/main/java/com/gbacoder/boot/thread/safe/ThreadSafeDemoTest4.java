package com.gbacoder.boot.thread.safe;

/**
 * synchronized method
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
 * # method 1
 * synchronized(monitor) {
 *     // code that needed to be synchronized, code that manipulate shared data
 *     // whichever thread has the monitor, the thread has the access to the synchronized block of code
 *     // monitor must be the same object for every thread
 * }
 *
 * # method 2
 * synchronized method: code that manipulate shared data are all within synchronized method
 *
 */
public class ThreadSafeDemoTest4 {
    public static void main(String[] args) {
        TicketVendor4 t1 = new TicketVendor4();
        TicketVendor4 t2 = new TicketVendor4();
        TicketVendor4 t3 = new TicketVendor4();

        t1.setName("Vendor 1");
        t2.setName("Vendor 2");
        t3.setName("Vendor 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketVendor4 extends Thread {
    static int ticket = 100; // number of tickets, add static to make sure only 1 copy of data across multiple-thread
    static boolean isFlag = true; // shared data
    @Override
    public void run() {
        while(isFlag) {
            try {
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            snatchingTicket();
        }
    }

    // non-static method, monitor is "this", no, not one and only
    // static method, monitor is "TicketVendor4.class", yes, one and only
    // some method, you cannot declare as static, so it varies case to case
    public static synchronized void snatchingTicket() {
        if (ticket > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + ": ticket# " + ticket );
            ticket--;
        } else {
            isFlag = false;
        }
    }
}
