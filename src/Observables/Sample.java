package Observables;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

public class Sample {

    // backpressure!!!
    // (in rx java!!!)
    // observables do not deal with backpressure, but flowables (flows) do!
    // --> this I will have to study

    // CRUD vs reactive

    public static void main(String[] args) {

        List<String> symbols = Arrays.asList("GOOG", "AMZN", "INTC");

        // creates a feed of data we can observe
        Observable<StockInfo> feed = StockServer.getFeed(symbols);
        System.out.println("got observable...");

        // but nothing will happen, unless we subscribe to it - which is this step
        feed
                .filter(stockInfo -> stockInfo.theValue < 100)

                //.onErrorResumeNext() - this way I can provide an alternative observable on error

                .subscribe(
                stockInfo -> System.out.println(stockInfo),
                error -> System.out.println("bad things happened: " + error),
                () -> System.out.println("I'm done, the joy!") // data channel closes up on error or complete

        );

        //System.out.println("I will subscribe again now!");

        // ok, I can subscribe again!
//        feed.subscribe(
//                stockInfo -> System.out.println(stockInfo)
//        );

    }
}

