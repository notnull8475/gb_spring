package com.geekbrains.gbjavaee.servlets;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebServlet (urlPatterns = {"/first_servlet"}, name = "FirstServlet")
public class FirstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        log.info("New GET request");
        resp.getWriter().printf("<h1>New GET request</h1>");
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        log.info("New POST request");
        resp.getWriter().printf("<h1>New POST request</h1>");
    }
}

