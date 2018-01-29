package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.services.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class BookController {

    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", itemService.listAllItems());
        return "books";
    }

    @RequestMapping("book/{id}")
    public String showBook(@PathVariable Long id, Model model){
        model.addAttribute("book", itemService.getItemById(id));
        return "bookshow";
    }

    @RequestMapping("book/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("book", itemService.getItemById(id));
        return "bookform";
    }

    @RequestMapping("book/new")
    public String newBook(Model model){
        model.addAttribute("book", new Item());
        return "bookform";
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String saveBook(Item book){
        itemService.saveItem(book);
        return "redirect:/book/" + book.getId();
    }

    @RequestMapping("book/delete/{id}")
    public String delete(@PathVariable Long id){
        itemService.deleteItem(id);
        return "redirect:/books";
    }

}