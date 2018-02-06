package com.lxdnz.bit794.tm3.library_project.web.forms;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.ItemType;
import org.hibernate.validator.constraints.NotBlank;

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

    public Item createItem() {
        // set up new Item as type Book
        Item book = new Item();
        book.setTitle(getTitle());
        book.setCreator(getAuthor());
        book.setItemType(getType());
        return book;
    }
}
