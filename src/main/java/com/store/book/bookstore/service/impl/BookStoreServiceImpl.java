package com.store.book.bookstore.service.impl;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.model.SearchRequest;
import com.store.book.bookstore.service.BookStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    Logger logger = LoggerFactory.getLogger(BookStoreServiceImpl.class);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
    List<Book> bookList = new ArrayList<Book>(){
        {
            try {
                add(new Book(1, "Book1", "Author1", simpleDateFormat.parse("02/15/2017")));
                add(new Book(2,"Book2","Author2",simpleDateFormat.parse("08/25/2016")));
                add(new Book(3,"Book3","Author3",simpleDateFormat.parse("04/13/2018")));
                add(new Book(4,"Book4","Author4",simpleDateFormat.parse("11/19/2015")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public List<Book> getAllBookOrderBy(String orderBy) {
        logger.info("inside getAllBookOrderBy. fetching all books order by: {}", orderBy);
        return this.bookList;
    }

    @Override
    public List<Book> searchBook(SearchRequest searchRequest) {
        return null;
    }

    @Override
    public boolean addNewBook(Book book) {
        if(book != null){

        }
        return false;
    }

    @Override
    public boolean removeBook(long id) {
        return false;
    }

    @Override
    public boolean updateExistingBook(Book book) {
        return false;
    }
}
