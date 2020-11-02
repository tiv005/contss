package com.nm.expense.dao;

import com.nm.pojo.AuditRec;
import com.nm.pojo.Expemse;
import com.nm.pojo.ExpemseDetali;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface IExpemseDao {

    /**
     * 添加报销单的方法
     * @return
     * @throws Exception
     */
    int addExpemseDao(Expemse expemse) throws Exception;

    /**
     * 审核查询对应报销单的信息
     * @param expemse  查询的条件数据
     * @return
     */
    List<Expemse> UpdateExpemseManagerAuditDao(Expemse expemse) throws Exception;

    /**
     * 查询报销单的明细数据信息
     *      当前报销单的报销明细数据信息
     * @param geteId
     * @return
     * @throws Exception
     */
    List<ExpemseDetali> selectExpemseDetaliListDao(Integer geteId) throws Exception;

    /**
     *审核报销单的记录数据
     * @param auditRec
     * @return
     * @throws Exception
     */
    int addExpenseAduitMessageDao(AuditRec auditRec) throws Exception;

    /**
     * 审核历史记录
     * @param geteId
     * @return
     * @throws Exception
     */
    List<AuditRec> selectAuditRecListDao(Integer geteId) throws Exception;

    /**
     * 修改报销的信息
     * @param expemse
     * @return
     * @throws Exception
     */
    int UpdateExpenseManagerDao(Expemse expemse) throws Exception;
}
