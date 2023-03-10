package CompletableFutures;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

public class Pipeline {

    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> noFuture = new CompletableFuture<>();

        var x = noFuture
                .thenApply(item -> item * item)
                .exceptionally(throwable -> {
                    System.out.println("failed, shhh: " + throwable.getMessage());
                    return -1;
                })
                .thenApply(item -> item + 10)
                .thenAccept(System.out::println);

        x.thenAccept(s -> System.out.println("hello"));

        System.out.println("prepared the pipeline - completable future in PENDING STATE");

        sleep(500);

        //noFuture.complete(10); // completable future in // complete with Resolved state
        //noFuture.completeExceptionally(new RuntimeException("boom!")); // complete with Rejected state
        // the second one won't be triggered, there's always 0-1 executions of a future, it's
        // not a stream!!!

        // how long we're going to be in pending state?
        // both in life and programming never do something without timeout

        //noFuture.completeOnTimeout(11, 1, TimeUnit.SECONDS);
        noFuture.orTimeout(1, TimeUnit.SECONDS); // this causes an exception, WHICH IS HANDLED AS ALL THE OTHERS!
        //noFuture.complete(10); // completable future in // complete with Resolved state
        // complete on timeout is ignored when the future is resolved (rejected)

        sleep(1500);

        // onTimeout is equivalent to completeExceptionally
    }
}
