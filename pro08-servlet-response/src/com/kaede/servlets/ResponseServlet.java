package com.kaede.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author kaede
 * @create 2022-11-05
 */

@WebServlet("/ResponseServlet")
public class ResponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //run1(response);
        run2(response);
    }

    //使用字节流向页面输出
    public void run1(HttpServletResponse response) throws IOException {
        //设置浏览器打开文件时编码
        response.setHeader("Content-Type", "text/html;charset=UTF-8");
        //获取字节输出流
        OutputStream os = response.getOutputStream();
        byte[] str = "枫programming...".getBytes("UTF-8");
        //输出中文
        os.write(str);
    }

    //使用字符流向页面输出
    public void run2(HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write("枫programming...");
    }

}
