package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Loan;

import java.util.List;

public interface LoanService extends CRUDService<Loan> {

    Loan getByItemID(Long itemID);
    List<Loan> getByUserID(Long userID);
    List<Loan> getActiveLoans();
}
