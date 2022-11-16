package com.steveny.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/lobby/*")
public class loginFilter implements Filter {

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession();
        Object userLogin = session.getAttribute("userLogin");
        if (userLogin != null) {
            filterChain.doFilter(request, servletResponse);
        } else {
            session.setAttribute("msgCase", "userNotLogin");
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath + "/login.html");
        }
    }

    public void init(FilterConfig filterConfig) {
    }


    public void destroy() {
    }
}
