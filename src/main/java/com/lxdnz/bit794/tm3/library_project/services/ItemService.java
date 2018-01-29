package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;

public interface ItemService {
    Iterable<Item> listAllItems();

    long count();

    Item getItemById(Long id);

    Item saveItem(Item item);

    void deleteItem(Long id);
}
