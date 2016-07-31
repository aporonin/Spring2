package pl.spring.demo.mock;

import static org.junit.Assert.*;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.BookTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class BookServiceImplTest {

   @InjectMocks
   private BookServiceImpl bookService;
  @Mock
    private BookDao bookDao;

    @Before(value = "")
    public void setUp() {
       MockitoAnnotations.initMocks(this);
   }

   @Test
    public void testShouldSaveBook() {
        // given
        BookTo book = new BookTo(null, "title", "author");
        Mockito.when(bookDao.save(book)).thenReturn(new BookTo(1L, "title", "author"));
        // when
        BookTo result = bookService.saveBook(book);
        // then
        Mockito.verify(bookDao).save(book);
        assertEquals(1L, result.getId().longValue());
    }
   

}
