package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.web.support.Helper;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.Reservation;
import com.lxdnz.bit794.tm3.library_project.system.model.concrete.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ReserveServiceTest {

    private ReserveService reserveService;
    private UserService userService;
    private ItemService itemService;

    @Autowired
    public void setReserveService(ReserveService reserveService) {
        this.reserveService = reserveService;
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
    public void testCreateReserve() {
        // Set up User to reserve Item
        User testUser = userService.findByUsername("user");
        Item testItem = itemService.getById(1L);
        // get initial size of active reserves
        int activeCount = reserveService.getActiveReserves().size();
        /*
            Create Reservation method to be implemented in the System
         */
        Reservation testReservation = new Reservation(testItem, testUser);
        testItem.setIsReserved(true);
        itemService.saveOrUpdate(testItem);
        reserveService.saveOrUpdate(testReservation);

        /*
            Assertion the saves and updates have happened
         */
        Reservation checkTestReservationById = reserveService.getById(testReservation.getId());
        // checks the reserve is stored
        assertEquals(checkTestReservationById.getId(), testReservation.getId());
        // checks the status is true
        assertTrue(checkTestReservationById.isStillReserved());

        /*
            More test methods in here
         */
        // active Reserve tests
        List<?> activeReserves = reserveService.getActiveReserves();
        assertEquals(activeReserves.size(), activeCount + 1);
        activeReserves = null; // kill the activeReserves because we only needed the count.

        // finally remove test object from database and remove reserved status of item
        // method for un-reserving an object
        Item removeTestItem = itemService.getById(testItem.getId());
        removeTestItem.setIsReserved(false);
        itemService.saveOrUpdate(removeTestItem);
        reserveService.delete(testReservation.getId());
        // assertions
        assertNull(reserveService.getById(testReservation.getId()));
        assertFalse(itemService.getById(testItem.getId()).getIsReserved());
        // checking the reserve is no longer active
        List<?> secondActiveCheck = reserveService.getActiveReserves();
        assertEquals(secondActiveCheck.size(), activeCount);
    }

    @Test
    public void testReserveByHelper() {
        // Set up User to reserve Item
        User testUser = userService.findByUsername("user");
        Item testItem = itemService.getById(2L);
        Helper helper = new Helper();
        // get initial size of active reserves
        int activeCount = reserveService.getActiveReserves().size();

        // check the users were pulled from db
        assertNotNull(testItem);
        assertNotNull(testUser);

        /*
            Create Reservation by ReserveHelper
         */
        Reservation testReservation = helper.reserveItem(testItem, testUser);
        itemService.saveOrUpdate(testItem);
        reserveService.saveOrUpdate(testReservation);

        // check Item has been updated an reservation exists
        Item checkItem = itemService.getById(2L);
        assertTrue(checkItem.getIsReserved());

        //check reservation exists
        List<?> activeCheck = reserveService.getActiveReserves();
        assertEquals(activeCheck.size(), activeCount + 1);

        /*
            Remove reserve by helper
         */
        // Get the latest version of Reservation and Item
        Reservation removeReservation = reserveService.getById(testReservation.getId());
        Item unReserveItem = itemService.getById(removeReservation.getItemID());
        // use the helper method
        helper.unreserveReservation(removeReservation, unReserveItem);
        // save objects to DB
        itemService.saveOrUpdate(unReserveItem);
        reserveService.saveOrUpdate(removeReservation);

        // check Item has been updated an reservation exists
        Item uncheckItem = itemService.getById(2L);
        assertFalse(uncheckItem.getIsReserved());

        //check reservation exists
        List<?> inactiveCheck = reserveService.getActiveReserves();
        assertEquals(inactiveCheck.size(), activeCount);

        // All passed, reservation will remain in DB flagged as inactive
        // For DB integrity (leaving in original state) delete reservation
        reserveService.delete(removeReservation.getId());

    }
}
