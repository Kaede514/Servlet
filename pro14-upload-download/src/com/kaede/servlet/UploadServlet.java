package com.kaede.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-06
 */

@MultipartConfig
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        System.out.println("name = " + name);
        //获取Part对象，参数为表单中文件域的name属性值
        Part myfile = req.getPart("myfile");
        //通过Part对象得到上传的文件名
        String fileName = myfile.getSubmittedFileName();
        System.out.println("fileName = " + fileName);
        //上传文件到指定目录
        myfile.write("E:/" + fileName);
    }

}
