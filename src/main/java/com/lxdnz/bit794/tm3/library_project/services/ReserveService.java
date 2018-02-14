package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;

import java.util.List;

public interface ReserveService extends CRUDService<Reservation> {

    Reservation getByItem(Item item);
    List<Reservation> getByUser(User user);
    List<Reservation> getActiveReserves();
}
