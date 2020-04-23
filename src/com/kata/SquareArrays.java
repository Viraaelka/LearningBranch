package com.kata;

import java.util.Arrays;

public class SquareArrays {

// =================================================================================================================

    public static boolean compBest1(int[] a, int[] b) {
        if ((a == null) || (b == null)) {
            return false;
        }
        int[] aa = Arrays.stream(a).map(n -> n * n).toArray();
        Arrays.sort(aa);
        Arrays.sort(b);
        return (Arrays.equals(aa, b));
    }
// ===================================================================================================================

    public static boolean compBest2(final int[] a, final int[] b) {
        return a != null && b != null && a.length == b.length
                && Arrays.equals(Arrays.stream(a).map(i -> i * i).sorted().toArray(), Arrays.stream(b).sorted().toArray());
    }
}
