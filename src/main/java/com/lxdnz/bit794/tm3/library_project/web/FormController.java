package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.services.ItemService;
import com.lxdnz.bit794.tm3.library_project.services.RoleService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import com.lxdnz.bit794.tm3.library_project.web.forms.ItemForm;
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

@Controller
public class FormController {

    private static final String SIGNUP_VIEW_NAME = "signup";
    private static final String NEWITEM_VIEW_NAME = "itemform";

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    private RoleService roleService;

    @Autowired
    public void setRoleService(RoleService roleService) { this.roleService = roleService; }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signup(Model model) {

        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute(new SignupForm());
        /*
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return SIGNUP_VIEW_NAME.concat(" :: signupForm");
        }
        */
        return SIGNUP_VIEW_NAME;

    }

    @PostMapping("signup")
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors,
                         RedirectAttributes ra, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", userService.getCurrentUser());
            return SIGNUP_VIEW_NAME;
        }
        User user = userService.saveOrUpdate(signupForm.createUser());
        user.addRole(roleService.getById(1L));

        userService.saveOrUpdate(user);

        model.addAttribute("user", userService.getCurrentUser());
        MessageHelper.addSuccessAttribute(ra, "signup success");
        return "redirect:/";
    }

    @RequestMapping(value = "itemform", method = RequestMethod.GET)
    public String newItem(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute(new ItemForm());
        /*
        if (AjaxUtils.isAjaxRequest(requestedWith)) {
            return NEWITEM_VIEW_NAME.concat(" :: itemForm");
        }
        */
        return NEWITEM_VIEW_NAME;

    }

    @PostMapping("itemform")
    public String newItem(@Valid @ModelAttribute ItemForm itemForm, Errors errors,
                          RedirectAttributes ra, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("user", userService.getCurrentUser());
            return NEWITEM_VIEW_NAME;
        }
        itemService.saveOrUpdate(itemForm.createItem());
        model.addAttribute("user", userService.getCurrentUser());
        MessageHelper.addSuccessAttribute(ra, "Added item success");
        return "redirect:/";
    }


}
