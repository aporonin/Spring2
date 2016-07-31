package pl.spring.demo.dao;

import java.util.List;

import pl.spring.demo.to.BookTo;

/**
 * Interface BookDao
 */
public interface BookDao {

	/**
	 * @return - list of books
	 */
	List<BookTo> findAll();

	/**
	 * @param title
	 * @return list of books with selected title
	 */
	List<BookTo> findBookByTitle(String title);

	/**
	 * @param author
	 * @return list of books with selected author
	 */
	List<BookTo> findBooksByAuthor(String author);

	/**
	 * @param book
	 * @return saved book
	 */
	BookTo save(BookTo book);

	/**
	 * @return book ID
	 */
	long getNextBookID();
}
