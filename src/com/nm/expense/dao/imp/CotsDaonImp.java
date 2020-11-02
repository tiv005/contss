package com.nm.expense.dao.imp;

import com.nm.expense.dao.ICotsDaon;
import com.nm.pojo.Cots;
import com.nm.utiles.C3p0Util;
import com.nm.utiles.ThisStaticCode;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class CotsDaonImp implements ICotsDaon {

    /**
     * 查询所有报销费用数据信息
     * @return
     * @throws Exception
     */
    @Override
    public List<Cots> selectCotsListDao() throws Exception {
        //进行编写查询所有费用的数据信息
        String sql = "SELECT* FROM t_cots WHERE cMark!=?";
        List<Cots> list = C3p0Util.queryList(sql, Cots.class, ThisStaticCode.NO_CODE);
        return list;
    }
}
