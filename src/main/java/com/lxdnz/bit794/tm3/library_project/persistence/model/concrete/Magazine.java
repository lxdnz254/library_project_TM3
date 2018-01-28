package com.lxdnz.bit794.tm3.library_project.persistence.model.concrete;

import com.lxdnz.bit794.tm3.library_project.persistence.model.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.ItemType;

import javax.persistence.Column;
import java.math.BigDecimal;

public class Magazine extends Item {

    private static ItemType itemTypeMag = ItemType.MAGZ;

    @Column
    private String publisher;

    public Magazine() {
        super();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public void setPrice(BigDecimal price) {
        super.setPrice(itemTypeMag.getPrice());
    }

    @Override
    public void setItemType(ItemType itemType) {
        super.setItemType(itemTypeMag);
    }

    @Override
    public void setRentalDays(Integer rentalDays) {
        super.setRentalDays(itemTypeMag.getHireDays());
    }
}
