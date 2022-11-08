package com.kaede.servlets;

import javax.servlet.ServletContext;
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

@WebServlet("/sc")
public class ServletContextDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //调用httpServlet父类GenericServlet的getServletContext方法获取ServletContext对象
        ServletContext context = super.getServletContext();
        //返回context上下文初始化参数的名称
        Enumeration<String> initParameterNames = context.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            //获取初始化参数名称
            String initParamName = initParameterNames.nextElement();
            //获取相应的初始参数的值
            String initParamValue = context.getInitParameter(initParamName);
            //向页面输出
            writer.write(initParamName + "  :  " + initParamValue + "<br/>");
        }
        // 关闭流
        writer.close();
    }

}
