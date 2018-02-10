package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Loan;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.User;

import java.util.List;

public interface LoanService extends CRUDService<Loan> {

    Loan getByItemID(Long itemID);
    List<Loan> getByUserID(Long userID);
    List<Loan> getActiveLoans();
}
