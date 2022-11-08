package com.kaede.fruit.servlet;

import com.kaede.myssm.basedao.JDBCUtils;
import com.kaede.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-07
 */

@WebServlet("/index")
public class IndexServlet extends ViewBaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //渲染视图，前缀+名称+后缀 /index.html
        super.processTemplate("index", req, resp);
    }

}
