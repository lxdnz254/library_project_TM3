package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.helpers.Helper;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Loan;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.services.ItemService;

import com.lxdnz.bit794.tm3.library_project.services.LoanService;
import com.lxdnz.bit794.tm3.library_project.services.ReserveService;
import com.lxdnz.bit794.tm3.library_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.core.context.SecurityContextHolder.getContext;

@Controller
public class ItemController {

    private ItemService itemService;
    private ReserveService reserveService;
    private LoanService loanService;
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
    }

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/items", method = RequestMethod.GET)
    public String listItem(Model model) {
        model.addAttribute("items", itemService.listAll());
        return "items";
    }

    @RequestMapping("item/{id}")
    public String showItem(@PathVariable Long id, Model model){
        model.addAttribute("user", getUser());
        model.addAttribute("item", itemService.getById(id));
        return "itemshow";
    }

    @RequestMapping("item/edit/{id}")
    public String editItem(@PathVariable Long id, Model model){
        model.addAttribute("user", getUser());
        model.addAttribute("item", itemService.getById(id));
        return "edititem";
    }

    @RequestMapping(value = "item", method = RequestMethod.POST)
    public String saveItem(Item item){
        // Keep the reservation and checkout status the same on editing
        Item oldItem = itemService.getById(item.getId());
        item.setIsReserved(oldItem.getIsReserved());
        item.setIsRented(oldItem.getIsRented());
        itemService.saveOrUpdate(item);
        return "redirect:/item/" + item.getId();
    }

    @RequestMapping("item/delete/{id}")
    public String deleteItem(@PathVariable Long id){
        itemService.delete(id);
        return "redirect:/";
    }

    @RequestMapping("item/reserve/{id}")
    public String reserveItem(@PathVariable Long id, Model model, Helper helper) {
        if (!userHasItemLoaned(id)) {
            User reserveUser = getUser();
            Item reserveItem = itemService.getById(id);
            Reservation reservation = helper.reserveItem(reserveItem, reserveUser);
            itemService.saveOrUpdate(reserveItem);
            reserveService.saveOrUpdate(reservation);
            model.addAttribute("message", helper.successReserve(reserveItem, reserveUser));
        } else {
            model.addAttribute("message", helper.userHasItemOnLoan());
        }

        return "redirect:/";
    }

    @RequestMapping("item/checkout/{id}")
    public String checkoutItem(@PathVariable Long id) {
        Item checkoutItem = itemService.getById(id);
        User checkoutUser = getUser();
        Loan newLoan = new Loan(checkoutItem, checkoutUser);
        checkoutItem.setIsRented(true);
        checkoutUser.setCurrentBalance(checkoutUser.getCurrentBalance()
                .add( checkoutItem.getItemType().getPrice() ));
        itemService.saveOrUpdate(checkoutItem);
        userService.saveOrUpdate(checkoutUser);
        loanService.saveOrUpdate(newLoan);
        return "redirect:/";
    }

    @RequestMapping("item/return/{id}")
    public String returnItem(@PathVariable Long id) {
        Item returnItem = itemService.getById(id);
        Loan returnLoan = loanService.getByItemID(returnItem.getId());
        returnItem.setIsRented(false);
        itemService.saveOrUpdate(returnItem);
        // check for reservation here and send to new user
        loanService.delete(returnLoan.getId());

        return "redirect:/";
    }

    @RequestMapping("/loans")
    public String allLoans(Model model) {
        model.addAttribute("user", getUser());
        model.addAttribute("loans", loanService.getActiveLoans());

        return "loans";
    }

    @RequestMapping("/userloans")
    public String userLoans(Model model) {
        User currentUser = getUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("loans", loanService.getByUserID(currentUser.getId()));
        return "loans";
    }

    @RequestMapping("/reserves")
    public String allReserves(Model model) {
        model.addAttribute("user", getUser());
        model.addAttribute("reserves", reserveService.getActiveReserves());
        return "reserves";
    }

    @RequestMapping("/userreserves")
    public String userReserves(Model model) {
        User currentUser = getUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("reserves", reserveService.getByUser(currentUser));
        return "reserves";
    }

    @RequestMapping("/unreserve/{id}")
    public String unreserveItem(@PathVariable Long id) {
        Reservation unreserveReservation = reserveService.getById(id);
        unreserveReservation.setStillReserved(false);
        Item item = itemService.getById(unreserveReservation.getItemID());
        item.setIsReserved(false);
        itemService.saveOrUpdate(item);
        reserveService.saveOrUpdate(unreserveReservation);

        return "redirect:/";
    }


    /**
     * Helpers for Item actions
     */

    /**
     * checks if a User has Item on loan already
     * @param id
     * @return
     */

    private boolean userHasItemLoaned(Long id) {
        User reserveUser = getUser();
        Item reserveItem = itemService.getById(id);
        Loan loanCheck = loanService.getByItemID(reserveItem.getId());
        return (loanCheck != null && loanCheck.getUserID().equals(reserveUser.getId()));
    }

    private User getUser() {
        Authentication auth = getContext().getAuthentication();
        String name = auth.getName(); //get logged in username
        return userService.findByUsername(name);
    }
}