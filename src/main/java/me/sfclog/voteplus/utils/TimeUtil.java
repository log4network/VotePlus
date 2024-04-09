package me.sfclog.voteplus.utils;

public class TimeUtil {

    public static String gettime(int totalSecs) {
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
