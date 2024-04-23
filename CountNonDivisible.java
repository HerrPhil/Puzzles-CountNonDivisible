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
            System.out.printf("count non-divisors: %d%n", result[i]);
        }
    }

    public int [] solution(int [] A) {

        System.out.printf("input %s%n", Arrays.toString(A));

        // Create a bucket for a bucket sort.
        // It works by distributing the elements of an array into a number of buckets

        // The value of the 1st dimension of the bucket is simply the requirement in the lesson task.
        // One is added to N * 2 because the array is zero-based.
        int [][] bucket = new int [A.length * 2 + 1][2];

        // bucket sorting
        // for every value in array A
        for (int a : A) {

            // count this occurrence of a
            bucket[a][0]++;
            // initialize number of divisors as -1 to mean not identified
            bucket[a][1] = -1;

        }

        // bucket viewing
        System.out.printf("bucket viewing%n");
        for (int i = 0; i < bucket.length; i++) {

            System.out.printf("(%d, %d) value is %d, ", i, 0, bucket[i][0]);
            System.out.printf("(%d, %d) value is %d%n", i, 1, bucket[i][1]);

        }

        // for every value in array A
        for (int a : A) {

            System.out.printf("processing a = %d%n", a);

            // when this value of a has no divisors
            if (bucket[a][1] == -1) {

                // then,

                // initialize the divisor count to zero
                bucket[a][1] = 0;

                // apply the following algorithm to count all divisors of this value of a

                // checking q of n where q^2 <= n is integer factorization 101
                // for every factor j where j^2 <= a, starting with 1
                for (int j = 1; j * j <= a; j++) {

                    System.out.printf("analyzing j = %d%n", j);
    
                    // check whether j is a divisor of a; the remainder is zero
                    boolean isDivisorOfa = a % j == 0;
                    System.out.printf("%s%n", isDivisorOfa ? "j is divisor of a" : "j is not divisor of a");

                    // check whether j has not reached the stop condition
                    // corallary to j * j < a
                    boolean jNotSquareRootOfa = a / j != j;
                    System.out.printf("%s%n", jNotSquareRootOfa ? "j is not square root of a" : "j is square root of a");

                    // corrallary to j * j == a; value of a / j and j point to same element in bucket
                    boolean jIsSquareRootOfa = a / j == j;
                    System.out.printf("%s%n", jIsSquareRootOfa ? "** j is square root of a" : "** j is not square root of a");
                    
                    if (isDivisorOfa && jNotSquareRootOfa) {

                        // factorization of j potential divisor of a - add occurrences of j to divisors of a
                        System.out.printf("a value %d has %d occurrences%n", j, bucket[j][0]);
                        bucket[a][1] += bucket[j][0];
                        System.out.printf("a value %d now as %d divisors%n", a, bucket[a][1]);

                        // factorization of a / j potential divisor of a - add occurrences of a / j to divisors of a
                        System.out.printf("a value %d has %d occurrences%n", a / j, bucket[a / j][0]);
                        bucket[a][1] += bucket[a / j][0];
                        System.out.printf("a value %d now as %d divisors%n", a, bucket[a][1]);
 
                    } else if (isDivisorOfa && jIsSquareRootOfa) {

                        // factorization of j potential divisor of a - add occurences of j to divisors of a
                        System.out.printf("a value %d has %d occurrences%n", j, bucket[j][0]);
                        bucket[a][1] += bucket[j][0];
                        System.out.printf("a value %d now as %d divisors%n", a, bucket[a][1]);

                    }

                }

            } else {

                System.out.printf("value of a = %d has divisors%n", a);

            }
        } // end for

        // Recycle the A array for the non-divisor values.
        // Using array A to set results for not arranging new space.
        for (int i = 0; i < A.length; i++) {

            // The difference between the count of all values of A
            // and the number of divisors for a given value of A
            // is the number of non-divisors of a given value of A
            A[i] = A.length - bucket[A[i]][1];

        }


        return A;

    }

}
