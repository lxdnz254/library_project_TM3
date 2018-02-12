package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.SearchItem;

import java.util.List;

public interface ItemService extends CRUDService<Item>{

    Iterable<Item> listAllItems();
    List<Item> listAllItemsBySearchedTitle(String string);
    List<Item> listAllItemsBySearchedCreator(String string);
    List<Item> listAllItemsBySearchedTitleOrCreator(String string);

    List<Item> selectListBySearchType(String string, SearchItem searchItem);

    long count();

}
