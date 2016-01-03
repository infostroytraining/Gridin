package com.infostroy.analyzer.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Duplicates extends Analyze {

    public Duplicates(String fileName) {
	this(fileName, false);
    }

    public Duplicates(String fileName, boolean parallel) {
	this.fileName = fileName;
	this.parallelMode = parallel;
    }

    public String getResultByImpl() {
	StringBuilder sb = new StringBuilder();
	Map<String, Integer> result = new Frequency(fileName).getFrequencyFromInputFile();
	List<Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
	Collections.sort(list, (a, b) -> a.getKey().length() - b.getKey().length());
	for (int i = 0; i < 3; ++i) {
	    if (list.get(i).getValue() > 1) {
		sb.append(new StringBuilder(list.get(i).getKey().toUpperCase()).reverse().toString())
		        .append(System.lineSeparator());
	    }
	}
	return sb.toString();
    }

    public String getResultUsingJava8() {
	StringBuilder sb = new StringBuilder();
	List<String> words = Arrays.asList(readDataFromFile(fileName));
	Stream<String> stream = parallelMode ? words.parallelStream() : words.stream();
	stream.filter(i -> Collections.frequency(words, i) > 1).collect(Collectors.toSet()).stream()
	        .map(item -> new StringBuilder(item).reverse().toString().toUpperCase())
	        .sorted((s1, s2) -> s1.length() - s2.length()).limit(3)
	        .forEach(item -> sb.append(item).append(System.lineSeparator()));
	return sb.toString();
    }

    public static void main(String[] args) {
	System.out.print(new Duplicates("input3.txt").getResultByImpl());
	System.out.println("~~~~~~~Java8~~~~~~~~~");
	System.out.println(new Duplicates("input3.txt", false).getResultUsingJava8());
    }

}
