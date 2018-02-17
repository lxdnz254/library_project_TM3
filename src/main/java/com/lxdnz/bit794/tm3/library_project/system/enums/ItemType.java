package com.lxdnz.bit794.tm3.library_project.system.enums;

import java.math.BigDecimal;

public enum ItemType {

    BOOK,DVD,MAGZ;

    private static String string;
    private static Integer hireDays;
    private static BigDecimal price;
    private static String creatorName;

    ItemType() {

    }

    @Override
    public String toString() {

        switch(this) {
            case BOOK: {
                string = "Book";
                break;
            }
            case DVD: {
                string = "DVD";
                break;
            }
            case MAGZ: {
                string = "Magazine";
                break;
            }
        }
        return string;
    }

    public Integer getRentalDays() {

        switch(this) {
            case BOOK: {
                hireDays = 21;
                break;
            }
            case DVD: {
                hireDays = 7;
                break;
            }
            case MAGZ: {
                hireDays = 7;
                break;
            }
        }
        return hireDays;
    }

    public BigDecimal getPrice() {

        switch(this) {
            case BOOK: {
                price = BigDecimal.valueOf(0.70);
                break;
            }
            case DVD: {
                price = BigDecimal.valueOf(5.00);
                break;
            }
            case MAGZ: {
                price = BigDecimal.valueOf(2.00);
                break;
            }
        }
        return price;
    }

    public String getCreatorName() {

        switch(this) {
            case BOOK: {
                creatorName = "Author(s)";
                break;
            }
            case DVD: {
                creatorName = "Director";
                break;
            }
            case MAGZ: {
                creatorName = "Publisher";
                break;
            }
        }
        return creatorName;
    }
}
