package com.infostroy.analyzer.tasks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Frequency implements Analyzer {

	@Override
	public Map<String, Integer> analyze(String fileName) {
		Map<String, Integer> result = new HashMap<>();
		String[] words;
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Cp1251"));
			String tmp = "";
			while ((tmp = br.readLine()) != null) {
				sb.append(tmp);
			}
			br.close();
		} catch (IOException e) {
		}
		words = sb.toString().split(" ");

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
				return b.getValue() - a.getValue();
			}
		});
		for (Map.Entry<String, Integer> elem : list) {
			sb.append(elem.getKey()).append(" -> ").append(elem.getValue()).append(System.lineSeparator());
		}
		return sb.toString();
	}

}
