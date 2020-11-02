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
 * 用户删除servlet
 * @author 容学斌
 */
@WebServlet("/system/deleteUsers")
public class DeleteUsersServlet extends HttpServlet {
    //调用service层
    IUsersSerivce  iUsersSerivce = new UsersSerivceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Users users = RequestBeanUtils.requestToBean(request, Users.class);

            int  i=  iUsersSerivce.deleteUsersServcie(users);
            String  tip="删除成功";

            if(i<1){
                tip="删除失败";
            }
            request.setAttribute("tip",tip);
            //进行跳转到对应的页面
            request.getRequestDispatcher("/system/pageUsersListServlet").forward(request,response);

        }catch (Exception  e){

            e.printStackTrace();
        }
    }
}
