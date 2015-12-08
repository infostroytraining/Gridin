package com.infostroy.analyzer;

import java.util.Scanner;

import com.beust.jcommander.JCommander;
import com.infostroy.analyzer.tasks.Duplicates;
import com.infostroy.analyzer.tasks.Frequency;
import com.infostroy.analyzer.tasks.Length;

public class Application {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			String input = "";
			Commander jct = new Commander();
			Length length = new Length();
			Frequency frequency = new Frequency();
			Duplicates duplicates = new Duplicates();
			while (true) {
				String[] inputCommand = sc.nextLine().split(" ");
				JCommander jCommander = new JCommander(jct, inputCommand);
				String task = jct.getTask();
				boolean help = jct.getHelp();
				input = jct.getInput();
				if (task != null) {
					switch (task) {
					case "length":
						long start = System.currentTimeMillis();
						System.out.println(length.getResult(input));
						long end = System.currentTimeMillis();
						System.out.println("elapsed time: " + (end - start) + " millis");
						break;
					case "duplicates":
						long start2 = System.currentTimeMillis();
						System.out.println(duplicates.getResult(input));
						long end2 = System.currentTimeMillis();
						System.out.println("elapsed time: " + (end2 - start2) + " millis");
						break;
					case "frequency":
						long start3 = System.currentTimeMillis();
						System.out.println(frequency.getResult(input));
						long end3 = System.currentTimeMillis();
						System.out.println("elapsed time: " + (end3 - start3) + " millis");
						break;
					default:
						break;
					}
				} else if (help) {
					jCommander.usage();
				}
			}
		}
	}
}
