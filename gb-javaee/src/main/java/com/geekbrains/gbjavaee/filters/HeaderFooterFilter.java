package com.geekbrains.gbjavaee.filters;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpFilter;
import java.io.IOException;

@Slf4j
//@WebServlet(urlPatterns = {"/*"})
public class HeaderFooterFilter extends HttpFilter {
    private transient FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
                         FilterChain chain) throws IOException, ServletException {
        filterConfig.getServletContext().
                getRequestDispatcher("/header.html").include(req, resp);
        chain.doFilter(req, resp);
        filterConfig.getServletContext().
                getRequestDispatcher("/footer.html").include(req, resp);
    }


    public void destroy() {
    }
}
