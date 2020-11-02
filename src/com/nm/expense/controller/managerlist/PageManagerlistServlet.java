package com.nm.expense.controller.managerlist;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.expense.service.IExpemse;
import com.nm.expense.service.imp.IExpemseImp;
import com.nm.pojo.Expemse;
import com.nm.utiles.DateForMat;
import com.nm.utiles.ThisStaticCode;
import org.apache.commons.beanutils.ConvertUtils;

import static com.nm.utiles.ThisStaticCode.*;

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
@WebServlet("/expense/pageManagerlistServlet")
public class PageManagerlistServlet extends HttpServlet {
    IExpemse iExpemse = new IExpemseImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //获取条件查询的信息
                //有时间限制 格式化时间
                //这里的Date 也是util包下
            ConvertUtils.register(new DateForMat(), Date.class);
            Expemse expemse = RequestBeanUtils.requestToSimpleBean(request, Expemse.class);
            //这里是查询经理的审核的报销数据信息
            expemse.setAuditeState(new String[]{OK_SUBMIT,OK_MANAGER,NO_MANAGER});
            System.out.println("查询格式化时间"+expemse.getStartDate());
            System.out.println("查询格式化时间"+expemse.getEndDate());
            //调用service层方法

            List<Expemse> list = iExpemse.UpdateExpemseManagerAudit(expemse);

            //再一次将查询数据返回给页面
            request.setAttribute("list",list);

            //再一次将数据返回给页面
            request.setAttribute("expemse",expemse);

            request.getRequestDispatcher("/view/expense/managerAudit/expense_managerlist.jsp").forward(request,response);

        }catch (Exception e){

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //调用service层
            //这里是查询经理的审核的报销数据信息
            Expemse expemse = new Expemse();
            expemse.setAuditeState(new String[]{OK_SUBMIT,OK_MANAGER,NO_MANAGER});

            List<Expemse> list = iExpemse.UpdateExpemseManagerAudit(expemse);

            //再一次将查询数据返回给页面
            request.setAttribute("list",list);

            request.getRequestDispatcher("/view/expense/managerAudit/expense_managerlist.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
