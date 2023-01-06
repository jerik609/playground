package CompletableFutures;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class Compose {

    public static CompletableFuture<Integer> create(int value) {
        return CompletableFuture.supplyAsync(() -> value);
    }

    public static CompletableFuture<Integer> inc(int number) {
        return CompletableFuture.supplyAsync(() -> number + 1);
    }

    public static void main(String[] args) throws InterruptedException {

        // bad example - returns the first future, not the actual result - we need to get the second future
        //create(2).thenApply(data -> inc(data)).thenAccept(System.out::println);

        // now this is much better, it chains completable futures!
        create(2).thenCompose(data -> inc(data)).thenAccept(System.out::println);

        // if something returns a completable future, we want to wait on
        // that future, not the data

        sleep(250);
    }
}
