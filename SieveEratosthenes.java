import java.util.*;
import java.util.stream.*;

public class SieveEratosthenes {

    public static void main(String [] args) {
        System.out.printf("Hello Sieve of Eratosthenes solution #1%n");
        if (args != null && args.length == 1 && args[0].toLowerCase().equals("-usage")) {
            System.out.printf("java SieveEratosthenes%n");
            return;
        }
        // A is an array of sequential integers increasing by 1 from 2
        int N = 17; // N value, any size
        SieveEratosthenes sieve = new SieveEratosthenes();
        int [] primes = sieve.solution(N);
        System.out.printf("The prime numbers from 1 to %d are %s%n", N, Arrays.toString(primes));
    }

    public int [] solution(int N) {
        boolean [] sieve = new boolean [N + 1]; // one extra for good measure? TBD
        Arrays.fill(sieve, true);
        sieve[0] = false;
        sieve[1] = false;
        int i = 2;

        // Leverage the observation that every composite number has a divisor of at most square root of N.
        // The composite number has a divisor which is a prime number.
        // It is sufficient to remove only multiples of prime numbers not exceeding square root of N.
        while (i * i <= N) { // computational form of stop condition of i < square root of N

            if (sieve[i]) {

                int k = i * i;
                while (k <= N) {

                    sieve[k] = false;
                    k += i;

                }

            }

            System.out.printf("After %d iteration the sieve is%n", i);
            for (int check = 0; check < sieve.length; check++) {
                System.out.printf("  index(%d) value(%b)%n", check, sieve[check]);
            }

            i++;

            System.out.printf("Next while check on i * i = %d and N = %d%n", i * i, N);

        }

        Stream.Builder<Integer> builder = Stream.builder();
        for (int p = 0; p < sieve.length; p++) {
    
            if (sieve[p]) {

                builder.add(p);
            }
            

        }

        return builder.build().mapToInt(x -> x).toArray();
    }

}
