package com.lxdnz.bit794.tm3.library_project.services;

import com.lxdnz.bit794.tm3.library_project.persistence.model.Book;

public interface BookService {
    Iterable<Book> listAllBooks();

    long count();

    Book getBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Long id);
}
