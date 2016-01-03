package com.infostroy.analyzer.tasks;

public class TimeredTask {

    public static void run(AnalyzerResult analyzerResult) {
	long start = System.currentTimeMillis();
	System.out.println(analyzerResult.getResult());
	long end = System.currentTimeMillis();
	System.out.println("Elapsed time: " + (end - start) + " millis");
    }
}
