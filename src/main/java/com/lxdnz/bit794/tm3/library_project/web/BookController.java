package com.lxdnz.bit794.tm3.library_project.web;

import com.lxdnz.bit794.tm3.library_project.persistence.model.concrete.Item;
import com.lxdnz.bit794.tm3.library_project.persistence.repos.ItemRepository;
import com.lxdnz.bit794.tm3.library_project.services.ItemService;

import com.lxdnz.bit794.tm3.library_project.web.forms.BookForm;
import com.lxdnz.bit794.tm3.library_project.web.support.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class BookController {



    private ItemService itemService;

    private ItemRepository itemRepository;

    @Autowired
    public void setItemRepository(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("books", itemService.listAll());
        return "books";
    }

    @RequestMapping("book/{id}")
    public String showBook(@PathVariable Long id, Model model){
        model.addAttribute("book", itemService.getById(id));
        return "bookshow";
    }

    @RequestMapping("book/edit/{id}")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("book", itemService.getById(id));
        return "editbook";
    }

    @RequestMapping(value = "book", method = RequestMethod.POST)
    public String saveBook(Item book){
        itemService.saveOrUpdate(book);
        return "redirect:/book/" + book.getId();
    }

    @RequestMapping("book/delete/{id}")
    public String delete(@PathVariable Long id){
        itemService.delete(id);
        return "redirect:/books";
    }


}