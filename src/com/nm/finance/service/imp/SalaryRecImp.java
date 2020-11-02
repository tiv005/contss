package com.nm.finance.service.imp;

import com.nm.finance.dao.ISalaryRecDao;
import com.nm.finance.dao.imp.SalaryRecDaoImp;
import com.nm.finance.service.ISalaryRec;
import com.nm.pojo.SalaryRec;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class SalaryRecImp implements ISalaryRec {
    ISalaryRecDao iSalaryRecDao = new SalaryRecDaoImp();
    /**
     * 用户薪资的添加
     * @param salaryRec
     * @return
     * @throws Exception
     */
    @Override
    public int addiSalaryRecService(SalaryRec salaryRec) throws Exception {

        return iSalaryRecDao.addiSalaryRecDao(salaryRec);
    }

    /**
     * 所有用户薪资查询
     * @param salaryRec
     * @return
     * @throws Exception
     */
    @Override
    public List<SalaryRec> selectSalaryList(SalaryRec salaryRec) throws Exception {
        return iSalaryRecDao.selectSalaryListDao(salaryRec);
    }

    /**
     * 显示数据的图形报表
     * @param
     * @param
     * @param salaryRec
     * @return
     * @throws Exception
     */
    @Override
    public List<SalaryRec> selectSalaryHighcharts(SalaryRec salaryRec) throws Exception {
        return iSalaryRecDao.selectSalaryHighchartsDao(salaryRec);
    }


}
