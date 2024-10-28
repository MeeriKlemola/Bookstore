package hh.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import hh.bookstore.web.BookController;
import hh.bookstore.web.BookRestController;
import hh.bookstore.web.CategoryController;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private BookController BookController;

	@Test
	public void contextLoadsBookController() throws Exception {
		assertThat(BookController).isNotNull();
	}

	@Autowired
	private BookRestController RestController;

	@Test
	public void contextLoadsRestController() throws Exception {
		assertThat(RestController).isNotNull();
	}

	@Autowired
	private CategoryController CategoryController;

	@Test
	public void contextLoadsCategoryController() throws Exception {
		assertThat(CategoryController).isNotNull();
	}
}
