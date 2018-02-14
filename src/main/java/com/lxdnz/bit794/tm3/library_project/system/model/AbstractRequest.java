package com.lxdnz.bit794.tm3.library_project.system.model;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;

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
    private String requestUsername;
    private String requestItemTitle;
    private String requestItemCreator;

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

    protected void setItemID(Long itemID) {
        this.itemID = itemID;
    }

    protected void setUserID(Long userID) {
        this.userID = userID;
    }

    public String getRequestUsername() {
        return requestUsername;
    }

    public void setRequestUsername(String requestUsername) {
        this.requestUsername = requestUsername;
    }

    public String getRequestItemTitle() {
        return requestItemTitle;
    }

    public void setRequestItemTitle(String requestItemTitle) {
        this.requestItemTitle = requestItemTitle;
    }

    public String getRequestItemCreator() {
        return requestItemCreator;
    }

    public void setRequestItemCreator(String requestItemCreator) {
        this.requestItemCreator = requestItemCreator;
    }
}
