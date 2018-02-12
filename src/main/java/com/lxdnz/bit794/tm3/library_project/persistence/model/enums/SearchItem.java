package com.lxdnz.bit794.tm3.library_project.persistence.model.enums;

import com.lxdnz.bit794.tm3.library_project.services.ItemService;

import java.util.List;

public enum SearchItem {

    TITLE,CREATOR,ANY;

    private static String string;

    SearchItem(){}

    @Override
    public String toString() {
        switch(this) {
            case TITLE: {
                string = "Title";
                break;
            }
            case CREATOR: {
                string = "Author/Director";
                break;
            }
            case ANY: {
                string = "Either";
                break;
            }
        }
        return string;
    }
}
