package com.kaede.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author kaede
 * @create 2022-11-06
 *
 * 黑名单过滤器
 */

@WebFilter(
    urlPatterns = {"/login2"},
    initParams = {
        @WebInitParam(name = "user1", value = "user"),
        @WebInitParam(name = "user2", value = "root")
    }
)
public class BlackListFilter implements Filter {

    private FilterConfig fConfig;

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        this.fConfig = fConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        Boolean success = true;
        //获取前台登录的账号信息
        String name = request.getParameter("username");
        //获取过滤器中的初始化参数
        Enumeration<String> blackListNames = fConfig.getInitParameterNames();
        //判断前台登录账号是否为空
        if ("".equals(name)) {
            response.getWriter().write("用户名不能为空");
        } else {
            //登录账号不为空，循环遍历黑名单
            while (blackListNames.hasMoreElements()) {
                //若登录账号是黑名单账号则不允许登录
                if (fConfig.getInitParameter(blackListNames.nextElement()).equals(name)) {
                    success = false;
                    break;
                }
            }
            if (success) {
                chain.doFilter(request, response);
            } else {
                response.getWriter().write("<h1 style=\"color: red\">温馨提示：您的账号存在风险，暂时不能为您提供服务</h1>");
            }
        }
    }

    @Override
    public void destroy() {
    }

}