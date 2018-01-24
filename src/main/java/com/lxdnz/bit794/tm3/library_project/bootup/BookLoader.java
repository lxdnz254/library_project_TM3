package com.lxdnz.bit794.tm3.library_project.bootup;

import com.lxdnz.bit794.tm3.library_project.persistence.model.Book;
import com.lxdnz.bit794.tm3.library_project.persistence.repo.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;



import java.util.List;
import java.util.logging.Logger;

@Component
public class BookLoader implements ApplicationListener<ContextRefreshedEvent>{

    private BookRepository bookRepository;

    private Logger log = Logger.getGlobal();

    @Autowired
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        seedBookTable();

    }

    private void seedBookTable() {

        List<Book> b = (List<Book>)bookRepository.findAll();

        if (b == null || b.size() <= 0) {

            Book book1 = new Book();
            book1.setTitle("I, Robot");
            book1.setAuthor("Asimov, Issac");
            bookRepository.save(book1);
            log.info("Book1 - I,Robot by Issac Asimov saved.. id: " + book1.getId());

            Book book2 = new Book();
            book2.setTitle("Tu Arohae, Inter-disciplinary Critical Thinking");
            book2.setAuthor("Fish, William & Duffin, Stephen");
            bookRepository.save(book2);
            log.info("Book2 - Critical Thinking by William Fish & Stephen Duffin saved.. id: " + book2.getId());
        } else {
            log.info("Book bootstrapping not required");
        }
    }
}
