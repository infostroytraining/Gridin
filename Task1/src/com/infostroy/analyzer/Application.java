package com.infostroy.analyzer;

import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.infostroy.analyzer.tasks.TaskFactory;

public class Application {

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(System.in)) {
	    String input = "";
	    Commander jct = new Commander();
	    while (true) {
		String[] inputCommand = sc.nextLine().split(" ");
		JCommander jCommander = new JCommander(jct, inputCommand);
		input = jct.getInput();
		if (jct.getHelp()) {
		    jCommander.usage();
		} else {
		    long start = System.currentTimeMillis();
		    System.out.println(
		            TaskFactory.getTaskByName(jct.getTask()).getResultUsingJava8(input, jct.getParallel()));
		    long end = System.currentTimeMillis();
		    System.out.println("elapsed time: " + (end - start) + " millis");
		}
	    }
	}
    }
}
