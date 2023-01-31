package Observables;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;

import java.util.List;

import static java.lang.Thread.sleep;

public class StockServer {

    public static Observable<StockInfo> getFeed(List<String> symbols) {
        System.out.println("created...");

        return Observable.create(emitter -> emitPrice(emitter, symbols));
    }

    // stream (java8) vs observable
    // java8 stream: basically an iterator - it is lazy (processes as much as needed, but not all),
    //               but it is only a data channel, exceptions are a pain
    // observable: has three channels: data, error, complete - exceptions are send over error channel as
    //             first class citizens
    // nice description -> in imperative programming, on exceptions, we roll back the stack
    //                  -> in reactive programming, on exceptions, we simply handle them as any other event and continue,
    //                     we always move forward, never backward

    // but how to achieve backpressure?
    // and how to achieve completion?

    private static void emitPrice(ObservableEmitter<StockInfo> emitter, List<String> symbols) {
        System.out.println("Ready to emit...");

        var count = 0;

        while(true) { //(count < 3) {
            symbols.stream()
                    .map(StockInfo::fetch)
                    .forEach(stockInfo -> emitter.onNext(stockInfo));
            sleep(1000);
            count++;
        }

        //emitter.onError(new RuntimeException("oh, I failed at the very end"));

        //emitter.onComplete(); // sending the "all data is done"

        //emitter.onNext(new StockInfo("blah", 0.0));

    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
