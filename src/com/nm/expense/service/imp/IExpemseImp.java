package com.nm.expense.service.imp;

import com.nm.expense.dao.IExpemseDao;
import com.nm.expense.dao.imp.IExpemseDaoImp;
import com.nm.expense.service.IExpemse;
import com.nm.pojo.AuditRec;
import com.nm.pojo.Expemse;
import com.nm.pojo.ExpemseDetali;
import com.nm.utiles.ThisAssert;
import com.nm.utiles.ThisStaticCode;

import static com.nm.utiles.ThisStaticCode.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class IExpemseImp implements IExpemse {
    IExpemseDao iExpemseDao = new IExpemseDaoImp();
    /**
     * 添加报销单的方法
     * @return
     * @throws Exception
     */
    @Override
    public int addExpemseService(Expemse expemse) throws Exception {
        //可以进行断言，报销单的名称不能为空
        ThisAssert.isNotNull("报销单的名称不能为空",expemse.geteName());
        expemse.seteMark(ThisStaticCode.OK_CODE+"");
        //调用Dao层方法

        return iExpemseDao.addExpemseDao(expemse);
    }

    /**
     * 审核查询对应报销单的信息
     * @param expemse  查询的条件数据
     * @return
     */
    @Override
    public List<Expemse> UpdateExpemseManagerAudit(Expemse expemse) throws Exception {
        //进行调用DAO数据

        ArrayList<Expemse> arrayList = new ArrayList<>();
        List<Expemse> list = iExpemseDao.UpdateExpemseManagerAuditDao(expemse);

        //将状态码设置彩色按钮
        for (Expemse expemse1 : list) {
            if(OK_SUBMIT.equals( expemse1.geteState())){
                //设置能看得到,已提交审核的
                expemse1.seteStateHtml("<button type='button' class='btn btn-info'>已提交审核的</button>");
                //经理可以进行审核的，已经提交审核的和经理审核失败的
                //可以再一次进行审核
                expemse1.setManageAduit("<a type='button' class='btn btn-link' href='expense/manageAduitExpense?aduitId="+expemse1.geteId()+"' >审核</a>");
                expemse1.setUpdateUrl("<a type='button' class='btn btn-link' href='expense/showExpenseDetail?expenseId="+expemse1.geteId()+"' >查询详情</a>");
            }
            //设置能看得到,未提交审核的
            if(NO_SUBMIT.equals( expemse1.geteState())){

                expemse1.seteStateHtml("<button type='button' class='btn btn-default'>未提交审核的</button>");
                expemse1.setUpdateUrl("<a type='button' class='btn btn-link' href='expense/showExpenseDetail?expenseId="+expemse1.geteId()+"' >查询详情</a>" +
                        "<a type='button' class='btn btn-warning' href='expense/updateExpenseDetail?updateExpenseId="+expemse1.geteId()+"' >修改报销</a>");

            }
            //设置能看得到,经理审核通过的
            if(OK_MANAGER.equals( expemse1.geteState())){

                expemse1.seteStateHtml("<button type='button' class='btn btn-primary'>经理审核通过的</button>");
                expemse1.setUpdateUrl("<a type='button' class='btn btn-link' href='expense/showExpenseDetail?expenseId="+expemse1.geteId()+"' >查询详情</a>");
                expemse1.setFinancAaudit("<a type='button' class='btn btn-warning' href='financ/FinancAaudit?financExpenseId="+expemse1.geteId()+"' >审核</a>");

            }
            //设置能看得到,经理审核未通过的
            if(NO_MANAGER.equals( expemse1.geteState())){

                expemse1.seteStateHtml("<button type='button' class='btn btn-danger'>经理审核未通过的</button>");
                expemse1.setManageAduit("<a type='button' class='btn btn-link' href='expense/manageAduitExpense?aduitId="+expemse1.geteId()+"' >审核</a>");
                expemse1.setUpdateUrl("<a type='button' class='btn btn-link' href='expense/showExpenseDetail?expenseId="+expemse1.geteId()+"' >查询详情</a>" +
                        "<a type='button' class='btn btn-warning' href='expense/updateExpenseDetail?updateExpenseId="+expemse1.geteId()+"' >修改报销</a>");

            }
            //设置能看得到,财务审核通过
            if(OK_FINANCE.equals( expemse1.geteState())){

                expemse1.seteStateHtml("<button type='button' class='btn btn-success'>财务审核通过</button>");
                expemse1.setUpdateUrl("<a type='button' class='btn btn-link' href='expense/showExpenseDetail?expenseId="+expemse1.geteId()+"' >查询详情</a>");

            }
            //设置能看得到,财务审核未通过
            if(NO_FINANCE.equals( expemse1.geteState())){

                expemse1.seteStateHtml("<button type='button' class='btn btn-danger'>财务审核未通过</button>");
                expemse1.setUpdateUrl("<a type='button' class='btn btn-link' href='expense/showExpenseDetail?expenseId="+expemse1.geteId()+"' >查询详情</a>");
                expemse1.setFinancAaudit("<a type='button' class='btn btn-warning' href='financ/FinancAaudit?financExpenseId="+expemse1.geteId()+"' >审核</a>");


            }
            arrayList.add(expemse1);
        }
        return arrayList;
    }

    /**
     * 查询报销单的明细数据信息
     *      当前报销单的报销明细数据信息
     * @param geteId
     * @return
     * @throws Exception
     */
    @Override
    public List<ExpemseDetali> selectExpemseDetaliList(Integer geteId) throws Exception {
        return iExpemseDao.selectExpemseDetaliListDao(geteId);
    }

    /**
     *审核报销单的记录数据
     * @param auditRec
     * @return
     * @throws Exception
     */
    @Override
    public int addExpenseAduitMessage(AuditRec auditRec) throws Exception {


        return iExpemseDao.addExpenseAduitMessageDao(auditRec);
    }

    /**
     * 审核历史记录
     * @param geteId
     * @return
     * @throws Exception
     */
    @Override
    public List<AuditRec> selectAuditRecList(Integer geteId) throws Exception {

        List<AuditRec> auditRecs = iExpemseDao.selectAuditRecListDao(geteId);
        ArrayList<AuditRec> arrayList = new ArrayList<>();

        for (AuditRec auditRec : auditRecs) {
            if(OK_SUBMIT.equals( auditRec.getA_State())){
                //设置能看得到,已提交审核的
                auditRec.setA_StateHtml("<button type='button' class='btn btn-info'>已提交审核的</button>");
            }
            //设置能看得到,未提交审核的
            if(NO_SUBMIT.equals( auditRec.getA_State())){

                auditRec.setA_StateHtml("<button type='button' class='btn btn-default'>未提交审核的</button>");
            }
            //设置能看得到,经理审核通过的
            if(OK_MANAGER.equals(auditRec.getA_State())){

                auditRec.setA_StateHtml("<button type='button' class='btn btn-primary'>经理审核通过的</button>");
            }
            //设置能看得到,经理审核未通过的
            if(NO_MANAGER.equals( auditRec.getA_State())){

                auditRec.setA_StateHtml("<button type='button' class='btn btn-danger'>经理审核未通过的</button>");
            }
            //设置能看得到,财务审核通过
            if(OK_FINANCE.equals( auditRec.getA_State())){

                auditRec.setA_StateHtml("<button type='button' class='btn btn-success'>财务审核通过</button>");
            }
            //设置能看得到,财务审核未通过
            if(NO_FINANCE.equals( auditRec.getA_State())){

                auditRec.setA_StateHtml("<button type='button' class='btn btn-danger'>财务审核未通过</button>");
            }

            arrayList.add(auditRec);
        }

        return arrayList;
    }

    /**
     * 修改报销的信息
     * @param expemse
     * @return
     * @throws Exception
     */
    @Override
    public int UpdateExpenseManager(Expemse expemse) throws Exception {

        return iExpemseDao.UpdateExpenseManagerDao(expemse);
    }
}
