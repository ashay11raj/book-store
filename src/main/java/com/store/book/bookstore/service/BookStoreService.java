package com.store.book.bookstore.service;

import com.store.book.bookstore.model.Book;

import java.util.List;


public interface BookStoreService {
    List<Book> getAllBookOrderBy(String orderBy);
    List<Book> searchBook(Book book);
    boolean addNewBook(Book book);
    boolean removeBook(int id);
    boolean updateExistingBook(Book book);
}
