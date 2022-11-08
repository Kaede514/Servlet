package com.kaede.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置页面输出格式
        resp.setContentType("text/html;charset=UTF-8");
        //修改req缓冲区的字符集为UTF-8
        req.setCharacterEncoding("utf-8");
        PrintWriter writer = resp.getWriter();
        //获取表单数据
        String username = req.getParameter("username");
        //查看当前会话是否已有账号登录
        String logined = (String) req.getSession().getAttribute("username");
        if (username == null || "".equals(username)) {
            System.out.println("非法操作，您没有输入用户名");
            resp.sendRedirect("/pro12/login.html");
        } else {
            if (username.equals(logined)) {
                writer.write("<h1>hello</h1>"
                    + "<h3>您好，您已经登录了账户：" + logined + "</h3>"
                    + "如要登录其他账号，请先退出当前账号重新登录！");
            } else {
                //将当前账号加入会话中
                req.getSession().setAttribute("username", username);
                writer.write("<h1>hello</h1>"
                    + "<h3>" + username + "，欢迎您的到来</h3>");
            }
            //从上下文中获取已经登录账号的集合
            List<String> onlineUserList = (List<String>) this.getServletContext().getAttribute("onlineUserList");
            if (onlineUserList != null) {
                //向页面输出结果
                writer.write("<h3>当前在线人数为：" + onlineUserList.size() + "</h3>" +
                    "<table border=\"1\" width=\"20%\">");
                for (int i = 0; i < onlineUserList.size(); i++) {
                    writer.write("<tr>" + "<td align=\"center\">" + onlineUserList.get(i) + " </td>" + "</tr>");
                }
            }
            writer.write("</table><br/>" + "<a href=\"/pro12/logout\">退出登录</a>");
        }
    }

}
