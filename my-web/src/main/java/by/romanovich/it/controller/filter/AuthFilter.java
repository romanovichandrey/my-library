package by.romanovich.it.controller.filter;

import by.romanovich.it.pojos.users.Users;
import by.romanovich.it.service.ServiceUser;
import by.romanovich.it.service.ServiceUsersImpl;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private static final Logger log = Logger.getLogger(AuthFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Users user;
        String login;
        String password;
        RequestDispatcher dispatcher;
        ServiceUser serviceUser = ServiceUsersImpl.getServiceUser();
        HttpSession session = ((HttpServletRequest)servletRequest).getSession(false);
        try {
            login = servletRequest.getParameter("login");
            password = servletRequest.getParameter("password");

            Boolean bool2 = serviceUser.isCheckAuthUser(login, password);
            if(bool2) {
                session.setAttribute("user", serviceUser.getUsersByEmailAndPassword(login, password));
            }
            user = (Users)session.getAttribute("user");
            login = user.getLogin();
            password = user.getPassword();
            Boolean bool = serviceUser.isCheckAuthUser(login, password);
            if(bool) {
                dispatcher = servletRequest.getRequestDispatcher("/books");
                dispatcher.forward(servletRequest, servletResponse);
            } else {
                dispatcher = servletRequest.getRequestDispatcher("WEB-INF/views/header.jsp");
                dispatcher.forward(servletRequest, servletResponse);
            }

        } catch (NullPointerException e) {
            log.error(e);
        }
        dispatcher = servletRequest.getRequestDispatcher("index.jsp");
        dispatcher.forward(servletRequest, servletResponse);
        return;


    }

    @Override
    public void destroy() {

    }
}
