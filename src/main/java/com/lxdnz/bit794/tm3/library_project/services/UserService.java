package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.User;

public interface UserService extends CRUDService<User> {
    User findByUsername(String username);
}
