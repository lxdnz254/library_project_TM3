package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.persistence.model.Book;
import com.lxdnz.bit794.tm3.library_project.services.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", bookService.listAllBooks());
        return "books";
    }

    @RequestMapping("book/{id}")
    public String showBook(@PathVariable Long id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        return "bookshow";
    }

    @RequestMapping("book/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        return "bookform";
    }

    @RequestMapping("book/new")
    public String newBook(Model model){
        model.addAttribute("book", new Book());
        return "bookform";
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String saveBook(Book book){
        bookService.saveBook(book);
        return "redirect:/book/" + book.getId();
    }

    @RequestMapping("book/delete/{id}")
    public String delete(@PathVariable Long id){
        bookService.deleteBook(id);
        return "redirect:/books";
    }

}