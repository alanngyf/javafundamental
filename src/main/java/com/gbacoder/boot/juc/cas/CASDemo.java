package com.gbacoder.boot.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author alanulog
 * @create 2023-12-09
 *
 * 什麼是 CAS? Compare And Swap
 * CAS是CPU的並發原理
 *
 * Unsafe類 用於處理
 * java無法操作內存
 * java調用C++ （使用 native 方法）
 * C++可以操作內存
 *
 *
 * CAS: ABA問題 (狸貓換太子)
 * CAS: 比較當前工作內存中的值和主內存中的值，如果這個值是期望的，那麼則執行操作！如果不是就一直循環
 *
 * 缺點：
 * 1. 循環會耗時
 * 2. 一次性只能保證一個共享變量的原子性
 * 3. ABA問題
 */
public class CASDemo {

    /**
     * CAS
     * .compareAndSet() 比較並交換
     * */
//    public static void main(String[] args) {
//        AtomicInteger atomicInteger = new AtomicInteger(2020);
//
//        // 對於我們平時寫的SQL： 樂觀鎖
//
//        // public final boolean compareAndSet(int expect, int update) {...}
//        // 如果期望值是2020 就更新為2021， 否則不更新
//        // ================ 搗亂的線程 ===================================
//        System.out.println(atomicInteger.compareAndSet(2020, 2021));
//        System.out.println(atomicInteger);
//
//        System.out.println(atomicInteger.compareAndSet(2021, 2020));
//        System.out.println(atomicInteger);
//
//        // ================ 期望的線程 ===================================
//        System.out.println(atomicInteger.compareAndSet(2020, 6666));
//        System.out.println(atomicInteger);
//    }

    public static void main(String[] args) {
        //AtomicInteger atomicInteger = new AtomicInteger(2020);
        // 【强制】所有整型包装类对象之间值的比较，全部使用 equals 方法比较。
        //  说明:对于 Integer var = ? 在 -128 至 127 之间的赋值，Integer 对象是在 IntegerCache.cache 产生，会复用已有对象，
        //  这个区间内的 Integer 值可以直接使用 == 进行判断，
        //  但是这个区间之外的所有数据，都会在堆上产生，并不会复 用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。

        // 正常業務操作 比較的一般都是對象 AtomicStampedReference<User>, 對象的引用一般是唯一的
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(10, 1);

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp(); // 獲得版本號
            System.out.println("A1 =>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(atomicStampedReference.compareAndSet(10, 12,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A2 =>" + atomicStampedReference.getStamp() );

            System.out.println(atomicStampedReference.compareAndSet(12, 10,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("A3 =>" + atomicStampedReference.getStamp() );

        }, "A").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp(); // 獲得版本號
            System.out.println("B1 =>" + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(10, 16,
                    atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("B1 =>" + atomicStampedReference.getStamp());

        }, "B").start();


        System.out.println("START=========== compare Integer");
        Integer a = 127; // 在 -128 至 127 之间的赋值，Integer 对象是在 IntegerCache.cache 产生，会复用已有对象
        Integer b = 127;
        System.out.println("a == b " + (a == b));

        Integer c = 128; // 但是这个区间之外的所有数据，都会在堆上产生，并不会复 用已有对象，这是一个大坑，推荐使用 equals 方法进行判断。
        Integer d = 128;
        System.out.println("c == d " + (c == d));
        System.out.println("c.equals(d) " + (c.equals(d)));
        System.out.println("END=========== compare Integer");
    }
}
