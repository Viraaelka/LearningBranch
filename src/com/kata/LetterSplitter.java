package com.kata;

import java.util.Arrays;

import static java.util.stream.Stream.of;

public class LetterSplitter {
    //======================================================

    public static int solveBest1(String s) {
        return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
    }

    //======================================================

    public static int solve(String s) {
        String[] splittedLine = s.split("[aeiou]");
        for (String se : splittedLine) {
            System.out.println("splittedLine = " + se);
            int k = se.chars().map(cu -> {
                cu = cu - 96;
                System.out.println("cu = " + cu);
                return cu;
            }).sum();
            System.out.println("k = " + k);
        }
        return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
    }

    //======================================================
    public static int solveBest2(final String s) {
        return Arrays.stream(s.split("[aeiou]+"))
                .mapToInt(t -> t.chars().sum() - t.length() * 96)
                .max()
                .getAsInt();
    }
    //======================================================

    public static int solveBest3(final String s) {
        String s2 = "aeiou";
        int sum = 0, x = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s2.contains("" + s.charAt(i))) {
                sum = Math.max(sum, x);
                x = 0;
            } else {
                x += (int) s.charAt(i) - 96;
            }
        }
        sum = Math.max(sum, x);
        return sum;
    }
}
