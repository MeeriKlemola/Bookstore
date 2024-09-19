package hh.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.bookstore.domain.Book;
import hh.bookstore.domain.BookRepository;
import hh.bookstore.domain.Category;
import hh.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner fetchData(CategoryRepository categoryRepository, BookRepository repository) {
		return (args) -> {
			log.info("save a couple of categories");
			Category category1 = new Category("Horror");
			categoryRepository.save(category1);
			Category category2 = new Category("Scifi");
			categoryRepository.save(category2);
			Category category3 = new Category("Comic");
			categoryRepository.save(category3);

			log.info("save a couple of books");
			repository.save(new Book("Misery", "Meeri Klemola", 2024, "123abc", 24.70, category1));
			repository.save(new Book("Pain", "Meeri Klemola", 2025, "123abb", 3.00, category3));

			log.info("fetch all students");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}