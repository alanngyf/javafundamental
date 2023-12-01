package com.gbacoder.boot.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Callable (threads) since JDK5.0
 *
 * @author alanulog
 * @create 2023-12-01
 *
 * both Callable and Runnable are interfaces that are used to encapsulate a task that
 * can be executed by another thread, but they have some significant differences:
 *
 * Return Value:
 *
 * Runnable: It doesn't return any result. The run() method in the Runnable interface has a void return type.
 * Callable: It can return a result. The call() method in the Callable interface returns a value and allows for generic typing.
 * Exception Handling:
 *
 * Runnable: It cannot throw checked exceptions. Any checked exceptions within the run() method must be caught and handled within the method itself.
 * Callable: It allows the throwing of checked exceptions from the call() method, making error handling more flexible and integrated.
 * Usage with ExecutorService:
 *
 * When you submit a Runnable to an ExecutorService, you receive a Future<?>, but this Future does not provide a way to retrieve the result of the computation (since Runnable does not return a result).
 * Submitting a Callable to an ExecutorService gives you a Future<T>, where T is the type of the result returned by the call() method. This Future can be used to retrieve the result of the computation.
 * Method Signature:
 *
 * Runnable: The method signature is public void run().
 * Callable: The method signature is public V call() throws Exception, where V is the type of the result.
 * Typical Use Cases:
 *
 * Runnable is typically used for tasks that do not return a result and do not throw checked exceptions. It’s been around since Java 1.0 and is widely used for creating threads.
 * Callable is more suitable for tasks that might return a result or throw exceptions. It is a more recent addition (introduced in Java 1.5) and is often used with the Executor framework.
 * In summary, while both Callable and Runnable are used for task execution in a multithreaded environment, Callable is more flexible and powerful, allowing for return values and throwing checked exceptions. Runnable, on the other hand, is simpler and useful for cases where you don't need to return a value or handle checked exceptions.
 */
class NumThread implements Callable {
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                sum += i;
            }
            Thread.sleep(100);
            if (sum > 1000) {
//                synchronized (this) {
//                    this.wait();
//                }
            }
        }

        return sum;
    }
}

public class CallableTest {
    public static void main(String[] args) {
        NumThread numThread = new NumThread();

        // FutureTask also implements Runnable, so when passing it as parameter to Thread, it's Runnable
        // Runnable futureTask = new FutureTask(numThread);
        FutureTask futureTask = new FutureTask(numThread);

        Thread t1 = new Thread(futureTask);
        t1.start();

        try {
            // futureTask.get() will be blocked until the thread is completed
            Object sum = futureTask.get();
            System.out.println("Total sum: " + sum);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
