package com.lxdnz.bit794.tm3.library_project.database.repositorys;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReserveRepository extends CrudRepository<Reservation, Long> {

    List<Reservation> findReservationByStillReservedIsTrue();

    List<Reservation> findAllByUserID(Long userID);
    List<Reservation> findAllByItemID(Long itemID);
    Reservation findOneByItemID(Long itemID);
}
