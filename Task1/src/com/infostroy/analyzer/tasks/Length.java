package com.infostroy.analyzer.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Length implements Analyzer {

    @Override
    public Map<String, Integer> analyze(String fileName) {
	Map<String, Integer> result = new HashMap<>();
	String[] words = readDataFromFile(fileName).split(" ");

	for (String word : words) {
	    result.put(word, word.length());
	}

	return result;
    }

    public String getResult(String fileName) {
	StringBuilder sb = new StringBuilder();
	Map<String, Integer> result = analyze(fileName);
	List<Entry<String, Integer>> list = new ArrayList<>(result.entrySet());
	Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
	    @Override
	    public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
		return b.getValue() - a.getValue();
	    }
	});
	for (Map.Entry<String, Integer> elem : list) {
	    sb.append(elem.getKey()).append(" -> ").append(elem.getValue()).append(System.lineSeparator());
	}
	return sb.toString();
    }

    public String getResultUsingJava8(String fileName, boolean parallelMode) {
	StringBuilder sb = new StringBuilder();
	List<String> words = Arrays.asList(readDataFromFile(fileName).split(" "));
	Stream<String> stream = parallelMode ? words.parallelStream() : words.stream();
	stream.collect(Collectors.toSet()).stream().collect(Collectors.toMap(item -> item, item -> item.length()))
	        .entrySet().stream().sorted((v1, v2) -> v2.getValue() - v1.getValue()).limit(3).forEach(item -> sb
	                .append(item.getKey()).append(" -> ").append(item.getValue()).append(System.lineSeparator()));
	return sb.toString();
    }

    public static void main(String[] args) {
	System.out.print(new Length().getResult("input.txt"));
	System.out.println("~~~~~~~Java8~~~~~~~~~");
	System.out.println(new Length().getResultUsingJava8("input.txt", false));
    }
}
