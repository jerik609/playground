package Executing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        //ExecutorService executor = new ForkJoinPool();

        Runnable task = () -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
                System.out.println("Hello!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };

        executor.submit(task);
        executor.submit(task);

        System.out.println("Main thread done");

    }
}
