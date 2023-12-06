package com.gbacoder.boot.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author alanulog
 * @create 2023-12-05
 *
 * 1. forkjoinPoll 通過它來執行
 * 2. 計算任務 forkjoinPoll.execute(ForkJoinTask task)
 * 3. 計算類要繼承 ForkJoinTask
 */
public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    private Long temp = 10000L;

    public ForkJoinDemo(Long start, Long end) {
        this.start = start;
        this.end = end;
    }

    // 計算方法
    @Override
    protected Long compute() {
        if ((end - start) < temp) {
            Long sum = 0L;
            for (Long i = start; i <= end; i++) {
                sum += i;
            }
            //System.out.println(sum);
            return sum;
        } else { // forkjoin
            // 分支合併運算
            Long middle = (end + start) / 2;
            ForkJoinDemo task1 = new ForkJoinDemo(start, middle);
            task1.fork(); // 拆分任務，把任務壓入線程隊列
            ForkJoinDemo task2 = new ForkJoinDemo(middle + 1, end);
            task2.fork();
            return task1.join() + task2.join();
        }
    }
}
