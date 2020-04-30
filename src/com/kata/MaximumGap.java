package com.kata;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MaximumGap {

    /**
     * maxGap {13,10,5,2,9}) ==> return (4)
     * maxGap ({-3,-27,-4,-2}) ==> return (23)
     */

    public static int maxGapBest1(int[] ary) {
        java.util.Arrays.sort(ary);
        int max = 0, prev = ary[0];
        for (int a : ary) {
            max = Math.max(a - prev, max);
            prev = a;
        }
        return max;
    }

//======================================================

    public static int maxGapBest2(int[] numbers) {
        Arrays.sort(numbers);
        return IntStream.range(1, numbers.length).map(i -> numbers[i] - numbers[i - 1]).max().getAsInt(); // Do your magic!
    }

//======================================================

    static int maxGapBest3(int[] numbers) {
        Arrays.sort(numbers);
        return IntStream.range(0, numbers.length - 1).sorted().map(i -> numbers[i + 1] - numbers[i]).max().orElse(0);
    }

// ====================================== MY SOLUTION=================================================

    public static int maxGap(int[] numbers) {
        numbers = Arrays.stream(numbers).sorted().toArray();
        int p = 0;
        int maxValue = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            if ((numbers[i + 1] > 0 && numbers[i] > 0) | (numbers[i] < 0 && numbers[i + 1] < 0)) {
                p = Math.abs(numbers[i + 1]) - Math.abs(numbers[i]);
                if (Math.abs(p) > maxValue)
                    maxValue = Math.abs(p);
            } else {
                p = Math.abs(numbers[i + 1]) + Math.abs(numbers[i]);
                if (p > maxValue)
                    maxValue = p;
            }
        }
        return maxValue; // Do your magic!
    }
}
