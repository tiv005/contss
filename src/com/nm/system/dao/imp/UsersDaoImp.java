package com.nm.system.dao.imp;

import com.nm.pojo.Menu;
import com.nm.pojo.Users;
import com.nm.system.dao.IUsersDao;
import com.nm.utiles.C3p0Util;
import com.nm.utiles.ThisStaticCode;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 容学斌
 */
public class UsersDaoImp implements IUsersDao {

    /**
     * 账号密码的登陆
     *   注意:同学们这里写的，那么写上你的大名:方面后续考核
     * @param users  登陆的账号和密码
     * @return   查询出来的用户信息
     * @throws Exception
     */
    @Override
    public Users doLoginDao(Users users) throws Exception {
        //进行查询数据
        //sqll   1表示当前的用户不使用  删除(假删除)
        //String sql="SELECT * FROM t_users  tu WHERE tu.uMrak !=1 AND tu.uAccout = ? AND  tu.uPwd=?";
       String sql="SELECT * FROM t_users  tu     INNER JOIN  t_role  tr  on  tu.r_Id=tr.r_Id AND tu.uMrak !=1 AND tu.uAccout = ?  AND  tu.uPwd= ? ";

        Users queryOne = C3p0Util.queryOne(sql, Users.class,users.getuAccout(),users.getuPwd());

        return queryOne;
    }


    /**
     * 查询当前登录用户的权限id菜单
     * @param r_id  角色ID编号
     * @return
     * @throws Exception
     */
    @Override
    public List<Menu> selectMenuDao(Integer r_id) throws Exception {
        String  sql="SELECT * FROM t_menu tm INNER JOIN role_menu  rm  ON tm.m_Id=rm.m_Id  AND rm.r_Id=?";

        List<Menu> menus = C3p0Util.queryList(sql, Menu.class, r_id);
        return menus;
    }

    /**
     * 用户信息的修改
     * @param users   修改的用户数据
     * @return
     * @throws Exception
     */
    @Override
    public int updateUsersDao(Users users) throws Exception {
        //sql
        String sql="UPDATE  t_users SET uName=?,uAge=?,uSex=?,uPhone=?,uPwd=? WHERE uId=?";
        int update = C3p0Util.update(sql, users.getuName(), users.getuAge(), users.getuSex(), users.getuPhone(), users.getuPwd()
                , users.getuId());
        return update;
    }
    /**
     * 当用户第一次点击用户管理的时候，就需要查询出所用的用户信息
     //是没有对应的查询条件的,所在这里为null ,没有任何的条件值进行查询所有的用户信息
     * @param users  查询用户的条件的数据
     * @return
     * @throws Exception
     */
    @Override
    public List<Users> selectUsersListAndWhereDao(Users users) throws Exception {
        //编写对应的sql
        //String sql
        // 查询所有的数据信息
        StringBuilder sql=  new StringBuilder("SELECT * FROM t_users tu INNER JOIN t_role tr on tr.r_Id=tu.r_Id AND uMrak !=?");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(ThisStaticCode.NO_CODE);//表示不可用

        /**
         * 条件查询：
         * 当如下的判断不满足的情况下，记性查询所有的数据
         * 如满足则查询对应的条件数据
         */

        //通过编号id进行查询
        if (users.getuId()!= null  &&  users.getuId()!=0){
            sql.append("   AND tu.uId= ? ");
            objects.add(users.getuId());
        }

        //通过姓名进行模糊查询
        if(users.getuName()!=null && !"".equals(users.getuName())){
            sql.append("   AND tu.uName like ? ");
            objects.add("%"+users.getuName()+"%");
        }

        return  C3p0Util.queryList(sql.toString(),Users.class,objects.toArray());
}
    /**
     * 用户的添加数据
     * @param users  添加的数据
     * @return
     * @throws Exception
     */
    @Override
    public int addUsersDao(Users users) throws Exception {
        //进行编写sql
        String sql="INSERT INTO t_users (r_Id,uName,uAge,uSex,uPhone,uSalary,uAccout,uPwd,uMrak)" +
                " VALUES (?,?,?,?,?,?,?,?,?)";

        return C3p0Util.update(sql,users.getR_Id(),users.getuName(),users.getuAge()
        ,users.getuSex(),users.getuPhone(),users.getuSalary(),users.getuAccout(),users.getuPwd(),users.getuMrak()
        );
    }
    /**
     * 用户的删除
     * @param users  删除的数据信息  其实就是假删除数据(修改对应数据的标识)
     * @return
     * @throws Exception
     */
    @Override
    public int deleteUsersDao(Users users) throws Exception {
        /**
         * 由于在这里删除(修改),有可能需要进行删除多条件
         *   由于有可能要删除多条数据，那么这里就需要对应的事务
         *     事务:
         *        要么一起成功，要么一起失败
         *
         */

        int i = 0 ;
        Connection conn = null;
          try {
              //编写SQL语句
              String  sql="UPDATE t_users SET uMrak=? WHERE uId=?";
              //获取sql对象
              conn= C3p0Util.getConn();

              //开启手动提交事务
              conn.setAutoCommit(false);
              QueryRunner runner = new QueryRunner();
              Integer[] ids = users.getIds();
              for(int j=0;j<ids.length;j++){
                  i+=runner.update(conn,sql,users.getuMrak(),ids[i]);
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
     * 用户的信息修改
     * @param users  需要修改的数据信息
     * @return
     * @throws Exception
     */
    @Override
    public int updateUsersInfoDao(Users users) throws Exception {
        //进行编写对应sql语句
        String sql="UPDATE  t_users SET uName=?,uAge=?,uSex=?,uPhone=?,uPwd=? WHERE uId=?";
        int update = C3p0Util.update(sql, users.getuName(), users.getuAge(), users.getuSex(), users.getuPhone(), users.getuPwd()
                , users.getuId());
        return update;
    }
}
