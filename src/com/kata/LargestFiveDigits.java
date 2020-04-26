package com.kata;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class LargestFiveDigits {
    /**
     * Largest 5 digit number in a series
     *
     * 1234567890 -> correct output: 67890
     */
    public int solveBest(final String digits) {
        return IntStream.range(0, digits.length() - 4)
                .mapToObj(i -> digits.substring(i, i + 5))
                .mapToInt(Integer::parseInt)
                .max()
                .orElse(0);
    }

// ====================================== MY SOLUTION=================================================

    public static int solve(final String digits) {
        return get(digits).stream().mapToInt(Integer::parseInt).max().getAsInt();
    }

    private static List<String> get(final String digits) {
        int arraySize = digits.length() - 4;
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0; i < arraySize; i++) {
            arrayList.add(digits.substring(i, i + 5));
        }
        return arrayList;
    }
}
