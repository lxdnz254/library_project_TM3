package com.lxdnz.bit794.tm3.library_project.web.forms;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.model.enums.ItemType;
import org.hibernate.validator.constraints.NotBlank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemForm {

    private static final String NOT_BLANK_MESSAGE = "{notBlank.message}";

    @NotBlank(message = ItemForm.NOT_BLANK_MESSAGE)
    private String title;

    @NotBlank(message = ItemForm.NOT_BLANK_MESSAGE)
    private String author;

    private ItemType type;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public ItemType getType() { return type;}

    public void setType(ItemType type) {
        this.type = type;
    }

    public List<ItemType> getItemTypeList() {
        List<ItemType> list = new ArrayList<>();
        Arrays.asList(ItemType.values()).forEach(list :: add);
        return list;
    }

    public Item createItem() {
        // set up new Item as type Book
        Item item = new Item();
        item.setTitle(getTitle());
        item.setCreator(getAuthor());
        item.setItemType(getType());
        return item;
    }
}
