package com.kaede.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-05
 *
 * 使用ServletContext 统计访问次数
 */

@WebServlet("/count")
public class CountServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        //获取ServletContext对象
        ServletContext context = getServletContext();
        //初始化时，向ServletContext中设置count属性，初始值为0
        context.setAttribute("count", 0);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //调用httpServlet父类GenericServlet的getServletContext方法获取ServletContext对象
        ServletContext context = super.getServletContext();
        //获取count的值，自增
        Integer count = (Integer) context.getAttribute("count");
        //存入到域对象中
        context.setAttribute("count", ++count);
        //向页面输出内容
        response.getWriter().write("<h3>hello</h3>");
    }

}
