package com.infostroy.analyzer.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Frequency extends Analyze {

    public Frequency(String fileName) {
	this(fileName, false);
    }

    public Frequency(String fileName, boolean parallel) {
	this.fileName = fileName;
	this.parallelMode = parallel;
    }

    public Map<String, Integer> getFrequencyFromInputFile() {
	Map<String, Integer> result = new HashMap<>();
	String[] words = readDataFromFile(fileName);

	for (String word : words) {
	    if (!result.containsKey(word)) {
		result.put(word, 1);
	    } else {
		result.put(word, result.get(word) + 1);
	    }
	}
	return result;
    }

    public String getResultByImpl() {
	StringBuilder sb = new StringBuilder();
	Map<String, Integer> result = getFrequencyFromInputFile();
	List<Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
	Collections.sort(list, (a, b) -> b.getValue() - a.getValue());
	for (int i = 0; i < 2; ++i) {
	    Map.Entry<String, Integer> elem = list.get(i);
	    sb.append(elem.getKey()).append(" -> ").append(elem.getValue()).append(System.lineSeparator());
	}
	return sb.toString();
    }

    public String getResultUsingJava8() {
	StringBuilder sb = new StringBuilder();
	List<String> words = Arrays.asList(readDataFromFile(fileName));
	Stream<String> stream = parallelMode ? words.parallelStream() : words.stream();
	stream.collect(Collectors.toSet()).stream()
	        .collect(Collectors.toMap(item -> item, item -> Collections.frequency(words, item))).entrySet().stream()
	        .sorted((v1, v2) -> v2.getValue() - v1.getValue()).limit(2).forEach(item -> sb.append(item.getKey())
	                .append(" -> ").append(item.getValue()).append(System.lineSeparator()));
	return sb.toString();
    }

    public static void main(String[] args) {
	System.out.print(new Frequency("input2.txt").getResultByImpl());
	System.out.println("~~~~~~~Java8~~~~~~~~~");
	System.out.println(new Frequency("input2.txt", false).getResultUsingJava8());
    }
}
