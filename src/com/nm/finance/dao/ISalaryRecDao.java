package com.nm.finance.dao;

import com.nm.pojo.SalaryRec;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public interface ISalaryRecDao {

    /**
     * 用户薪资的添加
     * @param salaryRec
     * @return
     * @throws Exception
     */
    int addiSalaryRecDao(SalaryRec salaryRec) throws Exception;

    /**
     * 所有用户薪资查询
     * @param salaryRec
     * @return
     * @throws Exception
     */
    List<SalaryRec> selectSalaryListDao(SalaryRec salaryRec) throws Exception;

    /**
     * 显示数据的图形报表
     * @param
     * @param
     * @return
     * @throws Exception
     */
    List<SalaryRec> selectSalaryHighchartsDao(SalaryRec salaryRec) throws Exception;
}
