package com.nm.system.service.imp;

import com.nm.pojo.Cots;
import com.nm.system.dao.ICotsDao;
import com.nm.system.dao.imp.CotsDonImp;
import com.nm.system.service.ICotsService;
import com.nm.utiles.ThisAssert;
import com.nm.utiles.ThisStaticCode;

import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class CotsServiceImp implements ICotsService {

    ICotsDao iCotsDao = new CotsDonImp();
    /**
     * 添加费用数据信息
     * @param cots
     * @return
     * @throws Exception
     */
    @Override
    public int addCotsSerivce(Cots cots) throws Exception {

        //可以进行断言，费用名称不能为空
        ThisAssert.isNotNull("费用的名称不能为空",cots.getcName());
        //费用描述不能为空
        ThisAssert.isNotNull("费用描述不能为空",cots.getcDesc());
        //组装费用描述的id编号
        cots.setcMark(ThisStaticCode.OK_CODE+"");
        //调用Dao信息
        int i = iCotsDao.addCotsDao(cots);
        return i;
    }

    /**
     * 查询出所有费用
     * @param cots
     * @return
     */
    @Override
    public List<Cots> selectCotsListAndWhere(Cots cots) throws Exception {

        return iCotsDao.selectCotsListAndWhereDao(cots);
    }

    /**
     * 删除选择费用
     * @param cots
     * @return
     * @throws Exception
     */
    @Override
    public int deleteCostServcie(Cots cots) throws Exception {
        //设置uMark值
        cots.setcMark(ThisStaticCode.NO_CODE+"");
        return iCotsDao.deleteCostServcieDao(cots);
    }

    /**
     * 修改费用表格
     * @param cost
     * @return
     * @throws Exception
     */
    @Override
    public int updateCostService(Cots cost) throws Exception {

        ThisAssert.isNotNull("费用名称不能为空",cost.getcName());
        //费用描述不能为空
        ThisAssert.isNotNull("费用描述不能为空",cost.getcDesc());
        //调用Dao
        int i = iCotsDao.updateCostServiceDao(cost);
        return i;
    }
}
