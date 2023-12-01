package com.gbacoder.boot.thread.communication;

/**
 * Print numbers from 1 to 100 using two threads, with each thread printing numbers alternately.
 *
 * @author alanulog
 * @create 2023-11-28
 *
 * these 3 methods must be used within synchronized block or synchronized method
 * these 3 methods must be called by the monitor with the block and method or it will give us illegalMonitorStateException
 * wait() : release monitor, and wait til it's notified
 * notify() : The notify() method awakens the thread with the highest priority among those currently waiting.
 * notifyAll() : awakens all waiting threads
 *
 * Lock & Condition to implement inter-threads communication
 *
 * sleep() v.s. wait()
 * - sleep is a static method, wait is not
 * - wait must be used within synchronized method or block, sleep doesn't need to
 * - when wait is used within synchronized method or block, it releases the monitor
 */
public class PrintNumberTest {
    public static void main(String[] args) {
        PrintNumber p = new PrintNumber();
        Thread t1 = new Thread(p, "Thread #1");
        Thread t2 = new Thread(p, "Thread #2");

        t1.start();
        t2.start();
    }
}

class PrintNumber implements Runnable {
    private int number = 1;

    @Override
    public void run() {
        while(true) {

            synchronized (this) { // monitor doesn't need to be 'this'
                this.notify(); // 'this' must be the monitor

                if (number <= 100) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ": " + number);
                    number++;

                    try {
                        /*thread will be waiting here until it's notified, and it will release the monitor as well*/
                        this.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    break;
                }
            }

        }
    }
}

