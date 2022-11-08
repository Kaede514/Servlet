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

@WebServlet("/DispatcherServlet")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置向页面输出内容格式
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //尝试在请求转发前向response缓冲区写入内容，最后在页面查看是否展示
        writer.write("<h1>这是转发前在响应信息内的内容！</h1>");
        //向reuqest域对象中添加属性，传递给下一个web资源
        request.setAttribute("name", "kaede");
        request.setAttribute("age", "18");
        request.setAttribute("hobby", "动画");
        //转发
        request.getRequestDispatcher("/do").forward(request, response);
    }

}
