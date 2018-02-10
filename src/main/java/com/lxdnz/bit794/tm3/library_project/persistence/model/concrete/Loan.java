package com.lxdnz.bit794.tm3.library_project.persistence.model.concrete;

import com.lxdnz.bit794.tm3.library_project.persistence.converters.DateFormatter;
import com.lxdnz.bit794.tm3.library_project.persistence.model.AbstractRequest;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Loan extends AbstractRequest {

    @Column
    private Date returnDate;

    @Transient
    private DateFormatter dateFormatter = new DateFormatter();

    /**
     * Default constructor
     */
    public Loan() {

    }

    /**
     * New loan constructor, sets the return date to the default item number of days,
     * from this moment
     * @param item
     * @param user
     */
    public Loan (Item item, User user) {
        this.setItemID(item.getId());
        this.setUserID(user.getId());
        this.setRequestUsername(user.getUsername());
        this.setRequestItemTitle(item.getTitle());
        this.setRequestItemCreator(item.getCreator());
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

    public String getWebReturnDate() {
        return dateFormatter.formatDate(this.getReturnDate());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((returnDate == null) ? 0 : returnDate.hashCode());
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
        Loan other = (Loan) obj;
        if (this.getId() != other.getId())
            return false;
        if (returnDate == null) {
            return other.returnDate == null;
        } else return returnDate.equals(other.returnDate);
    }
}
