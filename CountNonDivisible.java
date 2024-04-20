import java.util.*;
import java.util.stream.*;

public class CountNonDivisible {

    public static void main(String [] args) {
        System.out.printf("Hello Count Non-divisible solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java CountNonDivisible%n");
            return;
        }
        int [] A = new int [] {3, 1, 2, 3, 6};
        CountNonDivisible countNonDivisible = new CountNonDivisible();
        int [] result = countNonDivisible.solution(A);
        System.out.printf("The non-divisor counts are%n");
        for (int i = 0; i < A.length; i++) {
            System.out.printf("value: %d, count non-divisors: %d%n", A[i], result[i]);
        }
    }

    public int [] solution(int [] A) {

        return new int [A.length];

    }

}
