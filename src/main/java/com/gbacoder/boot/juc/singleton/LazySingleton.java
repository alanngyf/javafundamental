package com.gbacoder.boot.juc.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author alanulog
 * @create 2023-12-08
 */
public class LazySingleton {
    private static boolean flag = false;
    private volatile static LazySingleton lazySingleton;

    private LazySingleton() {
        synchronized (LazySingleton.class) {
            if (flag == false) {
                flag = true;
            } else {
                throw new RuntimeException("不要試圖用反射破壞異常");
            }
            if (lazySingleton != null) {
                throw new RuntimeException("不要試圖用反射破壞異常");
            } else {
                System.out.println(Thread.currentThread().getName() + " Constructor executed");
            }
        }

    }

    public static LazySingleton getInstance() {
        // 雙層檢測鎖模式的 懶漢式單例 DCL
        if (lazySingleton == null) {
            synchronized (LazySingleton.class) {
                if (lazySingleton == null) {
                    lazySingleton = new LazySingleton(); // 不是原子性操作
                    /**
                     * 1. 分配內存空間
                     * 2. execute construactor, initiate the instance
                     * 3. 把對象指向分配好的空間
                     *
                     * 可能會 happens-before 的現象 例如執行 132
                     * 線程A 執行了13 還沒有執行2
                     * 線程B 此時因為對象空間已經分配好了 線程B會直接返回對象
                     * */
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " OK " + lazySingleton);
        return lazySingleton;
    }

    // 多線程並發會出問題
//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                LazySingleton.getInstance();
//            }).start();
//        }
//    }

    // 反射
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
        // LazySingleton instance = LazySingleton.getInstance();

        Field flag1 = LazySingleton.class.getDeclaredField("flag");
        flag1.setAccessible(true);

        Constructor<LazySingleton> declaredConstructor = LazySingleton.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        LazySingleton instance = declaredConstructor.newInstance();

        flag1.set(instance, false);
        LazySingleton instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);

    }
}
