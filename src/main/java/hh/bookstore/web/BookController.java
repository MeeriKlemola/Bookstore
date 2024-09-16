package hh.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import hh.bookstore.domain.Book;
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

    // http://localhost:8080/booklist
    @GetMapping(value = "/booklist")
    public String getBookList(Model model) {
        model.addAttribute("books", repository.findAll());
        return "booklist"; //booklist.html
    }

    // http://localhost:8080/addbook
    @GetMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook"; //addbook.html
    }
    
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        return "redirect:/booklist";
    }

}
