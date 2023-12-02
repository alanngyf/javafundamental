package com.gbacoder.boot.juc.demo;

import java.util.concurrent.TimeUnit;

/**
 * concurrent vs parallel running
 * @author alanulog
 * @create 2023-12-01
 */
public class DemoTest {
    public static void main(String[] args) {
        // get the number of core in your CPU
        // CPU intensive, IO intensive
        System.out.println(Runtime.getRuntime().availableProcessors());

//        try {
//            TimeUnit.DAYS.sleep(1L);
//            TimeUnit.SECONDS.sleep(2L);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
