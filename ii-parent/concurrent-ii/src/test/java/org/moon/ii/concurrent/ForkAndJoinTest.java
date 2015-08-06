package org.moon.ii.concurrent;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoinTest {

    public static void main(String[] args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> task = forkJoinPool.submit(new CountTask(1, 1000));
        System.out.println(task.get());
    }

}

class CountTask extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 1L;
    private int threshold = 100;
    private int start;
    private int end;

    public CountTask(int threshold, int start, int end) {
        this.threshold = threshold;
        this.start = start;
        this.end = end;
    }

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if ((end - start) <= threshold) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) / 2;
            CountTask left = new CountTask(start, middle);
            CountTask right = new CountTask(middle + 1, end);
            left.fork();
            right.fork();
            sum = left.join() + right.join();
        }
        return sum;
    }
}