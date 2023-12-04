package com.gbacoder.boot.juc.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author alanulog
 * @create 2023-12-03
 *
 * CyclicBarrier: 加法計數器
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7, () -> {
            System.out.println("completed all 7 jobs");
        });

        for (int i = 1; i <= 14; i++) {
            final int temp = i;
            // lambda 可以操作到 i? 必須利用 temp 傳遞 i, 只能得到final类型
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " completed job #" + temp);

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
