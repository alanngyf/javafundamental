package com.gbacoder.boot.juc.singleton;

/**
 * @author alanulog
 * @create 2023-12-08
 *
 * 靜態內部類 static inner-class
 */
public class Holder {

    public static class InnerClass{
        private static final Holder HOLDER = new Holder();

    }

    private Holder() {
        System.out.println("Holder constructor()... executed");
    }

    public static Holder getInstance() {
        return InnerClass.HOLDER;
    }
}
