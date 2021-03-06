package com.store.book.bookstore.service.impl;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.service.BookStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class BookStoreServiceImpl implements BookStoreService {
    Logger logger = LoggerFactory.getLogger(BookStoreServiceImpl.class);
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    List<Book> bookList = new ArrayList<Book>(){
        {
            try {
                add(new Book(1, "Book1", "Author1", simpleDateFormat.parse("2017-02-15")));
                add(new Book(2,"Book2","Author2",simpleDateFormat.parse("2016-08-25")));
                add(new Book(3,"Book3","Author3",simpleDateFormat.parse("2018-04-13")));
                add(new Book(4,"Book4","Author4",simpleDateFormat.parse("2015-11-19")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public List<Book> getAllBookOrderBy(String orderBy) {
        logger.info("inside getAllBookOrderBy. fetching all books order by: {}", orderBy);
        if(orderBy != null){
            switch (orderBy){
                case "bookId":
                    this.bookList.sort((b1, b2) -> b1.getBookId() - b2.getBookId());
                    break;
                case "bookName":
                    this.bookList.sort((b1, b2) -> b1.getBookName().compareTo(b2.getBookName()));
                    break;
                case "author":
                    this.bookList.sort((b1, b2) -> b1.getAuthor().compareTo(b2.getAuthor()));
                    break;
                case "dateOfPublication":
                    this.bookList.sort((b1, b2) -> b1.getDateOfPublication().compareTo(b2.getDateOfPublication()));
                    break;
            }
        }
        logger.info("list of books: " + this.bookList);
        return this.bookList;
    }

    @Override
    public List<Book> searchBook(Book book) {
        List<Book> collect = this.bookList.stream().filter(b ->
            b.getBookId() == book.getBookId() || b.getBookName().equals(book.getBookName())
            || b.getAuthor().equals(book.getAuthor()) || (book.getDateOfPublication() != null && simpleDateFormat.format(b.getDateOfPublication()).equals(simpleDateFormat.format(book.getDateOfPublication())))
        ).collect(Collectors.toList());
        return collect;
    }

    @Override
    public boolean addNewBook(Book book) {
        Book existingBook = this.bookList.stream().filter(b -> b.getBookId() == book.getBookId()).findAny().orElse(null);
        if(book != null && existingBook == null){
            this.bookList.add(book);
            return true;
        }
        return false;
    }

    @Override
    public boolean removeBook(int bookId) {
        return this.bookList.removeIf(book -> book.getBookId() == bookId);
    }

    @Override
    public boolean updateExistingBook(Book book) {
        int index = IntStream.range(0, this.bookList.size())
                .filter(i -> book.getBookId() == this.bookList.get(i).getBookId())
                .findFirst()
                .orElse(-1);
        if(index != -1){
            this.bookList.set(index, book);
            return true;
        }
        return false;
    }
}
