package com.infostroy.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;
import com.infostroy.analyzer.tasks.TaskConverter;
import com.infostroy.analyzer.tasks.enums.Task;

public class Commander {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = { "-i", "--input" }, description = "Path for input fie", required = true)
    private String input = "";

    @Parameter(names = { "-t",
            "--task" }, converter = TaskConverter.class, description = "Name of task: frecuency, length, duplicates", required = true)
    private Task task;

    @Parameter(names = "--help", description = "Show information about how to use this app", help = true)
    private boolean help;

    @Parameter(names = "--exit", description = "Close application", required = false)
    private boolean exit;

    @Parameter(names = { "-p", "--parallel" }, description = "Calc tasks using parallelism", required = false)
    private boolean parallel;

    public String getInput() {
	return input;
    }

    public Task getTask() {
	return task;
    }

    public boolean getHelp() {
	return help;
    }

    public boolean getExit() {
	return exit;
    }

    public boolean getParallel() {
	return parallel;
    }
}
