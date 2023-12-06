package com.gbacoder.boot.juc.functionalinterface;

import java.util.function.Function;

/**
 * @author alanulog
 * @create 2023-12-05
 *
 * @FunctionalInterface
 * public interface Function<T, R> {
 *     R apply(T t); // 傳入參數T 返回類型R
 *     ...
 * }
 *
 * // 函數型接口，有一個輸入的參數， 有一個輸出的類型
 * // 只要是函數型接口 可以用lambda表達式簡化
 *
 */
public class Demo01 {
    public static void main(String[] args) {
        // 工具類， 輸出輸入的String
//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String str) {
//                return str;
//            }
//        };

        Function<String, String> function = (str) -> {return str;};

        System.out.println(function.apply("abc"));

    }
}
