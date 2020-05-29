package com.kata;

import java.math.BigInteger;
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

// ========================================================================================================

    /**
     * 25 squared is 625 , Ends with the same number's digits which are 25 -> "Automorphic"
     * 13 squared is 169 , Not ending with the same number's digits which are 69 -> "Not!!"
     */
    public static String autoMorphicB(int number) {
        String sq = number * number + "";
        return sq.matches(".*" + number) ? "Automorphic" : "Not!!";
    }

    public static String autoMorphicB2(int number) {
        return BigInteger.valueOf(number).pow(2).toString().endsWith(String.valueOf(number)) ? "Automorphic" : "Not!!";
    }

    public static String autoMorphicB3(int number) {
        return Integer.toString((int) Math.pow(number, 2)).contains(Integer.toString(number)) ? "Automorphic" : "Not!!";
    }
}
