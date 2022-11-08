package com.kaede.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kaede
 * @create 2022-11-06
 */

public class MyBindingListener implements HttpSessionBindingListener {

    private String username;

    public String getUsername() {
        return username;
    }

    public MyBindingListener() {
    }

    public MyBindingListener(String username) {
        super();
        this.username = username;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        List<String> onlineUserList = (List) application.getAttribute("onlineUserList");
        //第一次使用前，需要初始化
        if (onlineUserList == null) {
            onlineUserList = new ArrayList<String>();
        }
        //把用户名放入在线列表
        onlineUserList.add(this.username);
        application.setAttribute("onlineUserList", onlineUserList);
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent se) {
        HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        //从在线列表中删除用户名
        List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList");
        onlineUserList.remove(this.username);
        application.setAttribute("onlineUserList", onlineUserList);
    }

}
