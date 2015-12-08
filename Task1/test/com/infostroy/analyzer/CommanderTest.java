package com.infostroy.analyzer;

import org.junit.Assert;
import org.junit.Test;

import com.beust.jcommander.JCommander;

public class CommanderTest {

	@Test
	public void testCommander() {
		Commander jct = new Commander();
		String[] argv = { "-i", "input.txt", "-t", "length" };
		new JCommander(jct, argv);
		Assert.assertEquals("input.txt", jct.getInput());
		Assert.assertEquals("length", jct.getTask());
	}
}
