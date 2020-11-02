package com.nm.expense.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.expense.service.ICots;
import com.nm.expense.service.IExpemse;
import com.nm.expense.service.imp.CotsImp;
import com.nm.expense.service.imp.IExpemseImp;
import com.nm.pojo.AuditRec;
import com.nm.pojo.Cots;
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
@WebServlet("/expense/updateExpenseDetail")
public class UpdateExpenseDetailServlet extends HttpServlet {
    IExpemse iExpemse = new IExpemseImp();
    ICots iCots = new CotsImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //进行修改报销单数据信息
            Expemse expemse = RequestBeanUtils.requestToBean(request, Expemse.class);

            //进行调用service层数据
           int i =  iExpemse.UpdateExpenseManager(expemse);
           //提示修改状态
           String tip = "修改失败";
           if(i>0){
                tip = "修改成功";
           }


            //无论是修改成功或者失败，都要将审核历史记录 与当前报销单数据返回给页面
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

            /**
             * 查询所有费用明细数据
             */

            List<Cots> list = iCots.selectCotsList();
            request.setAttribute("Cotslist",list);



            request.setAttribute("tip",tip);
            request.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(request,response);

        }catch (Exception e){
            request.setAttribute("tip","修改失败");
            request.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(request,response);
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            //查询报销单的详情数据信息
            String expenseId = request.getParameter("updateExpenseId");

            //无论是审核成功或者失败都需要进行重新查询对应的报销明细和历史审核记录
            Expemse expemse = new Expemse();
            expemse.seteId(Integer.parseInt(expenseId));

            List<Expemse> expemses = iExpemse.UpdateExpemseManagerAudit(expemse);
            System.out.println(expemses);
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

            /**
             * 查询所有费用明细数据
             */

            List<Cots> list = iCots.selectCotsList();
            request.setAttribute("Cotslist",list);

            request.getRequestDispatcher("/view/expense/expense/expense_update.jsp").forward(request,response);

        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
