package com.kata;

public class Dollars {

    public int solveBest1(int n) {
        int[] denominations = new int[]{500, 200, 100, 50, 20, 10};
        int result = 0;

        for (int denomination : denominations) {
            result += n / denomination;
            n %= denomination;
        }

        return n == 0 ? result : -1;
    }

    public int solveBest2(int n) {
        return n % 10 == 0 ? (n % 500) + (n % 500 / 200) + (n % 500 % 200 / 100) + (n % 100 / 50) + (n % 50 / 20) + (n % 20 / 10) : -1;
    }
}
