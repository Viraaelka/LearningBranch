package com.kata;

import java.util.Arrays;
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

    /**
     * tidyNumber (12) ==> return (true)
     * The number's digits { 1 , 2 } are in non-Decreasing Order (i.e) 1 <= 2
     *
     * tidyNumber (1024) ==> return (false)
     * The Number's Digits {1 , 0, 2, 4} are not in non-Decreasing Order as 0 <= 1 .
     */
    public static boolean tidyNumber(int number) {
        return Arrays.equals(Arrays.stream(String.valueOf(number).split("")).mapToInt(Integer::valueOf).sorted().toArray(),
                Arrays.stream(String.valueOf(number).split("")).mapToInt(Integer::valueOf).toArray());
    }
}
