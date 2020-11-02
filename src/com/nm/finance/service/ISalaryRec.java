package com.nm.finance.service;

import com.nm.pojo.SalaryRec;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface ISalaryRec {
    /**
     * 用户薪资的添加
     * @param salaryRec
     * @return
     * @throws Exception
     */
    int addiSalaryRecService(SalaryRec salaryRec) throws Exception;

    /**
     * 所有用户薪资查询
     * @param salaryRec
     * @return
     * @throws Exception
     */
    List<SalaryRec> selectSalaryList(SalaryRec salaryRec) throws  Exception;

    /**
     * 显示数据的图形报表
     * @param
     * @param
     * @param salaryRec
     * @return
     * @throws Exception
     */
    List<SalaryRec> selectSalaryHighcharts(SalaryRec salaryRec) throws  Exception;
}
