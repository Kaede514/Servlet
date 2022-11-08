package com.kaede.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebFilter(
    urlPatterns = {"/login"},
    dispatcherTypes = {DispatcherType.REQUEST},
    initParams = {
        @WebInitParam(name = "name", value = "kaede"),
        @WebInitParam(name = "hobby", value = "anime")
    },
    servletNames = {"com.kaede.servlet.LoginServlet"}
)
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置向页面输出的格式与编码
        servletResponse.setContentType("text/html;charset=UTF-8");
        //通过Filter向页面输出内容
        servletResponse.getWriter().write("<h1>hello MyFilter</h1>");
    }

    @Override
    public void destroy() {
    }

}
