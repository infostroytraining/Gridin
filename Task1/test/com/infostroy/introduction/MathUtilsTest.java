package com.infostroy.introduction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.infostroy.introduction.MathUtils;

public class MathUtilsTest {

    @Test
    public void testGetGreatestCommonDivider() {
        assertEquals(1, new MathUtils().getGreatestCommonDivider(2, 3));
    }

    @Test
    public void testGetSumOfDigits() {
        assertEquals(45, new MathUtils().getSumOfDigits(123456789));
    }

    @Test
    public void testIsPrime() {
        assertTrue(new MathUtils().isPrime(2));
    }

    @Test
    public void testGetSumOfRow() {
        System.out.println(new MathUtils().getSumOfRow(5));
    }

    @Test
    public void testGetFibonacciSeries() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetPrimeSeries() {
        fail("Not yet implemented");
    }

}
