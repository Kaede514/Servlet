package com.kaede.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * @author kaede
 * @create 2022-11-05
 */

@WebServlet("/RequestHeader")
public class RequestHeader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //获得所有请求头字段的枚举集合
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            //获得请求头字段的值
            String value = request.getHeader(headers.nextElement());
            writer.write(headers.nextElement() + ":" + value + "<br/>");
        }
        writer.write("Content-Type:" + request.getContentType() + "<br/>");
        writer.write("Content-Length:" + request.getContentLength() + "<br/>");
        writer.write("CharacterEncoding:" + request.getCharacterEncoding() + "<br/>");
    }

}
