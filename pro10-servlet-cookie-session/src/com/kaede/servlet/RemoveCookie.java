package com.kaede.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebServlet("/RemoveCookie")
public class RemoveCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取cookie
        Cookie cookie = new Cookie("lastTime", "");
        //设置有效时间为0，删除cookie
        cookie.setMaxAge(0);
        //设置有效路径，必须与要删除的Cookie的路径一致
        cookie.setPath("/pro10");
        //回写
        resp.addCookie(cookie);
        //重定向
        resp.sendRedirect("/pro10/LoginTime");
    }

}
