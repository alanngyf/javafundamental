package com.gbacoder.boot.juc.unsafecollection;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author alanulog
 * @create 2023-12-03
 *
 * java.util.ConcurrentModificationException
 * 解决方案:
 *     > 1. Map<String, String> map = new ConcurrentHashMap<>();
 */
public class MapTest {
    public static void main(String[] args) {
        // this.loadFactor = DEFAULT_LOAD_FACTOR
        // initialCapacity
        // default: new HashMap<>(16, 0.75f); // 1 << 4 -> 16
        // Map<String, String> map = new HashMap<>();

        Map<String, String> map = new ConcurrentHashMap<>();
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
