package com.gbacoder.boot.juc.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 8鎖 關於鎖的八個問題
 * 1. 標準情況 兩個同步方法， 兩個Threads 先發sms 還是先打call?
 *    > 先發sms 因為Thread被阻塞了, monitor沒釋放 所以第二個線程得等到獲得 "this"才能繼續
 *    > synchronized method 'this' 是monitor 誰先拿到鎖誰先執行
 *
 * 3. 增加一個普通method後 是先send sms 還是 hello?
 *    > hello first, hello不是同步方法 執行不受鎖的影響
 *
 * 4. 兩個對象， 兩個同步方法 先send sms 是是先call?
 *    > 先call 因為 send sms被延遲了 與鎖無關 兩個對象各自有自己的鎖
 *
 * @author alanulog
 * @create 2023-12-02
 */
public class Test2 {
    public static void main(String[] args) {
        // 兩個對象, 兩個資源類
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        new Thread(() -> {
            phone1.sendSms();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

class Phone2 {


    // whichever Thread get the monitor, it will execute the block of code first
    public synchronized void sendSms() { // 'this' instance is the monitor and serves as lock
//        try {
//            wait(2000); // this release the monitor
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        try {
            TimeUnit.SECONDS.sleep(2); // this does not release the monitor
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("texting...");
    }

    public synchronized void call() {
        System.out.println("calling...");
    }

    // 這裡沒有鎖, 不是同步方法 不受鎖的影響
    public void hello() {
        System.out.println("hello...");
    }
}
