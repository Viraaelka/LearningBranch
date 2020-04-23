package com.kata;

import java.util.Arrays;
import java.util.stream.Collectors;

public class FormatDurationClass {

// =====================================================================================================================

    public static String formatDurationBest1(int seconds) {
        return seconds == 0 ? "now" :
                Arrays.stream(
                        new String[]{
                                formatTime("year", (seconds / 31536000)),
                                formatTime("day", (seconds / 86400) % 365),
                                formatTime("hour", (seconds / 3600) % 24),
                                formatTime("minute", (seconds / 60) % 60),
                                formatTime("second", (seconds % 3600) % 60)})
                        .filter(e -> e != "")
                        .collect(Collectors.joining(", "))
                        .replaceAll(", (?!.+,)", " and ");
    }

    public static String formatTime(String s, int time) {
        return time == 0 ? "" : Integer.toString(time) + " " + s + (time == 1 ? "" : "s");
    }
// =====================================================================================================================

    private static int S_PER_MIN = 60;
    private static int S_PER_HR = 60 * S_PER_MIN;
    private static int S_PER_DAY = 24 * S_PER_HR;
    private static int S_PER_YR = 365 * S_PER_DAY;

    private static String unit(int n, String kind) {
        return n == 0 ? "" : String.format("%d %s%s, ", n, kind, n == 1 ? "" : "s");
    }

    public static String formatDurationBest2(int s) {

        if (s == 0) return "now";

        int y, d, h, m;

        s -= (y = s / S_PER_YR) * S_PER_YR;
        s -= (d = s / S_PER_DAY) * S_PER_DAY;
        s -= (h = s / S_PER_HR) * S_PER_HR;
        s -= (m = s / S_PER_MIN) * S_PER_MIN;

        String ret = unit(y, "year") + unit(d, "day") + unit(h, "hour") + unit(m, "minute") + unit(s, "second");
        ret = ret.substring(0, ret.length() - 2); // remove trailing ', '
        return ret.lastIndexOf(",") == -1 ? ret : ret.replaceAll("(.+), (.+?)$", "$1 and $2"); // replace last , with "and"
    }
// =====================================================================================================================

    public static String formatDurationBest3(int seconds) {
        String res = "";
        int[] units = new int[]{31536000, 86400, 3600, 60, 1};
        String[] labels = new String[]{"year", "day", "hour", "minute", "second"};

        if (seconds == 0) return "now";

        for (int i = 0; i < 5; i++) {
            if (seconds >= units[i]) {
                int q = seconds / units[i];
                seconds = seconds % units[i];
                res += (res.equals("") ? "" : (seconds == 0 ? " and " : ", "))
                        + q + " " + labels[i] + (q > 1 ? "s" : "");
            }
        }
        return res;
    }
}
