package hh.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hh.bookstore.domain.BookRepository;

@Controller

public class BookController {

    @Autowired
    private BookRepository repository;

    // http://localhost:8080/index
    @GetMapping("/index")
    public String welcome() {
        return "index"; // index.html
    }

    @RequestMapping(value = "/booklist")
    public String studentList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist"; //booklist.html
    }

}
