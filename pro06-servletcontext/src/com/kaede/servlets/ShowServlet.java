package com.kaede.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-05
 */

@WebServlet("/show")
public class ShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //获取ServletContext中存放的count属性（即页面的访问次数）
        Integer count = (Integer) getServletContext().getAttribute("count");
        //若CountServlet已被访问
        if (count != null) {
            response.getWriter().write("<h3>该网站一共被访问了" + count + "次</h3>");
        } else {
            //若CountServlet未被访问，提示先访问CountServlet
            response.getWriter().write("<h3>请先访问 CountServlet</h3>");
        }
    }

}
