package com.kaede.fruit.controller;

import com.kaede.fruit.dao.FruitDAO;
import com.kaede.fruit.dao.impl.FruitDAOImpl;
import com.kaede.fruit.pojo.Fruit;
import com.kaede.myssm.myspringmvc.ViewBaseServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author kaede
 * @create 2022-11-07
 */

public class FruitController extends ViewBaseServlet {

    public void init(ServletContext servletContext) throws ServletException {
        super.init(servletContext);
    }

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //2.获取参数
        String fidStr = request.getParameter("fid");
        Integer fid = Integer.parseInt(fidStr);
        String fname = request.getParameter("fname");
        String priceStr = request.getParameter("price");
        int price = Integer.parseInt(priceStr);
        String fcountStr = request.getParameter("fcount");
        Integer fcount = Integer.parseInt(fcountStr);
        String remark = request.getParameter("remark");

        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price ,fcount ,remark ));

        //4.资源跳转
        response.sendRedirect("index.do");
    }

    private void edit(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        String fidStr = request.getParameter("fid");
        if(fidStr != null & !"".equals(fidStr)){
            int fid = Integer.parseInt(fidStr);
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            super.processTemplate("edit",request,response);
        }
    }

    private void delete(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        String fidStr = request.getParameter("fid");
        if(fidStr != null & !"".equals(fidStr)){
            int fid = Integer.parseInt(fidStr);
            fruitDAO.delFruit(fid);
            response.sendRedirect("index.do");
        }
    }

    private void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.processTemplate("add",request,response);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname = request.getParameter("fname");
        Integer price = Integer.parseInt(request.getParameter("price")) ;
        Integer fcount = Integer.parseInt(request.getParameter("fcount"));
        String remark = request.getParameter("remark");
        Fruit fruit = new Fruit(null, fname , price , fcount , remark ) ;
        fruitDAO.addFruit(fruit);

        response.sendRedirect("index.do");
    }

    private void index(HttpServletRequest request , HttpServletResponse response)throws IOException, ServletException {
        HttpSession session = request.getSession();
        Integer pageNo = 1;
        String oper = request.getParameter("oper");

        //如果oper!=null，说明是通过表单的查询按钮点击过来的
        //如果oper是空的，说明不是通过表单的查询按钮点击过来的
        String keyword = null;
        if("search".equals(oper)){
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1，keyword应该从请求参数中获取
            pageNo = 1;
            keyword = request.getParameter("keyword");
            if(keyword == null){
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        } else{
            //说明此处不是点击表单查询发送过来的请求（比如点击下面的上一页下一页或者直接在地址栏输入网址）
            //此时keyword应该从session作用域获取
            String pageNoStr = request.getParameter("pageNo");
            if(pageNoStr != null && !"".equals(pageNoStr)){
                pageNo = Integer.parseInt(pageNoStr);
            }
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null){
                keyword = (String)keywordObj;
            }else{
                keyword = "";
            }
        }

        session.setAttribute("pageNo",pageNo);
        List<Fruit> fruitList = fruitDAO.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList",fruitList);

        //总记录条数
        int fruitCount = fruitDAO.getFruitCount(keyword);
        //总页数
        int pageCount = (fruitCount+5-1)/5 ;
        session.setAttribute("pageCount",pageCount);

        super.processTemplate("index",request,response);
    }
}
