package edu.coursera.parallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Parallels {

    public long seqArraySum(long[] numbers)
    {
        long sum = 0;
        for (int i = 0; i < numbers.length; ++i) {
            sum += numbers[i];
        }

        return sum;
    }

    private static int getChunkSize(final int nChunks, final int nElements)
    {
        return (nElements + nChunks - 1) / nChunks;
    }

    private static int getChunkStartInclusive(final int chunk, final int nChunks,
                                              final int nElements)
    {
        final int chunkSize = getChunkSize(nChunks, nElements);
        return chunk * chunkSize;
    }

    private static int getChunkEndExclusive(final int chunk, final int nChunks,
                                            final int nElements)
    {
        final int chunkSize = getChunkSize(nChunks, nElements);
        final int end = (chunk + 1) * chunkSize;

        if (end > nElements) {
            return nElements;
        } else {
            return end;
        }
    }

    private static class RecSumArray extends RecursiveAction {
        static int SEQUENTIAL_THRESHOLD = 1000;
        final int low, high;

        final long[] numbers;
        long sum;

        final int numTasks;

        RecSumArray(final long[] numbers, final int low, final int high, final int numTasks) {
            this.numbers = numbers;
            this.low = low;
            this.high = high;
            this.numTasks = numTasks;

            this.sum = 0;
        }

        @Override
        protected void compute() {
            if (high - low <= SEQUENTIAL_THRESHOLD) {
                for (int i = low; i < high; ++i) {
                    sum += numbers[i];
                }
            } else {
                List<RecursiveAction> tasks = new ArrayList<>();
                for (int i = 0; i < numTasks; ++i) {
                    final int lowIndex = low + getChunkStartInclusive(i, numTasks, high - low);
                    final int highIndex = low + getChunkEndExclusive(i, numTasks, high - low);

                    tasks.add(new RecSumArray(numbers, lowIndex, highIndex, numTasks));
                }

                invokeAll(tasks);
                for (RecursiveAction task : tasks) {
                    sum += ((RecSumArray) task).sum;
                }
            }
        }
    }

    public long parArraySum(long[] numbers)
    {
        final int numTasks = 2;
        RecSumArray recSumArray = new RecSumArray(numbers, 0, numbers.length, numTasks);

        ForkJoinPool forkJoinPool = new ForkJoinPool(numTasks);
        forkJoinPool.invoke(recSumArray);

        return recSumArray.sum;
    }
}
