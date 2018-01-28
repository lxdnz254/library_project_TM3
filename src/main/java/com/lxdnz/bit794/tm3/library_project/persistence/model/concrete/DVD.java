package com.lxdnz.bit794.tm3.library_project.persistence.model.concrete;

import com.lxdnz.bit794.tm3.library_project.persistence.model.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.ItemType;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class DVD extends Item {

    private static ItemType itemTypeDVD = ItemType.DVD;

    @Column
    private String director;

    public DVD() {
        super();
    }

    @Override
    public void setItemType(ItemType itemType) {
        super.setItemType(itemTypeDVD);
    }

    @Override
    public void setRentalDays(Integer rentalDays) {
        super.setRentalDays(itemTypeDVD.getHireDays());
    }

    @Override
    public void setPrice(BigDecimal price) {
        super.setPrice(itemTypeDVD.getPrice());
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
