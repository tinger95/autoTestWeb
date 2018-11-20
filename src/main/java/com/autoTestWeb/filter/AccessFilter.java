package com.autoTestWeb.filter;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AccessFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        String uri = request.getRequestURI();
        if (!uri.contains(".cs") && !uri.contains(".js") && !uri.contains(".png")) {
            //登陆页面无需过滤
            if (session.getAttribute("user") == null && request.getServletPath().indexOf("/login.go") == -1) {
                System.out.println();
                response.sendRedirect("/login.go");
                return;
            }
            if (session.getAttribute("user") != null && request.getServletPath().equals("/")) {
                response.sendRedirect("/main.go");
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    public void destroy() {

    }
}
