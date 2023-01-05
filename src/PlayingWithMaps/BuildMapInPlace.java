package PlayingWithMaps;

import java.util.*;
import java.util.stream.Collectors;

public class BuildMapInPlace {
    // https://nurkiewicz.com/2019/03/mapmerge-one-method-to-rule-them-all.html

    public static void main(String[] args) {
        final var words = List.of("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");

        final Map<String, Integer> map = new HashMap<>();

        final Map<String, List<String>> map2 = new HashMap<>();

        words.forEach(word -> {
            //map.putIfAbsent(word, 0);
            //map.put(word, map.get(word) + 1);

            //map.putIfAbsent(word, 0);
            //map.computeIfPresent(word, (k, prev) -> prev + 1);

            //map.compute(word, (k, prev) -> prev != null ? prev + 1 : 1);

            /*
            default V merge(K key, V value, BiFunction<V, V, V> remappingFunction) {
                V oldValue = get(key);
                V newValue = (oldValue == null) ? value : remappingFunction.apply(oldValue, value);
                if (newValue == null) {
                    remove(key);
                } else {
                    put(key, newValue);
                }
                return newValue;
            }
             */

            // key
            // new value, used directly if null
            // remapping function, if old value, then perform op with old and new value

            map2.merge(word, new ArrayList<>(Collections.singletonList(word)), (prev, newVal) -> {
                prev.addAll(newVal);
                return prev;
            });
        });
        System.out.println("the outer loop: " + map2);

        // https://stackoverflow.com/questions/1005073/initialization-of-an-arraylist-in-one-line
        // https://stackoverflow.com/questions/41070619/append-object-to-list-and-return-result-in-java-8
        // ultimate?
        Map<String, List<String>> map3 = words.stream().collect(Collectors.toMap(
                word -> word,
                word -> new ArrayList<>(Collections.singletonList(word)),
                (prevList, newList) -> {
                    prevList.addAll(newList);
                    return prevList;
                }));
        System.out.println("pure functional: " + map3);
    }
}
