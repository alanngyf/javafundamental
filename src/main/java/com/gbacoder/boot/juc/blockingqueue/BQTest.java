package com.gbacoder.boot.juc.blockingqueue;

import com.gbacoder.boot.juc.lock8.Test4;

import java.util.Collection;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author alanulog
 * @create 2023-12-04
 *
 * 什麼時候會用BlockingQueue 阻塞隊列? 多線程並發處理，線程池
 *
 */
public class BQTest {
    public static void main(String[] args) throws InterruptedException {
//        test1();
//        test2();
//        test3();
        test4();
    }

    /*
    * 拋出異常
    *
    * add(x)
    * remove()
    * */
    public static void test1() {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        System.out.println(blockingQueue.element()); // 查看隊首元素

        System.out.println("========================");

        //java.lang.IllegalStateException: Queue full 拋出異常
//        System.out.println(blockingQueue.add("d"));
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());

        // java.util.NoSuchElementException 拋出異常
//        System.out.println(blockingQueue.remove());
    }

    /*
    * 有返回值 不拋出異常
    *
    * offer(x) 有返回值 true 成功 false 失敗
    * poll() 如果queue為空 返回null
    * */
    public static void test2() {
        // 隊列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        System.out.println(blockingQueue.peek());
        //java.lang.IllegalStateException: Queue full 拋出異常
        // System.out.println(blockingQueue.offer("d"));
        System.out.println("========================");

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        // java.util.NoSuchElementException 拋出異常
        System.out.println(blockingQueue.poll());
    }

    /*
    * 等待， 阻塞 （一直阻塞）
    * */
    public static void test3() throws InterruptedException {
        // 隊列大小
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");

        // blockingQueue.put("d"); // not enough capacity, keep waiting

        System.out.println("========================");
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());

        // System.out.println(blockingQueue.take()); // no element in blocking queue, keep waiting

    }

    /*
    * 等待， 阻塞 （過時不候)
    * */
    public static void test4() throws InterruptedException {
        ArrayBlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);

        blockingQueue.offer("a");
        blockingQueue.offer("b");
        blockingQueue.offer("c");
        blockingQueue.offer("d", 2, TimeUnit.SECONDS); //等待超果2s就退出
        System.out.println("========================");
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());

        System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));
    }
}
