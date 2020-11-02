package com.nm.finance.controller;

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
@WebServlet("/financ/FinancAaudit")
public class FinancAauditDetalServlet extends HttpServlet {
    IExpemse iExpemse = new IExpemseImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            //获取审核提交的数据
            // RequestBeanUtils.requestToBean(request,Expemse.class);
            AuditRec auditRec = RequestBeanUtils.requestToBean(request, AuditRec.class);
            //调用service层的方法
            int i = iExpemse.addExpenseAduitMessage(auditRec);
            String tip = "审核失败";
            if(i>0){
                tip = "审核成功";

            }
            //提示用户的信息返回给页面
            request.setAttribute("tip",tip);

            //无论是审核成功或者失败都需要进行重新查询对应的报销明细和历史审核记录
            Expemse expemse = new Expemse();
            expemse.seteId(auditRec.geteId());

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

            request.getRequestDispatcher("/view/finance/financAaudit/financeaudit_audit.jsp").forward(request,response);
        }catch (Exception e){
            String tip = "审核失败";
            request.setAttribute("tip",tip);
            request.getRequestDispatcher("/view/finance/financAaudit/financeaudit_audit.jsp").forward(request,response);
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            //调用service层数据

            //需要获取审核的报销单的id编号
            String aduitId = request.getParameter("financExpenseId");
            //调用service方法
            Expemse expemse = new Expemse();
            expemse.seteId(Integer.parseInt(aduitId));
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


            request.getRequestDispatcher("/view/finance/financAaudit/financeaudit_audit.jsp").forward(request,response);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
