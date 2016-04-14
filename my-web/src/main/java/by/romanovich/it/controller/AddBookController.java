package by.romanovich.it.controller;
import by.romanovich.it.pojos.books.Books;
import by.romanovich.it.pojos.books.Categories;
import by.romanovich.it.pojos.users.Users;
import by.romanovich.it.service.ServiceBook;
import by.romanovich.it.service.ServiceBooksImpl;
import by.romanovich.it.service.ServiceCategories;
import by.romanovich.it.service.ServiceCategoriesImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;



public class AddBookController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger.getLogger(AddBookController.class);

    public AddBookController() {
        super();

    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceCategories categoryServ = ServiceCategoriesImpl.getServiceCategories();
        List<Categories> categoryList = categoryServ.readAll();
        log.info(categoryList);
        request.setAttribute("categoryList", categoryList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/addBook.jsp");
        dispatcher.forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Books book;
        Users user = new Users();
        Categories category = new Categories();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String book_date = request.getParameter("book_date");
        float price = Float.parseFloat(request.getParameter("price"));
        Integer id_cat = Integer.parseInt(request.getParameter("id_cat"));
        Integer id_user = Integer.parseInt(request.getParameter("id_user"));

        user.setIdUser(id_user);
        category.setId_cat(id_cat);

        book = new Books(name, description, author, book_date, price, user, category);

        ServiceBook serviceBook = ServiceBooksImpl.getServiceBooks();
        try {
            serviceBook.create(book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("books");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            log.error(e);
        }

    }
}
