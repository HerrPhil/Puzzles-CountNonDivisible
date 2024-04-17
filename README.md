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
- for factorization [here](https://en.wikipedia.org/wiki/Factorization); we are interested in interested in integer factorization

(more to come...)
