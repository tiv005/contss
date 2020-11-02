package com.nm.system.dao.imp;

import com.nm.pojo.Cots;
import com.nm.pojo.Users;
import com.nm.system.dao.ICotsDao;
import com.nm.utiles.C3p0Util;
import com.nm.utiles.ThisStaticCode;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class CotsDonImp implements ICotsDao {
    /**
     * 添加费用数据信息
     * @param cots
     * @return
     * @throws Exception
     */
    @Override
    public int addCotsDao(Cots cots) throws Exception {
        //编写sql语句
        String sql = "INSERT INTO t_cots(cName,cDesc,cMark) VALUES(?,?,?)";
        int update = C3p0Util.update(sql, cots.getcName(), cots.getcDesc(), cots.getcMark());
        return update;
    }

    /**
     * 查询出所有费用
     * @param cots
     * @return
     */
    @Override
    public List<Cots> selectCotsListAndWhereDao(Cots cots) throws Exception {
        //编写sql语句
        //String sql = "SELECT*FROM t_cots WHERE cMark =?";
        //  List<Cots> cotsList = C3p0Util.queryList(sql, Cots.class, cots.getcMark());

        // 查询所有的数据信息
        StringBuilder sql=  new StringBuilder("SELECT*FROM t_cots tc WHERE cMark !=?");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(ThisStaticCode.NO_CODE);//表示不可用

        //通过编号id进行查询
        if (cots.getcId()!= null  &&  cots.getcId()!=0){
            sql.append("   AND tc.cId= ? ");
            objects.add(cots.getcId());
        }

        //通过姓名进行模糊查询
        if(cots.getcName()!=null && !"".equals(cots.getcName())){
            sql.append("   AND tc.cName like ? ");
            objects.add("%"+cots.getcName()+"%");
        }

        return C3p0Util.queryList(sql.toString(), Cots.class,objects.toArray());
    }

    /**
     * 删除选择费用
     * @param cots
     * @return
     * @throws Exception
     */
    @Override
    public int deleteCostServcieDao(Cots cots) throws Exception {

        int i = 0 ;
        Connection conn = null;
        try {
            //编写SQL语句
            String  sql="UPDATE t_cots SET cMark=? WHERE cId=?";
            //获取sql对象
            conn= C3p0Util.getConn();

            //开启手动提交事务
            conn.setAutoCommit(false);
            QueryRunner runner = new QueryRunner();
            Integer[] ids = cots.getIds();
            for(int j=0;j<ids.length;j++){
                i+=runner.update(conn,sql,cots.getcMark(),ids[i]);
            }
            //进行手动提交
            conn.commit();
            conn.setAutoCommit(true);
        }catch (Exception  e){
            //如果出现了异常，进行回滚
            conn.rollback();
            e.printStackTrace();
        }

        return i;
    }

    /**
     * 修改费用表格
     * @param cost
     * @return
     * @throws Exception
     */
    @Override
    public int updateCostServiceDao(Cots cost) throws Exception {
        //进行编写对应sql语句
        String sql="UPDATE  t_cots SET cName=?,cDesc=? WHERE cId=?";
        int update = C3p0Util.update(sql, cost.getcName(), cost.getcDesc(), cost.getcId());
        return update;
    }
}
