package com.infostroy.analyzer;

import java.util.Objects;
import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.infostroy.analyzer.tasks.Analyzer;
import com.infostroy.analyzer.tasks.TaskFactory;
import com.infostroy.analyzer.tasks.TimeredTask;

public class Application {

    public static void main(String[] args) {
	try (Scanner sc = new Scanner(System.in)) {
	    String input = "";
	    Commander jct = new Commander();
	    while (true) {
		String[] inputCommand = sc.nextLine().split(" ");
		JCommander jCommander = new JCommander(jct, inputCommand);
		if (jct.getExit()) {
		    break;
		}
		input = jct.getInput();
		if (jct.getHelp()) {
		    jCommander.usage();
		} else {
		    Analyzer task = TaskFactory.getTask(jct.getTask(), input, jct.getParallel());
		    if (Objects.isNull(task)) {
			System.out.println("Task not found. Try again.");
		    } else {
			TimeredTask.run(task::getResultUsingJava8);
		    }
		}
	    }
	}
    }
}
