# Puzzles-CountNonDivisible

Calculate the number of elements of an array that are not divisors of each element.

# Description

You are given an array A of N integers.

For each number A[i] such that 0 <= i < N, we want to count the number of elements
of the array that are not divisors of A[i]. We say that these elements are non-divisors.

For example, consider N = 5 and A such that:

```
A[0] = 3
A[1] = 1
A[2] = 2
A[3] = 3
A[4] = 6
```

For the following elements:

- A[0] = 3, the non-divisors are: 2, 6
- A[1] = 1, the non-divisors are: 3, 2, 3, 6
- A[2] = 2, the non-divisors are: 3, 3, 6
- A[3] = 3, the non-divisors are: 2, 6
- A[4] = 6, the non-divisors are: there aren't any non-divisors

Write a function:

```
class Solution { public int[] solution(int [] A); }
```

that, given an array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.

Result array should be returned as an array of integers.

For example, given:

```
A[0] = 3
A[1] = 1
A[2] = 2
A[3] = 3
A[4] = 6
```

the function should return [2, 4, 3, 2, 0], as explained above.

Write an efficient algorithm for the following assumptions:

- N is an integer within the range [1..50,000];
- each element of array A is an integer within the range [1..2 * N].

# Solutions

## Naive Solution

As always, loop through all values and compare to others in the array A for non-divisor;
this is indicated by a remainder <> 0.

This is known to be O(n<sup>2</sup>) performance.

## Background

The recommended solution includes reference to the algorithm known as the Seive of Eratosthenes.

The Codility lesson task includes a link to a document about the algorithm [here](https://codility.com/media/train/9-Sieve.pdf).

There are more details on wikipedia:

- for the Seive of Eratosthenes [here](https://en.wikipedia.org/wiki/Sieve_of_Eratosthenes) 
- for factorization [here](https://en.wikipedia.org/wiki/Factorization); we are interested in integer factorization

## Sieve of Eratosthenes

The point of discussing the Sieve of Eratosthenes is to prepare for a factorization example.

The underlying, efficient solution for this lesson task is to utilize factorization.

Let us proceed with the theory.

### Explanation of Sieve of Erastosthenes (see the codility PDF)

The Sieve of Eratosthenes is a very simple and popular technique for finding all the prime numbers
in the range from 2 to a given number *n*. The algorithm takes its name from the process of sieving -
in a simple way we remove multiples of consecutive numbers.

Initially, we have the set of all the numbers {2, 3, ..., n}. At each step we choose the
smallest number in the set and remove all its multiples. Notice that every composite number
has a divisor of at most √n. In particular, it has a divisor which is a prime number. It
is sufficient to remove only multiples of prime numbers not exceeding √n. In this way, all
composite numbers will be removed.

For an example, suppose n = 17.

First, we remove the multiples of the smallest element in the set, which is 2.
The element remaining in the set is 3, and we also remove its multiples, and so on.

```
  2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17
  2 3   5   7   9    11    13    15    17
  2 3   5   7        11    13          17

  there are no elements to remove for 5; stop algorithm 5 is greater than √n
```

The above algorithm can be slightly improved. Notice that we need not cross out multiples
of *i* which are less than *i*<sup>2</sup>. Such multiples are of the form k*i, where k < i.
These have already been removed by one of the prime divisors of *k*. After this improvement,
we obtain the following implementation:

```
def sieve(n):
    sieve = [True] * (n + 1)
    sieve[0] = sieve[1] = False
    i = 2
    while (i * i <= n):
        if (sieve[i]):
            k = i * i
            while (k <= n):
                sieve[k] = False
                k += i
        i += 1
    return sieve
```

Let's analyze the time complexity of the above algorithm. For each prime number *p*<sub>j</sub> <= √n
we cross out at most *n* / *p*<sub>j</sub>, so we get the following numbrs of operations:

n/2 + n/3 + n/5 + ...
 = sum(all pj less than or equal to n) *n* / *p*<sub>j</sub>
 = n * sum(all pj less than or equal to n) 1 / *p*<sub>j</sub>

The sum of the reciprocals of the primes *p*<sub>j</sub> <= *n* equals asymtotically *O*(log log *n*).
So the overall time complexity of this algorithm is *O*(*n* log log *n*). The proof is not trivial,
and is beyond the scope of this article.

### Explanation of applying Sieve of Eratosthenes to Prime Factorization

Factorization is the process of decomposition into prime factors. More precisely, for a given
number *x* we want to find primes *p*<sub>1</sub>, *p*<sub>2</sub>, ..., *p*<sub>k</sub> whose product equals *x*.

Use of the sieve enables fast factorization. Let's modify the sieve algorithm slightly.
For every crossed number we will remember the smallest prime that divides this number.

```
def arrayF(n):
    F = [0] * (n + 1)
    sieve[0] = sieve[1] = False
    i = 2
    while (i * i <= n):
        if (F[i] == 0):
            k = i * i
            while (k <= n):
                if (F[k] == 0):
                    F[k] = i // smallest prime
                k += i
        i += 1
    return F
```

For example, take an array *F* with a value *n* = 20:

```
smallest primes:   0  0  2  0  2  0  2  3  2  0  2  0  2  3  2  0  2  0  2
values (indices):  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20
```

With this approach we can factorize numbers vary quickly. If we know that one of the prime
factors of *x* is *p*, then all the prime factors of *x* are *p* plus the decomposition of *x* / *p*.


Factorization of x in *O*(log *x*)

```
def factorization(x, F):
    primeFactors = []
    while (F[x] > 0):
        primeFactors += [F[x]]
        x /= F[x]
    primeFactors += x
    return primeFactors
```

Number *x* cannot have more than log *x* prime factors, because every prime factor is >= 2.
Factorization by the above method works in *O*(log *x*) time complexity. Note that consecutive
factors will be presented in non-decreasing order.

## Recommended Solution

For the recommended solution, the abstract thinking required is to realize that, instead of prime factorization,
the solution should do integer factorization.

### Integer Factorization

By the fundamental theorem of arithmetic, every integer greater than 1 has a unique (up to the order of factors)
factorization into prime numbers, which are those integers which cannot be further factorized
into the product of integers greater than one.

For computing the factorization of an integer *n*, one needs an algorithm for find a divisor *q* of *n*
or deciding that *n* is prime. When such a divisor is found, the repeated application of this algorithm
to the factors *q* and *n* / *q* gives eventually the complete factorization of *n*.

For finding a divisor *q* of *n*, if any, it suffices to test all values of q such that
1 < *q* and *q*<sup>2</sup>. In fact, if *r* is a divisor of *n* such that r<sup>2</sup> > *n*,
then *q* = *n* / *r* is a divisor of *n* such that *q*<sup>2</sup> <= *n*.

If one tests the values of *q* in increasing order, the first divisor that is found is necessarily a prime number,
and the *cofactor* *r* = *n* / *q* cannot have any divisor smaller than *q*. For getting the complete factorization,
it suffices thus to continue the algorithm by searching a divisor of *r* that is not smaller than *q*
and not greater than √*r*.

There is no need to test all values of *q* for applying the method. In principle, it suffices to test only prime divisors.
This needs to have a table of prime numbers that may be generated for example with the sieve of Eratosthenes.
As the method of factorization does essentially the same work as the sieve of Eratosthenes, it is generally
more efficient to test for a divisor only those numbers for which it is not immediately clear whether they are prime or not.
Typically one may proceed by testing 2, 3, 5 and the numbers >5, whose last digit is 1, 3, 7, 9
and the sum of the digits is not a multiple of 3.

This method works well for factoring small integers, but is inefficient for larger integers.
For example, Pierre d Fermat was unable to discover that the 6th Fermat number:

1 + 2<sup>2</sup><sup>5</sup> = 1 + 2<sup>32</sup> = 4, 294, 967, 297

is not a prime number. In fact, applying the above method would require more than 10,000 divisions,
for a number that has 10 decimal digits.

There are more efficient factoring algorithms. However they remain relatively inefficient, as, with the
present state of the art, one cannot factorize, even with more powerful computers, a number of 500 decimal digits
that is the product of two randomly chosen prime numbers. This ensure the security of the RSA cryptosystem,
which is widely used to secure internet communication.
