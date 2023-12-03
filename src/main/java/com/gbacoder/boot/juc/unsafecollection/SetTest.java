package com.gbacoder.boot.juc.unsafecollection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Copy-on-write (COW) is a resource management technique used in computer programming to efficiently copy data resources.
 * When a system employs this technique, it initially allows multiple users to share a single copy of a resource
 * (such as memory or disk space) to minimize resource usage and improve performance.
 * When one of the users needs to modify or write to the resource, only then is a copy made (hence "copy-on-write").
 * The user then makes changes to this new copy, leaving the original unaltered for other users to continue to access.
 * This approach avoids unnecessary duplication of data when no changes are made, saving on memory and CPU cycles.
 *
 * @author alanulog
 * @create 2023-12-03
 *
 * java.util.ConcurrentModificationException
 * 解決方案:
 *     > 1. Set<String> set = Collections.synchronizedSet(new HashSet<>());
 *
 */
public class SetTest {
    public static void main(String[] args) {
        // Set<String> set = new HashSet<>();
        // Set<String> set = Collections.synchronizedSet(new HashSet<>());
        Set<String> set = new CopyOnWriteArraySet(new HashSet<String>());
        for (int i = 1; i <= 30; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
