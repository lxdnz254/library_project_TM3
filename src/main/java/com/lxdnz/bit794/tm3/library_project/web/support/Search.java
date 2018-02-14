package com.lxdnz.bit794.tm3.library_project.web.support;

import com.lxdnz.bit794.tm3.library_project.system.model.enums.SearchItem;
import com.lxdnz.bit794.tm3.library_project.system.model.enums.SearchUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

    private String searchterm;
    private SearchItem searchItem;
    private SearchUser searchUser;

    public Search() {

    }

    public Search(String searchterm) {
        this.searchterm = searchterm;
    }

    public String getSearchterm() {
        return searchterm;
    }

    public void setSearchterm(String searchterm) {
        this.searchterm = searchterm;
    }

    public SearchItem getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(SearchItem searchItem) {
        this.searchItem = searchItem;
    }

    public SearchUser getSearchUser() {
        return searchUser;
    }

    public void setSearchUser(SearchUser searchUser) {
        this.searchUser = searchUser;
    }

    public List<SearchItem> getSearchItemList() {
        List<SearchItem> list = new ArrayList<>();
        Arrays.asList(SearchItem.values()).forEach(list :: add);
        return list;
    }

    public  List<SearchUser> getSearchUserList() {
        List<SearchUser> list = new ArrayList<>();
        Arrays.asList(SearchUser.values()).forEach(list :: add);
        return list;
    }
}
