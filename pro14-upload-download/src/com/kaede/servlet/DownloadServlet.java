package com.kaede.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author kaede
 * @create 2022-11-06
 */

@WebServlet("/download")
public class DownloadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        String fileName = req.getParameter("fileName");
        if(fileName == null || "".equals(fileName.trim())) {
            resp.getWriter().write("请输入要下载的文件名");
            return;
        }
        //得到图片存放的路径
        String realPath = this.getServletContext().getRealPath("/download/");
        System.out.println("realPath = " + realPath);
        File file = new File(realPath + fileName);
        //判断文件是否存放并且是一个标准文件
        if(file.exists() && file.isFile()) {
            resp.setContentType("application/x-msdownload");
            resp.setHeader("Content-Disposition","attachment;filename=" + fileName);
            FileInputStream fis = new FileInputStream(file);
            ServletOutputStream sos = resp.getOutputStream();
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = fis.read(bytes)) != -1) {
                sos.write(bytes);
            }
            sos.close();
            fis.close();
        } else{
            resp.getWriter().write("文件不存在，请重试！");
        }
    }

}
