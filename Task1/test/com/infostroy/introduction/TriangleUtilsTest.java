package com.infostroy.introduction;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.infostroy.introduction.TriangleUtils;

public class TriangleUtilsTest {

    @Test
    public void testIsTriangle() {
        assertTrue(new TriangleUtils().isTriangle(5, 5, 6));
        assertFalse(new TriangleUtils().isTriangle(1, 2, 1));
    }

    @Test
    public void testGetTriangleArea() {
        assertEquals(12, new TriangleUtils().getTriangleArea(5, 5, 6), 0.1);
        assertEquals(0, new TriangleUtils().getTriangleArea(1, 2, 1), 0.1);
    }

}
