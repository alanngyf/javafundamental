package com.gbacoder.common;

/**
 * @author alanulog
 * @create 2023-12-21
 */
public class Product implements Comparable {

    private String name;
    private Double price;

    public Product() {
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    /*
    * 當前的類需要實現Comparable中的抽象方法 compareTo(Object o)
    * 在此方法中，指明如果判斷當前類的對象的大小
    *
    * 如果返回值是正數： 當前對象大
    * 如果返回值是負數： 當前對象小
    *
    * 如果返回值是 0, 一樣大
     * */
    @Override
    public int compareTo(Object o) {
        if (o == this) {
            return 0;
        }

        if (o instanceof Product) {
            Product p = (Product) o;

            // return Double.compare(this.price, p.price);
            int val = Double.compare(this.price, p.price);
            if (val != 0) {
                return val;
            }

            return this.name.compareTo(p.name);
        }

        // 手動拋出一個異常類的對象
        throw new RuntimeException("Type Mismatched");
    }

}
