package com.lxdnz.bit794.tm3.library_project.persistence.repos;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Loan;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<Loan, Long> {
    Loan findOneByUserID(Long userId);
    Loan findOneByItemID(Long itemId);
}
