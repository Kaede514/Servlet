package com.kaede.listener;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebListener
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        //从上下文中获取已经登录账号的集合
        List<String> onlineUserList = (List<String>) se.getSession().getServletContext().getAttribute("onlineUserList");
        //在上下文中没有登陆用户
        if(onlineUserList == null || onlineUserList.size() == 0) {
            onlineUserList = new ArrayList<String>();
        }
        String username =(String) se.getSession().getAttribute("username");
        //向已登录集合中添加当前账号
        onlineUserList.add(username);
        se.getSession().getServletContext().setAttribute("onlineUserList", onlineUserList);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        ServletContext application = session.getServletContext();
        List<String> onlineUserList = (List<String>) application.getAttribute("onlineUserList");
        //取得登录的用户名
        String username = (String) session.getAttribute("username");
        if(username != null && !"".equals(username) && onlineUserList != null && onlineUserList.size() > 0 ) {
            //从在线列表中删除用户名
            onlineUserList.remove(username);
        }
    }

}
