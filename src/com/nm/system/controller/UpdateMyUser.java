package com.nm.system.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.pojo.Users;
import com.nm.system.service.IUsersSerivce;
import com.nm.system.service.imp.UsersSerivceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 修改个人信息servlet
 * @author 容学斌
 */
@WebServlet("/system/updateMyUser")
public class UpdateMyUser extends HttpServlet {

    IUsersSerivce  iUsersSerivce=new UsersSerivceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          try {
          //进行获取数据(修改的数据信息)
        Users users = RequestBeanUtils.requestToBean(request, Users.class);
        System.out.println(users);

        //调用Service层方法
            int i = iUsersSerivce.updateUsersInfo(users);
               if(i<1){
                   //进行跳转到修改个人信息的页面
                   request.setAttribute("tip","用户信息修改失败");
                   request.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(request,response);
               }else{

                   //跳转到登陆页面，重写进行登陆
                   // request.getRequestDispatcher("/system/outLoginServlet").forward(request,response);

                   //清空session中的数据
                   HttpSession session = request.getSession();
                   session.removeAttribute("users");
                   session.removeAttribute("listMneu");
                  // response.sendRedirect("/view/login.jsp");
                   request.getRequestDispatcher("/view/login_info.jsp").forward(request,response);
               }
          }catch (Exception  e){
              e.printStackTrace();
              //进行跳转到修改个人信息的页面并将页面信息返回给用户
              request.setAttribute("tip","用户信息修改失败");
              request.getRequestDispatcher("/view/system/user/userinfo_show.jsp").forward(request,response);
          }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
