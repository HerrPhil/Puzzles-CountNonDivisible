import java.util.*;
import java.util.stream.*;

public class Factorization {

    public static void main(String [] args) {
        System.out.printf("Hello Factorizaton solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java Factorization%n");
            return;
        }
        // A is an array of sequential integers increasing by 1 from 2
        int N = 20; // N value, any size
        Factorization factorization = new Factorization();
        int [][] factors = factorization.solution(N);
        System.out.printf("The factors from 1 to %d are%n", N);
        for (int i = 0; i < factors.length; i++) {
            System.out.printf("x: %d, factors:", i);
            int [] values = factors[i];
            for (int j = 0; j < values.length; j++) {
                int value = values[j];
                System.out.printf(" %d", value);
            }
            System.out.printf("%n");
        }
    }

    public int [][] solution(int N) {

        int [] smallestPrimeFactors = getSmallestFactorizationValues(N);

        return getFactorization(N, smallestPrimeFactors);

    }

    /**
     * Use of the sieve enables fast factorization.
     * Let us modify the sieve algorithm slightly.
     * For every crossed number, we will remember the smallest prime that divides this number.
     */
    private int [] getSmallestFactorizationValues(int N) {

        int [] smallestPrimeFactors = new int [N + 1];
        Arrays.fill(smallestPrimeFactors, 0);
        int i = 2;

        while (i * i <= N) { // computational form of stop condition of i < square root of N

            int k = i * i;

            while (k <= N) {

                if (smallestPrimeFactors[k] == 0) {

                    smallestPrimeFactors[k] = i;

                }

                k += i;

            }

            i += 1;

        }

        System.out.printf("Factors of crossed numbers%n");
        for (int c = 0; c < N; c++) {

            System.out.printf("number: %d, smallest prime factor: %d", c, smallestPrimeFactors[c]);
            if (smallestPrimeFactors[c] == 0) {

                System.out.printf("%n");

            } else {

                System.out.printf(" crossed%n");

            }

        }

        return smallestPrimeFactors;

    }

    private int [][] getFactorization(int N, int [] smallestPrimeFactors) {

        int [][] allFactorization = new int[N + 1][];

        for (int i = 0; i <= N; i++) {

            allFactorization[i] = factorization(i, smallestPrimeFactors);

        }

        return allFactorization;

    }

    private int [] factorization(int i, int [] smallestPrimeFactors) {

        int valueToDecompose = i;
        Stream.Builder<Integer> builder = Stream.builder();

        while (smallestPrimeFactors[valueToDecompose] > 0) {

            builder.add(smallestPrimeFactors[valueToDecompose]);
            valueToDecompose /= smallestPrimeFactors[valueToDecompose];

        }

        builder.add(valueToDecompose);

        return builder.build().mapToInt(x -> x).toArray();

    }

}
