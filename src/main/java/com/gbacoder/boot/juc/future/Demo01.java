package com.gbacoder.boot.juc.future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author alanulog
 * @create 2023-12-06
 *
 * Asyn Call 異步調用 Ajax
 *  異步執行
 *    成功回調
 *    失敗回調
 *
 */
public class Demo01 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 沒有返回值的異步回調
//        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(Thread.currentThread().getName() + " runAsync=>Void");
//        });
//
//        System.out.println("1111");
//
//        System.out.println(completableFuture.get()); // 獲取阻塞執行結果

        // 有返回值的 supplyAsync 異步回調； ajax 成功和失敗的回調
        // 成功回調 返回值
        // 失敗回調 返回錯誤信息
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + " supplyAsync=>Integer");
            int i = 10/0;
            return 1024;
        });

        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t => " + t);
            System.out.println("u => " + u);
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233;
        }).get());
    }

    /**
     * success code 200
     * error code 404 500
     * */
}
