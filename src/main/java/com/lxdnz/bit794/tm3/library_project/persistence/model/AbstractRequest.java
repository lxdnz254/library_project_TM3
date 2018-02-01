package com.lxdnz.bit794.tm3.library_project.persistence.model;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public class AbstractRequest extends AbstractModelClass {

    @Transient
    private Item item;
    @Transient
    private User user;

    @Column
    private Long itemID;
    private Long userID;

    public Item getItem() {
        return item;
    }

    public User getUser() {
        return user;
    }

    public Long getItemID() {
        return itemID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
