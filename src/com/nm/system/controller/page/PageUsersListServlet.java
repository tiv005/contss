package com.nm.system.controller.page;

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
 * 用户管理servlet
 * @author 容学斌
 */
@WebServlet("/system/pageUsersListServlet")
public class PageUsersListServlet extends HttpServlet {
    IUsersSerivce  iUsersSerivce = new UsersSerivceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //获取条件查询的值
            Users users = RequestBeanUtils.requestToBean(request, Users.class);

            //调用Serivce层的方法
            //
            List<Users> list = iUsersSerivce.selectUsersListAndWhere(users);
            //将数据返回给页面
            request.setAttribute("list",list);
            //将条件查询的数据返回给页面
            request.setAttribute("users",users);


        }catch (Exception  e){

            e.printStackTrace();
        }

        request.getRequestDispatcher("/view/system/user/userinfo_list.jsp").forward(request,response);



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //当用户第一次点击用户管理的时候，就需要查询出所有用的用户信息
        //null是没有对应的查询条件的,所在这里为null ,没有任何的条件值进行查询所有的用户信息
          try {
              //调用Serivce层的方法
              List<Users> list = iUsersSerivce.selectUsersListAndWhere(new Users());
              //将数据返回给页面
              request.setAttribute("list",list);
            }catch (Exception  e){
                e.printStackTrace();
            }
        request.getRequestDispatcher("/view/system/user/userinfo_list.jsp").forward(request,response);
    }
}
