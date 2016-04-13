package by.romanovich.it.filter;

import by.romanovich.it.pojos.users.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthFilter implements Filter {

    private String errorPage;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if(filterConfig != null)
            errorPage = filterConfig.getInitParameter("error_page");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpSession session = ((HttpServletRequest)servletRequest).getSession(false);
        Users user;
        if(session == null) {
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher(errorPage);
            dispatcher.forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
