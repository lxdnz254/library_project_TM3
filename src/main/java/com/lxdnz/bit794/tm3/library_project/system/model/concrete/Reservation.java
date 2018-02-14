package com.lxdnz.bit794.tm3.library_project.system.model.concrete;

import com.lxdnz.bit794.tm3.library_project.system.model.AbstractRequest;

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
        this.setItemID(item.getId());
        this.setUserID(user.getId());
        this.setRequestUsername(user.getUsername());
        this.setRequestItemTitle(item.getTitle());
        this.setRequestItemCreator(item.getCreator());
        this.stillReserved = true;
    }

    /**
     * Standard constructor
     */
    public Reservation() {

    }

    /* Getters */


    public boolean isStillReserved() {
        return stillReserved;
    }

    /* Setters */

    public void setStillReserved(boolean stillReserved) {
        this.stillReserved = stillReserved;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)(this.getId() ^ (this.getId() >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Reservation other = (Reservation) obj;
        if (this.getId() != other.getId())
            return false;
        if (stillReserved) {
            return other.stillReserved;
        } else return stillReserved && other.stillReserved;
    }
}
