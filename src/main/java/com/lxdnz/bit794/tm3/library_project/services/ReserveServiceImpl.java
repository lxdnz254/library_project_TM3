package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.database.repositorys.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReserveServiceImpl implements ReserveService {

    private ReserveRepository reserveRepository;

    @Autowired
    public void setReserveRepository(ReserveRepository reserveRepository) {
        this.reserveRepository = reserveRepository;
    }

    @Override
    public List<Reservation> listAll() {
        List<Reservation> reservations = new ArrayList<>();
        reserveRepository.findAll().forEach(reservations :: add);
        return reservations;
    }

    @Override
    public Reservation getById(Long id) {
        return reserveRepository.findOne(id);
    }

    @Override
    public Reservation saveOrUpdate(Reservation domainObject) {
        return reserveRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        reserveRepository.delete(id);
    }

    @Override
    public Reservation getByItem(Item item) {
        List<Reservation> itemReserves = reserveRepository.findAllByItemID(item.getId());
        List<Reservation> activeItemReserve = itemReserves.stream()
                .filter(getActiveReserves()::contains)
                .collect(Collectors.toList());

        return activeItemReserve.get(0);
    }

    @Override
    public List<Reservation> getByUser(User user) {
        // Get the entire list of reservation by a user
        List<Reservation> userReserves = reserveRepository.findAllByUserID(user.getId());
        // filter against the active reserves
        return userReserves.stream()
                .filter(getActiveReserves()::contains)
                .collect(Collectors.toList());
    }

    @Override
    public List<Reservation> getActiveReserves() {
        return reserveRepository.findReservationByStillReservedIsTrue();
    }
}
