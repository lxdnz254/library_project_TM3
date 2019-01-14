package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.enums.SearchItem;
import com.lxdnz.bit794.tm3.library_project.database.repositorys.ItemRepository;
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
        itemRepository.findAllByTitleIgnoreCaseContaining(string).forEach(items :: add);
        return items;
    }

    @Override
    public List<Item> listAllItemsBySearchedCreator(String string) {
        List<Item> items = new ArrayList<>();
        itemRepository.findAllByCreatorIgnoreCaseContaining(string).forEach(items :: add);
        return items;
    }

    @Override
    public List<Item> listAllItemsBySearchedTitleOrCreator(String string) {
        List<Item> items = new ArrayList<>();
        itemRepository
                .findAllByTitleIgnoreCaseContainingOrIgnoreCaseCreatorContaining(string, string)
                .forEach(items :: add);
        return items;
    }

    @Override
    public List<Item> selectListBySearchType(String string, SearchItem searchItem) {
        switch (searchItem) {
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
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public Item saveOrUpdate(Item domainObject) {
        return itemRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
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
