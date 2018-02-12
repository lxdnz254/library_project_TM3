package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.helpers.Helper;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.services.ItemService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import com.lxdnz.bit794.tm3.library_project.web.support.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {

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
    public String homePage(Model model, Helper helper) {
        model.addAttribute("search", new Search());
        model.addAttribute("appName", appName);
        model.addAttribute("items", itemService.listAllItems());
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("message", helper.latestUserMessage(currentUser));

        return "home";
    }

    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String searchItem(Model model, Search search, Helper helper) {
        model.addAttribute("searcheditems",
                itemService.selectListBySearchType(
                        search.getSearchterm(),
                        search.getSearchItem()));
        return homePage(model, helper);
    }
}