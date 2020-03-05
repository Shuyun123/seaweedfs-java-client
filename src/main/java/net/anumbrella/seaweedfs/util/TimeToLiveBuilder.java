package net.anumbrella.seaweedfs.util;

public class TimeToLiveBuilder {
    public static String buildMinutes(int count) {
        return count + "m";
    }

    public static String buildHours(int count) {
        return count + "h";
    }

    public static String buildDays(int count) {
        return count + "d";
    }

    public static String buildWeeks(int count) {
        return count + "w";
    }

    public static String buildMonths(int count) {
        return count + "M";
    }

    public static String buildYears(int count) {
        return count + "y";
    }
}
