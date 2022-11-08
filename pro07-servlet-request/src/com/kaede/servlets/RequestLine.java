package com.kaede.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author kaede
 * @create 2022-11-05
 */

@WebServlet("/RequestLine")
public class RequestLine extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("请求方式:" + request.getMethod() + "<br/>" +
            "URI:" + request.getRequestURI() + "<br/>" +
            "请求字符串:" + request.getQueryString() + "<br/>" +
            "应用名字（上下文）:" + request.getContextPath() + "<br/>" +
            "Servlet所映射的路径:" + request.getServletPath() + "<br/>" +
            "客户端的IP地址:" + request.getRemoteAddr() + "<br/>" +
            "客户端的完整主机名:" + request.getRemoteHost() + "<br/>"
        );
    }

}