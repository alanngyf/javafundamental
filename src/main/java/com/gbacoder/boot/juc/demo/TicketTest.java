package com.gbacoder.boot.juc.demo;

/**
 * @author alanulog
 * @create 2023-12-02
 */
public class TicketTest {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 60; i++) {
                    ticket.ticketSold();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.ticketSold();
            }
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 60; i++) {
                ticket.ticketSold();
            }
        }, "C").start();
    }
}

// Resource Class, OOP
class Ticket {
    private int numberOfTicket = 50;

    // ticket sold
    public synchronized void ticketSold() {
        if (numberOfTicket > 0) {
            System.out.println(Thread.currentThread().getName() + " sold ticket #" + (numberOfTicket--) +"; remaining number of tickets " + numberOfTicket);
        }
    }
}
