package com.gbacoder.boot.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * A print first, then print B, then print C
 *
 * @author alanulog
 * @create 2023-12-02
 */
public class ProducerConsumerTest2 {
    public static void main(String[] args) {
        Data2 data = new Data2();

        new Thread(()-> {for (int i = 0; i < 10; i++) data.printA();}, "A1").start();
        new Thread(()-> {for (int i = 0; i < 10; i++) data.printB();}, "B").start();
        new Thread(()-> {for (int i = 0; i < 10; i++) data.printC();}, "C").start();

    }
}

class Data2 {
    private int number = 1;

    Lock lock = new ReentrantLock();
    Condition condition1 = lock.newCondition();
    Condition condition2 = lock.newCondition();
    Condition condition3 = lock.newCondition();

    public void printA() {
        lock.lock();
        try {
            while (number != 1) {
                condition1.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> AAAAA");
            number = 2;
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printB() {
        lock.lock();
        try {
            while (number != 2) {
                condition2.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> BBBBB");
            number = 3;
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void printC() {
        lock.lock();
        try {
            while (number != 3) {
                condition3.await();
            }
            System.out.println(Thread.currentThread().getName() + "=> CCCCC");
            number = 1;
            condition1.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
