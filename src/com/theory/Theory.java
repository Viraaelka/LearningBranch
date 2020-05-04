package com.theory;

import java.util.Arrays;
import java.util.List;

public class Theory {
    /**
     * IntStream.rangeClosed() and IntStream.range() both generates incremental number by one from start to end point in java 8.
     *
     * IntStream.rangeClosed(2,6) works as >= and  <=  and  produces
     *
     * 2,3,4,5,6
     *
     * IntStream.range(2,6) works as >= and  < and  produces same but does not include the last number
     *
     * 2,3,4,5
     */

    /**
     * What is the difference?
     * <p>
     * Stream<Person> arraysStream = Arrays.stream(new Person[] {town.get(0)});
     * Stream<Person> streamOf= Stream.of(new Person[] {town.get(0)});
     * <p>
     * Разница только в том, что метод Arrays.stream принимает в качестве аргумента массив, а метод Stream.of описан
     * с использованием varargs:
     * <p>
     * public static<T> Stream<T> of(T... values) {
     * return Arrays.stream(values);
     * }
     * Т.е. во втором случае можно не создавать новый массив, а просто писать:
     * <p>
     * Stream<Person> streamOf= Stream.of(town.get(0));
     * <p>
     * Есть одна интересная штука: Stream.of() массив примитивов воспринимает как один элемент,
     * массив же объектов он воспринимает так же, как Arrays.stream(). Поэтому следует быть осторожным с Stream.of(),
     * когда мы переводим массив примитивов в Stream<T>. Но лучше использовать для примитивов специальные классы и методы,
     * например, IntStream.of() и т.д
     */

    // REDUCE

    List<String> strings = Arrays.asList("aaa", "bbb", "ccc", "ddd", "ffff");
    String s = strings.stream()
            .reduce("", (left, right) -> left.length() > right.length() ? left : right);

    // The same snippet:
    public static String getU() {
        List<String> strings = Arrays.asList("aaa", "bbb", "ccc", "ddd", "ffff");

        String s = strings.stream()
                .reduce("WHAT", (left, right) -> {
                    System.out.println("left = " + left + "\nright = " + right);
                    String k = left.length() > right.length() ? left : right;
                    return k;
                });
        return s;
    }
    /*
     OUTPUT:
    left = WHAT
    right = aaa
    left = WHAT
    right = bbb
    left = WHAT
    right = ccc
    left = WHAT
    right = ddd
    left = WHAT
    right = fffff
     */
}
