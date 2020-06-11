package com.kata;

import java.util.Arrays;

public class MaxProduct {

    /**
     * maxProduct ({4, 3, 5}, 2) ==>  return (20)
     * Since the size (k) equal 2 , then the subsequence of size 2 whose gives product of maxima is 5 * 4 = 20
     * maxProduct ({8, 10 , 9, 7}, 3) ==>  return (720)
     * Since the size (k) equal 3 , then the subsequence of size 3 whose gives product of maxima is 8 * 9 * 10 = 720
     */

    public static long maxProductBest1(int[] numbers, int subSize) {
        return Arrays.stream(numbers)
                .sorted()
                .skip(numbers.length - subSize)
                .mapToLong(Long::valueOf)
                .reduce(1, (x, y) -> x * y);
    }

    public static long maxPro(int[] numbers, int sub) {
        return Arrays.stream(numbers)
                .sorted()
                .skip(numbers.length - sub)
                .boxed()
                .mapToLong(Integer::longValue)
                .reduce((x, y) -> x * y)
                .orElseThrow(() -> new NumberFormatException());
    }

    // ====================================== MY SOLUTION=================================================

    public static long maxProduct(int[] numbers, int sub_size) {
        long k = 1;
        int[] max = Arrays.stream(numbers).sorted().skip(numbers.length - sub_size).toArray();
        for (Integer i : max) {
            k *= i;
        }
        return k;
    }
}
