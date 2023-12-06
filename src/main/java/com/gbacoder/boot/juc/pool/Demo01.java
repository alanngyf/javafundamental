package com.gbacoder.boot.juc.pool;

import java.util.concurrent.*;

/**
 * @author alanulog
 * @create 2023-12-04
 * 使用線程池後，使用線程池來創建線程
 *
 * Executors 工具類 3大方法
 *
 * Executors.newSingleThreadExecutor(); // 單個線程
 * Executors.newFixedThreadPool(5); // 創建一個固定的線程池
 * Executors.newCachedThreadPool(); // 可伸縮, 看 execute 了多少個線程
 *
 * ALIBABA 開發手冊
 * 线程池不允许使用 Executors 去创建，而是通过 ThreadPoolExecutor 的方式，
 * 这样的处理方 式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险。
 *
 * 7大參數 ThreadPoolExecutor
 * public ThreadPoolExecutor(int corePoolSize, // 核心線程數
 *                               int maximumPoolSize, // 核心線程數
 *                               long keepAliveTime, //
 *                               TimeUnit unit, // 等候時間單位
 *                               BlockingQueue<Runnable> workQueue, // 阻塞隊列
 *                               ThreadFactory threadFactory, //
 *                               RejectedExecutionHandler handler) {
 *     }
 *
 * 4種拒絕策略
 * 1. new ThreadPoolExecutor.AbortPolicy() //默認, throws RejectedExecutionException
 * 2. new ThreadPoolExecutor.CallerRunsPolicy() //哪来的去那里 下面的例子 回到main thread执行
 * 3. new ThreadPoolExecutor.DiscardPolicy() //隊列滿了有可能丟掉任務 不會拋出異常
 * 4. new ThreadPoolExecutor.DiscardOldestPolicy() //隊列滿了和最早的線程競爭， 也不會拋出異常
 *
 * 最大線程該如何定義
 * 1. CPU 密集型 // 多少核就定為多少，可以保持CPU效率最高
 * 2. IO  密集型 // 15個大型程序， IO非常佔用資源 判斷你程序中耗用IO線程 設置為2倍
 */
public class Demo01 {
    public static void main(String[] args) {
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//        ExecutorService threadPool = Executors.newCachedThreadPool();
        System.out.println(Runtime.getRuntime().availableProcessors());

        // 自定義線程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2,
                Runtime.getRuntime().availableProcessors(), // CPU 密集型寫法
                3,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy()); // 拒絕策略 maximumPoolSize + BlockingQueue都滿了 還有新thread近來

        try {
            // 最大承載 隊列 + maximumPoolSize = 3 + 5 = 8
            // 超出最大承載數 可能會導致異常 java.util.concurrent.RejectedExecutionException
            for (int i = 1; i <= 10 ; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " OK");
                });
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
