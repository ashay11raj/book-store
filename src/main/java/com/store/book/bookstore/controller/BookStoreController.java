package com.store.book.bookstore.controller;

import com.store.book.bookstore.model.Book;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController(value = "/bookstore")
@EnableAutoConfiguration
public class BookStoreController {

    @RequestMapping(path = "/getallbook/{orderby}", method = RequestMethod.GET)
    public List<Book> getAllBook(@PathVariable("orderby") String orderBy)
    {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy");
        List<Book> bookList = new ArrayList<Book>();
        try {
            bookList.add(new Book(1,"Book1","Author1",simpleDateFormat.parse("02/15/2017")));
            bookList.add(new Book(2,"Book2","Author2",simpleDateFormat.parse("08/25/2016")));
            bookList.add(new Book(3,"Book3","Author3",simpleDateFormat.parse("04/13/2018")));
            bookList.add(new Book(4,"Book4","Author4",simpleDateFormat.parse("11/19/2015")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return bookList;
    }

}
