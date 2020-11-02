package com.nm.system.dao;

import com.nm.pojo.Cots;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface ICotsDao {

    /**
     * 添加费用数据信息
     * @param cots
     * @return
     * @throws Exception
     */
    int addCotsDao(Cots cots) throws Exception;

    /**
     * 查询出所有费用
     * @param cots
     * @return
     */
    List<Cots> selectCotsListAndWhereDao(Cots cots) throws Exception;

    /**
     * 删除选择费用
     * @param cots
     * @return
     * @throws Exception
     */
    int deleteCostServcieDao(Cots cots) throws Exception;

    /**
     * 修改费用表格
     * @param cost
     * @return
     * @throws Exception
     */
    int updateCostServiceDao(Cots cost) throws Exception;
}
