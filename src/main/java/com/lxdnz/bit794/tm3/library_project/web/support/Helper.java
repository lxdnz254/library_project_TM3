package com.lxdnz.bit794.tm3.library_project.web.support;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import org.springframework.stereotype.Component;

@Component
public class Helper {


    /*
      Reservation Helper methods
     */

    /**
     * Helper to create a new reservation of an Item
     * @param item An Item object to be reserved
     * @param user The user object reserving the Item
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

    /*
     * String returns for the MessageHelper
     */
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
        return "User: " + checkoutUser.getUsername()
                + ", has checked out item: " + checkoutItem.getTitle();
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

    public String userSearchSuccess() {
        return "Successfully searched for users";
    }

    public String successSaveUser(User user) {
        return "Successfully save user: " + user.getUsername();
    }

    public String deleteUserSuccess(User user) {
        return "Successfully deleted user: " + user.getUsername();
    }

    public String deleteUserWarning(User user) {
        return "User: " + user.getUsername() + "has current Loans or Reserves, cannot be deleted";
    }

    public String paymentSuccess(User payee) {
        return payee.getUsername() + " has paid their outstanding fees";
    }
}
