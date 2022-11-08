package com.kaede.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebServlet("/LoginTimeSession")
public class LoginTimeSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置页面输出的格式
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = response.getWriter();
        //记录当前的时间
        Date date = new Date();
        //时间的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //会话创建时间
        Date CreationTime = new Date(request.getSession().getCreationTime());
        //会话上次关联的时间
        Date LastAccessedTime = new Date(request.getSession().getLastAccessedTime());
        //格式化
        String sDate = sdf.format(date);
        //将当前时间赋值到session域对象中
        request.getSession().setAttribute("lastTime", sDate);
        //设置会话的失效时间
        request.getSession().setMaxInactiveInterval(30);
        //对session中各个信息输出到页面
        writer.write("<h1>hello</h1>" + "<h3>当前时间：" + sDate + "</h3>"
            + "当前会话的SessionID:  " + request.getSession().getId() + "<br/>"
            + "创建此会话的时间为：" + sdf.format(CreationTime) + "<br/>"
            + "Sesssion上次关联的时间为：" + sdf.format(LastAccessedTime) + "<br/>"
            + "会话保持打开状态的最大时间间隔：" + request.getSession().getMaxInactiveInterval() + "<br/>"
        );
    }

}
