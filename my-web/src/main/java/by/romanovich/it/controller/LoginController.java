package by.romanovich.it.controller;

import by.romanovich.it.pojos.users.Citys;
import by.romanovich.it.pojos.users.Readers;
import by.romanovich.it.pojos.users.Streets;
import by.romanovich.it.pojos.users.Users;
import by.romanovich.it.service.ServiceUser;
import by.romanovich.it.service.ServiceUsersImpl;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class LoginController extends HttpServlet {

    private Users user;

    private Citys city;

    private Streets street;

    private Readers reader;

    private static final long serialVersionUID = 1L;

    private static final Logger log = Logger.getLogger(LoginController.class);

    public LoginController() {
        super();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/views/registr.jsp");
        dispatcher.forward(request, response);

    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String r_name = request.getParameter("r_name");
        String surname = request.getParameter("surname");
        String userTel = request.getParameter("userTel");
        String c_name = request.getParameter("c_name");
        String s_name = request.getParameter("s_name");
        ServiceUser serviceUser = ServiceUsersImpl.getServiceUser();
        city = new Citys(c_name);
        street = new Streets(s_name);
        reader = new Readers(r_name, surname, userTel, city, street);
        user = new Users(login, password, reader);
        HttpSession session = request.getSession();
        if(user != null) {
            try {
                session.setAttribute("user", serviceUser.create(city, street, reader, user));
            } catch (SQLException e) {
                log.error(e);
            }
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/books");
        dispatcher.forward(request, response);
    }
}
