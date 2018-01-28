package com.lxdnz.bit794.tm3.library_project.persistence.repos;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}
