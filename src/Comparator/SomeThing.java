package Comparator;

import java.util.ArrayList;
import java.util.Comparator;

public class SomeThing {
    public String moo;

    SomeThing(String moo) {
        this.moo = moo;
    }

    public static void main(String[] args) {
        var list = new ArrayList<SomeThing>();

        list.add(new SomeThing("g"));
        list.add(new SomeThing("x"));
        list.add(new SomeThing("a"));
        list.add(new SomeThing("r"));

        Comparator<SomeThing> someThingComparator = (o1, o2) -> o1.moo.compareTo(o2.moo);

        list.sort(someThingComparator);
        list.forEach(x -> System.out.println(x.moo));
    }
}
