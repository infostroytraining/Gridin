package com.infostroy.analyzer.tasks.enums;

public enum Task {

    FREQUENCY("frecuency"), LENGTH("length"), DUPLICATES("duplicates"), EMPTY("");

    private String name;

    Task(String t) {
	name = t;
    }

    public String getName() {
	return name;
    }
}
