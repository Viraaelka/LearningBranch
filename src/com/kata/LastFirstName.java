package com.kata;

import java.util.Arrays;
import java.util.stream.Collectors;

public class LastFirstName {
    /**
     * Abbreviate a Two Word Name
     * Sam Harris => S.H
     * Patrick Feeney => P.F
     */

    public static String abbrevNameBest(String name) {
        return name.toUpperCase().replaceAll("(.).*\\s(.).*", "$1.$2");
    }

// ====================================== MY SOLUTION=================================================

    public static String abbrevName(String name) {
        System.out.println("name = " + name);
        String output = Arrays.stream(name.split(" ")).map(k -> k.charAt(0) + ".").map(String::toUpperCase).collect(Collectors.joining());
        return output.substring(0, output.length() - 1);
    }
}
