package com.gbacoder.boot.juc.singleton;

/**
 * @author alanulog
 * @create 2023-12-08
 *
 * 餓漢式 singleton
 */
public class Hungry {

    // 可能會浪費空間
    private byte[] data1 = new byte[1024 * 1024];
    private byte[] data2 = new byte[1024 * 1024];
    private byte[] data3 = new byte[1024 * 1024];
    private byte[] data4 = new byte[1024 * 1024];

    private final static Hungry HUNGRY  = new Hungry();

    private Hungry() {

    }

    public static Hungry getInstance() {
        return HUNGRY;
    }
}
