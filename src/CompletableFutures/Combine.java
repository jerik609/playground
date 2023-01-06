package CompletableFutures;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class Combine {

    public static CompletableFuture<Integer> create(int value) {
        return CompletableFuture.supplyAsync(() -> value);
    }

    public static CompletableFuture<Integer> inc(int number) {
        return CompletableFuture.supplyAsync(() -> number + 1);
    }

    public static void main(String[] args) throws InterruptedException {
        // combines results from two completable futures
        create(3)
                .thenCombine(create(4), (result1, result2) -> result1 + result2)
                .thenAccept(result -> System.out.println("Combined: " + result));
        sleep(250);
    }
}
