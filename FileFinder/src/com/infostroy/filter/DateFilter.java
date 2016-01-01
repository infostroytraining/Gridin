package com.infostroy.filter;

import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.TimeZone;

import com.infostroy.filter.exception.AcceptException;

public class DateFilter extends Filter {

    private LocalDate startDate;

    private LocalDate endDate;

    private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public DateFilter(Filter nextFilter, String startDate, String endDate) {
	super(nextFilter);
	this.startDate = LocalDate.parse(startDate, dateFormat);
	this.endDate = LocalDate.parse(endDate, dateFormat);
    }

    @Override
    public boolean currentAccept(File file) throws AcceptException {
	if (Objects.nonNull(file)) {
	    LocalDate lastModifiedDate = LocalDateTime
	            .ofInstant(Instant.ofEpochMilli(file.lastModified()), TimeZone.getDefault().toZoneId())
	            .toLocalDate();
	    return lastModifiedDate.isAfter(startDate) && lastModifiedDate.isBefore(endDate);
	}
	return false;
    }
}
