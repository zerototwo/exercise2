package com;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class TestForkJoinPool {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        Long invoke = pool.invoke(new ForkJoinSumCalculate(0L, 4L));
        System.out.println(invoke);
    }
}

class ForkJoinSumCalculate extends RecursiveTask<Long> {

    private long start;
    private long end;

    private static final long THURSHOLD  = 0L;

    public ForkJoinSumCalculate(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long length = end - start;

        if (length <= THURSHOLD) {

            return start;
        } else {

        long middle=(start+end) /2;
            ForkJoinSumCalculate left = new ForkJoinSumCalculate(start, middle);
            left.fork();

            ForkJoinSumCalculate right = new ForkJoinSumCalculate(middle + 1, end);
            right.fork();

            Long join = left.join();
            Long join1 = right.join();
            return  join+join1 ;


        }
    }
}
