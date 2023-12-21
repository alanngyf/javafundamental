package com.gbacoder.common.comparator;

import com.gbacoder.common.Product;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author alanulog
 * @create 2023-12-21
 */
public class ComparatorTest {

    @Test
    public void test1() {
        Product[] arr = new Product[5];
        arr[0] = new Product("Iphone 15 pro", 1299.00);
        arr[1] = new Product("Samsung", 899.00);
        arr[2] = new Product("Google Pixel", 899.00);
        arr[3] = new Product("HTC", 499.00);
        arr[4] = new Product("Motorola", 699.00);

        // 創建一個實現了Comparator接口的實現類的對象
//        Comparator com = new Comparator() {
//            @Override
//            public int compare(Object o1, Object o2) {
//                if (o1 instanceof Product && o2 instanceof Product) {
//                    Product p1 = (Product) o1;
//                    Product p2 = (Product) o2;
//
//                    return -Double.compare(p1.getPrice(), p2.getPrice());
//                }
//
//                throw new RuntimeException("Type mismatch");
//            }
//        };

        Comparator com2 = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Product && o2 instanceof Product) {
                    Product p1 = (Product) o1;
                    Product p2 = (Product) o2;
                    return p1.getName().compareTo(p2.getName());
                }

                throw new RuntimeException("Type mismatch");
            }
        };
        Arrays.sort(arr, com2);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }
}
