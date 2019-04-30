# book-store
Simple spring boot REST API for a book store.

This application provide book data to the client as per thier requirments. Client can perform below operation with this application:
1. Add new book
2. Update existing book data
3. Remove book(Only user with ADMIN role)
4. Search book using any of the available field
5. Get list of all books order by any available field

Clone this application. Import it intellij or eclipse. Build and run the application. Below API's will be available on http://localhost:8080/

GET:  http://localhost:8080/bookstore/getbooks/{orderBy}
POST: http://localhost:8080/bookstore/getbooks
POST: http://localhost:8080/bookstore/addbook
POST: http://localhost:8080/bookstore/updatebook
DELETE: /bookstore/removebook/{bookId}

Please refer detailed API documentation on below URL:
http://localhost:8080/swagger-ui.html