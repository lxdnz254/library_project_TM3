package com.lxdnz.bit794.tm3.library_project.persistence.model.enums;

import com.lxdnz.bit794.tm3.library_project.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

public enum SearchBy {

    TITLE,CREATOR,ANY;

    private static String string;
    private ItemService itemService;
    private static List<?> list;

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    SearchBy(){}

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

    public List<?> getList(String searchTerm) {
        switch (this) {
            case TITLE: {
                list = itemService.listAllItemsBySearchedTitle(searchTerm);
                break;
            }
            case CREATOR: {
                list = itemService.listAll();
                break;
            }
            case ANY: {
                list = itemService.listAll();
                break;
            }
        }
        return list;
    }
}
