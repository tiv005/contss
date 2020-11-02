package com.nm.system.dao;

import com.nm.pojo.Menu;
import com.nm.pojo.Users;
import org.displaytag.decorator.EscapeXmlColumnDecorator;

import java.util.List;

/**
 * @author 容学斌
 */
public interface IUsersDao {

    /**
     * 账号密码的登陆
     * @param users  登陆的账号和密码
     * @return   查询出来的用户信息
     * @throws Exception
     */
    Users doLoginDao(Users users)throws Exception;

    /**
     * 查询当前登录用户的权限id菜单
     * @param r_id  角色ID编号
     * @return
     * @throws Exception
     */
    List<Menu> selectMenuDao(Integer r_id)throws Exception;
    /**
     * 用户信息的修改
     * @param users   修改的用户数据
     * @return
     * @throws Exception
     */
    int updateUsersDao(Users users)throws Exception;

    /**
     * 当用户第一次点击用户管理的时候，就需要查询出所用的用户信息
     //是没有对应的查询条件的,所在这里为null ,没有任何的条件值进行查询所有的用户信息
     * @param users  查询用户的条件的数据
     * @return
     * @throws Exception
     */
    List<Users> selectUsersListAndWhereDao(Users users)throws Exception;
    /**
     * 用户的添加数据
     * @param users  添加的数据
     * @return
     * @throws Exception
     */
    int addUsersDao(Users users)throws Exception;
    /**
     * 用户的删除
     * @param users  删除的数据信息
     * @return
     * @throws Exception
     */
    int deleteUsersDao(Users users)throws Exception;

    /**
     * 用户的信息修改
     * @param users  需要修改的数据信息
     * @return
     * @throws Exception
     */
    int updateUsersInfoDao(Users users) throws Exception;
}
