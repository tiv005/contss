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
import java.util.List;

/**
 * 修改用户的servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/system/updateUser")
public class UpdateUserServlet extends HttpServlet {
    IUsersSerivce iUsersSerivce = new UsersSerivceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

            //获取需要修改的信息（修改数据信息）
            Users users = RequestBeanUtils.requestToBean(request, Users.class);

            //进行调用service层的数据信息
           int i =  iUsersSerivce.updateUsersService(users);
           //默认情况下是修改成功的
            String str = "修改失败";
           if(i>0){
               str= "修改成功";

           }
           //将提示信息数据反馈回页面
            request.setAttribute("tip",str);

           //需要将再一次修改数据返回给页面
            request.setAttribute("users",users);
            //进行跳转页面
            request.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(request,response);

        }catch (Exception e){
            //出现异常
            request.setAttribute("tip","修改失败");
            e.printStackTrace();
            request.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{

//            int uid = 0;
//            if(request.getParameter("uId") != null && !"".equals(request.getParameter("uId"))) {
//                uid = Integer.parseInt(request.getParameter("uId"));
//            }

            //获取数据数据信息id编号进行查询出元数据（没有修改数据信息）
            String uId = request.getParameter("uId");
            System.out.println(uId);

            //将String类型转化为int类型
            int uid = Integer.parseInt(uId);

            //调用service层数据，来进行查询需要修改的数据,调用之前现成查询数据
            Users users = new Users();
            users.setuId(uid);
            //这里通过id通过查询数据，这里查询出来数据是一条
            List<Users> users1 = iUsersSerivce.selectUsersListAndWhere(users);
            Users us = users1.get(0);
            System.out.println(us);

            //再一次将数据返回页面
            request.setAttribute("users",us);

            //进行跳转到jsp页面
            request.getRequestDispatcher("/view/system/user/userinfo_update.jsp").forward(request,response);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
