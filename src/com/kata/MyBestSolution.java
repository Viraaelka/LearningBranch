package com.kata;

import java.util.stream.IntStream;

public class MyBestSolution {

    /**
     * if 145 = 1! + 4! + 5!
     * -> "STRONG!!!!" else -> "Not Strong !!"
     *
     */
    public static String isStrongNumber(int num) {
        int result = String.valueOf(num)
                .chars()
                .map(c -> c - 48)
                .mapToObj(var -> IntStream.range(1, var + 1).reduce((x, y) -> x * y).orElse(0))
                .reduce((x, y) -> x + y).orElse(0);
        return result == num ? "STRONG!!!!" : "Not Strong !!";
    }
}
