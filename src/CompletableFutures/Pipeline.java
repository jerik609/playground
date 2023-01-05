package CompletableFutures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class Pipeline {

    public static void mainGotcha(String[] args) throws InterruptedException {
        CompletableFuture<Integer> noFuture = new CompletableFuture<Integer>()
                .thenApply(item -> item * item)
                .thenApply(item -> item + 10);

        System.out.println("prepared the pipeline");

        noFuture.thenAccept(System.out::println);
        // ^^^^^ THIS WOULD NOT WORK, NOTICE WE SET THIS TO THE ORIGINAL FUTURE
        // NOT THE RESULT OF THE PIPELINE WE BUILT EARLIER!
        // BE CAREFUL WHEN CHAINING LIKE THIS!

        System.out.println("prepared the output");

        sleep(1000);

        noFuture.complete(10);

    }

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> noFuture = new CompletableFuture<>();

        var x = noFuture
                .thenApply(item -> item * item)
                .thenApply(item -> item + 10)
                .thenAccept(System.out::println);

        x.thenAccept(s -> System.out.println("hello"));

        System.out.println("prepared the pipeline");

        sleep(1000);

        noFuture.complete(10);
    }
}
