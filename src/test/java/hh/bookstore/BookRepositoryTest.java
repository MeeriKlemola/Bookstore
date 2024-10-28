package hh.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.Book;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test // search functionality test
    public void findByTitleShouldReturnBook() {
        List<Book> books = repository.findByTitle("Misery");

        assertThat(books).hasSize(1);
        assertThat(books.get(0).getPublicationYear()).isEqualTo(2024);
    }

    @Test // create functionality test
    public void createNewBook() {
        Book book = new Book("TestBook", "JUnit", 1, "1", 22.50, null);
        repository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test // delete functionality test
    public void deleteBook() {
        Book book = new Book("TestBook", "JUnit", 1, "1", 22.50, null);
        repository.save(book);
        repository.deleteById(book.getId());
        assertThat(repository.findById(book.getId())).isEmpty();
    }

}
