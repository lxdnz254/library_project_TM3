package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.SearchBy;
import com.lxdnz.bit794.tm3.library_project.persistence.repos.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> listAll() {
        List<Item> items = new ArrayList<>();
        itemRepository.findAll().forEach(items :: add);
        return items;
    }

    @Override
    public Iterable<Item> listAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> listAllItemsBySearchedTitle(String string) {
        List<Item> items = new ArrayList<>();
        itemRepository.findAllByTitleContains(string).forEach(items :: add);
        return items;
    }

    @Override
    public List<Item> listAllItemsBySearchedCreator(String string) {
        List<Item> items = new ArrayList<>();
        itemRepository.findAllByCreatorContains(string).forEach(items :: add);
        return items;
    }

    @Override
    public List<Item> listAllItemsBySearchedTitleOrCreator(String string) {
        List<Item> items = new ArrayList<>();
        itemRepository.findAllByTitleContainsOrCreatorContains(string, string).forEach(items :: add);
        return items;
    }

    @Override
    public List<Item> selectListBySearchType(String string, SearchBy searchBy) {
        switch (searchBy) {
            case TITLE: {
                return listAllItemsBySearchedTitle(string);
            }
            case CREATOR:{
                return listAllItemsBySearchedCreator(string);
            }
            case ANY:{
                return listAllItemsBySearchedTitleOrCreator(string);
            }
            default: {
                return listAll();
            }
        }
    }

    @Override
    public Item getById(Long id) {
        return itemRepository.findOne(id);
    }

    @Override
    public Item saveOrUpdate(Item domainObject) {
        return itemRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(id);
    }

    @Override
    public long count() {
        Iterable<Item> items = listAllItems();
        int count =0;
        for (Item i: items) {
            count++;
        }
        return count;
    }
}
