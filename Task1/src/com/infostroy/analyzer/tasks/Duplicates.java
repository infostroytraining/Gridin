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

public class Duplicates implements Analyzer {

    @Override
    public Map<String, Integer> analyze(String fileName) {
	Map<String, Integer> result = new HashMap<>();
	String[] words = readDataFromFile(fileName).split(" ");

	for (String word : words) {
	    if (!result.containsKey(word)) {
		result.put(word, 1);
	    } else {
		result.put(word, result.get(word) + 1);
	    }
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
		return a.getKey().length() - b.getKey().length();
	    }
	});
	for (int i = 0; i < list.size(); ++i) {
	    if (list.get(i).getValue() > 1) {
		sb.append(new StringBuilder(list.get(i).getKey().toUpperCase()).reverse().toString())
		        .append(System.lineSeparator());
	    }
	    if (i == 2) {
		break;
	    }
	}
	return sb.toString();
    }

    public String getResultUsingJava8(String fileName, boolean parallelMode) {
	StringBuilder sb = new StringBuilder();
	List<String> words = Arrays.asList(readDataFromFile(fileName).split(" "));
	Stream<String> stream = parallelMode ? words.parallelStream() : words.stream();
	stream.filter(i -> Collections.frequency(words, i) > 1).collect(Collectors.toSet()).stream()
	        .map(item -> new StringBuilder(item).reverse().toString().toUpperCase())
	        .sorted((s1, s2) -> s1.length() - s2.length()).limit(3)
	        .forEach(item -> sb.append(item).append(System.lineSeparator()));
	return sb.toString();
    }

    public static void main(String[] args) {
	System.out.print(new Duplicates().getResult("input3.txt"));
	System.out.println("~~~~~~~Java8~~~~~~~~~");
	System.out.println(new Duplicates().getResultUsingJava8("input3.txt", false));
    }
}
