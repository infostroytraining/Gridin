package com.infostroy.filter;

import java.io.File;
import java.util.Objects;

import com.google.common.io.Files;

public class ExtentionFilter extends Filter {

    private String extention;

    public ExtentionFilter(Filter next, String extention) {
	super(next);
	this.extention = extention;
    }

    @Override
    public boolean currentAccept(File file) {
	if (Objects.nonNull(file)) {
	    String fileExtention = Files.getFileExtension(file.getName());
	    return fileExtention.equalsIgnoreCase(extention);
	}
	return false;
    }
}
