package com.nm.expense.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.expense.service.IExpemse;
import com.nm.expense.service.imp.IExpemseImp;
import com.nm.pojo.Expemse;
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
 * 报销单查询servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/expense/selectExpenseServlet")
public class SelectExpenseServlet extends HttpServlet {
    IExpemse iExpemse = new IExpemseImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //获取查询数据的信息
            //进行格式化时间
            ConvertUtils.register(new DateForMat(), Date.class);
            //获取查询数据的条件
            Expemse expemse = RequestBeanUtils.requestToSimpleBean(request, Expemse.class);

            //调用service层数据信息
            List<Expemse> expemsesList = iExpemse.UpdateExpemseManagerAudit(expemse);
            //将查询的结果返回页面
            request.setAttribute("expemsesList",expemsesList);

            //再一次将查询的数据返回给页面
            request.setAttribute("expense",expemse);
            //进行页面跳转
            request.getRequestDispatcher("/view/expense/expense/expense_list.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //调用service层数据信息
            List<Expemse> expemsesList = iExpemse.UpdateExpemseManagerAudit(new Expemse());
            //将查询的结果返回页面
            request.setAttribute("expemsesList",expemsesList);

            request.getRequestDispatcher("/view/expense/expense/expense_list.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
