package com.lxdnz.bit794.tm3.library_project.services;


import com.lxdnz.bit794.tm3.library_project.database.converters.DateFormatter;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Loan;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class LoanServiceTest {

    private LoanService loanService;
    private UserService userService;
    private ItemService itemService;

    @Autowired
    public void setLoanService(LoanService loanService) {
        this.loanService = loanService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Test
    public void testLoans() {
        // get current amount of loans and create new Loan for item
        int loanCount = loanService.listAll().size();
        User user = userService.findByUsername("user");
        Item item = itemService.getById(1L);
        BigDecimal initialBalance = user.getCurrentBalance();
        DateFormatter dateFormatter = new DateFormatter();

        /**
         * generate Loan method
         */
        Loan loan = new Loan(item, user); // new loan object
        item.setIsRented(true); // flag item as loaned
        // add rental price of loan to user balance
        user.setCurrentBalance(user.getCurrentBalance().add( item.getItemType().getPrice() ));
        // loan should auto save return date
        loanService.saveOrUpdate(loan);
        itemService.saveOrUpdate(item);
        userService.saveOrUpdate(user);

        // check the loan is in table
        assertNotNull(loanService.getById(loan.getId()));
        // get copy of loan and test results
        Loan savedLoan = loanService.getById(loan.getId());
        assertEquals(savedLoan.getId(), loan.getId());
        assertEquals(savedLoan.getItemID(), loan.getItemID());
        assertEquals(savedLoan.getUserID(),loan.getUserID());

        // test the createReturnDate method
        Date d = new Date();
        d.setTime(d.getTime() + (item.getItemType().getRentalDays() * 1000 * 60 * 60 * 24));
        String savedDate = dateFormatter.formatDate(savedLoan.getReturnDate());
        String formatD = dateFormatter.formatDate(d);
        String loanReturnDate = dateFormatter.formatDate(loan.getReturnDate());
        assertEquals(savedDate, formatD);
        assertEquals(savedDate, loanReturnDate);

        // test item is updated to rented state
        Item checkItem = itemService.getById(item.getId());
        assertTrue(checkItem.getIsRented());

        // Test user balance is updated correctly
        User checkUser = userService.getById(user.getId());
        assertEquals(checkUser.getCurrentBalance(), initialBalance.add( item.getItemType().getPrice()));

        // Test we can get Loan by User and Item
        /*
         * These are lists of Items and Users as there may be more than one Loan
         * for an Item or User in the DB
         */
        Loan itemLoan = loanService.getByItemID(item.getId());
        assertEquals(itemLoan.getItemID(), item.getId());

        List<?> userLoans = loanService.getByUserID(user.getId());
        assertTrue(userLoans.contains(loan));

        // Test we can get list of active loans
        List<?> activeLoans = loanService.getActiveLoans();
        // check loan is in the activeLoans
        assertTrue(activeLoans.contains(loan));
        // reset activeLoans otherwise we get Optimistic locking error
        activeLoans = null;

        /**
         * Delete the loan / return the item to library
         */
        Item deleteItem = itemService.getById(item.getId());
        deleteItem.setIsRented(false);
        itemService.saveOrUpdate(deleteItem);
        loanService.delete(loan.getId());
        activeLoans = loanService.getActiveLoans();
        // check loan is NOT active
        assertFalse(activeLoans.contains(loan));
        // check loan does not exist
        List<?> loanList = loanService.listAll();
        assertFalse(loanList.contains(loan));
    }
}