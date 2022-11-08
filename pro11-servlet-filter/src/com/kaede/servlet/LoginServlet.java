package com.kaede.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置向页面输出的格式与编码
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<h3>LoginServlet</h3>");
    }

}
