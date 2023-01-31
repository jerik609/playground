package Observables;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

import javax.sound.midi.Soundbank;
import java.io.IOException;

public class StockInfo {

    private final String theTicker;
    final double theValue;


    public StockInfo(String theTicker, double theValue) {
        this.theTicker = theTicker;
        this.theValue = theValue;
    }

    public static StockInfo fetch(String ticker) {
        // let's test errors :-)
        if (Math.random() > 0.9) throw new RuntimeException("oops, something went wrong :-(");

        final Stock price;
        try {
            price = YahooFinance.get(ticker);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new StockInfo(ticker, price.getQuote().getPrice().doubleValue());
    }

    @Override
    public String toString() {
        return "StockInfo{" +
                "theTicker='" + theTicker + '\'' +
                ", theValue=" + theValue +
                '}';
    }
}
