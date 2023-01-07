package Lambdas;

import java.util.function.Function;

public class Lambda {

    public static void main(String[] args) {

        final var x = Integer.valueOf(1);

        Function<Integer, Integer> lambda = (Integer moo) -> x + x;

        Runnable weird = () -> {};



    }


}
