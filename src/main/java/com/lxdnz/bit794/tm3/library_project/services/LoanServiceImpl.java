package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Loan;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;
import com.lxdnz.bit794.tm3.library_project.persistence.repos.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {

    private LoanRepository loanRepository;

    @Autowired
    public void setLoanRepository(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public List<?> listAll() {
        List<Loan> loans = new ArrayList<>();
        loanRepository.findAll().forEach(loans :: add);
        return loans;
    }

    @Override
    public Loan getById(Long id) {
        return loanRepository.findOne(id);
    }

    @Override
    public Loan saveOrUpdate(Loan domainObject) {
        return loanRepository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        loanRepository.delete(id);
    }

    @Override
    public Loan getByItemID(Long itemID) {
        return loanRepository.findOneByItemID(itemID);
    }

    @Override
    public List<Loan> getByUserID(Long userID) {
        return loanRepository.findAllByUserID(userID);
    }

    @Override
    public List<Loan> getActiveLoans() {
        Date d = new Date();
        return loanRepository.findAllByReturnDateIsAfter(d);
    }
}
