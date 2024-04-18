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
        int [] factors = factorization.solution(N);
        System.out.printf("The factors from 1 to %d are %s%n", N, Arrays.toString(factors));
    }

    public int [] solution(int N) {

        return new int [0];

    }

}
