package com.gbacoder.boot.thread.communication;

/**
 * Producer, Clerk, Consumer
 *
 * @author alanulog
 * @create 2023-11-30
 *
 *
 * Analysis
 * 1. Multiple-Threads? Yes, Producer & Consumer
 * 2. shared data? Yes, the product
 * 3. Thread Safety Issue? Yes, there is shared data
 * 4. how to handle thread safety issue? synchronization
 * 5. Communication between Threads? Yes
 */
public class ProducerConsumerTest {
    public static void main(String[] args) {
        Clerk clerk = new Clerk();
        Producer producer = new Producer(clerk);
        Thread p1 = new Thread(producer);
        p1.start();

        Consumer consumer = new Consumer(clerk);
        Thread c1 = new Thread(consumer);
        c1.start();

    }
}

class Clerk {
    private int numOfProduct = 0;

    // add good
    public synchronized void produceGood() {
        if (numOfProduct >= 20) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            numOfProduct++;
            notify();
        }

    }

    // sell good
    public synchronized void sellGood() {
        if (numOfProduct <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            numOfProduct--;
            notify();
        }

    }

    public int getNumOfProduct() {
        return numOfProduct;
    }
}

class Producer implements Runnable {
    private Clerk clerk;

    public Producer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        while (true) {
            // System.out.println("Producer produces good...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.produceGood();
            System.out.println(Thread.currentThread().getName() + "Producer produced product #" + clerk.getNumOfProduct());
        }
    }
}

class Consumer implements Runnable {
    private Clerk clerk;
    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }
    @Override
    public void run() {
        while (true) {
            // System.out.println("Consumer consumes good...");
            try {
                Thread.sleep(1600);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Consumer consumed product #" + clerk.getNumOfProduct());
            clerk.sellGood();
        }
    }
}