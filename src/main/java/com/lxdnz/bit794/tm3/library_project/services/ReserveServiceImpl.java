package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.persistence.repos.ReserveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        return reserveRepository.findOneByItemID(item.getId());
    }

    @Override
    public List<Reservation> getByUser(User user) {
        return reserveRepository.findAllByUserID(user.getId());
    }

    @Override
    public List<Reservation> getActiveReserves() {
        return reserveRepository.findReservationByStillReservedIsTrue();
    }
}
