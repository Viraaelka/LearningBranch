package com.kata;

import java.util.Arrays;

import static java.util.stream.Stream.of;

public class AlphabeticTask {

// =================================================================================================

    static int solveBest(String s) {
        return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
    }

// =================================================================================================
    public static int solveBest2(final String s) {
        return Arrays.stream(s.split("[aeiou]+"))
                .mapToInt(t -> t.chars().sum() - t.length() * 96)
                .max()
                .getAsInt();
    }
}
