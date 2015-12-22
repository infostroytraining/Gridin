package com.infostroy.filter;

import java.io.File;

import com.infostroy.filter.exception.AcceptException;

public abstract class Filter {

    private Filter nextFilter;

    protected Filter(Filter nextFilter) {
	this.nextFilter = nextFilter;
    }

    public boolean accept(File file) throws AcceptException {
	boolean result = currentAccept(file);
	if (nextFilter != null && result) {
	    return nextFilter.accept(file);
	}
	return result;
    }

    public abstract boolean currentAccept(File file) throws AcceptException;

}
