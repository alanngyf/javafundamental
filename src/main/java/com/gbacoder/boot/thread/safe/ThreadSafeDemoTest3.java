package com.gbacoder.boot.thread.safe;

/**
 * @author alanulog
 * @create 2023-11-20
 *
 * implements Runnable
 *
 * There won't be thread safety issue if all the thread is read only.
 * Thread safety typically involves multiple-threads manipulate (write) the same data
 *
 * ticket selling example:
 * There are 3 ticket vendors (3 threads), total 100 tickets
 *
 * possible issue:
 * - resell the same ticket via different vendors
 * - oversell the ticket
 */
public class ThreadSafeDemoTest3 {
    public static void main(String[] args) {
        TicketVendor3 ticketVendor = new TicketVendor3();

        Thread t1 = new Thread(ticketVendor);
        Thread t2 = new Thread(ticketVendor);
        Thread t3 = new Thread(ticketVendor);
        t1.setName("Vendor 1");
        t2.setName("Vendor 2");
        t3.setName("Vendor 3");

        t1.start();
        t2.start();
        t3.start();
    }
}

class TicketVendor3 implements Runnable {
    int ticket = 100; // Number of tickets
    // Object obj = new Object();

    boolean isFlag = true;

    @Override
    public void run() {

        while (isFlag) {

            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            snatching();

        }
    }

    // if the synchronized method is not static, then this is the default monitor
    // is "this" one and only? yes
    public synchronized void snatching() {
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
