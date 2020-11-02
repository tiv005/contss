package com.nm.system.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.finance.service.ISalaryRec;
import com.nm.finance.service.imp.SalaryRecImp;
import com.nm.pojo.SalaryRec;
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
 * 我的薪资servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/system/mySalaryServlet")
public class MySalaryServlet extends HttpServlet {
    ISalaryRec iSalaryRec = new SalaryRecImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //由于查询涉及时间就要格式化时间
            //格式化日期
            ConvertUtils.register(new DateForMat("yyyy-MM"), Date.class);
            //获取页面数据信息
            SalaryRec salaryRec = RequestBeanUtils.requestToSimpleBean(request, SalaryRec.class);
            //再一次获取用户的id
            Users users = (Users) request.getSession().getAttribute("users");
            //获取对应的编号
            Integer id = users.getuId();
            users.setuId(id);

            //进行service层查询数据
            List<SalaryRec> mySalaryList = iSalaryRec.selectSalaryList(salaryRec);
            request.setAttribute("mySalaryList",mySalaryList);

            //将查询的数据返回给页面
            request.setAttribute("salaryRec",salaryRec);
            request.getRequestDispatcher("/view/system/user/salarypayment_mylist.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //进行查询当前用户的信息
            //从session中获取用户id
            Users users = (Users) request.getSession().getAttribute("users");
            //获取对应的编号
            Integer id = users.getuId();
            //调用service中查询薪资的信息
            SalaryRec salaryRec = new SalaryRec();
            salaryRec.setuId(id);
            List<SalaryRec> mySalaryList = iSalaryRec.selectSalaryList(salaryRec);
            request.setAttribute("mySalaryList",mySalaryList);

            request.getRequestDispatcher("/view/system/user/salarypayment_mylist.jsp").forward(request,response);

        }catch (Exception e){

            e.printStackTrace();
        }


    }
}
