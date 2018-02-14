package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.web.support.Helper;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Role;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.services.LoanService;
import com.lxdnz.bit794.tm3.library_project.services.ReserveService;
import com.lxdnz.bit794.tm3.library_project.services.RoleService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import com.lxdnz.bit794.tm3.library_project.web.support.MessageHelper;
import com.lxdnz.bit794.tm3.library_project.web.support.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    private UserService userService;
    private RoleService roleService;
    private LoanService loanService;
    private ReserveService reserveService;

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @Autowired
    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUser(Model model, HttpServletRequest request) {
        model.addAttribute("search", new Search());
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("users", userService.listAll());
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            model.addAttribute("message", flashMap.get("message"));
        } else
        {
            model.asMap().get("message");
        }
        return "users";
    }

    @RequestMapping(value = "/searchusers", method = RequestMethod.POST)
    public String searchUser(Model model, Search search, Helper helper, HttpServletRequest req) {
        model.addAttribute("searchedusers",
                userService.listUserBySearchType(
                        search.getSearchterm(),
                        search.getSearchUser()
                ));
        MessageHelper.addSuccessAttribute(model, helper.userSearchSuccess());
        return listUser(model, req);
    }

    @RequestMapping("user/{id}")
    public String showUser(@PathVariable Long id, Model model, HttpServletRequest request){
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("userID", userService.getById(id));
        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            model.addAttribute("message", flashMap.get("message"));
        } else
        {
            model.asMap().get("message");
        }
        return "usershow";
    }

    @RequestMapping("user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("userID", userService.getById(id));
        return "edituser";
    }

    @RequestMapping(value = "userID", method = RequestMethod.POST)
    public String saveUser(User user, Helper helper, RedirectAttributes ra){

        // make sure the updated user has the correct role
        Role role = roleService.getById(1L);
        List<Role> userRoles = user.getRoles();
        if (!userRoles.contains(role)) {
            user.addRole(role);
        }
        userService.saveOrUpdate(user);
        MessageHelper.addSuccessAttribute(ra, helper.successSaveUser(user));
        return "redirect:/user/" + user.getId();
    }

    @RequestMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Long id, Model model,
                             RedirectAttributes ra, Helper helper){
        // check Loans and reserves for user before deleting
        if (!userHasLoans(id) && !userhasReserves(id)) {
            // remove user role first to maintain db integrity
            User deleteUser = userService.getById(id);
            Role role = roleService.getById(1L);
            deleteUser.removeRole(role);
            userService.saveOrUpdate(deleteUser);
            // then delete user
            userService.delete(id);
            MessageHelper.addSuccessAttribute(ra, helper.deleteUserSuccess(deleteUser));
        }
        else
        {
            MessageHelper.addWarningAttribute(ra, helper.deleteUserWarning(userService.getById(id)));

        }
        return "redirect:/users";
    }

    @RequestMapping("/paid/{id}")
    public String userPays(@PathVariable Long id, RedirectAttributes ra, Helper helper) {
        User payee = userService.getById(id);
        payee.setCurrentBalance(BigDecimal.ZERO);
        userService.saveOrUpdate(payee);
        MessageHelper.addSuccessAttribute(ra, helper.paymentSuccess(payee));
        return "redirect:/user/{id}";
    }

    private boolean userHasLoans(Long id) {
        return !loanService.getByUserID(id).isEmpty();
    }

    private boolean userhasReserves(Long id) {
        List<Reservation> activeReserves = reserveService.getActiveReserves();
        for(Reservation reservation: activeReserves) {
            if (reservation.getUserID() == id) {
                return true;
            }
        }
        return false;
    }
}
