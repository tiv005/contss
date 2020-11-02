package com.nm.system.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户退出servlet
 * @author 容学斌
 */
@WebServlet("/system/outLoginServlet")
public class OutLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

          //进行退出
        //进行格式化字符集
       // request.setCharacterEncoding("utf-8");
       // request.setCharacterEncoding("utf-8");
        //退出，就是清空Session中的数据，然后在进行跳转到登陆的页面

        HttpSession session = request.getSession();
        session.removeAttribute("users");
        session.removeAttribute("listMneu");
        session.removeAttribute("salaryRecs");
        //进行跳转到页码
        request.getRequestDispatcher("/view/login.jsp").forward(request,response);
//        response.sendRedirect("/view/login.jsp");

    }
}
