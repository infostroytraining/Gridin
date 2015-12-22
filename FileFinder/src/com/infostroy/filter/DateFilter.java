package com.infostroy.filter;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.infostroy.filter.exception.AcceptException;

public class DateFilter extends Filter {

    private Date startDate;

    private Date endDate;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    public DateFilter(Filter nextFilter, String startDate, String endDate) throws ParseException {
	super(nextFilter);
	this.startDate = dateFormat.parse(startDate);
	this.endDate = dateFormat.parse(endDate);
    }

    @Override
    public boolean currentAccept(File file) throws AcceptException {
	if (file != null) {
	    long lastModified = file.lastModified();
	    String lastModifiedFormat = dateFormat.format(lastModified);
	    Date lastModifiedDate = null;
	    try {
		lastModifiedDate = dateFormat.parse(lastModifiedFormat);
	    } catch (ParseException e) {
		e.printStackTrace();
		throw new AcceptException(e);
	    }
	    return lastModifiedDate.after(startDate) && lastModifiedDate.before(endDate);
	}
	return false;
    }
}
