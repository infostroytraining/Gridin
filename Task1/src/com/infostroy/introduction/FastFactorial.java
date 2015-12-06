package com.infostroy.introduction;

public class FastFactorial {

    private static long prodTree(long l, long r) {
        if (l > r) {
            return 1L;
        }
        if (l == r) {
            return l;
        }
        if (r - l == 1) {
            return (l * r);
        }
        long m = (l + r) / 2;
        return prodTree(l, m) * prodTree(m + 1, r);
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
