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