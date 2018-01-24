package com.lxdnz.bit794.tm3.library_project.repositories;

import com.lxdnz.bit794.tm3.library_project.configuration.RepositoryConfiguration;
import com.lxdnz.bit794.tm3.library_project.persistence.model.Book;
import com.lxdnz.bit794.tm3.library_project.persistence.repo.BookRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {RepositoryConfiguration.class})
public class BookRepositoryTest {

    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Test
    public void testSaveBook() {
        // setup book
        Book book = new Book();
        book.setTitle("The Bible");
        book.setAuthor("God");

        // new Book() auto-generates ID so cannot test for null id beforehand
        // save book, verify has Id value after save
        bookRepository.save(book);
        assertNotNull(book.getId()); // not null after save

        // fetch from DB
        Optional<Book> fetchedBook = bookRepository.findOne(book.getId());

        // should not be null
        assertNotNull(fetchedBook);

        // should equal
        assertEquals(book.getId(), fetchedBook.get().getId());
        assertEquals(book.getTitle(), fetchedBook.get().getTitle());

        //update Author
        fetchedBook.get().setAuthor("King James");
        bookRepository.save(fetchedBook.get());

        // get from DB, should be updated
        Optional<Book> fetchUpdatedBook = bookRepository.findOne(fetchedBook.get().getId());
        assertEquals(fetchedBook.get().getAuthor(), fetchUpdatedBook.get().getAuthor());

        // verify count of products in DB
        long bookCount = bookRepository.count();
        assertEquals(bookCount, 1);

        // get all books, list should only have one
        Iterable<Book> books = bookRepository.findAll();
        int count =0;
        for (Book b: books) {
            count++;
        }
        assertEquals(count, 1);
    }

}
