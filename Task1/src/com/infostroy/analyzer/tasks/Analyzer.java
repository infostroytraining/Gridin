package com.infostroy.analyzer.tasks;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public interface Analyzer {

    String getResultUsingJava8();

    String getResultByImpl();

    default String[] readDataFromFile(String fileName) {
	StringBuilder sb = new StringBuilder();
	try {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "Cp1251"));
	    String tmp = "";
	    while ((tmp = br.readLine()) != null) {
		sb.append(tmp);
	    }
	    br.close();
	} catch (IOException e) {
	    System.out.println("Invalid file name");
	}
	return sb.toString().split(" ");
    }
}
