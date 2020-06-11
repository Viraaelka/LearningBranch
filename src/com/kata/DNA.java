package com.kata;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DNA {

    private static Map<Character, String> DNAMap = new HashMap<>();

    static {
        DNAMap.put('A', "T");
        DNAMap.put('T', "A");
        DNAMap.put('C', "G");
        DNAMap.put('G', "C");
    }

    public static String makeComplement(String dna) {
        return dna
                .chars()
                .mapToObj(val -> DNAMap.get((char) val))
                .collect(Collectors.joining(""));
    }
}
