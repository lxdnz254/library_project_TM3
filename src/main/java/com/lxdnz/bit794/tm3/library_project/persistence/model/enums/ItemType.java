package com.lxdnz.bit794.tm3.library_project.persistence.model.enums;

import java.math.BigDecimal;

public enum ItemType {

    BOOK,DVD,MAGZ;

    private String string;
    private Integer hireDays;
    private BigDecimal price;

    @Override
    public String toString() {

        switch(this) {
            case BOOK: {
                string = "Book";
            }
            case DVD: {
                string = "DVD";
            }
            case MAGZ: {
                string = "Magazine";
            }
        }
        return string;
    }

    public Integer getHireDays() {

        switch(this) {
            case BOOK: {
                hireDays = 21;
            }
            case DVD: {
                hireDays = 7;
            }
            case MAGZ: {
                hireDays = 7;
            }
        }
        return hireDays;
    }

    public BigDecimal getPrice() {

        switch(this) {
            case BOOK: {
                price = BigDecimal.valueOf(0.70);
            }
            case DVD: {
                price = BigDecimal.valueOf(5.00);
            }
            case MAGZ: {
                price = BigDecimal.valueOf(2.00);
            }
        }
        return price;
    }
}
