package com.infostroy.analyzer.tasks;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DuplicatesTest {

    @Test
    public void testGetResult() {
	Duplicates duplicates = new Duplicates("input3.txt");
	String rezult = duplicates.getResultByImpl();
	String expected = "PAM" + System.lineSeparator() + "WOLLA" + System.lineSeparator() + "STNEMUGRA"
	        + System.lineSeparator();
	assertEquals(expected, rezult);
    }
}
