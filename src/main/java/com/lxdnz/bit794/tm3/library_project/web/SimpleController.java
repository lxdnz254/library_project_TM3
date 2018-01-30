package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.persistence.converters.UserToUserDetails;
import com.lxdnz.bit794.tm3.library_project.services.ItemService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import com.lxdnz.bit794.tm3.library_project.services.security.UserDetailsImpl;
import com.lxdnz.bit794.tm3.library_project.services.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.security.core.context.SecurityContextHolder.*;

@Controller
public class SimpleController {

    private ItemService itemService;
    private UserService userService;

    @Value("${spring.application.name}")
    String appName;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String homePage(Model model) {
        model.addAttribute("appName", appName);
        model.addAttribute("books", itemService.listAllItems());
        Authentication auth = getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("user", userService.findByUsername(name));

        return "home";
    }
}