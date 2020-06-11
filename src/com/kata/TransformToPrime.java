package com.kata;

import java.math.BigInteger;
import java.util.Arrays;

import static java.math.BigInteger.valueOf;
import static java.util.stream.IntStream.of;

public class TransformToPrime {

    public static int minimumNumberBest1(int[] numbers) {
        int sum = Arrays.stream(numbers).sum();
        BigInteger num = new BigInteger(String.valueOf(sum));
        int nextPrime = num.isProbablePrime(100)
                ? Integer.parseInt((num).toString())
                : Integer.parseInt((num).nextProbablePrime().toString());

        return nextPrime - sum;
    }

    public static int minimumNumberBest2(int[] numbers) {
        long sum = Arrays.stream(numbers).sum();
        return (int) (valueOf(sum - 1).nextProbablePrime().longValue() - sum);
    }

    static int minimumNumberBest3(int[] numbers) {
        int sum = of(numbers).sum();
        return valueOf(sum - 1L).nextProbablePrime().intValue() - sum;
    }

    static int minimumNumberBest4(int[] numbers) {
        int sum = of(numbers).sum(), prime = sum;
        while (!valueOf(prime++).isProbablePrime(10)) ;
        return prime - sum - 1;
    }
}
