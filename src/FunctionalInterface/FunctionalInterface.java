package FunctionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

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

        CustomFunInt.doNothingStatic();
        varX.doNothingByDefault();

        MightyTypeInference mighty = (a, b, c) -> a == b && a == c;
        ThirdType third = (a, b, c) -> a.equals(b) && b.equals(c);

        var ccc = new Comparable<>() {

            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };

        List<String> list = Arrays.asList("A", "AB", "Ab", "aB", "ab", "a");
        list.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);


    }
}
