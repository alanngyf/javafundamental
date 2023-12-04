package com.gbacoder.boot.juc.rw;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock
 * 獨佔鎖 （readWriteLock.writeLock().lock();）一次只可以被一個線程佔有
 * 共享鎖 （readWriteLock.readLock().lock();）多個線程可以同時佔有
 *
 * 讀-讀 可以同時進行
 * 讀-寫 不可以同時進行
 * 寫-寫 不可以同時進行
 *
 * @author alanulog
 * @create 2023-12-04
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        // MyCache myCache = new MyCache();
        MyCacheLock myCache = new MyCacheLock();

        // write
        for (int i = 1; i <= 15; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.put(temp + "", temp + "");
            }, String.valueOf(i)).start();
        }

        // read
        for (int i = 1; i <= 15; i++) {
            final int temp = i;
            new Thread(() -> {
                myCache.get(temp + "");
            }, String.valueOf(i)).start();
        }
    }
}

class MyCacheLock {
    private volatile Map<String, Object> map = new HashMap<>();
    // 讀寫鎖, 更細粒度的控制
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    // write, only 1 thread can write at the same time
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " write " + key);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " write OK...");
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    // read, all threads can read at the same time
    public void get(String key) {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " read " + key);
            Object val = map.get(key);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(Thread.currentThread().getName() + " read OK...");
        } catch (Exception e) {

        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

class MyCache {
    private volatile Map<String, Object> map = new HashMap<>();

    // write
    public void put(String key, Object value) {
        System.out.println(Thread.currentThread().getName() + " write " + key);
        map.put(key, value);
        System.out.println(Thread.currentThread().getName() + " write OK...");
    }

    // read
    public void get(String key) {
        System.out.println(Thread.currentThread().getName() + " read " + key);
        Object val = map.get(key);
        System.out.println(Thread.currentThread().getName() + " read OK...");
    }
}