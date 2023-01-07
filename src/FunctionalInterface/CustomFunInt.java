package FunctionalInterface;

import java.lang.FunctionalInterface;

@FunctionalInterface
interface CustomFunInt<S, T, U> {
    U computeStuff(S a, T b);

    static void doNothingStatic() { }

    default void doNothingByDefault() { }
}
