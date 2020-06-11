package com.kata;

import java.util.*;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.IntStream;
import java.util.Collection;

public class SortOutMenFromBoys {

//    public static int[] menFromBoysBest1(final int[] values) {
//        List<Integer> mens = IntStream.of(values)
//                .boxed()
//                .filter(number -> number % 2 == 0)
//                .sorted()
//                .collect(Collectors.toList());
//        List<Integer> boys = IntStream.of(values)
//                .boxed()
//                .filter(number -> number % 2 != 0)
//                .sorted(Comparator.reverseOrder())
//                .collect(Collectors.toList());
//        return List.of(mens, boys).stream()
//                .flatMap(Collection::stream)
//                .distinct()
//                .mapToInt(Integer::intValue)
//                .toArray();
//    }

    public static int[] menFromBoysBest2(final int[] values) {
        return Arrays.stream(values)
                .distinct()
                .boxed()
                .sorted(Comparator.comparing(i -> i % 2 == 0 ? i : -i))
                .sorted(Comparator.comparing(i -> Math.abs(i % 2)))
                .mapToInt(i -> i)
                .toArray();
    }

    public static int[] men(final int[] values){
        int arrEven[] = Arrays.stream(values).filter(a -> a%2 == 0).distinct().sorted().toArray();
        int arrOdd[] = Arrays.stream(values).filter(a -> a%2 != 0).distinct().map(a -> a * (-1)).sorted().map(a -> a * (-1)).toArray();
        return IntStream.concat(IntStream.of(arrEven), IntStream.of(arrOdd)).toArray();
    }

    public static int[] menFromBoysBest4(final int[] values) {
        return IntStream.concat(IntStream.of(values).filter(x -> x % 2 == 0).distinct().sorted(), IntStream.of(values).filter(x -> x % 2 != 0).distinct().map(i -> -i).sorted().map(i -> -i)).toArray();
    }

//    public static int[] menFromBoysBest5(int[] values) {
//        var boys = IntStream.of(values).filter(i -> i % 2 == 0).sorted();
//        var men = IntStream.of(values).filter(i -> i % 2 != 0).boxed().sorted(Collections.reverseOrder()).mapToInt(Integer::intValue);
//        return IntStream.concat(boys, men).distinct().toArray();
//    }

    // ====================================== MY SOLUTION=================================================

    public static int[] menFromBoys(final int[] values) {
        List<Integer> menList = new ArrayList<>();
        int[] even = Arrays.stream(values).sorted().distinct().filter(var -> var % 2 == 0).toArray();
        int[] odds = IntStream.of(values).sorted().distinct().filter(var -> var % 2 != 0).toArray();
        for (Integer o : odds) {
            menList.add(o);
        }
        Collections.reverse(menList);
        for (int i = even.length - 1; i >= 0; i--) {
            menList.add(0, even[i]);
        }
        return menList.stream().mapToInt(Integer::intValue).toArray();
    }


}
