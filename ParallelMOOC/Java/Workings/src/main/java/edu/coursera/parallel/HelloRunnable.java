package edu.coursera.parallel;

public class HelloRunnable implements Runnable {
    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();
        String groupName = Thread.currentThread().getThreadGroup().getName();

        System.out.println(String.format("Thread Name : %s, Group Name : %s", threadName, groupName));
    }
}
