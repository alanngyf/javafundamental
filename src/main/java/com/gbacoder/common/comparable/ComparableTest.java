package com.gbacoder.common.comparable;

import com.gbacoder.common.Product;
import org.junit.Test;

import java.util.Arrays;

/**
 * @author alanulog
 * @create 2023-12-21
 *
 *
 */
public class ComparableTest {

    @Test
    public void test1() {
        String[] arr = new String[] {"Tom", "Jerry", "Tony", "Rose", "Jack", "Lucy"};

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test2() {
        Product[] arr = new Product[5];
        arr[0] = new Product("Iphone 15 pro", 1299.00);
        arr[1] = new Product("Samsung", 899.00);
        arr[2] = new Product("Google Pixel", 899.00);
        arr[3] = new Product("HTC", 499.00);
        arr[4] = new Product("Motorola", 699.00);

        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    @Test
    public void test3() {
        Product p1 = new Product("Iphone 15 pro", 1299.00);
        Product p2 = new Product("Samsung", 899.00);
        int compare = p1.compareTo(p2);
        if (compare > 0) {
            System.out.println("p1 is bigger: " + p1);
        } else if (compare < 0) {
            System.out.println("p2 is bigger: " + p2);
        } else {
            System.out.println("p1 and p2 are the same");
        }
    }
}
