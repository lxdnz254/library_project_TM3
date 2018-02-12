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

    public String latestUserMessage(User user) {
        if (user == null) {
            return "Welcome to the Library System";
        }
        else
        {
            return "Hello " + user.getFirstName() + ", welcome to the Library system";
        }
    }

    /**
     * Loan Helper methods
     */
}
