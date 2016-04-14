package by.romanovich.it.controller;
import by.romanovich.it.pojos.books.Books;
import by.romanovich.it.service.ServiceBook;
import by.romanovich.it.service.ServiceBooksImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class BookController extends HttpServlet {

    private static final Logger log = Logger.getLogger(BookController.class);

    private static final long serialVersionUID = 1L;


    public BookController() {
        super();
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceBook bookServ = ServiceBooksImpl.getServiceBooks();
        List<Books> booksList = bookServ.readAll();
        log.info(booksList);
        request.setAttribute("bookList", booksList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/allBooks.jsp");
        dispatcher.forward(request, response);


    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

}

