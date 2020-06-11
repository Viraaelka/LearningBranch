package com.kata;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeleteRepeat {
    public static int[] deleteNthBest1(int[] elements, int maxOccurrences) {
        Map<Integer, Integer> occurrence = new HashMap<>();
        return IntStream.of(elements)
                .filter(motif -> occurrence.merge(motif, 1, Integer::sum) <= maxOccurrences)
                .toArray();
    }

// ================================================================================

    private static boolean shouldAdd(final Map<Integer, Integer> counts, final int element, final int maxOcurrences) {
        if (counts.getOrDefault(element, 0) < maxOcurrences) {
            counts.merge(element, 1, Integer::sum);
            return true;
        }
        return false;
    }

    static int[] deleteNthBest2(final int[] elements, final int maxOcurrences) {
        final Map<Integer, Integer> counts = new HashMap<>();
        return Arrays.stream(elements)
                .filter(element -> shouldAdd(counts, element, maxOcurrences))
                .toArray();
    }

    // ================================= MY SOLUTION=======================================================

    public static int[] deleteNth(int[] elements, int maxOccurrences) {
        if (maxOccurrences == 1)
            return IntStream.of(elements).distinct().toArray();
        LinkedList<Integer> arrayList = Arrays.stream(elements).boxed().collect(Collectors.toCollection(LinkedList::new));
        ArrayList<Integer> repeatedArray = new ArrayList<>();
        for (int elem : arrayList) {
            if (Collections.frequency(repeatedArray, elem) < maxOccurrences)
                repeatedArray.add(elem);
        }
        return repeatedArray.stream().mapToInt(Integer::intValue).toArray();
    }

}
