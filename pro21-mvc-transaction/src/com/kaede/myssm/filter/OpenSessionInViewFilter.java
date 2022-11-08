package com.kaede.myssm.filter;

import com.kaede.myssm.trans.TransactionManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;

@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try{
            //开启事务
            TransactionManager.beginTrans();
            filterChain.doFilter(servletRequest, servletResponse);
            //提交事务
            TransactionManager.commit();
        }catch (Exception e){
            e.printStackTrace();
            try {
                //回滚事务
                TransactionManager.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void destroy() {

    }

}
