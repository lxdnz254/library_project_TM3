package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.SearchUser;

import java.util.List;

public interface UserService extends CRUDService<User> {
    User findByUsername(String username);
    User getCurrentUser();

    List<User> listUserBySearchType(String searchterm, SearchUser searchUser);
    List<User> listAllUsersBySearchedUsername(String string);
    List<User> listAllUsersBySearchedNames(String string);
    List<User> listAllUsersBySearchAll(String string);
}
