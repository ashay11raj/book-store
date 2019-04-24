package com.store.book.bookstore.service;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.model.SearchRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookStoreService {
    List<Book> getAllBookOrderBy(String orderBy);
    List<Book> searchBook(SearchRequest searchRequest);
    boolean addNewBook(Book book);
    boolean removeBook(long id);
    boolean updateExistingBook(Book book);
}
