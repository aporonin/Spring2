package pl.spring.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.to.BookTo;

/**
 * Tests for class BookService
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonServiceTest-context.xml")
public class BookServiceImplTest {

	@Autowired
	private BookService bookService;
	@Autowired
	private CacheManager cacheManager;

	@Before
	public void setUp() {
		cacheManager.getCache("booksCache").clear();
	}

	@Test
	public void testShouldSetBookId() {
		// given
		final BookTo bookToSave = new BookTo();
		bookToSave.setId(null);
		// when
		bookService.saveBook(bookToSave);
		// then
		assertNotNull(bookToSave.getId());
	}

	@Test
	public void testShouldCheckBookId() {
		// given
		final BookTo bookToSave = new BookTo();
		bookToSave.setId(8L);
		// when
		bookService.saveBook(bookToSave);
		// then
		assertEquals(8L, bookToSave.getId().longValue());
	}

	@Test
	public void testShouldFindAllBooks() {
		// when
		List<BookTo> allBooks = bookService.findAllBooks();
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
		assertEquals(8, allBooks.size());
	}

	@Test
	@Ignore
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "Opium w rosole";
		// when
		List<BookTo> booksByTitle = bookService.findBooksByTitle(title);
		// then
		assertNotNull(booksByTitle);
		assertFalse(booksByTitle.isEmpty());
	}

}
