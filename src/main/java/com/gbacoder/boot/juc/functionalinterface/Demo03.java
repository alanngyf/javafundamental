package com.gbacoder.boot.juc.functionalinterface;

import java.util.function.Consumer;

/**
 * @author alanulog
 * @create 2023-12-05
 *
 * @FunctionalInterface
 * public interface Consumer<T> {
 *     void accept(T t); // 只有輸入 沒有返回值
 *     ...
 * }
 */
public class Demo03 {
    public static void main(String[] args) {
//        Consumer<String> consumer = new Consumer<String>() {
//            @Override
//            public void accept(String str) {
//                System.out.println(str);
//            }
//        };
        Consumer<String> consumer = str -> System.out.println(str);

        consumer.accept("abc");
    }
}
