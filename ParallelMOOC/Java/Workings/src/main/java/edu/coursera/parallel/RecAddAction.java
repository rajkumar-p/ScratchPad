package edu.coursera.parallel;

import java.util.concurrent.RecursiveAction;

public class RecAddAction extends RecursiveAction {
    final long[] array;
    final int low, high;

    long sum = 0;

    final int THRESHOLD = 1000;

    RecAddAction(long[] array) {
        this(array, 0, array.length);
    }

    RecAddAction(long[] array, int low, int high) {
        this.array = array;
        this.low = low;
        this.high = high;
    }

    @Override
    protected void compute() {
        if (high - low <= THRESHOLD) {
            this.addSequentially(this.array, this.low, this.high);
            return;
        }

        int mid = this.low + (this.high - this.low) / 2;
        RecAddAction leftSubProb = new RecAddAction(this.array, this.low, mid);
        RecAddAction rightSubProb = new RecAddAction(this.array, mid, this.high);

        /*
        You can use invokeAll(forkJoinTasks) instead of the below
         */
        // leftSubProb.fork();
        // rightSubProb.compute();
        // leftSubProb.join();

        invokeAll(leftSubProb, rightSubProb);
        this.sum = leftSubProb.sum + rightSubProb.sum;
    }

    void addSequentially(long[] array, int low, int high) {
        for (int i = low; i < high; ++i) {
            this.sum += array[i];
        }
    }
}
