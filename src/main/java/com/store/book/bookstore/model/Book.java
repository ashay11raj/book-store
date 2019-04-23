package com.store.book.bookstore.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;

public class Book {
    @NotNull(message = "required")
    long bookId;
    @NotNull(message = "required")
    String bookName;
    @NotNull(message = "required")
    String author;

    @DateTimeFormat(pattern="MM/dd/yyyy")
    @NotNull(message = "required") @Past
    Date dateOfPublication;

    public Book(@NotNull(message = "required") long bookId, @NotNull(message = "required") String bookName, @NotNull(message = "required") String author, @NotNull(message = "required") @Past Date dateOfPublication) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.dateOfPublication = dateOfPublication;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(Date dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

}