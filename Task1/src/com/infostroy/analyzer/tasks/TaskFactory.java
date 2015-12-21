package com.infostroy.analyzer.tasks;

public class TaskFactory {

    public static Analyzer getTaskByName(String nameOfTask) {
	switch (nameOfTask) {
	case "length":
	    return new Length();
	case "duplicates":
	    return new Duplicates();
	case "frequency":
	    return new Frequency();
	default:
	    break;
	}
	return null;
    }
}
