package CompletableFutures;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class Exceptionally {
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
    //    return return blowup              return return
    // --- f --- f --- f --f -- f --- f --- f --- f              // thens
    //                  \                  /
    //                   e --- e --- e -- e (recovery) --- e     // exceptional
    //                  blowup   blowup   return
}
