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
import java.io.IOException;

/**
 * 用户的添加servlet
 * @author 容学斌
 */
@WebServlet("/system/addUser")
public class AddUsersServlet extends HttpServlet {

    IUsersSerivce  iUsersSerivce=new UsersSerivceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
          try {
            //获取添加的数据信息
              Users users = RequestBeanUtils.requestToBean(request, Users.class);
            //调用Service中的方法
           int i = iUsersSerivce.addUsersService(users);
            if(i<1){
                request.setAttribute("tip","用户添加失败");
            }else{
                request.setAttribute("tip","用户添加成功");

            }
            //无论是否添加成功还是失败。
              // 那么都需要将对应的添加的数据信息返回给页面,

              request.setAttribute("user",users);
              request.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(request,response);

          }catch (Exception  e){
                e.printStackTrace();
              request.setAttribute("tip","用户添加失败");
              request.getRequestDispatcher("/view/system/user/userinfo_add.jsp").forward(request,response);

          }




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
