package CompletableFutures;

import java.util.concurrent.CompletableFuture;

import static java.lang.Thread.sleep;

public class PipelineWeird {
    public static void main(String[] args) throws InterruptedException {
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

}
