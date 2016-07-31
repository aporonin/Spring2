package pl.spring.demo.service;

import java.util.List;

import pl.spring.demo.to.BookTo;

/**
 * Interface BookService
 */
public interface BookService {

	/**
	 * @return list of all books
	 */
	List<BookTo> findAllBooks();

	/**
	 * @param title
	 * @return list of books with selected title
	 */
	List<BookTo> findBooksByTitle(String title);

	/**
	 * @param author
	 * @return list of books with selected author
	 */
	List<BookTo> findBooksByAuthor(String author);

	/**
	 * @param book
	 * @return save book;
	 */
	BookTo saveBook(BookTo book);
}
