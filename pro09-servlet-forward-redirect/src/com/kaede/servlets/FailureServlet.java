package com.kaede.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebServlet("/failure")
public class FailureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应输出的格式
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        int time = (int) getServletContext().getAttribute("time");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(time != 0) {
            String msg = "<h1>登陆失败，账号密码不正确</h1>" +
                "<h2>将在" + time + "秒后回到登录页面</h2>" +
                "<h2>当前时间为" + sdf.format(new Date()) + "</h2>";
            writer.write(msg);
            time--;
            //通过refresh头完成页面刷新
            resp.setHeader("refresh", "1;url=/pro09/failure");
            getServletContext().setAttribute("time", time);
        } else {
            resp.sendRedirect("/pro09/login.html");
        }
    }

}
