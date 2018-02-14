package com.lxdnz.bit794.tm3.library_project.database.converters;

import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DateFormatter {

    public String formatDate(Date date) {

        String strDateFormat = "MMM dd yyyy";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);

        DateFormat dateInstance = SimpleDateFormat.getDateInstance();
        return dateFormat.format(date);
    }
}
