package com.nm.expense.dao;

import com.nm.pojo.Cots;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface ICotsDaon {

    /**
     * 查询所有报销费用数据信息
     * @return
     * @throws Exception
     */
    List<Cots> selectCotsListDao() throws Exception;
}
