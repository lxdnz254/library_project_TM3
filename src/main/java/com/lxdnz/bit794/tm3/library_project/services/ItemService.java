package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;

public interface ItemService extends CRUDService<Item>{

    Iterable<Item> listAllItems();

    long count();

}
