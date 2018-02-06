package com.lxdnz.bit794.tm3.library_project.persistence.converters;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatter {

    public String formatDate(Date date) {
        String strDateFormat = "YYYY-MM-DD";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        return dateFormat.format(date);
    }
}
