package com.kaede.fruit.controller;

import com.kaede.fruit.pojo.Fruit;
import com.kaede.fruit.service.FruitService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author kaede
 * @create 2022-11-07
 */

public class FruitController {

    private FruitService fruitService = null;

    private String update(Integer fid , String fname , Integer price , Integer fcount , String remark) {
        //3.执行更新
        fruitService.updateFruit(new Fruit(fid, fname, price ,fcount ,remark ));

        //4.资源跳转
        return "redirect:index.do";
    }

    private String edit(Integer fid , HttpServletRequest request){
        if(fid != null){
            Fruit fruit = fruitService.getFruitByFid(fid);
            request.setAttribute("fruit",fruit);
            return "edit";
        }
        return "error";
    }

    private String delete(Integer fid) {
        if(fid != null){
            fruitService.delFruit(fid);
            return "redirect:index.do";
        }
        return "error";
    }

    private String toAdd() {
        return "add";
    }

    private String add(String fname, Integer price, Integer fcount, String remark) {
        Fruit fruit = new Fruit(0,fname , price , fcount , remark ) ;
        fruitService.addFruit(fruit);
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
        List<Fruit> fruitList = fruitService.getFruitList(keyword, pageNo);
        session.setAttribute("fruitList",fruitList);

        //总页数
        int pageCount = fruitService.getPageCount(keyword);
        session.setAttribute("pageCount",pageCount);

        return "index";
    }

}
