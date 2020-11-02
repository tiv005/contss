package com.nm.finance.dao.imp;

import com.nm.finance.dao.ISalaryRecDao;
import com.nm.pojo.SalaryRec;
import com.nm.utiles.C3p0Util;
import com.nm.utiles.ThisStaticCode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class SalaryRecDaoImp implements ISalaryRecDao {

    /**
     * 用户薪资的添加
     * @param salaryRec
     * @return
     * @throws Exception
     */
    @Override
    public int addiSalaryRecDao(SalaryRec salaryRec) throws Exception {

        //编写sql语句
        String sql = "INSERT INTO t_salary_rec(uId,rMonth,rDate,rSalary,rConn) VALUES(?,?,now(),?,?)";

        int i = C3p0Util.update(sql,salaryRec.getuId(),salaryRec.getrMonth(),salaryRec.getrSalary(),salaryRec.getrConn());

        return i;
    }

    /**
     * 所有用户薪资查询
     * @param salaryRec
     * @return
     * @throws Exception
     */
    @Override
    public List<SalaryRec> selectSalaryListDao(SalaryRec salaryRec) throws Exception {
        /**
         * SELECT tsr.*,tu.uName FROM t_salary_rec tsr INNER JOIN t_users tu ON tsr.uId=tu.uId
         AND tu.uMrak!=1 AND tu.uName='' AND tsr.rMonth='' AND tu.uId=''
         */

        StringBuilder builder = new StringBuilder("SELECT tsr.*,tu.uName FROM t_salary_rec tsr INNER JOIN t_users tu ON tsr.uId=tu.uId AND tu.uMrak!=? ");

        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(ThisStaticCode.NO_CODE);

        //用户名称判断
        if(salaryRec.getuName()!=null && !"".equals(salaryRec.getuName())){
            builder.append("  AND tu.uName like ?");
            arrayList.add("%"+salaryRec.getuName()+"%");
        }

        if(salaryRec.getrMonth()!=null && !"".equals(salaryRec.getrMonth())){
            builder.append(" AND tsr.rMonth=?");
            arrayList.add(salaryRec.getrMonth());
        }

        if(salaryRec.getuId()!=null &&  salaryRec.getuId()>0){
            builder.append("  AND tu.uId=?");
            arrayList.add(salaryRec.getuId());
        }

        List<SalaryRec> recList = C3p0Util.queryList(builder.toString(), SalaryRec.class, arrayList.toArray());

        return recList;
    }

    /**
     * 显示数据的图形报表
     * ,SUM(rConn) AS rConnTotal
     * GROUP BY rMonth
     * @param
     * @param
     * @return
     * @throws Exception
     */
    @Override
    public List<SalaryRec> selectSalaryHighchartsDao(SalaryRec salaryRec) throws Exception {
        //编写对应sql语句
        String sql = "SELECT rMonth,SUM(rSalary) AS rSalaryTotal ,SUM(rConn) AS rConnTotal FROM t_salary_rec GROUP BY rMonth  ";
        return C3p0Util.queryList(sql,SalaryRec.class);
    }


}
