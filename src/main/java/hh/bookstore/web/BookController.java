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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

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
        return "booklist"; // booklist.html
    }

    // http://localhost:8080/addbook
    @GetMapping(value = "/addbook")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook"; // addbook.html
    }

    // tallentaa lomakkeen tiedot
    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/booklist"; // booklist.html
    }

    // poistaa id:llä kirjan
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:/booklist"; // booklist.html
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Book book = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        // miksi tämä ei toimi ilman orElseä????

        model.addAttribute("book", book);

        return "editbook"; // editbook.html
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        repository.save(book);

        return "redirect:/booklist";
    }

}
