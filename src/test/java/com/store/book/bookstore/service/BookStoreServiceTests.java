package com.store.book.bookstore.service;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.model.SearchRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookStoreServiceTests {
	@Autowired
	BookStoreService bookStoreService;

	@Test
	public void getAllBookOrderByTest() {
		List<Book> listOfBook = bookStoreService.getAllBookOrderBy("bookId");
		assertNotNull(listOfBook);
	}

	@Test
	public void searchBookTest() {
		SearchRequest searchRequest = new SearchRequest();
		searchRequest.setBookId(2);
		List<Book> listOfBook = bookStoreService.searchBook(searchRequest);
		assertEquals(listOfBook.get(0).getBookId(), 2);
	}

	@Test
	public void addNewBookTest() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Book book = null;
		try {
			book = new Book(11, "book11", "Author11", simpleDateFormat.parse("02/15/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean bookAdded = bookStoreService.addNewBook(book);
		assertTrue(bookAdded);
	}

	@Test
	public void addNewBookWithExistingBookIdTest() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Book book = null;
		try {
			book = new Book(1, "book1", "Author1", simpleDateFormat.parse("02/15/2017"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		boolean bookAdded = bookStoreService.addNewBook(book);
		assertFalse(bookAdded);
	}
	@Test
	public void removeBookWithValidBookIdTest() {
		boolean bookRemoved = bookStoreService.removeBook(1);
		assertTrue(bookRemoved);
	}
	@Test
	public void removeBookWithInvalidBookIdTest() {
		boolean bookRemoved = bookStoreService.removeBook(12);
		assertFalse(bookRemoved);
	}
}
