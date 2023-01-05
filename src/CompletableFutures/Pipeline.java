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

    public static void noGotcha() throws InterruptedException {
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

    public static void main(String[] args) throws InterruptedException {
        error();
        sleep(200);
    }

    // these have an error channel
    // observe:

    public static int compute() {
        throw new RuntimeException("hello there!");
        //return 22;
    }

    public static CompletableFuture<Integer> create() {
        return CompletableFuture.supplyAsync(() -> compute());
    }

    // if things go well - we use then* method
    // if things go bad - we use exceptionally //=catch

    // exception means, that then* get skipped and jumps to nearest exceptionally
    public static void error() {
        create()
                .thenApply(data -> data * 2)
                .exceptionally(throwable -> handleException(throwable))
                .thenApply(data -> { // each operation determines the downstream data type
                    System.out.println("moooooo");
                    return data;
                })
                .thenApply(data -> data * 7) // if I put thenAccept - further operations have to work with Void
                .exceptionally(throwable -> handleException2(throwable)) // recovering
                .thenAccept(data -> System.out.println(data))
                .whenComplete((unused, throwable) -> System.out.println("DONE WITH: " + throwable.getMessage()));

    }

    public static <T> T handleException(Throwable t) {
        // with only one exceptionally statement
        System.out.println("HANDLE WITH THROW: " + t.getMessage()); // we will see this line
        throw new RuntimeException("beyond all hope"); // this will be packaged into a CompletableFuture
        // and we need another exceptionally to handle it :-) otherwise it disappears
    }

    public static Integer handleException2(Throwable t) {
        // with only one exceptionally statement
        System.out.println("HANDLE WITH VALUE: " + t.getMessage()); // we will see this line

        // and we need another exceptionally to handle it :-) otherwise it disappears
        System.out.println("RECOVERING ... ");
        return -1;
    }

    // --- f --- f --- f --f -- f --- f --- f --- f              // ok
    //                  \                  /
    //                   e --- e --- e -- e (recovery) --- e     // error handling
}
