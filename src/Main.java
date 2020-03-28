import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        long number = 1234678;
        //      Stream.of(digitize2(number)).forEach(System.out::println);
        solve("striopjkoly");
    }

    public static String formatDuration(int seconds) {
        // your code goes here
    }
// ==================================================================================================================

    class ConsonantValue {
        static int solve(String s) {
            return of(s.split("[aeiou]")).mapToInt(con -> con.chars().map(c -> c - 96).sum()).max().orElse(0);
        }

        public class ConsonantValue {
            public static int solve(final String s) {
                return Arrays.stream(s.split("[aeiou]+"))
                        .mapToInt(t -> t.chars().sum() - t.length() * 96)
                        .max()
                        .getAsInt();
            }
        }

        public static int solve(final String s) {
            String s2 = "aeiou";
            int sum = 0, x = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s2.contains("" + s.charAt(i))) {
                    sum = Math.max(sum, x);
                    x = 0;
                } else {
                    x += (int) s.charAt(i) - 96;
                }
            }
            sum = Math.max(sum, x);
            return sum;
        }

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
                temp[i] = (int) (nValue.charAt(size - i - 1) - 48);
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
    }
