package com.nm.expense.service.imp;

import com.nm.expense.dao.ICotsDaon;
import com.nm.expense.dao.imp.CotsDaonImp;
import com.nm.expense.service.ICots;
import com.nm.pojo.Cots;
import com.nm.system.dao.ICotsDao;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class CotsImp implements ICots {

    ICotsDaon iCotsDaon = new CotsDaonImp();

    /**
     * 查询所有报销费用数据信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Cots> selectCotsList() throws Exception {
        //进行调用Dao层

        return iCotsDaon.selectCotsListDao();
    }
}
