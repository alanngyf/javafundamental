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
 * 5. 增加兩個static的同步方法, 只有一個對象， send sms... vs call...??
 *    > 先 send sms 在 call...
 *    > 鎖的對象是方法的調用者
 *    > static -> 類一加載就有了! Class 模板 (Phone3.class) <- 全局唯一
 *
 * 6. 兩個對象 兩個static的同步方法
 *    > 先 send sms 在 call...
 *
 * @author alanulog
 * @create 2023-12-02
 */
public class Test3 {
    public static void main(String[] args) {
        // 兩個對象, 兩個資源類
        Phone3 phone1 = new Phone3();
        Phone3 phone2 = new Phone3();

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

class Phone3 {


    // whichever Thread get the monitor, it will execute the block of code first
    public static synchronized void sendSms() { // 'this' instance is the monitor and serves as lock
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

    public static synchronized void call() {
        System.out.println("calling...");
    }

}
