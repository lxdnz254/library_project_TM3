package com.lxdnz.bit794.tm3.library_project.repositories;

import com.lxdnz.bit794.tm3.library_project.configuration.RepositoryConfiguration;
import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.model.enums.ItemType;
import com.lxdnz.bit794.tm3.library_project.persistence.repos.ItemRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class ItemRepositoryTest {

    private ItemRepository itemRepository;
    long initalCount;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Test
    public void testSaveBook() {

        // if book already exists delete it
        if (itemRepository.findByTitle("The Bible") != null) {
            itemRepository.delete(itemRepository.findByTitle("The Bible"));
        }
        // get the initial count
        initalCount = itemRepository.count();
        // setup book
        Item book = new Item();
        book.setCreator("God");
        book.setTitle("The Bible");
        book.setItemType(ItemType.BOOK);

        // new Book() auto-generates ID so cannot test for null id beforehand
        // save book, verify has Id value after save
        itemRepository.save(book);
        assertNotNull(book.getId()); // not null after save

        // fetch from DB
        Item fetchedBook = itemRepository.findOne(book.getId());

        // should not be null
        assertNotNull(fetchedBook);

        // should equal - test all variable
        assertEquals(book.getId(), fetchedBook.getId());
        assertEquals(book.getTitle(), fetchedBook.getTitle());
        assertEquals(book.getCreator(), fetchedBook.getCreator());
        assertEquals(book.getItemType(), fetchedBook.getItemType());
        assertEquals(book.getItemType().getPrice(),
                fetchedBook.getItemType().getPrice());
        assertEquals(book.getItemType().getRentalDays(),
                fetchedBook.getItemType().getRentalDays());

        //update Author
        fetchedBook.setCreator("King James");
        itemRepository.save(fetchedBook);

        // get from DB, should be updated
        Item fetchUpdatedBook = itemRepository.findOne(fetchedBook.getId());
        assertEquals(fetchedBook.getCreator(), fetchUpdatedBook.getCreator());

        // verify count of products in DB
        long itemCount = itemRepository.count();
        assertEquals(itemCount, initalCount+1);

        // get all books, list should only have one
        Iterable<Item> items = itemRepository.findAll();
        int count =0;
        for (Item i: items) {
            count++;
        }
        assertEquals(count, initalCount+1);

        // remove book and assert bookRepo is back to original count
        itemRepository.delete(book.getId());
        assertEquals(itemRepository.count(), initalCount);
    }

}
