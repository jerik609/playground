package CompletableFutures;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Join {

    public static CompletableFuture<Integer> create(int x) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(">>> CREATE START: " + LocalTime.now());
            try {
                sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("create interrupted");
                throw new RuntimeException(e);
            }
            System.out.println("<<< CREATE DONE: " + LocalTime.now());
            return x;
        });
    }

    public static int compute(int x) {
        System.out.println(">>> COMPUTE START: " + LocalTime.now());
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("compute interrupted");
            throw new RuntimeException(e);
        }
        System.out.println("<<< COMPUTE DONE: " + LocalTime.now());
        return x + x;
    }

    public static CompletableFuture<Integer> compute2(int x) {
        System.out.println(">>> COMPUTE2 START: " + LocalTime.now());
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("compute2 interrupted");
            throw new RuntimeException(e);
        }
        System.out.println(">>> COMPUTE2 DONE: " + LocalTime.now());
        return CompletableFuture.supplyAsync(() -> x + x + x);
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("--- " + LocalTime.now() + " ---");
        create(10)
                .thenApply(x -> compute(x))
                .thenApply(x -> compute(x))
                //.thenCompose(x -> compute2(x))
//                .orTimeout(11, TimeUnit.SECONDS)
                .exceptionally(throwable -> {
                    System.out.println("UPPER EXCEPTIONALLY");
                    return -1;
                })
                .thenAccept(x -> System.out.println("intermediate: " + x))
                //.orTimeout(1, TimeUnit.SECONDS)
                .exceptionally(th -> {
                    System.out.println("LOWER EXCEPTIONALLY");
                    return null;
                })
                .thenAccept(x -> System.out.println("post action"))
        ;
        //        .join();
        System.out.println("--- " + LocalTime.now() + " ---");

        sleep(20000);
    }
}
