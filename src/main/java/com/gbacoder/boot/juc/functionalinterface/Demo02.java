package com.gbacoder.boot.juc.functionalinterface;

import java.util.function.Predicate;

/**
 * @author alanulog
 * @create 2023-12-05
 *
 * @FunctionalInterface
 * public interface Predicate<T> {
 *
 *     boolean test(T t); // 傳入一個參數T 返回類型boolean
 *     ...
 * }
 */
public class Demo02 {
    public static void main(String[] args) {
        // 判斷字符串是否為空
//        Predicate<String> predicate = new Predicate<String>() {
//
//            @Override
//            public boolean test(String str) {
//                return str.isEmpty();
//            }
//        };

        Predicate<String> predicate = str -> str.isEmpty();

        System.out.println(predicate.test("abc"));
        System.out.println(predicate.test(""));
    }
}
