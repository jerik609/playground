package TheTime;

import java.time.Period;
import java.util.Arrays;

public class PeriodAndDuration {

    public static void main(String[] args) {
        final Period period1 = Period.of(1981, 11, 3);
        final Period period2 = Period.parse("P1995Y5M4D");

        System.out.println("period1 = " + period1);
        System.out.println("period2 = " + period2);
    }


}
