package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Role;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.services.RoleService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUser(Model model) {
        model.addAttribute("users", userService.listAll());
        return "users";
    }

    @RequestMapping("user/{id}")
    public String showUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "usershow";
    }

    @RequestMapping("user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getById(id));
        return "edituser";
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public String saveUser(User user){
        // make sure the updated user has the correct role
        Role role = roleService.getById(1L);
        List<Role> userRoles = user.getRoles();
        if (!userRoles.contains(role)) {
            user.addRole(role);
        }
        userService.saveOrUpdate(user);
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping("user/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        // remove user role first to maintain db integrity
        User deleteUser = userService.getById(id);
        Role role = roleService.getById(1L);
        deleteUser.removeRole(role);
        userService.saveOrUpdate(deleteUser);
        // then delete user
        userService.delete(id);
        return "redirect:/users";
    }
}
