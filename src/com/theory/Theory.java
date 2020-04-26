package com.theory;

public class Theory {
    /**
     * What is the difference?
     *
     * Stream<Person> arraysStream = Arrays.stream(new Person[] {town.get(0)});
     * Stream<Person> streamOf= Stream.of(new Person[] {town.get(0)});
     *
     * Разница только в том, что метод Arrays.stream принимает в качестве аргумента массив, а метод Stream.of описан
     * с использованием varargs:
     *
     * public static<T> Stream<T> of(T... values) {
     *     return Arrays.stream(values);
     * }
     * Т.е. во втором случае можно не создавать новый массив, а просто писать:
     *
     * Stream<Person> streamOf= Stream.of(town.get(0));
     *
     * Есть одна интересная штука: Stream.of() массив примитивов воспринимает как один элемент,
     * массив же объектов он воспринимает так же, как Arrays.stream(). Поэтому следует быть осторожным с Stream.of(),
     * когда мы переводим массив примитивов в Stream<T>. Но лучше использовать для примитивов специальные классы и методы,
     * например, IntStream.of() и т.д
     *
     */


}
