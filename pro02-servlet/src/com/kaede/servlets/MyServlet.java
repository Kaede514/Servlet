package com.kaede.servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author kaede
 * @create 2022-11-05
 */

@WebServlet("/MyServlet")
public class MyServlet implements Servlet {
    //Servlet实例被创建后，调用init()方法进行初始化，该方法只能被调用一次
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
    }

    //返回ServletConfig对象，该对象包含了Servlet的初始化参数
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //每次请求，都会调用一次service()方法
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        //设置字符集
        servletResponse.setContentType("text/html;charset=UTF-8");
        //使用PrintWriter.write()方法向前台页面输出内容
        PrintWriter writer = servletResponse.getWriter();
        writer.write("hello");
        writer.close();
    }

    //返回关于Servlet的信息，例如作者、版本、版权等
    @Override
    public String getServletInfo() {
        return null;
    }

    //Servelet 被销毁时调用
    @Override
    public void destroy() {
    }
}
