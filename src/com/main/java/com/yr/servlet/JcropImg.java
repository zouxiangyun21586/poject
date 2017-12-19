package com.yr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yr.util.ImageCut;

public class JcropImg extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        int x = Integer.parseInt(request.getParameter("x"));
        int y = Integer.parseInt(request.getParameter("y"));
        int w = Integer.parseInt(request.getParameter("w"));
        int h = Integer.parseInt(request.getParameter("h"));
        // 得到文件的绝对路径D:\Software\apache-tomcat-7.0.23\webapps\test\css\A.jpg
        String path = this.getServletContext().getRealPath("/") + "images\\" + "0.jpg";
        ImageCut.imgCut(path, x, y, w, h);
        response.sendRedirect("picture.jsp");
        out.flush();
        out.close();
    }
}