package com.gbacoder.boot.juc.singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author alanulog
 * @create 2023-12-08
 *
 * 枚舉類不可以被反射破壞
 * Cannot reflectively create enum objects
 *
 * 底層指令
 * javap -p EnumSingleton.class
 */
public enum EnumSingleton {
    INSTANCE;

    public EnumSingleton getInstance() {
        return INSTANCE;
    }
}

class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;

        System.out.println(instance1);
        System.out.println(instance2);

        /**
         * Exception in thread "main" java.lang.NoSuchMethodException:
         * com.gbacoder.boot.juc.singleton.EnumSingleton.<init>()
         * */
        // Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(null);
        /**
         * Exception in thread "main" java.lang.IllegalArgumentException: Cannot reflectively create enum objects
         * */
        Constructor<EnumSingleton> declaredConstructor = EnumSingleton.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);

        EnumSingleton instance3 = declaredConstructor.newInstance();
        System.out.println(instance3);
    }
}
