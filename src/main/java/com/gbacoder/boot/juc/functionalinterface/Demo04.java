package com.gbacoder.boot.juc.functionalinterface;

import java.util.function.Supplier;

/**
 * @author alanulog
 * @create 2023-12-05
 *
 * @FunctionalInterface
 * public interface Supplier<T> {
 *     T get(); // 只有返回值 沒有輸入參數
 *     ...
 * }
 */
public class Demo04 {
    public static void main(String[] args) {
//        Supplier<String> supplier = new Supplier<Integer>() {
//            @Override
//            public String get() {
//                return 1024;
//            }
//        };
        Supplier<Integer> supplier = () -> 1024;
        System.out.println(supplier.get());
    }
}
