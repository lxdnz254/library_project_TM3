package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Role;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class UserServiceTest {

    private UserService userService;
    private RoleService roleService;
    private List<Role> roles;

    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Test
    public void testSaveUser() {
        // Big Decimal Value to add;
        BigDecimal value = BigDecimal.TEN;

        // get roles
        roles = (List<Role>) roleService.listAll();
        Role role = roles.get(0);
        // set up user
        User user = new User();
        user.setUsername("jim");
        user.setPassword("beam");
        user.setFirstName("James");
        user.setLastName("Beam, Snr");
        user.setCurrentBalance(BigDecimal.ZERO);
        user.addRole(role);

        // save user and test user has ID
        userService.saveOrUpdate(user);
        assertNotNull(user.getId());

        // fetch from DB
        User fetchedUser = userService.getById(user.getId());

        // should not be null
        assertNotNull(fetchedUser);

        // should equal
        assertEquals(user.getId(), fetchedUser.getId());
        assertEquals(user.getUsername(), fetchedUser.getUsername());

        // swap roles and test addition of balance
        fetchedUser.removeRole(role);
        fetchedUser.addRole(roles.get(1));
        fetchedUser.setCurrentBalance(fetchedUser.getCurrentBalance().add(value));
        userService.saveOrUpdate(fetchedUser);

        // get from DB, should be updated
        User fetchUpdatedUser = userService.getById(fetchedUser.getId());
        assertEquals(fetchUpdatedUser.getUsername(), fetchedUser.getUsername());
        assertEquals(fetchUpdatedUser.getCurrentBalance(), fetchedUser.getCurrentBalance());

        // remove from DB, and assert does not exist
        userService.delete(fetchUpdatedUser.getId());
        assertNull(userService.getById(fetchedUser.getId()));
    }
}
