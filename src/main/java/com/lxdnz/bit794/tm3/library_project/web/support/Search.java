package com.lxdnz.bit794.tm3.library_project.web.support;

import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.SearchBy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search {

    private String searchterm;
    private SearchBy searchBy;

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

    public SearchBy getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(SearchBy searchBy) {
        this.searchBy = searchBy;
    }

    public List<SearchBy> getSearchByList() {
        List<SearchBy> list = new ArrayList<>();
        Arrays.asList(SearchBy.values()).forEach(list :: add);
        return list;
    }
}
