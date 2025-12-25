package com.example.demo.util;

import java.time.Duration;
import java.time.LocalTime;

public class TimeUtils {

    public static long minutesBetween(LocalTime t1, LocalTime t2) {
        return Duration.between(t1, t2).toMinutes();
    }
}
