package Comparator;

import java.util.ArrayList;
import java.util.Comparator;

public class SomeThing {
    public String moo;
    public String moomoo;

    SomeThing(String moo, String moomoo) {
        this.moo = moo;
        this.moomoo = moomoo;
    }

    public static void main(String[] args) {
        var list = new ArrayList<SomeThing>();

        list.add(new SomeThing("g", "bbbbbbbb"));
        list.add(new SomeThing("x", "d"));
        list.add(new SomeThing("a", "bbbbb"));

        Comparator<SomeThing> someThingComparator = (o1, o2) -> o1.moo.compareTo(o2.moo);

        list.sort(someThingComparator);;
        list.forEach(x -> System.out.println(x.moo));

    }

}
