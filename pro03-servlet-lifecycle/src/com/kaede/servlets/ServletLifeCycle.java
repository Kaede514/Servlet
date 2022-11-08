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

@WebServlet("/LifeCycle")
public class ServletLifeCycle extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private int initCount = 0;
    private int httpCount = 0;
    private int destoryCount = 0;

    @Override
    public void init() throws ServletException {
        initCount++;
        super.init();
        //向控制台输出init方法被调用次数
        System.out.println("---------- initCount = " + initCount + " ----------");
    }

    @Override
    public void destroy() {
        destoryCount++;
        super.destroy();
        //向控制台输出destory方法被调用次数
        System.out.println("---------- destoryCount = " + destoryCount + " ----------");
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        httpCount++;
        //控制台输出doGet方法次数
        System.out.println("doGet方法：" + httpCount);
        //设置返回页面格式与字符集
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        //向页面输出
        writer.write("初始化次数:" + initCount + "<br/>" + "处理请求次数:" + httpCount + "<br/>" + "销毁次数:" + destoryCount);
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}
