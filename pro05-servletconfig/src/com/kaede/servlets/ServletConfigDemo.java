package com.kaede.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
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

@WebServlet(urlPatterns = "/sc", initParams = {
    @WebInitParam(name = "name", value = "kaede"),
    @WebInitParam(name = "hobby", value = "anime")
})
public class ServletConfigDemo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //获取servletName
        String servletName = this.getServletName();
        writer.write("servletName == " + servletName + "<br/>");
        //返回servlet的初始化参数的名称的集合
        Enumeration<String> initParameterNames = this.getInitParameterNames();
        //遍历集合获取初始化参数名称
        while (initParameterNames.hasMoreElements()) {
            //获取初始化参数名称
            String initParamName = initParameterNames.nextElement();
            //获取相应的初始参数的值
            String initParamValue = this.getInitParameter(initParamName);
            //向页面输出
            writer.write(initParamName + "  :  " + initParamValue + "<br/>");
        }
        // 关闭流
        writer.close();
    }

}