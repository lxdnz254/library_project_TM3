package com.lxdnz.bit794.tm3.library_project.persistence.repos;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    // search methods
    List<User> findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(String first, String last);

    List<User> findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrUsernameContaining(String t1, String t2, String t3);
    List<User> findAllByUsernameIgnoreCaseContaining(String term);

}
