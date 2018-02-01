package com.lxdnz.bit794.tm3.library_project.persistence.model.concrete;

import com.lxdnz.bit794.tm3.library_project.persistence.model.AbstractRequest;

import javax.persistence.Column;
import javax.persistence.Entity;


@Entity
public class Reservation extends AbstractRequest {

    @Column
    private boolean stillReserved;

    /**
     * Constructor for a reservation, called by user only when an Item is on Loan to another user.
     * Sets reservation status as true
     * @param item
     * @param user
     */
    public Reservation(Item item, User user) {
        this.setItem(item);
        this.setUser(user);
        this.stillReserved = true;
    }

    /* Getters */


    public boolean isStillReserved() {
        return stillReserved;
    }

    /* Setters */

    public void setStillReserved(boolean stillReserved) {
        this.stillReserved = stillReserved;
    }
}
