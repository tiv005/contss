package com.nm.expense.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.expense.service.IExpemse;
import com.nm.expense.service.imp.IExpemseImp;
import com.nm.pojo.Expemse;
import com.nm.pojo.Users;
import com.nm.utiles.DateForMat;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 我的报销单
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/expense/myThisShowListServlet")
public class MyThisShowListServlet extends HttpServlet {
    IExpemse iExpemse = new IExpemseImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            //由于这是查询当前用户的登录信息
            //获取当前用户的id编号，在完成登录session获取用户的地编号
            Users users = (Users) request.getSession().getAttribute("users");

            //由于这里条件有时间
            ConvertUtils.register(new DateForMat(), Date.class);
            Expemse expemse = RequestBeanUtils.requestToSimpleBean(request, Expemse.class);

            //拼接当前用户的id
            expemse.setuId(users.getuId());
            //调用service层数据信息
            List<Expemse> expemsesList = iExpemse.UpdateExpemseManagerAudit(expemse);
            //将查询的结果返回页面
            request.setAttribute("list",expemsesList);

            //将查询的数据进行返回到页面
            request.setAttribute("expense",expemse);


            request.getRequestDispatcher("/view/expense/expense/expense_mylist.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //由于这是查询当前用户的登录信息
            //获取当前用户的id编号，在完成登录session获取用户的地编号
            Users users = (Users) request.getSession().getAttribute("users");


            Expemse expemse = new Expemse();
            //拼接当前用户的id
            expemse.setuId(users.getuId());
            //调用service层数据信息
            List<Expemse> expemsesList = iExpemse.UpdateExpemseManagerAudit(expemse);
            //将查询的结果返回页面
            request.setAttribute("list",expemsesList);



            request.getRequestDispatcher("/view/expense/expense/expense_mylist.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
