package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.system.enums.SearchUser;
import com.lxdnz.bit794.tm3.library_project.database.repositorys.UserRepository;
import com.lxdnz.bit794.tm3.library_project.services.security.EncryptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private EncryptionService encryptionService;

    @Autowired
    public void setEncryptionService(EncryptionService encryptionService) {
        this.encryptionService = encryptionService;
    }


    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add); //fun with Java 8
        return users;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveOrUpdate(User domainObject) {
        if(domainObject.getPassword() != null){
            domainObject.setEncryptedPassword(encryptionService.encryptString(domainObject.getPassword()));
        }
        return userRepository.save(domainObject);
    }
    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return findByUsername(name);
    }

    @Override
    public List<User> listUserBySearchType(String searchterm, SearchUser searchUser) {
        switch (searchUser) {
            case ANY: {
                return listAllUsersBySearchAll(searchterm);
            }
            case NAME:{
                return listAllUsersBySearchedNames(searchterm);
            }
            case USERNAME:{
                return listAllUsersBySearchedUsername(searchterm);
            }
            default: {
                return listAll();
            }
        }
    }

    @Override
    public List<User> listAllUsersBySearchedUsername(String string) {
        return new ArrayList<>(userRepository.findAllByUsernameIgnoreCaseContaining(string));
    }

    @Override
    public List<User> listAllUsersBySearchedNames(String string) {
        return new ArrayList<>(userRepository
                .findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContaining(
                        string, string));
    }

    @Override
    public List<User> listAllUsersBySearchAll(String string) {
        return new ArrayList<>(userRepository
                .findAllByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrUsernameContaining(
                        string, string, string));
    }
}
