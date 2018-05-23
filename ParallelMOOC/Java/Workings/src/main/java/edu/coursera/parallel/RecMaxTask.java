package edu.coursera.parallel;

import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.RecursiveTask;

public class RecMaxTask extends RecursiveTask<Long> {
    final long[] array;

    final int low, high;
    long max;

    final int THRESHOLD = 1000;

    RecMaxTask(final long[] array) {
        this(array, 0, array.length);
    }

    RecMaxTask(final long[] array, final int low, final int high) {
        this.array = array;
        this.low = low;
        this.high = high;

        this.max = 0;
    }

    @Override
    protected Long compute() {
        if (this.high - this.low <= THRESHOLD) {
            this.max = this.getMaxSequentially(array, low, high);
            return this.max;
        }

        int mid = this.low + (this.high - this.low) / 2;
        RecMaxTask leftMax = new RecMaxTask(array, low, mid);
        RecMaxTask rightMax = new RecMaxTask(array, mid, high);

        invokeAll(leftMax, rightMax);
        this.max = Math.max(leftMax.max, rightMax.max);

        return this.max;
    }

    long getMaxSequentially(long[] array, int low, int high) {
        long max = 0;
        for (int i = low; i < high; ++i) {
            max = Math.max(max, array[i]);
        }

        return max;
    }
}
