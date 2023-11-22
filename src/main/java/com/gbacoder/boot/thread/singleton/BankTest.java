package com.gbacoder.boot.thread.singleton;

/**
 * singleton pattern, lazy-loaded & Thread safe
 *
 * initialization-on-demand holder idiom
 *
 * @author alanulog
 * @create 2023-11-21
 */
public class BankTest {

    static Bank b1 = null;
    static Bank b2 = null;
    static Bank b3 = null;
    static Bank b4 = null;

    public static void main(String[] args) {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                b1 = Bank.getInstance();
            }
        };

        Thread t2 = new Thread() {
            @Override
            public void run() {
                b2 = Bank.getInstance();
            }
        };

        t1.start();
        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(b1);
        System.out.println(b2);
        System.out.println("b1 == b2: " + (b1 == b2));
    }
}


class Bank {

    private Bank() {}

    /**
     * In the context of the singleton pattern and the double-checked locking idiom,
     * the volatile keyword is used to prevent a subtle issue
     * known as "publication of partially constructed objects."
     *
     * Without volatile, under certain circumstances,
     * the Java memory model could allow another thread to see a partially constructed object.
     * */
    private static volatile Bank instance = null;

    /**
     * This implementation is not thread-safe because it does not handle the case
     * where multiple threads might try to create an instance of Bank simultaneously.
     * If two or more threads enter the if (instance == null) block at the same time,
     * they could end up creating multiple instances of the Bank class, violating the singleton pattern.
     * */
    public static Bank getInstance() {
        if (instance == null) {

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            instance = new Bank();
        }
        return instance;
    }

    /**
     * To make the singleton pattern thread-safe,
     * you can use synchronization or other mechanisms to ensure that
     * only one thread can create an instance of the class at a time.
     *
     * Here's an example of making the getInstance method thread-safe using double-checked locking:
     *
     * */
    public static Bank getInstanceThreadSafe() {
        if (instance == null) {
            synchronized (Bank.class) {
                if (instance == null) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    instance = new Bank();
                }
            }
        }
        return instance;
    }
}

/**
 * Alternatively, you could use the initialization-on-demand holder idiom,
 * which relies on the fact that inner classes are not loaded until they are referenced.
 * This ensures thread safety without the need for explicit synchronization
 *
 * This version of the singleton pattern is both lazy-loaded and thread-safe.
 * */
class Bank2 {

    private Bank2() {}

    private static class Holder {
        private static final Bank2 instance = new Bank2();
    }

    public static Bank2 getInstance() {
        return Holder.instance;
    }
}