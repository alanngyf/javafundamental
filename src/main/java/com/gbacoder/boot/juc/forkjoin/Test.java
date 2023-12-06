package com.gbacoder.boot.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

/**
 * @author alanulog
 * @create 2023-12-05
 *
 * 1. Normal For Loop
 * 2. JoinFork (大數據量的時候使用)
 * 3. Steam + parallel + reduce (most efficient)
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //test1(); // sum=500000000500000000 time:20352
        //test2(); // sum=500000000500000000 time:8634
        test3(); // sum=500000000500000000 time:434
    }

    // sum=500000000500000000 time:20352
    public static void test1() {
        Long sum = 0L;

        long start = System.currentTimeMillis();

        for (Long i = 1L; i <= 1_000_000_000; i++) {
            sum += i;
        }

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " time:" + (end - start));
    }
    public static void test2() throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Long> task = new ForkJoinDemo(1L, 1_000_000_000L);
        ForkJoinTask<Long> submit = forkJoinPool.submit(task);//提交任務

        Long sum = submit.get();

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " time:" + (end - start));
    }
    public static void test3() {
        long start = System.currentTimeMillis();

        // stream 並行流
        long sum = LongStream.rangeClosed(0L, 1_000_000_000L).parallel().reduce(0, Long::sum);

        long end = System.currentTimeMillis();
        System.out.println("sum=" + sum + " time:" + (end - start));
    }
}
