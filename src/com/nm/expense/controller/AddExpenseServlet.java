package com.nm.expense.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.expense.service.ICots;
import com.nm.expense.service.IExpemse;
import com.nm.expense.service.imp.CotsImp;
import com.nm.expense.service.imp.IExpemseImp;
import com.nm.pojo.Cots;
import com.nm.pojo.Expemse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 报销单servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/expense/addExpenseServlet")
public class AddExpenseServlet extends HttpServlet {
    ICots iCots = new CotsImp();
    IExpemse iExpemse = new IExpemseImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //获取添加数据信息
            Expemse expemse = RequestBeanUtils.requestToBean(request, Expemse.class);
            //调用service层
            int i = iExpemse.addExpemseService(expemse);
            //设置提示用户的信息
            String tip ="报销单添加失败";
            if(i>0){
                tip = "报销单添加成功";
            }


            //无论报销单添加成功或者失败都需要进行查询费用明细并返回给页面
            List<Cots>  listCots = iCots.selectCotsList();
            //将查询到数据返回给页面
            request.setAttribute("listCots",listCots);

            //将数据返回给页面
            request.setAttribute("tip",tip);
            //跳转到页面
            request.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(request,response);


        }catch (Exception e){

            e.printStackTrace();
            //将数据返回给页面
            request.setAttribute("tip","报销单添加失败");
            request.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //进行查询所有可以报销的费用信息明细，并在页面上显示

            //调用service层数据
            List<Cots>  listCots = iCots.selectCotsList();
            //将查询到数据返回给页面
            request.setAttribute("listCots",listCots);


            //跳转至报销单的页面上
            request.getRequestDispatcher("/view/expense/expense/expense_add.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
