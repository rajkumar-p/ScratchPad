package edu.coursera.parallel;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws InterruptedException {
        HelloRunnable helloRunnable = new HelloRunnable();

        Thread newThread = new Thread(helloRunnable);
        newThread.start();

        HelloThread helloThread = new HelloThread();
        helloThread.start();

        ConcurrentPrograms concurrentPrograms = new ConcurrentPrograms();

        concurrentPrograms.runTaskInNewThread();
        concurrentPrograms.runTaskInExecutorService();
        concurrentPrograms.runTaskInExecutorServiceAndPrintReturnedValue();
        concurrentPrograms.runMultipleTaskInExecutorServiceAndPrintReturnedValue();
        concurrentPrograms.printAndSleepAndRepeat();

        int nElements = 500000;
        long[] array = new long[nElements];
        for (int i = 0; i < nElements; ++i) {
            array[i] = 100;
        }

        RecAddAction recAddAction = new RecAddAction(array, 0, array.length);
        ForkJoinPool.commonPool().invoke(recAddAction);

        System.out.println(String.format("Sum after ForkJoin sum : %d", recAddAction.sum));

        for (int i = 0; i < nElements; ++i) {
            array[i] = ThreadLocalRandom.current().nextLong(1, Long.MAX_VALUE);
        }

        RecMaxTask recMaxTask = new RecMaxTask(array, 0, array.length);
        Long maxValue = ForkJoinPool.commonPool().invoke(recMaxTask);

        System.out.println(String.format("Max value : %s", maxValue));
    }
}
