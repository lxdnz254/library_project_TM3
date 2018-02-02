package com.lxdnz.bit794.tm3.library_project.persistence.model.concrete;

import com.lxdnz.bit794.tm3.library_project.persistence.model.AbstractRequest;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;


@Entity
public class Loan extends AbstractRequest {

    @Column
    private Date returnDate;

    /**
     * New loan constructor, sets the return date to the default item number of days, from this moment
     * @param item
     * @param user
     */
    public Loan (Item item, User user) {
        this.setItem(item);
        this.setUser(user);
        this.returnDate = createReturnDate(item);
    }


    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    private Date createReturnDate(Item item) {
        Date d = new Date();
        d.setTime(d.getTime() + (item.getItemType().getRentalDays() * 1000 * 60 * 60 * 24));
        return d;
    }
}
