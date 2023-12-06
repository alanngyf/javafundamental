package com.gbacoder.boot.juc.stream;

import java.util.Arrays;
import java.util.List;

/**
 * @author alanulog
 * @create 2023-12-05
 */
public class Test {
    public static void main(String[] args) {
        User u1 = new User(1, "a", 21);
        User u2 = new User(2, "b", 22);
        User u3 = new User(3, "c", 23);
        User u4 = new User(4, "d", 24);
        User u5 = new User(6, "e", 25);
        // 集合就是存儲
        List<User> users = Arrays.asList(u1, u2, u3, u4, u5);

        // 計算交給Stream流
        // 鏈式編程
        users.stream()
                .filter(u -> u.getId() % 2 == 0)
                .filter(u -> u.getAge() >= 23)
                .map(u -> u.getName().toUpperCase())
                .sorted((uu1, uu2) -> -uu1.compareTo(uu2))
                .limit(1)
                .forEach(System.out::println);

    }
}
