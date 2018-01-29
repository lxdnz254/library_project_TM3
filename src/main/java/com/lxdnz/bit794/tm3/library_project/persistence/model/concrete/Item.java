package com.lxdnz.bit794.tm3.library_project.persistence.model.concrete;

import com.lxdnz.bit794.tm3.library_project.persistence.model.AbstractModelClass;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.ItemType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Item extends AbstractModelClass {

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String creator;

    @Column
    private BigDecimal price;

    @Column
    private Integer rentalDays;

    @Column
    private boolean isRented;

    @Column
    private boolean isReserved;

    @Column
    private Date hireDate;

    @Enumerated(EnumType.STRING)
    private ItemType itemType;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public BigDecimal getPrice() {
        return price.stripTrailingZeros();
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(Integer rentalDays) {
        this.rentalDays = rentalDays;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public void setItemAsBook() {
        setItemType(ItemType.BOOK);
        setRentalDays(ItemType.BOOK.getRentalDays());
        setPrice(ItemType.BOOK.getPrice());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((creator == null) ? 0 : creator.hashCode());
        result = prime * result + (int)(this.getId() ^ (this.getId() >>> 32));
        result = prime * result + ((title == null) ? 0 : title.hashCode());
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
        Item other = (Item) obj;
        if (creator == null) {
            if (other.creator != null)
                return false;
        } else if (!creator.equals(other.creator))
            return false;
        if (this.getId() != other.getId())
            return false;
        if (title == null) {
            return other.title == null;
        } else return title.equals(other.title);
    }
}
