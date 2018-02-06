package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;

import java.util.List;

public interface ItemService extends CRUDService<Item>{

    Iterable<Item> listAllItems();
    List<Item> listAllItemsBySearchedTitle(String string);


    long count();

}
