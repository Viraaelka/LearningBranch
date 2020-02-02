import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        long number = 1234678;
        Stream.of(digitize2(number)).forEach(System.out::println);

    }

    public static int[] digitize(long n) {
        String nValue = String.valueOf(n);
        int size = nValue.length();
        int[] temp = new int[size];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = Integer.parseInt(nValue.substring(size - i - 1, size - i));
        }
        return temp;
    }
    public static int[] digitize2(long n){
        String nValue = String.valueOf(n);
        int size = nValue.length();
        int [] temp = new int[size];
        for(int i = 0; i < size; i++){
            temp[i] = (int) (nValue.charAt(size-i-1) - 48);
        }
        for(int o : temp){
            System.out.println(o);
        }
        return temp;
    }
}
