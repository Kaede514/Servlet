package com.kaede.filter;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author kaede
 * @create 2022-11-06
 */

public class FirstChainFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setContentType("text/html;charset=UTF-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.write("FirstChainFilter 对请求进行处理<br/>");
        filterChain.doFilter(servletRequest, servletResponse);
        writer.write("FirstChainFilter 对响应进行处理<br/>");
    }

    @Override
    public void destroy() {
    }

}
