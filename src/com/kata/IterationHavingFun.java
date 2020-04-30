package com.kata;

import java.util.stream.IntStream;

public class IterationHavingFun {

    // 25 /2 = 25 + 12 + 6 + 3 + 1

    int halvingSumBest1(int n) {
        return n == 1 ? 1 : n + halvingSumBest1(n/2);
    }

    int halvingSumBest2(int n) {
        return IntStream.iterate(n, i -> i / 2).limit(n).filter(i -> i > 0).sum();
    }

// ====================================== MY SOLUTION=================================================

    static int halvingSum(int n) {
        int k = n;
        while (n != 0) {
            k += have(n);
            n = n/2;
        }
        return k;
    }

    static int have(int o) {
        return IntStream.of(o)
                .map(k -> k / 2)
                .iterator().nextInt();
    }
}
