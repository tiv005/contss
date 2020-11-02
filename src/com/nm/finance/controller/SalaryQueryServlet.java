package com.nm.finance.controller;

import com.nm.pojo.Users;
import com.nm.system.service.IUsersSerivce;
import com.nm.system.service.imp.UsersSerivceImp;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/finance/salaryQuery")
public class SalaryQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //使用ajax请求，那么返回的是json数据
            //而不是请求路径地址
            //进行查询某个用户的底薪
            String userId = request.getParameter("userId");
            int id = Integer.parseInt(userId);
            //进行查询当前用户的数据信息
            IUsersSerivce iUsersSerivce = new UsersSerivceImp();
            Users users = new Users();
            users.setuId(id);
            List<Users> usersList = iUsersSerivce.selectUsersListAndWhere(users);
            //获取当前用户的数据对象
            //并且获取当前用户的底薪
            Float salary = usersList.get(0).getuSalary();

            //由于最终json数据信息页面
            //当前的数据不是json，将当前数据转化为json

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("salary",salary);
            PrintWriter writer = response.getWriter();
            writer.print(jsonObject);
            writer.flush();
            writer.close();
            //然后将json数据返回给页面



            //然后将底薪返回给页面显示数据

            //request.getRequestDispatcher("").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doPost(request,response);

    }
}
