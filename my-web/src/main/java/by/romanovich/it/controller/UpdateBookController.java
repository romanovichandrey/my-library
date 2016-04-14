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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;

public class UpdateBookController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static Logger log = Logger.getLogger(UpdateBookController.class);

    public UpdateBookController() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        ServiceCategories categoryServ = ServiceCategoriesImpl.getServiceCategories();
        List<Categories> categorysList = categoryServ.readAll();
        @SuppressWarnings("unchecked")
        Enumeration<String> params = request.getParameterNames();
        log.info(params);
        String name = null;
        String description = null;
        String author = null;
        String book_date = null;
        String price = null;
        String id_book = null;

        while(params.hasMoreElements()) {

            String param = params.nextElement();
            name = "name".equals(param)?request.getParameter(param):name;
            description = "description".equals(param)?request.getParameter(param):description;
            author = "author".equals(param)?request.getParameter(param):author;
            book_date = "book_date".equals(param)?request.getParameter(param):book_date;
            price = "price".equals(param)?request.getParameter(param):price;
            id_book = "id_book".equals(param)?request.getParameter(param):id_book;

        }

        request.setAttribute("name", name);
        request.setAttribute("description", description);
        request.setAttribute("author", author);
        request.setAttribute("book_date", book_date);
        request.setAttribute("price", price);
        request.setAttribute("id_book", id_book);
        request.setAttribute("categoryList", categorysList);


        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/update.jsp");
        dispatcher.forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Books book;
        Users user = (Users)session.getAttribute("user");
        Categories category = new Categories();

        Integer id_book = Integer.parseInt(request.getParameter("id_book"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String author = request.getParameter("author");
        String book_date = request.getParameter("book_date");
        float price = Float.parseFloat(request.getParameter("price"));
        Integer id_cat = Integer.parseInt(request.getParameter("id_cat"));



        category.setId_cat(id_cat);


        book = new Books(id_book, name, description, author, book_date, price, user, category);

        ServiceBook serviceBook = ServiceBooksImpl.getServiceBooks();
        try {
            serviceBook.update(book);
        } catch (SQLException e) {
            log.error(e);
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher("books");
        dispatcher.forward(request, response);
    }
}
