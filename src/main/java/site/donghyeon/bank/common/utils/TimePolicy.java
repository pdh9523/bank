package site.donghyeon.bank.common.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public final class TimePolicy {

    public static final ZoneId KST = ZoneId.of("Asia/Seoul");

    private TimePolicy() {

    }

    public static Instant startOfToday() {
        return LocalDate.now(KST)
                .atStartOfDay(KST)
                .toInstant();
    }

    public static Instant startOfTomorrow() {
        return LocalDate.now(KST)
                .plusDays(1)
                .atStartOfDay(KST)
                .toInstant();
    }
}
