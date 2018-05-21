package edu.coursera.parallel;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ConcurrentPrograms {

    void runTaskInRunnableClass() {
        class HelloRunnable implements Runnable {

            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                String groupName = Thread.currentThread().getThreadGroup().getName();
                System.out.println("Hello, " + threadName + ", " + groupName);
            }
        }

        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread = new Thread(helloRunnable);
        thread.start();
    }

    void runTaskInThreadClass() {
        class HelloThread extends Thread {
            public void run() {
                String threadName = Thread.currentThread().getName();
                String groupName = Thread.currentThread().getThreadGroup().getName();
                System.out.println("Hello, " + threadName + ", " + groupName);
            }
        }

        HelloThread helloThread = new HelloThread();
        helloThread.start();
    }

    void runTaskInNewThread()
    {
        Runnable task = () -> {
            String threadName = Thread.currentThread().getName();
            String groupName = Thread.currentThread().getThreadGroup().getName();
            System.out.println("Hello, " + threadName + ", from " + groupName);
        };

        task.run();

        Thread newTask = new Thread(task);
        newTask.start();

        System.out.println("done.");
    }

    void runTaskInExecutorService()
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            String groupName = Thread.currentThread().getThreadGroup().getName();
            System.out.println("Hello, " + threadName + ", from " + groupName);
        });

        System.out.println("done.");
        executorService.shutdown();
    }

    void runTaskInExecutorServiceAndPrintReturnedValue()
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<Integer> int_value = executorService.submit(() -> {
            String threadName = Thread.currentThread().getName();
            String groupName = Thread.currentThread().getThreadGroup().getName();
            System.out.println("Hello, " + threadName + ", from " + groupName);

            return 123;
        });

        System.out.println("Future done? - " + int_value.isDone());
        try {
            String threadName = Thread.currentThread().getName();
            String groupName = Thread.currentThread().getThreadGroup().getName();
            System.out.println("Hello, " + threadName + ", from " + groupName);

            System.out.println("Getting future value - " + int_value.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("done.");
        executorService.shutdown();
    }

    void runMultipleTaskInExecutorServiceAndPrintReturnedValue() {
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Callable<String>> callables = Arrays.asList(
                () -> "Task1",
                () -> "Task2",
                () -> "Task3"
        );

        try {
            executorService.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException();
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void scheduleTaskInExecutorService()
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        int initialDelay = 0;
        int period = 5;

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            System.out.println("Scheduling : " + System.nanoTime());
        }, initialDelay, period, TimeUnit.SECONDS);
    }
}
