package com.infostroy.analyzer.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FrequencyTest {

    @Test
    public void testGetResult() {
	Frequency frequency = new Frequency("input2.txt");
	String rezult = frequency.getResultByImpl();
	String expected = "good -> 23" + System.lineSeparator() + "allow -> 2" + System.lineSeparator();
	assertEquals(expected, rezult);
    }
}
