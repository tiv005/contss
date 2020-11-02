package com.nm.expense.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.expense.service.IExpemse;
import com.nm.expense.service.imp.IExpemseImp;
import com.nm.pojo.AuditRec;
import com.nm.pojo.Expemse;
import com.nm.pojo.ExpemseDetali;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/expense/showExpenseDetail")
public class ShowExpenseDetailServlet extends HttpServlet {
    IExpemse iExpemse = new IExpemseImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //查询报销单的详情数据信息
            String expenseId = request.getParameter("expenseId");

            //无论是审核成功或者失败都需要进行重新查询对应的报销明细和历史审核记录
            Expemse expemse = new Expemse();
            expemse.seteId(Integer.parseInt(expenseId));

            List<Expemse> expemses = iExpemse.UpdateExpemseManagerAudit(expemse);
            //将查询数据返回给页面
            //获取当前审核报销单id编号对应的数据信息
            request.setAttribute("expemse",expemses.get(0));

            /**
             * 查询报销单明细数据信息
             *      查询当前报销的明细数据信息
             *
             */
            List<ExpemseDetali> expemseDetali = iExpemse.selectExpemseDetaliList(expemses.get(0).geteId());
            //再一次将数据返回给页面
            request.setAttribute("expemseDetali",expemseDetali);

            /**
             * 查询当前报销单的审核的历史记录数据
             */

            //调用service层数据
            List<AuditRec> auditRecs = iExpemse.selectAuditRecList(expemses.get(0).geteId());
            //将数据返回给页面
            request.setAttribute("auditRecs",auditRecs);

            request.getRequestDispatcher("/view/expense/expense/expense_show.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
