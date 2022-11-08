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

public class FruitController {

    private FruitDAO fruitDAO = new FruitDAOImpl();

    private String update(Integer fid , String fname , Integer price , Integer fcount , String remark) {
        //3.执行更新
        fruitDAO.updateFruit(new Fruit(fid, fname, price ,fcount ,remark ));

        //4.资源跳转
        return "redirect:index.do";
    }

    private String edit(Integer fid , HttpServletRequest request){
        if(fid != null){
            Fruit fruit = fruitDAO.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            return "edit";
        }
        return "error";
    }

    private String delete(Integer fid) {
        if(fid != null){
            fruitDAO.delFruit(fid);
            return "redirect:index.do";
        }
        return "error";
    }

    private String toAdd() {
        return "add";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;
        fruitDAO.addFruit(fruit);
        return "redirect:index.do";
    }

    private String index(String oper, String keyword, Integer pageNo, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if(pageNo == null) {
            pageNo = 1;
        }
        //如果oper!=null，说明是通过表单的查询按钮点击过来的
        //如果oper是空的，说明不是通过表单的查询按钮点击过来的
        if("search".equals(oper)){
            //说明是点击表单查询发送过来的请求
            //此时，pageNo应该还原为1，keyword应该从请求参数中获取
            pageNo = 1;
            if(keyword == null) {
                keyword = "";
            }
            session.setAttribute("keyword",keyword);
        } else{
            Object keywordObj = session.getAttribute("keyword");
            if(keywordObj != null) {
                keyword = (String) keywordObj;
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

        return "index";
    }
}
