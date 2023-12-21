package com.gbacoder.common.otherapi;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Random;

/**
 * @author alanulog
 * @create 2023-12-21
 *
 * System
 * Runtime
 * math
 * > BigInteger
 * > BigDecimal
 * Random
 */
public class OtherAPITest {

    @Test
    public void test1() {
        String javaVersion = System.getProperty("java.version");
        System.out.println("Java Version: " + javaVersion);

        String javaHome = System.getProperty("java.home");
        System.out.println("Java Home: " + javaHome);

        String osName = System.getProperty("os.name");
        System.out.println("OS Name: " + osName);

        String osVersion = System.getProperty("os.version");
        System.out.println("OS Version: " + osVersion);

        String userName = System.getProperty("user.name");
        System.out.println("Username: " + userName);

        String userHome = System.getProperty("user.home");
        System.out.println("User home: " + userHome);

        String userDir = System.getProperty("user.dir");
        System.out.println("User directory: " + userDir);
    }

    /**
     * 對應著Java進程的內存使用的運行時環境，是單例的
     * */
    @Test
    public void test2() {
        Runtime runtime = Runtime.getRuntime();
        long initialMemory = runtime.totalMemory();
        long maxMemory = runtime.maxMemory();
        String str = "";
        for (int i = 0; i < 10000; i++) {
            str += i;
        }
        long freeMemory = runtime.freeMemory();

        // 默認的內存佔用是物理內存的1/64
        System.out.println("總內存: " + initialMemory / 1024 / 1024 * 64 + "MB");
        // 最大內存是物理內存的4倍
        System.out.println("最大內存: " + maxMemory / 1024 / 1024 * 4 + "MB");
        System.out.println("空間內存: " + freeMemory / 1024 / 1024 + "MB");
        System.out.println("已用內存: " + (initialMemory - freeMemory) / 1024 / 1024 + "MB");
    }

    @Test
    public void test3() {
        System.out.println("Math.round(12.3): " + Math.round(12.3));
        System.out.println("Math.round(12.5): " + Math.round(12.5));
        System.out.println("Math.round(-12.5): " + Math.round(-12.5));
        System.out.println("Math.round(-12.3): " + Math.round(-12.3));
        System.out.println("Math.round(-12.6): " + Math.round(-12.6));
    }

    @Test
    public void test4() {
        // Integer 能存儲的最大整形值為 2 ^31 -1; Long 能存儲的最大為 2^63 - 1
        //long bigNumber = 31321483264873624736782746328L;
        System.out.println(Long.MAX_VALUE);
        System.out.println(Math.pow(2, 63) - 1);

        BigInteger b1 = new BigInteger("31321483264873624736782746328");
        BigInteger b2 = new BigInteger("11321483264873624736782746328");

        System.out.println("add: " + b1.add(b2));
        System.out.println("subtract: " + b1.subtract(b2));
        System.out.println("multiply: " + b1.multiply(b2));
        System.out.println("divide: " + b1.divide(b2));
        System.out.println("remainder: " + b1.remainder(b2));

    }

    @Test
    public void test5() {
        BigInteger bi = new BigInteger("123213214");
        BigDecimal bd = new BigDecimal("32132.123231");
        BigDecimal bd2 = new BigDecimal("11");
        System.out.println(bi);
        System.out.println(bd.divide(bd2, BigDecimal.ROUND_HALF_UP));
        System.out.println(bd.divide(bd2, 15, BigDecimal.ROUND_HALF_UP));

    }

    @Test
    public void test6() {
        Random random = new Random();
        int i = random.nextInt();
        System.out.println(i);

        int j = random.nextInt(10); // 隨機獲得[0, 10)範圍的整數
        System.out.println(j);
    }
}
