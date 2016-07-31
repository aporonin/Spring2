package pl.spring.demo.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.BookTo;

/**
 * Implementation of interface MethodBeforeAdvice
 */
public class BookDaoAdvisor implements MethodBeforeAdvice {

	@Autowired
	private BookDao bookDao;

	/* 
	 * method set book Id if id is null
	 */
	@Override
	public void before(Method method, Object[] objects, Object o) throws Throwable {
		if (null != objects && objects.length > 0 && objects[0] instanceof BookTo) {
			BookTo bookTo = (BookTo) objects[0];
			if (null == bookTo.getId()) {
				bookTo.setId(bookDao.getNextBookID());
			}
		}
	}

	private boolean hasAnnotation(Method method, Object o, Class annotationClazz) throws NoSuchMethodException {
		boolean hasAnnotation = method.getAnnotation(annotationClazz) != null;
		if (!hasAnnotation && o != null) {
			hasAnnotation = o.getClass().getMethod(method.getName(), method.getParameterTypes())
					.getAnnotation(annotationClazz) != null;
		}
		return hasAnnotation;
	}
}
