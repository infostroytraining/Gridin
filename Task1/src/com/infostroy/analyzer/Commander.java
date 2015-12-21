package com.infostroy.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

public class Commander {
    @Parameter
    private List<String> parameters = new ArrayList<>();

    @Parameter(names = { "-i", "--input" }, description = "Path for input fie", required = true)
    private String input = "";

    @Parameter(names = { "-t", "--task" }, description = "Name of task: frecuency, length, duplicates", required = true)
    private String task;

    @Parameter(names = "--help", description = "How to use this app", help = true)
    private boolean help;

    @Parameter(names = { "-p", "--parallel" }, description = "Calc tasks using parallelism", required = false)
    private boolean parallel;

    public String getInput() {
	return input;
    }

    public String getTask() {
	return task;
    }

    public boolean getHelp() {
	return help;
    }

    public boolean getParallel() {
	return parallel;
    }
}
