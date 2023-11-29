package com.gbacoder.boot.thread.communication;

/**
 * Print numbers from 1 to 100 using two threads, with each thread printing numbers alternately.
 *
 * @author alanulog
 * @create 2023-11-28
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

            synchronized (this) {
                notify();

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
                        wait();
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

