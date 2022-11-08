package com.kaede.fruit.servlet;

import com.kaede.fruit.dao.FruitDAO;
import com.kaede.fruit.dao.impl.FruitDAOImpl;
import com.kaede.fruit.pojo.Fruit;
import com.kaede.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddServlet extends ViewBaseServlet {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String fname = request.getParameter("fname");
        Integer price = Integer.parseInt(request.getParameter("price")) ;
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        Fruit fruit = new Fruit(null, fname , price , fcount , remark ) ;
        fruitDAO.addFruit(fruit);

        response.sendRedirect("index");
    }

}
