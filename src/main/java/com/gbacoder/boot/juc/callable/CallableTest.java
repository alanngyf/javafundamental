package com.gbacoder.boot.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author alanulog
 * @create 2023-12-03
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // public class FutureTask<V> implements RunnableFuture<V>
        // public interface RunnableFuture<V> extends Runnable, Future<V>

        MyThread myThread = new MyThread();
        FutureTask<Integer> futureTask = new FutureTask<>(myThread);

        new Thread(futureTask, "A").start();
        new Thread(futureTask, "B").start(); //結果會被緩存，提高效率

        // return value within futureTask
        Integer integer = (Integer) futureTask.get(); // get() 方法會產生阻塞 // 放在最後
        // 或者使用異步通信來處理
        System.out.println(Thread.currentThread().getName() + ": returnVal == " +integer);


    }
}
class MyThread implements Callable<Integer> {
    @Override
    public Integer call() {
        System.out.println(Thread.currentThread().getName() + ": call()...");
        return 1024;
    }
}
