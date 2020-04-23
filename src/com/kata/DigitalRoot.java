package com.kata;

import java.util.Arrays;
import java.util.stream.Stream;

public class DigitalRoot {
    //======================================================

    public static int digital_root(int n) {
        int u = n;
        while (true) {
            u = String.valueOf(u).chars().map(c -> c - 48).sum();
            if (String.valueOf(u).length() == 1)
                break;
        }
        return String.valueOf(n).length() == 1 ? n : u;
    }
    //======================================================

    public static int digital_rootBest(int n) {
        final int result = String.valueOf(n).chars().reduce(0, (acc, curr) -> acc + (curr - '0'));
        return result < 10 ? result : digital_root(result);
    }

    //======================================================
    public static int digital_rootBest2(int n) {
        if (n < 10) {
            return Stream.of(String.valueOf(n).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
        } else {
            return digital_root(Stream.of(String.valueOf(n).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum());
        }
    }
    //======================================================

    public static int digital_rootBest3(int n) {
        return n > 9 ? digital_root(reduceNumbers(n)) : n;
    }

    private static Integer reduceNumbers(int n) {
        return Arrays.stream(String.valueOf(n).split(""))
                .reduce(0, (a, b) -> a + Integer.parseInt(b), Integer::sum);
    }
}
