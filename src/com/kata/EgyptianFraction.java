package com.kata;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class EgyptianFraction {
    //======================================================

    /**
     * Wrapper function that converts String to BigDecimal and wrap answer in []
     */
    public static String decomposeBest1(String nrStr, String drStr) {
        // Convert to Big Decimal values (Note: even long would overflow very fast)
        BigDecimal numerator = new BigDecimal(nrStr);
        BigDecimal denominator = new BigDecimal(drStr);

        List<BigDecimal[]> decompose = decompose(numerator, denominator);

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        sb.append(decompose.stream().map(entry -> {
            if (entry.length == 1) {
                return entry[0].equals(BigDecimal.ZERO) ? "" : entry[0].toString();
            } else {
                return entry[1].equals(BigDecimal.ONE) ? entry[0].toString() : String.format("%s/%s", entry[0].toString(), entry[1].toString());
            }
        }).collect(Collectors.joining(", ")));

        sb.append("]");
        return sb.toString();
    }

    public static List<BigDecimal[]> decompose(BigDecimal numerator, BigDecimal denominator) {
        if (numerator.remainder(denominator).equals(BigDecimal.ZERO)) {
            // if denominator perfectly divides numerator (say 8/2) add 4 in List
            BigDecimal[] bigDecimals = new BigDecimal[]{numerator.divide(denominator, RoundingMode.DOWN)};

            return new ArrayList<>(Collections.singleton(bigDecimals));
        } else if (denominator.remainder(numerator).equals(BigDecimal.ZERO)) {
            // if denominator perfectly divides numerator (say 2/8) add 1/4 in List
            BigDecimal[] bigDecimals = {BigDecimal.ONE, denominator.divide(numerator, RoundingMode.DOWN)};

            return new ArrayList<>(Collections.singleton(bigDecimals));
        } else {
            // if numerator doesn't divide denominator perfectly
            // denominator/numerator + 1
            BigDecimal n = denominator.divide(numerator, RoundingMode.DOWN).add(BigDecimal.ONE);

            BigDecimal newNumerator = numerator.multiply(n).subtract(denominator);
            BigDecimal newDenominator = denominator.multiply(n);

            List<BigDecimal[]> list = new ArrayList<>();
            list.add(new BigDecimal[]{BigDecimal.ONE, n});

            list.addAll(decompose(newNumerator, newDenominator));

            return list;
        }
    }

    //======================================================
    public static String decomposeBest2(String nrStr, String drStr) {
        long a = Integer.parseInt(nrStr);
        long b = Integer.parseInt(drStr);
        long denum = 2;
        String s;
        if (a > b) {
            s = ", " + a / b;
            a %= b;
        } else s = "";
        while (a > 0) {
            if (a * denum >= b) {
                s += ", 1/" + denum;
                a = a * denum - b;
                b *= denum;
            }
            denum++;
        }
        return s.isEmpty() ? "[]" : "[" + s.substring(2) + "]";
    }

    //======================================================


    public static final BigInteger ZERO = BigInteger.ZERO;
    public static final BigInteger ONE = BigInteger.ONE;
    public static final BigInteger TWO = BigInteger.valueOf(2L);

    public static String decomposeBest3(String nrStr, String drStr) {
        BigInteger numer = BigInteger.valueOf(Long.parseLong(nrStr));
        BigInteger denom = BigInteger.valueOf(Long.parseLong(drStr));

        List<String> fractions = new LinkedList<>();
        if (numer.compareTo(denom) >= 0) {
            fractions.add(numer.divide(denom).toString());
            numer = numer.mod(denom);
        }
        while (numer.compareTo(ZERO) > 0) {
            BigInteger next = denom.divide(numer);
            if (denom.mod(numer).compareTo(ZERO) != 0)
                next = next.add(ONE);
            fractions.add("1/" + next.toString());
            numer = numer.multiply(next).subtract(denom);
            denom = denom.multiply(next);
        }
        return fractions.toString();
    }

    // ======== just to read how it can be written in terms of beautiful skill of programming ===================
    class Decomp {

        public String decomposeBest4(String nrStr, String drStr) {
            Decomp solution = new Decomp(nrStr, drStr);
            solution.addCoefficientToListAndGetReducedNumerator();
            solution.addFractionPartsToList();
            return solution.answers.toString();
        }

        List<String> answers;
        long numerator;
        long denominator;

        Decomp(String nrStr, String drStr) {
            this.numerator = Long.parseLong(nrStr);
            this.denominator = Long.parseLong(drStr);
            this.answers = new ArrayList<String>();
        }

        void addCoefficientToListAndGetReducedNumerator() {
            long quotient = numerator / denominator;
            if (quotient > 0) {
                answers.add(String.valueOf(quotient));
            }
            numerator -= denominator * quotient;
        }

        void addFractionPartsToList() {
            while (numerator > 0) {
                long denominatorOfPartFraction = (int) Math.ceil(((double) denominator) / numerator);
                answers.add("1/" + denominatorOfPartFraction);
                numerator = (numerator * denominatorOfPartFraction) - denominator;
                denominator *= denominatorOfPartFraction;
            }
        }
    }

    //======================================================
}
