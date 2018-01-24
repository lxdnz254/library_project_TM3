package com.lxdnz.bit794.tm3.library_project.persistence.service;

import com.lxdnz.bit794.tm3.library_project.persistence.model.Book;

import java.util.Optional;

public interface BookService {
    Iterable<Book> listAllBooks();

    Book getBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Long id);
}
