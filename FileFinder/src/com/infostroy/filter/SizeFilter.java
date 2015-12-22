package com.infostroy.filter;

import java.io.File;

public class SizeFilter extends Filter {

    private long minSize;

    private long maxSize;

    public SizeFilter(Filter next, long minSize, long maxSize) {
	super(next);
	this.minSize = minSize;
	this.maxSize = maxSize;
    }

    @Override
    public boolean currentAccept(File file) {
	if (file != null) {
	    long fileSize = file.length();
	    return fileSize < maxSize & fileSize > minSize;
	}
	return false;
    }
}
