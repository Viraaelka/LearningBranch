public class Main {
    public static void main(String[] args) throws Exception {
        long number = 1234678;
        System.out.println(formatDuration(3660));
    }

    public static String formatDuration(int seconds) throws Exception {
        StringBuilder dateString = new StringBuilder();
        if (seconds > 0) {
            int[] k = null;
            if (seconds >= 60 && seconds < 3600)
                k = minutesCalculation(seconds);
            else if (seconds >= 3600 && seconds < 86400)
                k = hoursCalculation(seconds);
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
                }
            } catch (NullPointerException e) {
                if (seconds == 0)
                    return "now";
                dateString.append(seconds).append(" ").append(getMultipleTime(seconds, "second"));
            }
//        String ku = "\\d+[a-zA-Z]+,\\d+[a-zA-Z]+";
//        if (dateString.toString().matches(ku))
//            System.out.println("yeep");
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
    private static int[] daysCalculation(int seconds){
        int days = seconds / ;
    }
    private static int[] yearCalculation(int seconds){
        int year = seconds /
    }
// ==================================================================================================================

//    class ConsonantValue {
//        static int solve(String s) {
//            return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
//        }
//
//        public class ConsonantValue {
//            public static int solve(final String s) {
//                return Arrays.stream(s.split("[aeiou]+"))
//                        .mapToInt(t -> t.chars().sum() - t.length() * 96)
//                        .max()
//                        .getAsInt();
//            }
//        }
//
//        public static int solve(final String s) {
//            String s2 = "aeiou";
//            int sum = 0, x = 0;
//            for (int i = 0; i < s.length(); i++) {
//                if (s2.contains("" + s.charAt(i))) {
//                    sum = Math.max(sum, x);
//                    x = 0;
//                } else {
//                    x += (int) s.charAt(i) - 96;
//                }
//            }
//            sum = Math.max(sum, x);
//            return sum;
//        }

    public static int solve(final String s) {
        String alPart = " ";
        String newS = s.split(alPart)[0];
        int max = 0;
        int currentValue = 0;
        do {
            alPart = getAlphaPart(newS, s).split(" - ")[0].trim();
            max = currentValue;
            currentValue = getValue(alPart);
            currentValue = Math.max(max, currentValue);
            if (alPart.length() == newS.length())
                break;
            newS = newS.substring(alPart.length() + 1);
        } while (newS.length() != 0);
        return currentValue;
    }

    private static String getAlphaPart(String newS, String s) {
        int count = 0;
        String alPart = "";
        Character[] alphabet = {'j', 'x', 'w', 'y', 'b', 'c', 'd', 'f', 'g', 'h', 'q', 'p', 'r', 's', 't', 'v', 'w', 'k', 'l', 'm', 'n', 'z'};
        for (Character letter : newS.toCharArray()) {
            for (Character al : alphabet) {
                if (letter.equals(al)) {
                    alPart += letter;
                    break;
                } else
                    count++;
            }
            if (count == alphabet.length)
                break;
            count = 0;
        }
        return alPart;
    }

    private static int getValue(String alPart) {
        int currentValue = 0;
        String alphabetLine = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
        for (Character ch : alPart.toCharArray()) {
            currentValue += alphabetLine.indexOf(ch.toString()) + 1;
        }
        return currentValue;
    }
// ==================================================================================================================

    public static int[] digitize(long n) {
        String nValue = String.valueOf(n);
        int size = nValue.length();
        int[] temp = new int[size];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = Integer.parseInt(nValue.substring(size - i - 1, size - i));
        }
        return temp;
    }

    public static int[] digitize2(long n) {
        String nValue = String.valueOf(n);
        int size = nValue.length();
        int[] temp = new int[size];
        for (int i = 0; i < size; i++) {
            temp[i] = nValue.charAt(size - i - 1) - 48;
        }
        for (int o : temp) {
            System.out.println(o);
        }
        return temp;
    }

    public static String balancedNumber(long n) {
        String nValue = String.valueOf(n);
        int size = nValue.length();
        int halfSize = nValue.length() / 2;
        int resultRight = 0, resultLeft = 0;
        if (nValue.length() <= 2)
            return "Balanced";
        else {
            if (nValue.length() % 2 == 0) {
                for (int i = 0; i < halfSize - 1; i++) {
                    resultRight += nValue.charAt(i) - 48;
                }
                System.out.println("resultRight = " + resultRight);
            } else {
                for (int i = 0; i < halfSize; i++) {
                    resultRight += nValue.charAt(i) - 48;
                }
                System.out.println("resultRight = " + resultRight);
            }
            for (int i = halfSize + 1; i < size; i++) {
                resultLeft += nValue.charAt(i) - 48;
            }
            System.out.println("resultLeft = " + resultLeft);
            if (resultLeft == resultRight)
                return "Balanced";
            else return "Not balanced";
        }
    }

    public static int[] digitize3(long n) {
        return new StringBuilder().append(n)
                .reverse()
                .chars()
                .map(Character::getNumericValue)
                .toArray();
    }

    //    public static int[] digitize4(long n) {
//        Stack<Integer> digits = new Stack<>();
//        while (n > 0) {
//            digits.push((int) (n % 10));
//            n /= 10;
//        }
//
//        return digits.stream().mapToInt(Integer::intValue).toArray();
//    }

    public static int[] digitize5(long n) {
        int[] out = new int[(int) Math.log10(n) + 1];
        int index = 0;
        while (n != 0) {
            out[index] = (int) (n % 10);
            n /= 10;
            index++;
        }
        return out;
    }
    public static String decompose(String nrStr, String drStr) {
        stringBuilder.setLength(0);
        System.out.println("n= " + nrStr + " : d = " + drStr);
        int nr = Integer.parseInt(nrStr);
        int dr = Integer.parseInt(drStr);
        if (nr / dr > 0 && nr % dr == 0)
            return stringBuilder.append("[").append(nr / dr).append("]").toString();
        return nr == 0 || dr == 0 ? "[]" :
                new StringBuilder().append("[").append(deco(nr, dr).replaceAll(", (?!\\d+)", "")).append("]").toString();
    }

    public static String getFirstPart(int nr, int dr) {
        return new StringBuilder().append("1/").append(getRoundedValueToUpperValue(nr, dr)).append(", ").toString();
    }

    public static String getSecondPartOfFormula(int a, int b) {
        return String.valueOf(getNegativeMod(a, -b)) + " - " + String.valueOf(b * getRoundedValueToUpperValue(a, b));
    }

    public static StringBuilder stringBuilder = new StringBuilder();

    public static String deco(int nr, int dr) {
        String divideNumberString = "";
        stringBuilder.append(getFirstPart(nr, dr));
        String kep = getSecondPartOfFormula(nr, dr);
        if (!kep.equals("")) {
            int nr2 = Integer.parseInt(kep.split(" - ")[0]);
            int dr2 = Integer.parseInt(kep.split(" - ")[1]);
            divideNumberString = divideNumber(nr2, dr2);
            if (!divideNumberString.equals("")) {
                System.out.println(divideNumberString);
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
        while (c < 10) {
            if (nr % c == 0 && dr % c == 0)
                return String.valueOf(nr / c) + " - " + String.valueOf(dr / c);
            c++;
        }
        return "";
    }

    public static int getRoundedValueToUpperValue(int nr, int dr) {
        return (int) Math.ceil((1.0 * dr) / nr);
    }

    public static int getNegativeMod(int b, int a) {
        int c = 1;
        while (true) {
            if (Math.abs(b) * c >= Math.abs(a)) {
                if (b > 0)
                    c = (b * c) + a;
                else
                    c = Math.abs((b * c) - a);
                break;
            }
            c++;
        }
        return c;
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
