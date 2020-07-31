package com.spring.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Project springboot-web
 * @Package com.spring.web.servlet
 * @Author xuzhenkui
 * @Date 2020/4/4 8:45
 */
@WebServlet(name = "basicServlet", asyncSupported = true, urlPatterns = "/basic")
public class BasicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Welcome to Basic prod branch Servlet");
    }
}
