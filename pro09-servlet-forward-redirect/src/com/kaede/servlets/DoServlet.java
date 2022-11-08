package com.kaede.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * @author kaede
 * @create 2022-11-05
 */

@WebServlet("/do")
public class DoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置向页面输出内容格式
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();

        String name = (String) request.getAttribute("name");
        String age = (String) request.getAttribute("age");
        String hobby = (String) request.getAttribute("hobby");
        writer.write("<h3>" + name + "</h3>");
        writer.write("<h3>" + age + "</h3>");
        writer.write("<h3>" + hobby + "</h3>");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sex = request.getParameter("sex");
        String city = request.getParameter("city");
        String[] languages = request.getParameterValues("language");
        writer.write("用户名：" + username + "<br/>" + "密码：" + password + "<br/>" + "性别：" + sex + "<br/>" +
            "城市：" + city + "<br/>" + "使用过的语言：" + Arrays.toString(languages) + "<br/>");
        String characterEncoding = request.getCharacterEncoding();
    }

}
