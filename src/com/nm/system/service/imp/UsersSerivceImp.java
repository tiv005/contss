package com.nm.system.service.imp;

import com.nm.pojo.Users;
import com.nm.system.dao.IUsersDao;
import com.nm.system.dao.imp.UsersDaoImp;
import com.nm.system.service.IUsersSerivce;
import com.nm.utiles.ThisAssert;
import com.nm.utiles.ThisStaticCode;

import java.util.List;

/**
 * @author 容学斌
 */
public class UsersSerivceImp implements IUsersSerivce {

  IUsersDao iUsersDao=new UsersDaoImp();


    /**
     * 账号密码的登陆
     * @param users  登陆的账号和密码
     * @return   查询出来的用户信息
     * @throws Exception
     */
    @Override
    public Users doLoginSerivce(Users users) throws Exception {
        //要进行断言。。。。 ?
        /*
        * 这里的断言  其实就是进行判断对应的参数的
        *        类似之前的if 流程控制语句
        * */
      //进行判断用户的账号和密码不能为空
        /**
         * 如下使用if语句，如果有N个方法或是是N个参数那么是不是要写N个这样的if语句判断
         *   从而就有了断言
         */
/*        if(users.getuAccout()!=null){
            //。。。。。。。
        }*/
        ThisAssert.isNotNull(users.getuAccout(),"登陆账号不能为空");
        ThisAssert.isNotNull(users.getuPwd(),"密码不能为空");

        //在进行调用Dao层的方法
     Users us=   iUsersDao.doLoginDao(users);
        //可以进行再次断言 输入的账号和密码，数据库中是否存在
        //这里就不在过多的编写了

        return us;
    }
    /**
     * 用户信息的修改
     * @param users   修改的用户数据
     * @return
     * @throws Exception
     */
    @Override
    public int updateUsersInfo(Users users) throws Exception {
        //进行断言
        //用户的密码不能为空
        ThisAssert.isNotNull("用户的密码不能为空",users.getuPwd());
        //进行调用dao层数据
        return  iUsersDao.updateUsersDao(users);
    }
    /**
     * 当用户第一次点击用户管理的时候，就需要查询出所用的用户信息
     //是没有对应的查询条件的,所在这里为null ,没有任何的条件值进行查询所有的用户信息
     * @param users  查询用户的条件的数据
     * @return
     * @throws Exception
     */
    @Override
    public List<Users> selectUsersListAndWhere(Users users) throws Exception {

        return iUsersDao.selectUsersListAndWhereDao(users);
    }

    /**
     * 用户的添加数据
     * @param users  添加的数据
     * @return
     * @throws Exception
     */
    @Override
    public int addUsersService(Users users) throws Exception {
        //可以进行断言
        //如断言账号和密码不能为空
        ThisAssert.isNotNull("账号名不能为空",users.getuAccout());
        ThisAssert.isNotNull("密码不能为空",users.getuPwd());
        users.setuMrak(ThisStaticCode.OK_CODE+"");

        return iUsersDao.addUsersDao(users);
    }
    /**
     * 用户的删除
     * @param users  删除的数据信息
     * @return
     * @throws Exception
     */
    @Override
    public int deleteUsersServcie(Users users) throws Exception {
        //设置uMark值
        users.setuMrak(ThisStaticCode.NO_CODE+"");

        return iUsersDao.deleteUsersDao(users);
    }

    /**
     * 用户的信息修改
     * @param users  需要修改的数据信息
     * @return
     * @throws Exception
     */
    @Override
    public int updateUsersService(Users users) throws Exception {
        //可以进行断言
        // 用户姓名不能为空
        ThisAssert.isNotNull("用户的信息不能为空",users.getuName());
        //调用Dao
        return iUsersDao.updateUsersInfoDao(users);
    }
}
