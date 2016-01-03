package com.infostroy.analyzer.tasks;

import com.beust.jcommander.IStringConverter;
import com.infostroy.analyzer.tasks.enums.Task;

public class TaskConverter implements IStringConverter<Task> {

    @Override
    public Task convert(String value) {
	Task[] tasks = Task.values();
	for (Task task : tasks) {
	    if (task.getName().equals(value)) {
		return task;
	    }
	}
	return Task.EMPTY;
    }
}
