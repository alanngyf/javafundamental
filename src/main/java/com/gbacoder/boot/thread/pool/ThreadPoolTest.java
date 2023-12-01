package com.gbacoder.boot.thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author alanulog
 * @create 2023-12-01
 *
 * Using a thread pool in Java, or in any programming environment,
 * offers several advantages compared to creating new threads for every task.
 *
 * Here are the key benefits:
 *
 * Resource Management:
 * Creating and destroying threads for each task can be expensive in terms of system resources and time.
 * Thread pools manage a set of reusable threads. By reusing existing threads,
 * the overhead of thread creation and destruction is minimized, leading to better system resource management.
 *
 * Improved Performance:
 * Since thread creation is minimized, the time taken to process tasks is often reduced,
 * leading to improved overall application performance.
 * This is particularly noticeable in high-load scenarios or applications
 * that require frequent execution of concurrent tasks.
 *
 * Control over Resource Utilization:
 * Thread pools allow for the specification of the maximum number of threads
 * that can be active at any time. This provides a way to control the resource utilization of an application,
 * preventing resource exhaustion under heavy load by limiting the number of concurrent threads.
 *
 * Better Stability:
 * Limiting the number of threads can prevent system instability caused by having too many concurrent threads,
 * which might consume excessive memory or CPU resources.
 *
 * Task Queue Management:
 * Thread pools often provide a way to queue tasks.
 * This means if all threads are busy, tasks can wait in a queue until a thread becomes available.
 * This helps in smoothly handling the load, especially during peak traffic.
 *
 * Fine-Tuning:
 * Thread pools can be fine-tuned according to the requirements of the application.
 * For instance, you can configure the number of threads,
 * the keep-alive time (time a thread can be idle before being terminated),
 * the maximum task queue size, and the rejection policy for new tasks when the queue is full.
 *
 * Load Balancing:
 * Thread pools can help in evenly distributing the workload across multiple threads.
 * This is particularly beneficial in server applications that handle multiple client requests.
 *
 * Graceful Shutdown:
 * Thread pools provide mechanisms to gracefully shut down the pool,
 * optionally waiting for running tasks to finish.
 * This is important for releasing system resources and ensuring that tasks are completed before the application shuts down.
 *
 * Better Error Handling:
 * Handling errors in a controlled environment like a thread pool can be easier and more systematic
 * compared to handling errors in individually managed threads.
 */
class NumThread implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 == 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

class NumThread1 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (i % 2 != 0) {
                System.out.println(Thread.currentThread().getName() + ": " + i);
            }
        }
    }
}

public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
        ThreadPoolExecutor service1 = (ThreadPoolExecutor) service;

        service1.setMaximumPoolSize(50);

        service.execute(new NumThread()); // Runnable as parameter
        service.execute(new NumThread1()); // Runnable as parameter

        // Callable
        // service.submit(Callable callable)
        service.shutdown();
    }
}
