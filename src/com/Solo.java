package com;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Stream.of;

public class Solo {
    public static void main(String[] args) throws Exception {
        int[] a = new int[]{121, 144, 19, 161, 19, 144, 19, 11};
        int[] b = new int[]{14641, 20736, 361, 361, 25921, 361, 20736, 121};
        /*
           System.out.println("****** Basic Tests small numbers******");
        List<Integer> ts = new ArrayList<>(Arrays.asList(50, 55, 56, 57, 58));
        int n = SumOfK.chooseBestSum(163, 3, ts);
        assertEquals(163, n);
        ts = new ArrayList<>(Arrays.asList(50));
        Integer m = SumOfK.chooseBestSum(163, 3, ts);
        assertEquals(null, m);
        ts = new ArrayList<>(Arrays.asList(91, 74, 73, 85, 73, 81, 87));
        n = SumOfK.chooseBestSum(230, 3, ts);
        assertEquals(228, n);
         */
//        System.out.println(chooseBestSum(1680, 1, new ArrayList<>(Arrays.asList(92, 127, 335, 251, 499, 138, 200, 48, 436, 379, 456, 154, 380, 378, 182, 324, 477, 415, 50, 288))));
//        System.out.println(chooseBestSum(1599, 4, new ArrayList<>(Arrays.asList(374, 132, 378, 436, 380, 178, 205, 484, 477, 159, 351, 124, 321, 34, 319, 169, 375, 90, 375, 290))));
//        System.out.println("final = " + chooseBestSum(331, 2, new ArrayList<>(Arrays.asList(91, 74, 73, 85, 73, 81, 87))));
//        System.out.println(getNegativeMod(-15, 7));
//        System.out.println(decompose("3", "4"));

        System.out.println("final = " + decompose("14", "15"));
//        System.out.println(getAdditionalSecond(16, 38098944));
//        System.out.println(getRoundedValueToUpperValue(351, 59796412));
//        System.out.println(getSecondPartOfFormula(13, 351708));
    }

    public static String decompose(String nrStr, String drStr) {
        stringBuilder.setLength(0);
        System.out.println("n = " + nrStr + " : d = " + drStr);
        int nr = Integer.parseInt(nrStr);
        int dr = Integer.parseInt(drStr);
        if (nr / dr > 0 && nr % dr == 0)
            return stringBuilder.append("[").append(nr / dr).append("]").toString();
        else if (nr != 0 && dr / nr > 0 && dr % nr == 0)
            return stringBuilder.append("[").append("1/").append(dr / nr).append("]").toString();
        return nr == 0 || dr == 0 ? "[]" :
                new StringBuilder().append("[").append(deco(nr, dr).replaceAll(", (?!\\d+)", "")).append("]").toString();
    }

    public static String getFirstPart(int nr, int dr) {
        int k;
        return (k = Integer.parseInt(getRoundedValueToUpperValue(nr, dr))) <= maxInt && k > 0 ?
                new StringBuilder().append("1").append(k == 1 ? "" : "/" + k).append(", ").toString() : "";
    }

    public static String getSecondPartOfFormula(int a, int b) {
        if (a >= Integer.MAX_VALUE || b > Integer.MAX_VALUE || getAdditionalSecond(a, b).equals(""))
            return "";
        return String.valueOf(getNegativeMod(a, b)) + " - " + getAdditionalSecond(a, b);
    }

    public static String getAdditionalSecond(int a, int b) {
        int k;
        long p;
        try {
            p = Long.parseLong(getRoundedValueToUpperValue(a, b)) * b;
            if (p / getNegativeMod(a, b) < maxInt && p % getNegativeMod(a, b) == 0)
                return p + "";
        } catch (NumberFormatException e) {

        }
        return (k = Integer.parseInt(getRoundedValueToUpperValue(a, b)) * b) <= maxInt && k > 0 ? k + "" : "";
    }

    public static StringBuilder stringBuilder = new StringBuilder();
    public static final int maxInt = Integer.MAX_VALUE;

    public static String deco(int nr, int dr) {
        String divideNumberString = "";
        if (!getFirstPart(nr, dr).equals(""))
            stringBuilder.append(getFirstPart(nr, dr));
        else
            return stringBuilder.toString();
        String kep = "";
        if ((kep = getSecondPartOfFormula(nr, dr)).equals(""))
            return stringBuilder.toString();
        int nr2 = 0;
        int dr2 = 0;
        if (!kep.equals("")) {
            try {
                nr2 = Integer.parseInt(kep.split(" - ")[0]);
                dr2 = Integer.parseInt(kep.split(" - ")[1]);
            } catch (NumberFormatException e) {
                long h = Long.parseLong(kep.split(" - ")[1]);
                return stringBuilder.append(1).append("/").append(h / nr2).append(", ").toString();
            }
            divideNumberString = divideNumber(nr2, dr2);
            if (!divideNumberString.equals("")) {
                nr2 = Integer.parseInt(divideNumberString.split(" - ")[0]);
                dr2 = Integer.parseInt(divideNumberString.split(" - ")[1]);
            }
            if (nr2 == 1)
                stringBuilder.append(nr2).append("/").append(dr2).append(", ");
            else {
                deco(nr2, dr2);
            }
        }
        return stringBuilder.toString();
    }

    public static String divideNumber(int nr, int dr) {
        int c = 2;
        int a = 0, b = 0;
        if (dr % nr == 0)
            return "1 - " + dr / nr;
        while (c < 10) {
            if (nr % c == 0 && dr % c == 0)
                return String.valueOf(nr / c) + " - " + String.valueOf(dr / c);
            c++;
        }
        return "";
    }

    public static String getRoundedValueToUpperValue(int nr, int dr) {
        int k;
        return (k = (int) Math.ceil((1.0 * dr) / nr)) <= maxInt && k > 0 ? String.valueOf(k) : "";
    }

    public static int getNegativeMod(int b, int a) {
        int c = 1;
        int temp = 0;
        if (b >= maxInt)
            return 0;
        while (true) {
            temp = Math.abs(b) * c;
            if (temp > maxInt)
                return 0;
            if (temp > Math.abs(a)) {
                c = Math.abs((b * c) - a);
                break;
            }
            c++;
        }
        return c;
    }


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

    public static int digital_root(int n) {
        int u = n;
        while (true) {
            u = String.valueOf(u).chars().map(c -> c - 48).sum();
            if (String.valueOf(u).length() == 1)
                break;
        }
        return String.valueOf(n).length() == 1 ? n : u;
    }
    //======================================================

    public static int digital_rootBest(int n) {
        final int result = String.valueOf(n).chars().reduce(0, (acc, curr) -> acc + (curr - '0'));
        return result < 10 ? result : digital_root(result);
    }

    //======================================================
    public static int digital_rootBest2(int n) {
        if (n < 10) {
            return Stream.of(String.valueOf(n).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum();
        } else {
            return digital_root(Stream.of(String.valueOf(n).split(""))
                    .mapToInt(Integer::parseInt)
                    .sum());
        }
    }
    //======================================================

    public static int digital_rootBest3(int n) {
        return n > 9 ? digital_root(reduceNumbers(n)) : n;
    }

    private static Integer reduceNumbers(int n) {
        return Arrays.stream(String.valueOf(n).split(""))
                .reduce(0, (a, b) -> a + Integer.parseInt(b), Integer::sum);
    }
    //======================================================

    public static TreeSet<Integer> sumOfElementsLessLimit = new TreeSet<>();

    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        if (k == 1) {
            ls.forEach(el -> {
                if (el <= t)
                    sumOfElementsLessLimit.add(el);
            });
            return sumOfElementsLessLimit.last();
        }

        System.out.println("distance = " + t + " : limit = " + k);
        ls.forEach(System.out::println);
        int[] y = new int[ls.size()];
        for (int i = 0; i < y.length; i++) {
            y[i] = ls.get(i);
        }
        ArrayList<Integer> array = new ArrayList<>();
        int sum = 0;
//        sum = Arrays.stream(y).sum();
        int count = 0;
        int r = 0;
        StringBuilder stringBuilder = new StringBuilder();
        int member = 0;
        do {
            array.add(ls.get(count));
            stringBuilder.append(ls.get(count)).append(";");
            // 50, 55, 57, 58, 60
            r = member;
            while (array.size() != k && member < (ls.size() - 1)) {
                try {
                    if (ls.get(count) != ls.get(r)) {
                        array.add(ls.get(r));
                        stringBuilder.append(ls.get(r)).append(";");
                    }
                    sum = getSumfArraysElements(array);
                    if (sum <= t)
                        sumOfElementsLessLimit.add(sum);
                    r++;
                } catch (IndexOutOfBoundsException e) {
                    count++;
                    member++;
                }
            }
            member++;
            System.out.println("======================");
            array.forEach(System.out::println);
            System.out.println("======================");
            array.clear();
            if (r == ls.size()) {
                count++;
                member = 0;
            }
        } while (count <= ls.size() - 1);
        getSumOfOddValues(ls, k, t);
        sumOfElementsLessLimit.forEach(System.out::println);
        return k > ls.size() ? null : sumOfElementsLessLimit.last();
    }

    public static void getSumOfOddValues(List<Integer> ls, int limit, int distance) {
        int count = 0;
        int sum = 0;
        int r = 0;
        int member = 0;
        ArrayList<Integer> array = new ArrayList<>();
//        50, 55, 57, 58, 60, 61, 62, 63, 98, 78
        do {
            array.add(ls.get(count));
            // 50, 55, 57, 58, 60
            r = member;
            while (array.size() != limit && member < (ls.size() - 1)) {
                try {
                    if (ls.get(count) != ls.get(r)) {
                        array.add(ls.get(r));
                    }
                    sum = getSumfArraysElements(array);
                    if (sum <= distance)
                        sumOfElementsLessLimit.add(sum);
                    r += 2;
                } catch (IndexOutOfBoundsException e) {
                    count++;
                    member++;
                }
            }
            member += 2;
            System.out.println("======================");
            array.forEach(System.out::println);
            System.out.println("======================");
            array.clear();
            if (r >= ls.size()) {
                count++;
                member = 0;
            }
        } while (count <= ls.size() - 1);
    }

    public static int getSumfArraysElements(List<Integer> list) {
        int sum = 0;
        for (Integer o : list) {
            sum += o;
        }
        return sum;
    }


//      System.out.println("====================================");
//        sumOfElementsLessLimit.forEach(System.out::println);
//        System.out.println(sumOfElementsLessLimit.last());
//        System.out.println("====================================");

//    public static int getClosestValueToLimit(int currentValue, int previosValue, int limitT) {
//        int difference = currentValue < limitT && previosValue > limitT ? currentValue : previosValue;
//        if (currentValue > limitT && previosValue > limitT)
//            return 0;
//        if (currentValue < limitT && previosValue < limitT) {
//            int a = Math.abs(limitT - currentValue);
//            int b = Math.abs(limitT - previosValue);
//            difference = a < b ? currentValue : previosValue;
//            System.out.println("currentValue = " + currentValue + " : difference = " + difference);
//        }
//        return limitT - currentValue == 0 ? currentValue :
//                (limitT - previosValue == 0 ? previosValue : difference);
//    }

    public static int getSumOfFirstAndTwoLastElements(List<Integer> originalList) {
        System.out.println(originalList.get(0) + " : " + originalList.get(originalList.size() - 2) + " : " + originalList.get(originalList.size() - 1));
        return originalList.get(0) + originalList.get(originalList.size() - 2) + originalList.get(originalList.size() - 1);
    }

    public static boolean comp(int[] a, int[] b) {
        if (a == null || b == null)
            return false;
        int state = 0;
        state = (int) Arrays.stream(b)
                .distinct()
                .filter(elementOfB ->
                        Arrays.stream(a).distinct().filter(elementOfA ->
                                elementOfA != 0 && elementOfB / elementOfA == elementOfA && elementOfB % elementOfA == 0)
                                .count() > 0)
                .count();
        if (Arrays.stream(a).anyMatch(elemA -> elemA == 0 &&
                Arrays.stream(b).filter(elemB -> elemB == 0).count() > 0))
            state++;
        System.out.println("a = " + Arrays.stream(a).distinct().count() + " : b = " + Arrays.stream(b).distinct().count() + " : state = " + state);
        if (state == Arrays.stream(b).distinct().count() && isDuplicated(a, b))
            return true;
        return false;
    }

    public static boolean isDuplicated(int[] a, int[] b) {
        int count = 0;
        HashSet<Integer> set = new HashSet<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Integer elemB : b) {
            if (!map.keySet().contains(elemB)) {
                count = (int) Arrays.stream(b).filter(e -> elemB == e).count();
                map.put(elemB, count);
            }
        }
        map.forEach((k, v) -> System.out.println("k = " + k + " : v = " + v));
        List<Boolean> booleanList = new ArrayList<>();
        map.forEach((k, v) -> {
            if (v > 1) {
                int matchedCounter = (int) Arrays.stream(a).filter(elemA -> k / elemA == elemA).count();
                if (matchedCounter == map.get(k))
                    booleanList.add(true);
                else booleanList.add(false);
            }
        });
        booleanList.forEach(System.out::println);
        int trueValues = (int) booleanList.stream().filter(bool -> bool == true).count();
        if (trueValues == booleanList.size())
            return true;
        else
            return false;
    }

    // =================================================================================================================
    public static boolean compBest1(int[] a, int[] b) {
        if ((a == null) || (b == null)) {
            return false;
        }
        int[] aa = Arrays.stream(a).map(n -> n * n).toArray();
        Arrays.sort(aa);
        Arrays.sort(b);
        return (Arrays.equals(aa, b));
    }
// =====================================================================================================================

    public static boolean compBest2(final int[] a, final int[] b) {
        return a != null && b != null && a.length == b.length && Arrays.equals(Arrays.stream(a).map(i -> i * i).sorted().toArray(), Arrays.stream(b).sorted().toArray());
    }

    // =====================================================================================================================
    public static boolean compBest3(int[] a, int[] b) {
        if (a == null || b == null || a.length != b.length) return false;

        int sumA = 0;
        int sumB = 0;

        for (int i = 0; i < a.length; i++) {
            sumA += Math.abs(a[i]);
            sumB += Math.sqrt(b[i]);
        }
        return sumA == sumB;
    }

    // =====================================================================================================================
    public static String formatDuration(int seconds) throws Exception {
        StringBuilder dateString = new StringBuilder();
        if (seconds >= 0) {
            int[] k = null;
            if (seconds >= 60 && seconds < 3600)
                k = minutesCalculation(seconds);
            else if (seconds >= 3600 && seconds < 86400)
                k = hoursCalculation(seconds);
            else if (seconds >= 86400 && seconds < 31536000)
                k = daysCalculation(seconds);
            else if (seconds >= 31536000)
                k = yearCalculation(seconds);
            try {
                switch (k.length) {
                    case 2:
                        if (k[0] != 0)
                            dateString.append(k[0]).append(" ").append(getMultipleTime(k[0], "minute"));
                        if (k[1] != 0)
                            dateString.append(" and ").append(k[1]).append(" ").append(getMultipleTime(k[1], "second"));
                        break;
                    case 3:
                        if (k[0] != 0)
                            dateString.append(k[0]).append(" ").append(getMultipleTime(k[0], "hour"));
                        if (k[1] != 0)
                            dateString.append(", ").append(k[1]).append(" ").append(getMultipleTime(k[1], "minute"));
                        if (k[2] != 0)
                            dateString.append(" and ").append(k[2]).append(" ").append(getMultipleTime(k[2], "second"));
                        break;
                    case 4:
                        if (k[0] != 0)
                            dateString.append(k[0]).append(" ").append(getMultipleTime(k[0], "day"));
                        if (k[1] != 0)
                            dateString.append(", ").append(k[1]).append(" ").append(getMultipleTime(k[1], "hour"));
                        if (k[2] != 0)
                            dateString.append(", ").append(k[2]).append(" ").append(getMultipleTime(k[2], "minute"));
                        if (k[3] != 0)
                            dateString.append(" and ").append(k[3]).append(" ").append(getMultipleTime(k[3], "second"));
                        break;
                    case 5:
                        if (k[0] != 0)
                            dateString.append(k[0]).append(" ").append(getMultipleTime(k[0], "year"));
                        if (k[1] != 0)
                            dateString.append(", ").append(k[1]).append(" ").append(getMultipleTime(k[1], "day"));
                        if (k[2] != 0)
                            dateString.append(", ").append(k[2]).append(" ").append(getMultipleTime(k[2], "hour"));
                        if (k[3] != 0)
                            dateString.append(", ").append(k[3]).append(" ").append(getMultipleTime(k[3], "minute"));
                        if (k[4] != 0)
                            dateString.append(" and ").append(k[4]).append(" ").append(getMultipleTime(k[4], "second"));
                        break;
                }
            } catch (NullPointerException e) {
                if (seconds == 0)
                    return "now";
                dateString.append(seconds).append(" ").append(getMultipleTime(seconds, "second"));
                return dateString.toString();
            }
            if (!dateString.toString().contains(" and ")) {
                String temp = substituteLastComma(dateString.toString());
                dateString.setLength(0);
                dateString.append(temp);
            }
        }
        return dateString.toString();
    }

    private static String getMultipleTime(int minute, String time) {
        return (minute == 1) ? time : time + "s";
    }

    private static int[] minutesCalculation(int seconds) {
        return new int[]{seconds / 60, seconds - (seconds / 60) * 60};
    }

    private static int[] hoursCalculation(int seconds) {
        int hours = seconds / 3600;
        int[] minAndSec = minutesCalculation(seconds - hours * 3600);
        return new int[]{hours, minAndSec[0], minAndSec[1]};
    }

    private static int[] daysCalculation(int seconds) {
        int days = seconds / 86400;
        int[] hoursMinAndSec = hoursCalculation(seconds - days * 86400);
        return new int[]{days, hoursMinAndSec[0], hoursMinAndSec[1], hoursMinAndSec[2]};
    }

    private static int[] yearCalculation(int seconds) {
        int year = seconds / (365 * 86400);
        int[] daysHoursMinAndSec = daysCalculation(seconds - year * 365 * 86400);
        return new int[]{year, daysHoursMinAndSec[0], daysHoursMinAndSec[1], daysHoursMinAndSec[2], daysHoursMinAndSec[3]};
    }

    private static String applyRegexFunction(String inputString) {
        String pattern = "[\\d* [a-zA-Z]*[,*] ]*\\d* [a-zA-Z]*, \\d+ [a-zA-Z]+";
        Pattern patternModel = Pattern.compile(pattern);
        Matcher matcher = patternModel.matcher(inputString);
        String outputString = "";
        if (matcher.find()) {
            System.out.println("Yeeeeeeeeeeeeep, matched");
            outputString = inputString.replaceAll(", ", " and ");
        }
        return outputString;
    }

    private static String substituteLastComma(String inputString) {
        int index = 0;
        int count = (int) inputString.chars().filter(k -> k == ',').count();
        if (count == 1) {
            inputString = applyRegexFunction(inputString);
        } else if (inputString.contains(",")) {
            index = inputString.lastIndexOf(",");
            inputString = inputString.substring(0, index) + " and " + inputString.substring(index + 2, inputString.length());

        }
        return inputString;
    }

// =====================================================================================================================

    public static String formatDurationBest1(int seconds) {
        return seconds == 0 ? "now" :
                Arrays.stream(
                        new String[]{
                                formatTime("year", (seconds / 31536000)),
                                formatTime("day", (seconds / 86400) % 365),
                                formatTime("hour", (seconds / 3600) % 24),
                                formatTime("minute", (seconds / 60) % 60),
                                formatTime("second", (seconds % 3600) % 60)})
                        .filter(e -> e != "")
                        .collect(Collectors.joining(", "))
                        .replaceAll(", (?!.+,)", " and ");
    }

    // =====================================================================================================================

    public static String formatTime(String s, int time) {
        return time == 0 ? "" : Integer.toString(time) + " " + s + (time == 1 ? "" : "s");
    }

    private static int S_PER_MIN = 60;
    private static int S_PER_HR = 60 * S_PER_MIN;
    private static int S_PER_DAY = 24 * S_PER_HR;
    private static int S_PER_YR = 365 * S_PER_DAY;

    private static String unit(int n, String kind) {
        return n == 0 ? "" : String.format("%d %s%s, ", n, kind, n == 1 ? "" : "s");
    }

    public static String formatDurationBest2(int s) {

        if (s == 0) return "now";

        int y, d, h, m;

        s -= (y = s / S_PER_YR) * S_PER_YR;
        s -= (d = s / S_PER_DAY) * S_PER_DAY;
        s -= (h = s / S_PER_HR) * S_PER_HR;
        s -= (m = s / S_PER_MIN) * S_PER_MIN;

        String ret = unit(y, "year") + unit(d, "day") + unit(h, "hour") + unit(m, "minute") + unit(s, "second");
        ret = ret.substring(0, ret.length() - 2); // remove trailing ', '
        return ret.lastIndexOf(",") == -1 ? ret : ret.replaceAll("(.+), (.+?)$", "$1 and $2"); // replace last , with "and"
    }

    // =====================================================================================================================
    public static String formatDurationBest3(int seconds) {
        String res = "";
        int[] units = new int[]{31536000, 86400, 3600, 60, 1};
        String[] labels = new String[]{"year", "day", "hour", "minute", "second"};

        if (seconds == 0) return "now";

        for (int i = 0; i < 5; i++) {
            if (seconds >= units[i]) {
                int q = seconds / units[i];
                seconds = seconds % units[i];
                res += (res.equals("") ? "" : (seconds == 0 ? " and " : ", "))
                        + q + " " + labels[i] + (q > 1 ? "s" : "");
            }
        }
        return res;
    }

// ========================Sveta's decision =================================================================================
//      private DataTable transformMap(Map<String, String> map) {
//        List<List<String>> dataAr = new ArrayList<>();
//        map.forEach((k, v) -> {
//            dataAr.add(Arrays.asList(k, map.get(k)));
//        });
//        return DataTable.create(dataAr);
//    }
// ==========================================================================================================================

//    static int solveBest1(String s) {
//        return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
//    }
//    public static int solve(String s) {
//        String[] splittedLine = s.split("[aeiou]");
//        for (String se : splittedLine) {
//            System.out.println("splittedLine = " + se);
//            int k = se.chars().map(cu -> {
//                cu = cu - 96;
//                System.out.println("cu = " + cu);
//                return cu;
//            }).sum();
//            System.out.println("k = " + k);
//        }
//        return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
//    }
//    public static int solveBest2(final String s) {
//        return Arrays.stream(s.split("[aeiou]+"))
//                .mapToInt(t -> t.chars().sum() - t.length() * 96)
//                .max()
//                .getAsInt();
//    }
//
//    public static int solveBest3(final String s) {
//        String s2 = "aeiou";
//        int sum = 0, x = 0;
//        for (int i = 0; i < s.length(); i++) {
//            if (s2.contains("" + s.charAt(i))) {
//                sum = Math.max(sum, x);
//                x = 0;
//            } else {
//                x += (int) s.charAt(i) - 96;
//            }
//        }
//        sum = Math.max(sum, x);
//        return sum;
//    }

}