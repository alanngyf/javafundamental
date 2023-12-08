package com.gbacoder.boot.juc.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author alanulog
 * @create 2023-12-08
 *
 *  volatile 保證可見性 ensures visibility
 *  volatile 不保證原子性
 *  volatile 禁止 指令重排 Happens-before
 *
 *  javap -c VolatileDemo02
 *  // 查看字節碼文件
 *
 * 不加 lock 或者 synchronized 怎樣保證原子性?
 * > 使用原子類 解決 原子性問題 (底層牽涉 Unsafe類 特殊存在)
 *
 * 什麼是指令重排?
 * 源代碼 -> 編譯器優化的重排 -> 指令並行也可能會重排 -> 內存系統也可能會重排 -> 執行
 * 處理器進行指令重排的時候 考慮：數據之間的依賴問題
 *
 * example
 * int x = 1; //step 1
 * int y = 2; //step 2
 * x = x + 5; //step 3
 * y = x * x; //step 4
 *
 * 我們期望的執行順序 1234 但是執行時可能會變成 2134, 1324
 * 可不可能4123? 不可以
 *
 * 內存屏障 (memory barriers) CPU指令
 * 1. 保證特定的操作的執行順序
 * 2. 可以保證某些變量的內存可見性 (利用volatile特性實現了可見性)
 *
 *
 * A memory barrier is a type of instruction used in concurrent programming to control the order of memory operations (reads and writes).
 * It prevents certain types of reordering of memory operations by the compiler or processor.
 * There are different types of memory barriers, each designed to ensure that
 * operations before the barrier are completed before operations that come after it.
 * This is crucial in multi-threaded environments to maintain consistency and prevent memory access anomalies.
 *
 * the volatile keyword is used to indicate that a variable's value will be modified by different threads.
 * Declaring a variable as volatile ensures that its value is always read from and written directly to the main memory,
 * not just to a CPU cache. This guarantees visibility, meaning that when one thread updates the value of a volatile variable,
 * other threads will immediately see the updated value.
 *
 * It's a way to prevent threads from seeing stale or inconsistent data due to caching at the CPU level.
 * However, it's important to note that volatile does not provide atomicity or prevent race conditions by itself.
 *
 */
public class VolatileDemo02 {

    // 原子類的 integer AtomicInteger
    private volatile static AtomicInteger num = new AtomicInteger(); // 加了 volatile, num 最後都不一定是20k

    public static void add() {
        //num++; // 不是一個原子性操作
        num.getAndIncrement(); // AtomicInteger +1 方法, CAS
    }

    public static void main(String[] args) {

        // 理論上 num 結果應該為20k
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    VolatileDemo02.add();
                }
            }).start();
        }

        while (Thread.activeCount() > 2) { // main, gc and other
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + " " + num);
    }
}
