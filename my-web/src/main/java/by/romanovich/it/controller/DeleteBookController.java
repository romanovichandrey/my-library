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
import java.sql.SQLException;

public class DeleteBookController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(DeleteBookController.class);


    public DeleteBookController() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String str = request.getQueryString();
        int id_book = Integer.parseInt(str.substring(8, 9));
        Books book;
        book = new Books(id_book);
        ServiceBook serviceBook = ServiceBooksImpl.getServiceBooks();
        try {
            serviceBook.delete(book);
        } catch (SQLException e) {
            log.error(e);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("books");
        dispatcher.forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }

}

