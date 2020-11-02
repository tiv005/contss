package com.nm.expense.service;

import com.nm.pojo.Cots;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface ICots {

    /**
     * 查询所有报销费用数据信息
     * @return
     * @throws Exception
     */
    List<Cots> selectCotsList() throws Exception;
}
