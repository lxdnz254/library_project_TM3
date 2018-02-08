package com.lxdnz.bit794.tm3.library_project.persistence.repos;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReserveRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findReservationByStillReservedIsTrue();

    List<Reservation> findAllByUserID(Long userID);
    Reservation findOneByItemID(Long itemID);
}
