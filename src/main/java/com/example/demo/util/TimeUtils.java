package com.example.demo.util;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TimeUtils {
    public static long minutesBetween(LocalTime t1, LocalTime t2) {
        return ChronoUnit.MINUTES.between(t1, t2);
    }
}