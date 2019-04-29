package com.store.book.bookstore.controller;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.service.BookStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.json.simple.JSONObject;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/bookstore")
public class BookStoreController {
    Logger logger = LoggerFactory.getLogger(BookStoreController.class);
    @Autowired
    BookStoreService bookStoreService;


    @RequestMapping(path = "/getbooks", method = RequestMethod.POST)
    public List<Book> searchBook(@RequestBody Book book)
    {
        logger.info("inside BookStoreController searchBook method");
        return bookStoreService.searchBook(book);
    }

    @RequestMapping(path = "/getbooks/{orderby}", method = RequestMethod.GET)
    public @ResponseBody List<Book> getAllBook(@PathVariable("orderby") String orderBy)
    {
        logger.info("inside BookStoreController getAllBook method");
        return bookStoreService.getAllBookOrderBy(orderBy);
    }

    @RequestMapping(path = "/addbook", method = RequestMethod.POST)
    public JSONObject addBook(@RequestBody Book book)
    {
        logger.info("inside BookStoreController addBook method");
        JSONObject response = new JSONObject();
        if(bookStoreService.addNewBook(book)){
            response.put("errorcode", "000");
            response.put("errormsg", "Add Book: SUCCESS");
        } else{
            response.put("errorcode", "100");
            response.put("errormsg", "Add Book: FAILED");
        }
        return response;
    }

    @RequestMapping(path = "/updatebook", method = RequestMethod.POST)
    public JSONObject updateBook(@RequestBody Book book)
    {
        logger.info("inside BookStoreController updateBook method");
        JSONObject response = new JSONObject();
        if(bookStoreService.updateExistingBook(book)){
            response.put("errorcode", "000");
            response.put("errormsg", "Update Book: SUCCESS");
        } else{
            response.put("errorcode", "100");
            response.put("errormsg", "Update Book: FAILED");
        }
        return response;
    }

    @RequestMapping(path = "/removebook/{bookid}", method = RequestMethod.DELETE)
    public JSONObject removeBook(@PathVariable("bookid") int bookId)
    {
        logger.info("inside BookStoreController removeBook method");
        JSONObject response = new JSONObject();
        if(bookStoreService.removeBook(bookId)){
            response.put("errorcode", "000");
            response.put("errormsg", "Remove Book: SUCCESS");
        } else{
            response.put("errorcode", "100");
            response.put("errormsg", "Remove Book: FAILED");
        }
        return response;
    }

    /*@RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request,
                       HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (request.isRequestedSessionIdValid() && session != null) {
            session.invalidate();
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
            cookie.setValue(null);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }*/
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
