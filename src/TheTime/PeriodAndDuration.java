package TheTime;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;
import java.util.Arrays;

public class PeriodAndDuration {

    public static void main(String[] args) {
        doPeriods();
        doDurations();
        doInstants();
    }

    private static void doInstants() {
        System.out.println("--> instants");

        final var instant1 = Instant.now();
        System.out.println("instant1 = " + instant1);

        final var instantEpoch = Instant.EPOCH;
        System.out.println(instantEpoch);

        System.out.println("instantEpoch.getEpochSecond() = " + instantEpoch.getEpochSecond());
        System.out.println("instantEpoch.getNano() = " + instantEpoch.getNano());

        final var inst1 = Instant.ofEpochMilli(100);
        final var inst2 = Instant.ofEpochSecond(100);
        final var inst3 = Instant.ofEpochSecond(100, 100);

        System.out.println("inst1 = " + inst1);
        System.out.println("inst2 = " + inst2);
        System.out.println("inst3 = " + inst3);

        final var inst4 = Instant.ofEpochSecond(-100);
        System.out.println("inst4 = " + inst4);

        final var testInstant = Instant.now();
        System.out.println("testInstant.atZone(ZoneId.of(\"GMT+4\")) = " + testInstant.atZone(ZoneId.of("GMT+4")));
        System.out.println("testInstant.atZone(ZoneId.of(\"Europe/Berlin\")) = " + testInstant.atZone(ZoneId.of("Europe/Berlin")));
        System.out.println("testInstant.atZone(ZoneId.systemDefault()) = " + testInstant.atZone(ZoneId.systemDefault()));

        System.out.println("default zone: " + ZoneId.systemDefault());
        System.out.println("default zone rules: " + ZoneId.systemDefault().getRules());

        final var parsedInstant = Instant.parse("2002-12-18T12:21:33Z");
        System.out.println("parsedInstant = " + parsedInstant);
        System.out.println("parsedInstant.atZone(ZoneId.of(\"GMT+3\")) = " + parsedInstant.atZone(ZoneId.of("GMT+3")));

        final var compInst1 = Instant.now();
        System.out.println("compInst1 = " + compInst1);
        final var compInst2 = compInst1.plus(Duration.of(2, ChronoUnit.HOURS));

        System.out.println("compInst2.isAfter(compInst1) = " + compInst2.isAfter(compInst1));
        System.out.println("compInst2.isBefore(compInst1) = " + compInst2.isBefore(compInst1));

        System.out.println("compInst2.compareTo(compInst1) = " + compInst2.compareTo(compInst1));
        System.out.println("compInst1.plus(Period.parse(\"P10D\")) = " + compInst1.plus(Period.parse("P10D")));
        
        System.out.println("Instant.EPOCH.until(compInst1.plus(3, ChronoUnit.DAYS), ChronoUnit.DAYS) = " + Instant.EPOCH.until(Instant.EPOCH.plus(3, ChronoUnit.DAYS), ChronoUnit.DAYS));

        System.out.println("compInst2.getLong(ChronoField.INSTANT_SECONDS) = " + compInst2.getLong(ChronoField.INSTANT_SECONDS));

        System.out.println("compInst2.getLong(ChronoField.INSTANT_SECONDS) = " + compInst2.getLong(ChronoField.INSTANT_SECONDS));
        System.out.println("compInst2.get(ChronoField.NANO_OF_SECOND) = " + compInst2.get(ChronoField.NANO_OF_SECOND));
        try {
            System.out.println("compInst2.get(ChronoField.EPOCH_DAY) = " + compInst2.get(ChronoField.EPOCH_DAY));
        } catch (UnsupportedTemporalTypeException e) {
            System.out.println("temporal exception");
        }

        
    }

    private static void doDurations() {
        System.out.println("--> durations");

        final Duration duration1 = Duration.of(100, ChronoUnit.MICROS);
        System.out.println("duration1 = " + duration1);

        final Duration duration2 = Duration.parse("PT0.000001S");
        System.out.println("duration2.get(ChronoUnit.NANOS) = " + duration2.get(ChronoUnit.NANOS));

        final var localDateTimeA = LocalDateTime.now();
        final var localDateTime = LocalDateTime.now().plus(Duration.of(200, ChronoUnit.MINUTES));

        System.out.println("Duration.between(localDateTimeA, localDateTime) = " + Duration.between(localDateTimeA, localDateTime));

        final var duration3 = Duration.parse("PT100H43M23231S");
        System.out.println("duration3 = " + duration3);

        // extraction
        System.out.println("duration3.get(ChronoUnit.DAYS) = " + duration3.get(ChronoUnit.SECONDS));
        System.out.println("duration3.get(ChronoUnit.NANOS) = " + duration3.get(ChronoUnit.NANOS));

        System.out.println("Duration.between(localDateTimeA, LocalDateTime.now()).getNano() = " + Duration.between(localDateTimeA, LocalDateTime.now()).getNano());

        // operations
        final var localDateTime1 = LocalDateTime.now();
        final var smallDuration = Duration.of(1, ChronoUnit.DAYS);

        System.out.println("smallDuration.addTo(localDateTime1) = " + smallDuration.addTo(localDateTime1));
        System.out.println("smallDuration.subtractFrom(localDateTime1) = " + smallDuration.subtractFrom(localDateTime1));

        final var bigDuration = Duration.of(2, ChronoUnit.DAYS);

        System.out.println("bigDuration.plus(smallDuration) = " + bigDuration.plus(smallDuration));
        System.out.println("bigDuration.minus(smallDuration) = " + bigDuration.minus(smallDuration));
    }

    public static void doPeriods() {
        System.out.println("--> periods");

        final Period period1 = Period.of(1981, 11, 3);
        final Period period2 = Period.parse("P1995Y5M4D");

        System.out.println("period1 = " + period1);
        System.out.println("period2 = " + period2);

        final Period period3 = Period.between(LocalDate.of(1993, 12, 24), LocalDate.of(2021, 11, 3));
        System.out.println("period3 = " + period3);

        final Period period4 = Period.parse("P-10Y-2M-35D");
        System.out.println("period4 = " + period4);

        // extracting
        System.out.println("period4 months = " + period4.getMonths());
        System.out.println("period4.get(ChronoUnit.YEARS) = " + period4.get(ChronoUnit.YEARS));

        // operations
        final var smallPeriod = Period.of(1, 2, 3);
        System.out.println("smallPeriod.addTo(LocalDateTime.now()) = " + smallPeriod.addTo(LocalDateTime.now()));
        System.out.println("smallPeriod.subtractFrom(ZonedDateTime.now(ZoneId.of(\"EET\"))) = " + smallPeriod.subtractFrom(ZonedDateTime.now(ZoneId.of("EET"))));

        final var bigPeriod = Period.of(3000, 222, 788);

        System.out.println("bigPeriod.plus(smallPeriod) = " + bigPeriod.plus(smallPeriod));
        System.out.println("smallPeriod.minus(bigPeriod) = " + smallPeriod.minus(bigPeriod));

        Period period  = Period.of(1, 1, 1);
        System.out.println(period.addTo(LocalDate.of(2000, 2, 22)));
    }



}
