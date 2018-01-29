package com.lxdnz.bit794.tm3.library_project.persistence.repos;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {

    List<Item> findByTitle(String title);
    Item findOne(long id);
}
