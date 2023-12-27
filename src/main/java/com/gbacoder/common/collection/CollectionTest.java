package com.gbacoder.common.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * @author alanulog
 * @create 2023-12-26
 */
public class CollectionTest {

    /*
    * add(Object obj)
    * addAll(Collection coll)
    * size()
    * */
    @Test
    public void test1() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add(123);
        coll.add(new Object());
        coll.add(new Person("Tom", 12));

        System.out.println(coll);
        System.out.println(coll.size());

        Collection coll1 = new ArrayList();
        coll1.add("BB");
        coll1.add(456);

        coll.addAll(coll1);
        System.out.println(coll);

        System.out.println(coll.size());
    }

    /**
     * contains(Object o)
     * containsAll(Collection coll)
     * */
    @Test
    public void test2() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add(128);//自動裝箱
        coll.add(new Object());
        coll.add(new Person("Tom", 12));

        // isEmpty()
        System.out.println(coll.isEmpty());
        // contains(Object o)
        System.out.println(coll.contains(128)); // true, use equals under the hood
        System.out.println(coll.contains("AA")); // true
        System.out.println("new Object()... " + coll.contains(new Object())); // true
        System.out.println("new Person(Tom, 12)... " + coll.contains(new Person("Tom", 12))); // false if equals is not override

        // containsAll(Collection c)
        Collection coll1 = new ArrayList();
        coll1.add("AA");
        coll1.add(128);
        System.out.println(coll.containsAll(coll1));

        // equals()
    }

    /**
     * void clear()
     * boolean remove(Object o)
     * boolean removeAll(Collection coll)
     * boolean retainAll(Collection coll)
     *
     * */
    @Test
    public void test3() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);//自動裝箱
        coll.add(new Object());

        System.out.println(coll);


//        coll.clear();
//        System.out.println(coll);
//        System.out.println(coll.size());

        System.out.println(coll.remove(new Person("Tom", 12)));
        coll.remove("AA");
        System.out.println(coll);

    }

    /**
     * Object[] toArray()
     * hashCode()
     * iterator()
     * */
    @Test
    public void test4() {
        Collection coll = new ArrayList();

        coll.add("AA");
        coll.add("AA");
        Person p1 = new Person("Tom", 12);
        coll.add(p1);
        coll.add(128);//自動裝箱
        coll.add(new Object());

        // Collection to Array
        Object[] arr = coll.toArray();
        System.out.println(Arrays.toString(arr));

        //hashCode()
        System.out.println(coll.hashCode());

        Iterator iterator = coll.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
