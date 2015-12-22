package com.infostroy;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.infostroy.filter.DateFilter;
import com.infostroy.filter.ExtentionFilter;
import com.infostroy.filter.Filter;
import com.infostroy.filter.NameFilter;
import com.infostroy.filter.SizeFilter;
import com.infostroy.filter.exception.AcceptException;

public class FileFinder {

    public static void main(String[] args) throws AcceptException, ParseException {
	Scanner scanner = new Scanner(System.in);
	while (true) {
	    Filter filter = createFilter(scanner);

	    System.out.println("������� ������� ������:");
	    String folder = scanner.next();
	    File file = new File(folder);

	    List<File> files = Arrays.asList(file.listFiles());

	    System.out.println(files);
	    List<File> result = new ArrayList<>();
	    if (filter != null) {
		for (File current : files) {
		    if (filter.accept(current)) {
			result.add(current);
		    }
		}
	    }
	    System.out.println(result);
	    System.out.println("������ ����� ������? (y/n)");
	    String doAgain = scanner.next();
	    if (doAgain.charAt(0) == 'n') {
		break;
	    }
	}
	scanner.close();
    }

    private static Filter createFilter(Scanner scanner) throws ParseException {
	Filter filter = null;
	System.out.println("������ �� ����� ����� ? (0/1)");
	int filterName = scanner.nextInt();
	if (filterName == 1) {
	    System.out.println("������� ��� �����: ");
	    String fileName = scanner.next();

	    filter = new NameFilter(filter, fileName);
	}
	System.out.println("������ �� ���������� ����� ? (0/1)");
	int filterExtention = scanner.nextInt();
	if (filterExtention == 1) {
	    System.out.println("������� ���������� �����: ");
	    String fileName = scanner.next();
	    filter = new ExtentionFilter(filter, fileName);
	}
	System.out.println("������ �� ������� ����� ? (0/1)");
	int filterSize = scanner.nextInt();
	if (filterSize == 1) {
	    System.out.println("������� ����������� ������ �����: ");
	    long minSize = scanner.nextLong();
	    System.out.println("������� ������������ ������ �����: ");
	    long maxSize = scanner.nextLong();
	    filter = new SizeFilter(filter, minSize, maxSize);
	}
	System.out.println("������ �� ���� ���������� ��������� ����� ? (0/1)");
	int filterDate = scanner.nextInt();
	if (filterDate == 1) {
	    System.out.println("������� ��������� ���� ���������� ��������� �����: ");
	    String startDate = scanner.next();
	    System.out.println("������� �������� ���� ���������� ��������� �����: ");
	    String endDate = scanner.next();
	    filter = new DateFilter(filter, startDate, endDate);
	}
	return filter;
    }
}
