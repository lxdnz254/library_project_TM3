package com.lxdnz.bit794.tm3.library_project.persistence.repos;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Loan;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    List<Loan> findAllByUserID(Long userId);
    Loan findOneByItemID(Long itemId);
    List<Loan> findAllByReturnDateIsAfter(Date date);
}
