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
import hh.bookstore.domain.AppUser;
import hh.bookstore.domain.AppUserRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner fetchData(CategoryRepository categoryRepository, BookRepository bookRepository, AppUserRepository userRepository) {
		return (args) -> {
			Category category1 = new Category("Horror");
			categoryRepository.save(category1);
			Category category2 = new Category("Scifi");
			categoryRepository.save(category2);
			Category category3 = new Category("Comic");
			categoryRepository.save(category3);

			log.info("save a couple of books");
			bookRepository.save(new Book("Misery", "Meeri Klemola", 2024, "123abc", 24.70, category1));
			bookRepository.save(new Book("Pain", "Meeri Klemola", 2025, "123abb", 3.00, category3));

			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@mail.com", "USER");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@mail.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}