package hh.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // http://localhost:8080/index
    @GetMapping("/index")
    public String welcome() {
        return "index"; // index.html
    }

    // http://localhost:8080/booklist
    @GetMapping(value = "/booklist")
    public String getBookList(Model model) {
        model.addAttribute("books", bookRepository.findAll());

        return "booklist"; // booklist.html
    }

    // http://localhost:8080/addbook
    @GetMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());

        return "addbook"; // addbook.html
    }

    // tallentaa lomakkeen tiedot sekä lisäyksessä, että päivittäessä
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        bookRepository.save(book);

        return "redirect:/booklist"; // booklist.html
    }

    // poistaa id:llä kirjan
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);

        return "redirect:../booklist"; // booklist.html
    }

    // editoi id:llä kirjaa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable("id") Long bookId, Model model) {
        model.addAttribute("book", bookRepository.findById(bookId));

        return "editbook"; // editbook.html
    }

}
