package com.gbacoder.boot.juc.unsafecollection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author alanulog
 * @create 2023-12-03
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("1", "2", "3");
        list.forEach(System.out::println);

        ArrayList<String> arr = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                arr.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(arr);
            }, String.valueOf(i)).start();
        }
    }
}
