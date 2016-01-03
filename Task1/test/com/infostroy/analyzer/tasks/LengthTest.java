package com.infostroy.analyzer.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LengthTest {

    @Test
    public void testGetResult() {
	Length length = new Length("input.txt");
	String rezult = length.getResultByImpl();
	String expected = "battle -> 6" + System.lineSeparator() + "map -> 3" + System.lineSeparator() + "a -> 1"
	        + System.lineSeparator();
	assertEquals(expected, rezult);
    }
}
