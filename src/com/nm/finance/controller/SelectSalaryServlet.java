package com.nm.finance.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.finance.service.ISalaryRec;
import com.nm.finance.service.imp.SalaryRecImp;
import com.nm.pojo.SalaryRec;
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
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/finance/selectSalaryServlet")
public class SelectSalaryServlet extends HttpServlet {
    ISalaryRec iSalaryRec = new SalaryRecImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //进行获取查询的的数据
            //格式化日期
            ConvertUtils.register(new DateForMat("yyyy-MM"), Date.class);
            //获取页面数据信息
            SalaryRec salaryRec = RequestBeanUtils.requestToSimpleBean(request, SalaryRec.class);

            //进行调用service层中查询的方法
            List<SalaryRec> salaryReclist =  iSalaryRec.selectSalaryList(salaryRec);
            //将查询的数据返回给页面
            request.setAttribute("salaryReclist",salaryReclist);

            //再一次将数据返回给页面到对应页面
            request.setAttribute("salary",salaryRec);


            request.getRequestDispatcher("/view/finance/salary/salarypayment_list.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            //进行调用service层中数据信息
            List<SalaryRec> salaryReclist =  iSalaryRec.selectSalaryList(new SalaryRec());

            //将查询的数据返回给页面
            request.setAttribute("salaryReclist",salaryReclist);
            request.getRequestDispatcher("/view/finance/salary/salarypayment_list.jsp").forward(request,response);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
