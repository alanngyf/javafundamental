package com.gbacoder.boot.thread.exer;

/**
 *
 * @author alanulog
 * @create 2023-11-20
 *
 * Two individuals simultaneously deposit $3,000 each into a shared account.
 * Each person makes three deposits of $1,000 each.
 * is there a thread safety issue?
 */
public class AccountTest {
    public static void main(String[] args) {
        Account account = new Account();

        Customer customer1 = new Customer(account, "Customer A");
        Customer customer2 = new Customer(account, "Customer B");

        customer1.start();
        customer2.start();
    }
}

class Account {
    private double balance;

    public synchronized void deposit(double amt) { // is "this" one and only? yes
        balance += amt;

        System.out.println(Thread.currentThread().getName() + " deposits $" + amt + "; total balance: " + balance);
    }
}

class Customer extends Thread {
    Account account;

    public Customer(Account acct, String name) {
        super(name);
        this.account = acct;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account.deposit(1000);
        }
    }
}
