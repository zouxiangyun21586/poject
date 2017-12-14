package com.yr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//ע��
public class OutServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // false����������session����ֻ�Ǵ�request�л�ȡ��
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute("username");
        // �Ӷ���index.jsp
        response.sendRedirect("login.jsp");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
