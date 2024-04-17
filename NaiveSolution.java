import java.util.*;

public class NaiveSolution {

    public static void main(String [] args) {
        System.out.printf("Hello naive solution%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java NaiveSolution%n");
            return;
        }
        // N is the number of values and may be any value between [1..50,000]
        int [] A = new int [] {3, 1, 2, 3, 6}; // N values may be any value between [1..2 * N]
        NaiveSolution nonDivisor = new NaiveSolution();
        int [] nonDivisorCounts = nonDivisor.solution(A);
        System.out.printf("The number of non-divisors is %s%n", Arrays.toString(nonDivisorCounts));
    }

    public int [] solution(int [] A) {
        int iterations = 0;
        int checks = 0;
        int calculations = 0;
        System.out.printf("input array is %s%n", Arrays.toString(A));
        int N = A.length;
        int [] counts = new int [N];
        Arrays.fill(counts, 0);
        for (int i = 0; i < N; i++) {

            iterations++;

            int dividend = A[i];

            for (int j = 0; j < N; j++) {

                iterations++;

                checks++;
                if ( i == j) {

                    continue; // skip comparison of dividend with dividend

                }

                int divisor = A[j];

                calculations++;
                int remainder = dividend % divisor;

                checks++;
                if (remainder != 0) {

                    counts[i]++; // must be non-divisor

                }

            }

        }

        System.out.printf("Performance: iterations = %d, checks = %d, calculations = %d%n", iterations, checks, calculations);

        return counts;
    }

}
