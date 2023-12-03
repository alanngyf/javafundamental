package com.gbacoder.boot.juc.unsafecollection;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author alanulog
 * @create 2023-12-03
 *
 * // java.util.ConcurrentModificationException
 * 解決方案:
 *   > 1. List<String> arr = new Vector<>(); //Thread Safe, since 1.0
 *   > 2. List<String> arr = Collections.synchronizedList(new ArrayList<>());
 *   > 3. List<String> arr = new CopyOnWriteArrayList<>(); // since 1.5
 *        > 寫入時避免覆蓋, 造成數據問題
 *        > 讀寫分離
 *        > CopyOnWriteArrayList is better than Vector because it uses Lock rather than synchronized method
 */
public class ListTest {
    public static void main(String[] args) {
//        List<String> list = Arrays.asList("1", "2", "3");
//        list.forEach(System.out::println);

        // java.util.ConcurrentModificationException
//        List<String> arr = new ArrayList<>();
        List<String> arr = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                arr.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(arr);
            }, String.valueOf(i)).start();
        }
    }
}
