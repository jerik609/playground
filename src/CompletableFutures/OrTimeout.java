package CompletableFutures;

import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class OrTimeout {

    static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            sleep(20000);
            return "Hello-Educative";
        });


        long timeOutValue = 3;
        TimeUnit timeUnit = TimeUnit.SECONDS;

        System.out.println(LocalTime.now());
        completableFuture
                .thenApply(s -> {
                    sleep(10000);
                    return s;
                })
                .orTimeout(timeOutValue, timeUnit)
                .exceptionally(th -> {
                    System.out.println(LocalTime.now());
                    throw new RuntimeException("still broken");
                })
                .join();
        System.out.println(LocalTime.now());
    }
}