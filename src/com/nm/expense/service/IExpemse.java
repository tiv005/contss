package com.nm.expense.service;

import com.nm.pojo.AuditRec;
import com.nm.pojo.Expemse;
import com.nm.pojo.ExpemseDetali;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface IExpemse {

    /**
     * 添加报销单的方法
     * @return
     * @throws Exception
     */
    int addExpemseService(Expemse expemse) throws Exception;

    /**
     * 审核查询对应报销单的信息
     * @param expemse  查询的条件数据
     * @return
     */
    List<Expemse> UpdateExpemseManagerAudit(Expemse expemse) throws Exception;

    /**
     * 查询报销单的明细数据信息
     *      当前报销单的报销明细数据信息
     * @param geteId
     * @return
     * @throws Exception
     */
    List<ExpemseDetali> selectExpemseDetaliList(Integer geteId) throws Exception;

    /**
     *审核报销单的记录数据
     * @param auditRec
     * @return
     * @throws Exception
     */
    int addExpenseAduitMessage(AuditRec auditRec) throws Exception;

    /**
     * 审核历史记录
     * @param geteId
     * @return
     * @throws Exception
     */
    List<AuditRec> selectAuditRecList(Integer geteId) throws Exception;

    /**
     * 修改报销的信息
     * @param expemse
     * @return
     * @throws Exception
     */
    int UpdateExpenseManager(Expemse expemse) throws Exception;
}
