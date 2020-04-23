package com.kata;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SortedLinkFromMap {

// =============================================================================================================

    public static List<String> myLanguagesBest3(final Map<String, Integer> results) {
        return results.entrySet()
                .stream()
                .filter(entry -> entry.getValue() >= 60)
                .sorted(Comparator.comparingInt(Map.Entry<String, Integer>::getValue).reversed())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static List<String> myLanguagesBest1(final Map<String, Integer> results) {
        /*
        results.entrySet() can output following:
        Hindi=60
        Dutch=93
        Greek=71
         */
        return results.entrySet()
                .stream()
                .filter(me -> me.getValue() >= 60)
                .sorted((m1, m2) -> Integer.compare(m2.getValue(), m1.getValue()))
                .map(me -> me.getKey())
                .collect(toList());
    }

    // =============================================================================================================

    public static List<String> myLanguagesBest2(final Map<String, Integer> m) {
        return m.keySet().stream().filter(e -> m.get(e) >= 60).sorted((a, b) -> m.get(b) - m.get(a)).collect(toList());
        // развернула этот вариант:
//        return m.keySet().stream().filter(e -> m.get(e) >= 60).sorted((a, b) -> {
//            System.out.println("m.get(b) = " + m.get(b));
//            System.out.println("m.get(a) = " + m.get(a));
//            System.out.println(m.get(b) - m.get(a));
//            System.out.println("------------------------");
//            return m.get(b) - m.get(a);
//        }).collect(toList());
        // m.get(b) = 60
        //m.get(a) = 93
        //-33
        //------------------------
        //m.get(b) = 93
        //m.get(a) = 71
        //22
        //------------------------
        //m.get(b) = 60
        //m.get(a) = 71
        //-11
        //------------------------
        //m.get(b) = 93
        //m.get(a) = 71
        //22
        //------------------------
    }
// ===============================================MY SOLUTION====================================================

    public static List<String> myLanguages(final Map<String, Integer> results) {

        List<Integer> arrayList = results.values().stream().filter(v -> v >= 60).sorted().distinct().collect(toList());
        Collections.reverse(arrayList);
        ArrayList<String> arrayList1 = new ArrayList<>();
        for (Integer g : arrayList) {
            for (String k : results.keySet()) {
                if (g == results.get(k))
                    arrayList1.add(k);
            }
        }
        return arrayList1;
    }
}
