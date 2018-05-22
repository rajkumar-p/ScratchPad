package edu.coursera.parallel;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
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
    }
}
