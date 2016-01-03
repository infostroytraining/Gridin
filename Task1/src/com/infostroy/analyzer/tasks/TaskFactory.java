package com.infostroy.analyzer.tasks;

import com.infostroy.analyzer.tasks.enums.Task;

public class TaskFactory {

    private TaskFactory() {
    }

    public static Analyzer getTask(Task task, String fileName, boolean parallelMode) {
	switch (task) {
	case LENGTH:
	    return new Length(fileName, parallelMode);
	case DUPLICATES:
	    return new Duplicates(fileName, parallelMode);
	case FREQUENCY:
	    return new Frequency(fileName, parallelMode);
	default:
	    return null;
	}
    }
}
