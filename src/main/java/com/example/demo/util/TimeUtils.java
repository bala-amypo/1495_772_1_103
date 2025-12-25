package com.example.demo.util;

import java.time.LocalTime;
import java.time.Duration;

public class TimeUtils {

    // Calculates minutes between two LocalTime instances
    public static long minutesBetween(LocalTime t1, LocalTime t2) {
        return Duration.between(t1, t2).toMinutes();
    }
}
