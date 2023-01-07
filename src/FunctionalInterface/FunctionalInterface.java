package FunctionalInterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FunctionalInterface {

    class ComputeSome implements Function<Integer, Integer> {
        @Override
        public Integer apply(Integer a) {
            return a * a;
        }
    }

    public static void main(String[] args) {

        var fun1 = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer o) {
                return o + o;
            }
        };

        Function<Integer, Integer> fun2 = a -> a + a;

        BiFunction<Integer, Integer, Integer> fun3 = Integer::sum;

        CustomFunInt<String, String, String> varX = String::concat;

        System.out.println(varX.computeStuff("a", "b"));
    }
}
