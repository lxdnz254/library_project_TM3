package com.lxdnz.bit794.tm3.library_project.helpers;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import org.springframework.stereotype.Component;

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
        item.setReserved(true);
        return reservation;
    }

    /**
     * Helper to put an item in a state of unreserved
     * @param removeReservation
     * @param item
     */
    public void unreserveReservation(Reservation removeReservation, Item item) {
        item.setReserved(false);
        removeReservation.setStillReserved(false);
    }

    public String successReserve(Item reserveItem, User reserveUser) {
        return "User " + reserveUser.getUsername()+ " successfully reserved item: "
                + reserveItem.getTitle();
    }

    public String userHasItemOnLoan() {
        return "User already has item on Loan, so cannot reserve";
    }

    /**
     * Loan Helper methods
     */
}
