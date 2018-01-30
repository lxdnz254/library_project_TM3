package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.ItemType;
import com.lxdnz.bit794.tm3.library_project.services.ItemService;
import com.lxdnz.bit794.tm3.library_project.services.RoleService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import com.lxdnz.bit794.tm3.library_project.web.forms.BookForm;
import com.lxdnz.bit794.tm3.library_project.web.forms.SignupForm;
import com.lxdnz.bit794.tm3.library_project.web.support.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class FormController {

    private static final String SIGNUP_VIEW_NAME = "signup";
    private static final String NEWBOOK_VIEW_NAME = "bookform";

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) { this.roleService = roleService; }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute(new SignupForm());
        /*
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }
        */
        return SIGNUP_VIEW_NAME;

    }

    @PostMapping("signup")
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        User user = userService.saveOrUpdate(signupForm.createUser());
        user.addRole(roleService.getById(Long.valueOf(1)));
        userService.saveOrUpdate(user);

        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
        return "redirect:/";
    }

    @RequestMapping(value = "bookform", method = RequestMethod.GET)
    public String newBook(Model model) {

        model.addAttribute(new BookForm());
        model.addAttribute("allTypes", Arrays.asList(ItemType.values()));
        /*
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SIGNUP_VIEW_NAME.concat(" :: bookForm");
        }
        */
        return NEWBOOK_VIEW_NAME;

    }

    @PostMapping("bookform")
    public String signup(@Valid @ModelAttribute BookForm bookForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return NEWBOOK_VIEW_NAME;
        }
        itemService.saveOrUpdate(bookForm.createItem());
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "bookform.success");
        return "redirect:/";
    }
}
