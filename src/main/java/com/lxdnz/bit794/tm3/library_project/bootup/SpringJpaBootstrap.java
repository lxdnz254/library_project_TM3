package com.lxdnz.bit794.tm3.library_project.bootup;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Book;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Role;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.persistence.repos.BookRepository;
import com.lxdnz.bit794.tm3.library_project.services.RoleService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;



import java.util.List;

@Component
public class SpringJpaBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private BookRepository bookRepository;
    private UserService userService;
    private RoleService roleService;

    private Logger log = Logger.getLogger(SpringJpaBootstrap.class);

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        seedBookTable();
        loadUsers();
        loadRoles();


    }

    private void seedBookTable() {

        List<Book> b = (List<Book>)bookRepository.findAll();

        if (b == null || b.size() <= 0) {

            Book book1 = new Book();
            book1.setTitle("I, Robot");
            book1.setAuthor("Asimov, Issac");
            bookRepository.save(book1);
            log.info("Book1 - I,Robot by Issac Asimov saved.. id: " + book1.getId());

            Book book2 = new Book();
            book2.setTitle("Tu Arohae, Inter-disciplinary Critical Thinking");
            book2.setAuthor("Fish, William & Duffin, Stephen");
            bookRepository.save(book2);
            log.info("Book2 - Critical Thinking by William Fish & Stephen Duffin saved.. id: " + book2.getId());
        } else {
            log.info("Book bootstrapping not required");
        }
    }

    private void loadUsers() {

        List<?> u = userService.listAll();

        if (u == null || u.size() <= 0) {

            User user1 = new User();
            user1.setUsername("user");
            user1.setPassword("user");
            userService.saveOrUpdate(user1);

            User user2 = new User();
            user2.setUsername("admin");
            user2.setPassword("admin");
            userService.saveOrUpdate(user2);
        }
        else
        {
            log.info("Users Loaded already");
        }

    }

    private void loadRoles() {

        List<?> r = roleService.listAll();

        if (r == null || r.size() <= 0) {
            Role role = new Role();
            role.setRole("USER");
            roleService.saveOrUpdate(role);
            log.info("Saved role" + role.getRole());
            Role adminRole = new Role();
            adminRole.setRole("ADMIN");
            roleService.saveOrUpdate(adminRole);
            log.info("Saved role" + adminRole.getRole());
            assignUsersToUserRole();
            assignUsersToAdminRole();
        }
        else
        {
            log.info("Roles assigned already");
        }

    }
    private void assignUsersToUserRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("USER")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("user")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
    private void assignUsersToAdminRole() {
        List<Role> roles = (List<Role>) roleService.listAll();
        List<User> users = (List<User>) userService.listAll();

        roles.forEach(role -> {
            if (role.getRole().equalsIgnoreCase("ADMIN")) {
                users.forEach(user -> {
                    if (user.getUsername().equals("admin")) {
                        user.addRole(role);
                        userService.saveOrUpdate(user);
                    }
                });
            }
        });
    }
}
