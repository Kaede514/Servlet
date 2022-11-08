package com.kaede.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebServlet("/LoginTime")
public class LoginTime extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 1.获取所有的cookie，判断是否是第一次访问
         * 2.如果是第一次访问
         *    * 输出欢迎，记录当前的时间，回写到浏览器
         * 3.如果不是第一次访问
         *    * 获取时间，输出到浏览器，记录当前的时间，回写到浏览器
         */
        //设置字符中文乱码问题
        response.setContentType("text/html;charset=UTF-8");
        //获取所有的cookie
        Cookie[] cookies = request.getCookies();
        //通过指定cookie名称来查找cookie
        Cookie cookie = getCookieByName(cookies, "lastTime");
        //判断，如果cookie==null，说明是第一次访问
        if (cookie == null) {
            //输出欢迎，记录当前的时间，回写到浏览器
            response.getWriter().write("<h1>hello</h1>" + "<h3>欢迎您的到来！</h3>");
        } else {
            //获取cookie的值，输出浏览器，记录当前的时间，回写到浏览器
            String value = cookie.getValue();
            //输出浏览器（cookie的值中含有""，需要进行解码）
            response.getWriter().write("<h1>hello</h1>" + "<h3>欢迎您的归来!</h3>" +
                "<h3>您上次的时间是" + URLDecoder.decode(value) +
                "</h3>" + "<a href=\"/pro10/RemoveCookie\">清除Cookie</a>");
        }
        //记录当前的时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sDate = sdf.format(date);
        //使用cookie回写（cookie的值中含有""，需要进行编码才能使用）
        Cookie c = new Cookie("lastTime", URLEncoder.encode(sDate));
        //设置有效时间为30秒
        c.setMaxAge(30);
        //设置有效路径
        c.setPath("/pro10");
        //回写
        response.addCookie(c);
    }

    //通过指定名称查找指定的cookie
    public static Cookie getCookieByName(Cookie[] cookies, String name) {
        if (cookies == null) {
            return null;
        } else {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) {
                    return cookie;
                }
            }
            return null;
        }
    }

}
