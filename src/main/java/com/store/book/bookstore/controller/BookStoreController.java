package com.store.book.bookstore.controller;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.model.SearchRequest;
import com.store.book.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {
    @Autowired
    BookStoreService bookStoreService;


    @RequestMapping(path = "/getbooks", method = RequestMethod.POST)
    public List<Book> searchBook(@RequestBody Book book)
    {
        return bookStoreService.searchBook(book);
    }

    @RequestMapping(path = "/getbooks/{orderby}", method = RequestMethod.GET)
    public @ResponseBody List<Book> getAllBook(@PathVariable("orderby") String orderBy)
    {
        return bookStoreService.getAllBookOrderBy(orderBy);
    }

    @RequestMapping(path = "/addbook", method = RequestMethod.POST)
    public boolean addBook(@RequestBody Book book)
    {
        return bookStoreService.addNewBook(book);
    }

    @RequestMapping(path = "/updatebook", method = RequestMethod.POST)
    public boolean updateBook(@RequestBody Book book)
    {
        return bookStoreService.updateExistingBook(book);
    }

    @RequestMapping(path = "/removebook/{bookid}", method = RequestMethod.DELETE)
    public boolean removeBook(@PathVariable("bookid") int bookId)
    {
        return bookStoreService.removeBook(bookId);
    }



}
