package com.nm.system.service;

import com.nm.pojo.Cots;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface ICotsService {
    /**
     * 添加费用数据信息
     * @param cots
     * @return
     * @throws Exception
     */
    int addCotsSerivce(Cots cots) throws Exception;

    /**
     * 查询出所有费用
     * @param cots 条件查询
     * @return
     */
    List<Cots> selectCotsListAndWhere(Cots cots) throws Exception;

    /**
     * 删除选择费用
     * @param cots
     * @return
     * @throws Exception
     */
    int deleteCostServcie(Cots cots) throws Exception;

    /**
     * 修改费用表格
     * @param cost
     * @return
     * @throws Exception
     */
    int updateCostService(Cots cost) throws Exception;

}
