package com.kaede.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

/**
 * @author kaede
 * @create 2022-11-05
 */

@WebServlet("/read")
public class ReadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        InputStream is = null;
        try {
            //获取相对路径中的输入流对象
            is = getServletContext().getResourceAsStream("/WEB-INF/classes/jdbc.properties");
            //获取输入流
            Properties properties = new Properties();
            //加载
            properties.load(is);
            //获取文件中的内容
            String name = properties.getProperty("user");
            String pwd = properties.getProperty("password");
            String url = properties.getProperty("url");
            String driverClass = properties.getProperty("driverClass");
            writer.write("user：" + name + "<br/>" + "password：" + pwd + "<br/>" +
                "URL：" + url + "<br/>" + "driverClass：" + driverClass);
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

}
