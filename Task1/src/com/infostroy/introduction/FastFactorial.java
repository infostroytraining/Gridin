package com.infostroy.introduction;

public class FastFactorial {

    private static long prodTree(long left, long right) {
        if (left > right) {
            return 1L;
        }
        if (left == right) {
            return left;
        }
        if (right - left == 1) {
            return (left * right);
        }
        long m = (left + right) / 2;
        return prodTree(left, m) * prodTree(m + 1, right);
    }

    public static long factorial(long number) {
        if (number < 0) {
            return 0;
        }
        if (number == 0) {
            return 1;
        }
        if (number == 1 || number == 2) {
            return number;
        }
        return prodTree(2, number);
    }
}
