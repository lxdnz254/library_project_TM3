package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.web.support.Helper;
import com.lxdnz.bit794.tm3.library_project.web.support.MessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Helper helper, RedirectAttributes ra){
        MessageHelper.addSuccessAttribute(ra, helper.loginSuccess());
        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Helper helper, RedirectAttributes ra) {
        MessageHelper.addSuccessAttribute(ra, helper.logout());
        return "redirect:/";
    }

}
