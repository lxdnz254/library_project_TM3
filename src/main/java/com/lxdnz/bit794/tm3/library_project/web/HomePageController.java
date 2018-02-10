package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.services.ItemService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import com.lxdnz.bit794.tm3.library_project.web.support.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.security.core.context.SecurityContextHolder.*;

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
    public String homePage(Model model) {
        model.addAttribute("search", new Search());
        model.addAttribute("appName", appName);
        model.addAttribute("items", itemService.listAllItems());
        Authentication auth = getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        model.addAttribute("user", userService.findByUsername(name));
        //model.addAttribute("message", "The Library Information goes Here");

        return "home";
    }

    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String searchTitle(Model model, Search search) {
        model.addAttribute("searcheditems",
                itemService.selectListBySearchType(
                        search.getSearchterm(),
                        search.getSearchBy()));
        return homePage(model);
    }
}