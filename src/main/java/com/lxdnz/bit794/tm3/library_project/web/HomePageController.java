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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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

    @RequestMapping(value ="/", method = RequestMethod.GET)
    public String homePage(Model model, HttpServletRequest request) {
        model.addAttribute("search", new Search());
        model.addAttribute("appName", appName);
        model.addAttribute("items", itemService.listAllItems());
        User currentUser = userService.getCurrentUser();
        model.addAttribute("user", currentUser);

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            model.addAttribute("message", flashMap.get("message"));
        } else
        {
            model.asMap().get("message");
        }
        return "home";
    }

    @RequestMapping(value="/search", method= RequestMethod.POST)
    public String searchItem(Model model, Search search, Helper helper, HttpServletRequest req) {
        model.addAttribute("searcheditems",
                itemService.selectListBySearchType(
                        search.getSearchterm(),
                        search.getSearchItem()));
        model.asMap().put("message", helper.searchSuccess());
        return homePage(model, req);
    }
}