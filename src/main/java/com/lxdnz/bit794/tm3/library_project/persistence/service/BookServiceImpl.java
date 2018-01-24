package com.lxdnz.bit794.tm3.library_project.persistence.service;

import com.lxdnz.bit794.tm3.library_project.persistence.model.Book;
import com.lxdnz.bit794.tm3.library_project.persistence.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public void setBookRepository(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    @Override
    public Iterable<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findOne(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.delete(id);
    }
}
