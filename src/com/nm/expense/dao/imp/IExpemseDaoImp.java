package com.nm.expense.dao.imp;

import com.nm.expense.dao.IExpemseDao;
import com.nm.pojo.AuditRec;
import com.nm.pojo.Expemse;
import com.nm.pojo.ExpemseDetali;
import com.nm.utiles.C3p0Util;
import com.nm.utiles.ThisStaticCode;
import com.sun.org.apache.bcel.internal.generic.NEW;
import jdk.nashorn.internal.ir.LiteralNode;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
public class IExpemseDaoImp implements IExpemseDao {

    /**
     * 添加报销单的方法
     * @return
     * @throws Exception
     */
    @Override
    public int addExpemseDao(Expemse expemse) throws Exception {
        //进行编写sql语句，这里要进行编写多条SQL语句
        //1.进行添加报销单，获取当前报销单的id编号，为了方便添加下一个逻辑数据表（费用明细数据，当前报销单的明细）
        //2.就要添加报销单的报销明细
        //由于要执行多条sql数据，就需要事务
        Connection conn = null;
        int i = 0;
        try{
            conn =  C3p0Util.getConn();
            //手动开启事务
            conn.setAutoCommit(false);

            QueryRunner queryRunner = new QueryRunner();

            //添加报销单逻辑
            //1.先添加报销
            String sql = "INSERT INTO t_expemse(uId,eName,eToltel,eDesc,eDate,eState,eMark)"+
                     "  VALUES(?,?,?,?,now(),?,?)";
            i+= queryRunner.update(conn,sql,expemse.getuId(),expemse.geteName(),expemse.geteToltel(),
                    expemse.geteDesc(),expemse.geteState(),expemse.geteMark());

            //2.获取当前报销单的名称
            String idsql = "SELECT LAST_INSERT_ID()";
              //int id = queryRunner.query(conn,idsql,new ScalarHandler<BigInteger>()).intValue();
            String orderId = queryRunner.query(conn,idsql,new ScalarHandler()).toString();
            Integer id = Integer.valueOf(orderId);


            //3.在添加当前报销单明细数据
            String costsql = "INSERT INTO t_expemse_detali(cId,eId,d_Desc,d_Monery) "+
                    " VALUES(?,?,?,?)";
            Integer[] costIds = expemse.getCostIds();
            String[] detailDescs = expemse.getDetailDescs();
            Float[] detailMoneys = expemse.getDetailMoneys();
            for (int j = 0; j < costIds.length; j++) {
                i+= queryRunner.update(conn,costsql,costIds[j],id,detailDescs[j],detailMoneys[j]);
            }
            //提交
            conn.commit();
            //设置为自动提交
            conn.setAutoCommit(true);

        }catch (Exception e){

            //如出现异常，进行回滚
            conn.rollback();
            e.printStackTrace();
        }
        return i;
    }

    /**
     * 审核查询对应报销单的信息
     * @param expemse  查询的条件数据
     * @return
     */
    @Override
    public List<Expemse> UpdateExpemseManagerAuditDao(Expemse expemse) throws Exception {
        //编写sql语句
        /**
         * SELECT te.*,tu.uName FROM t_expemse te INNER JOIN t_users tu ON te.uId=tu.uId AND te.eState IN("1","2","-1") AND
         * te.eId="" AND tu.uName="" AND te.eName="" AND te.eDate >"" AND te.eDate <"" AND te.eMark !=1
         */
        StringBuilder sql= new StringBuilder("SELECT te.*,tu.uName FROM t_expemse te INNER JOIN t_users tu ON te.uId=tu.uId AND te.eMark !=?");
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(ThisStaticCode.NO_CODE+"");

        //进行编写对应的条件值
        //姓名查询
        if(expemse.getuName()!=null && !"".equals(expemse.getuName())){
            //进行拼接对应SQL语句
            sql.append("  AND tu.uName like ?");
            arrayList.add("%"+expemse.getuName()+"%");
        }

        //通过状态进行查询出对应的数据
        if(expemse.geteState()!=null && !"".equals(expemse.geteState())){
            sql.append("  AND te.eState = ?");
            arrayList.add(expemse.geteState());
        }

        //开始时间
        if(expemse.getStartDate()!=null && !"".equals(expemse.getStartDate())){
            sql.append("  AND te.eDate >= ?");
            arrayList.add(expemse.getStartDate());
        }

        //结束时间
        if(expemse.getEndDate()!=null && !"".equals(expemse.getEndDate())){
            sql.append("  AND te.eDate <= ?");
            arrayList.add(expemse.getEndDate());
        }

        //报销原因
        if(expemse.geteName()!=null && !"".equals(expemse.geteName())){
            sql.append("  AND te.eName like ?");
            arrayList.add("%"+expemse.geteName()+"%");
        }

        //进行拼接id
        if(expemse.geteId()!=null && expemse.geteId()>0){
            sql.append("   AND te.eId= ?");
            arrayList.add(expemse.geteId());
        }

        if(expemse.getuId()!=null && expemse.getuId()>0){
            sql.append("   AND te.uId= ?");
            arrayList.add(expemse.getuId());
        }

        //再一次进行拼接审批标识
        if(expemse.getAuditeState()!=null && expemse.getAuditeState().length>0){
            sql.append("   AND te.eState IN (");
            StringBuilder code = new StringBuilder();
            String [] state = expemse.getAuditeState();
            for (int i = 0; i < state.length; i++) {
                if(i==state.length-1){
                    code.append(state[i]);
                    break;
                }
                code.append(state[i]+",");
            }
            sql.append(code.toString()+")");
        }
        System.out.println("sql语句"+sql.toString());
        System.out.println(arrayList);

        List<Expemse> expemses = C3p0Util.queryList(sql.toString(), Expemse.class, arrayList.toArray());
        return expemses;
    }

    /**
     * 查询报销单的明细数据信息
     *      当前报销单的报销明细数据信息
     * @param geteId
     * @return
     * @throws Exception
     */
    @Override
    public List<ExpemseDetali> selectExpemseDetaliListDao(Integer geteId) throws Exception {
        //编写对应的sql
        String sql = "SELECT ted.*,tc.cName FROM t_expemse_detali ted INNER JOIN t_cots tc ON ted.cId=tc.cId AND ted.eId=?";
        List<ExpemseDetali> detalis = C3p0Util.queryList(sql, ExpemseDetali.class, geteId);
        return detalis;
    }

    /**
     *审核报销单的记录数据
     * @param auditRec
     * @return
     * @throws Exception
     */
    @Override
    public int addExpenseAduitMessageDao(AuditRec auditRec) throws Exception {
        /**
         * 由于报销单和有报销状态和审核表中有审核状态
         *      那么在进行审核的那一刻开始（无论经理还是财务），那么在审核时候
         *      两张表中报销状态与审核状态必须是一致的
         *
         *      由于在不同的表中，在进行审核的时候
         *      （其实就是审核记录中添加数据
         *      报销单中修改数据（修改报销状态）
         *      ）
         *
         *      那么这时候就需要事务
         */
        //编写sql语句
        Connection conn = C3p0Util.getConn();
        int j = 0;
        try{

            //设置事务手动提交
            conn.setAutoCommit(false);
            QueryRunner runner = new QueryRunner();
            //1.添加审核记录表

            String sql = "INSERT INTO t_audit_rec (eId,uId,a_State,a_Sugg,a_Date) VALUES (?,?,?,?,now())";

           j+= runner.update(conn,sql,auditRec.geteId(),auditRec.getuId(),auditRec.getA_State(),auditRec.getA_Sugg());
            //2.修改报销单的报销状态
            String upSql = "UPDATE t_expemse SET eState=? WHERE eId=?";
            j+= runner.update(conn,upSql,auditRec.getA_State(),auditRec.geteId());

            conn.commit();
            conn.setAutoCommit(true);
        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
        }
        return j;
    }

    /**
     * 审核历史记录
     * @param geteId
     * @return
     * @throws Exception
     */
    @Override
    public List<AuditRec> selectAuditRecListDao(Integer geteId) throws Exception {

        //编写sql语句
        String sql = "SELECT tar.*,tu.uName FROM t_audit_rec tar INNER JOIN t_users tu ON tar.uId=tu.uId AND tar.eId=?";

        List<AuditRec> auditRecs = C3p0Util.queryList(sql, AuditRec.class, geteId);

        return auditRecs;
    }

    /**
     * 修改报销的信息
     * @param expemse
     * @return
     * @throws Exception
     */
    @Override
    public int UpdateExpenseManagerDao(Expemse expemse) throws Exception {
        /**
         * 由于进行修改信息
         * 1.修改报销单数据信息
         * 2.修改当前报销单的明细数据
         *      明细数据特别多
         *      由于之前有数据存在，1.要修改，就是删除之前明细
         *      2.再添加新明细数据
         *      由于这里要执行多条sql,要用到事务
         */
        Connection conn = C3p0Util.getConn();
        int j = 0;
        try{

            //手动开启事务
            conn.setAutoCommit(false);
            QueryRunner runner = new QueryRunner();
            //1.修改报销单的数据信息
            String sql = "UPDATE t_expemse te SET te.eName=?,te.eToltel=?,te.eDesc=? ,te.eState=? WHERE te.eId=?";
           j+= runner.update(conn,sql,expemse.geteName(),expemse.geteToltel(),expemse.geteDesc(),expemse.geteState(),expemse.geteId());

            /**
             * 2.修改当前报销单的明细数据
             * 2-1.要修改，就是删除之前明细
             * 2-2.再添加新明细数据
             */
            //2-1.要修改，就是删除之前明细
            String delsql = "DELETE FROM t_expemse_detali  WHERE eId=?";
            j+= runner.update(conn,delsql,expemse.geteId());

            //2-2.再添加新明细数据
            String costsql = "INSERT INTO t_expemse_detali(cId,eId,d_Desc,d_Monery) "+
                    " VALUES(?,?,?,?)";
            Integer[] costIds = expemse.getCostIds();
            String[] detailDescs = expemse.getDetailDescs();
            Float[] detailMoneys = expemse.getDetailMoneys();
            for (int i = 0; i < costIds.length; i++) {
                j+= runner.update(conn,costsql,costIds[i],expemse.geteId(),detailDescs[i],detailMoneys[i]);
            }

            conn.commit();
            //自动开启事务
            conn.setAutoCommit(true);


        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
        }
        return j;
    }

}
