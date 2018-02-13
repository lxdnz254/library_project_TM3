package com.lxdnz.bit794.tm3.library_project.helpers;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class Helper {


    /**
     * Reservation Helper methods
     */

    /**
     * Helper to create a new reservation of an Item
     * @param item
     * @param user
     * @return
     */
    public Reservation reserveItem(Item item, User user) {

        Reservation reservation = new Reservation(item, user);
        item.setIsReserved(true);
        return reservation;
    }

    /**
     * Helper to put an item in a state of unreserved
     * @param removeReservation
     * @param item
     */
    public void unreserveReservation(Reservation removeReservation, Item item) {
        item.setIsReserved(false);
        removeReservation.setStillReserved(false);
    }

    public String successReserve(Item reserveItem, User reserveUser) {
        return "User " + reserveUser.getUsername()+ " successfully reserved item: "
                + reserveItem.getTitle();
    }

    public String userHasItemOnLoan() {
        return "User already has item on Loan, so cannot reserve";
    }


    public String loginSuccess() {
        return "Sucessfully Logged in";
    }
    public String searchSuccess() {
        return "Succesful search";
    }

    public String logout() {
        return "Logged out";
    }

    public String successCheckout(Item checkoutItem, User checkoutUser) {
        return "User " + checkoutUser.getUsername()
                + " has checked out item: " + checkoutItem.getTitle();
    }

    public String unreserveSuccess(Item item) {
        return "Successfully unreserved item: " + item.getTitle();
    }

    public String successReturn(Item returnItem) {
        return "Succesfully returned item: " + returnItem.getTitle();
    }

    public String deleteWarning() {
        return "Item is currently reserved or on loan so cannot be deleted";
    }

    public String deleteSuccess() {
        return "Successfully deleted Item";
    }

    public String saveItemSuccess(Item item) {
        return "Succesfully saved item: " + item.getTitle();
    }
}
