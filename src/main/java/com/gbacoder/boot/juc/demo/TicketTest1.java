package com.gbacoder.boot.juc.demo;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author alanulog
 * @create 2023-12-02
 */
public class TicketTest1 {
    public static void main(String[] args) {
        Ticket1 ticket = new Ticket1();
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.ticketSold();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.ticketSold();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 60; i++) ticket.ticketSold();}, "C").start();

    }
}

// Resource Class, OOP
class Ticket1 {
    private int numberOfTicket = 50;
    Lock lock = new ReentrantLock();

    // ticket sold
    public void ticketSold() {
        lock.lock();
        try {
            if (numberOfTicket > 0) {
                System.out.println(Thread.currentThread().getName() + " sold ticket #" + (numberOfTicket--) +"; remaining number of tickets " + numberOfTicket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
