package com.store.book.bookstore.controller;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {
    @Autowired
    BookStoreService bookStoreService;

    @RequestMapping(path = "/getallbook/{orderby}", method = RequestMethod.GET)
    public List<Book> getAllBook(@PathVariable("orderby") String orderBy)
    {

        return bookStoreService.getAllBookOrderBy(orderBy);
    }

}
