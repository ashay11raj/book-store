package com.store.book.bookstore.controller;

import com.store.book.bookstore.model.Book;
import com.store.book.bookstore.service.BookStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value="onlinebookstore", description="API's to fetch book data from book store.")
public class BookStoreController {
    Logger logger = LoggerFactory.getLogger(BookStoreController.class);
    @Autowired
    BookStoreService bookStoreService;

    @ApiOperation(value = "View a list of available books matching search criteria.", response = List.class)
    @RequestMapping(path = "/getbooks", method = RequestMethod.POST)
    public List<Book> searchBook(@RequestBody Book book)
    {
        logger.info("inside BookStoreController searchBook method");
        return bookStoreService.searchBook(book);
    }
    @ApiOperation(value = "View a list of available books order by selected field in path variable.", response = List.class)
    @RequestMapping(path = "/getbooks/{orderby}", method = RequestMethod.GET)
    public @ResponseBody List<Book> getAllBook(@PathVariable("orderby") String orderBy)
    {
        logger.info("inside BookStoreController getAllBook method");
        return bookStoreService.getAllBookOrderBy(orderBy);
    }
    @ApiOperation(value = "Add new book in book store.", response = JSONObject.class)
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
    @ApiOperation(value = "Update existing book data.", response = JSONObject.class)
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
    @ApiOperation(value = "Remove existing book in the book store. this operation is only permitted for ADMIN user.", response = JSONObject.class)
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
    @ApiOperation(value = "Logout user from book store.", response = String.class)
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
