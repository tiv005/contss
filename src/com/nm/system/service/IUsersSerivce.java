package com.nm.system.service;

import com.nm.pojo.Users;

import java.util.List;

public interface IUsersSerivce {

    /**
     * 账号密码的登陆
     *   注意:同学们这里写的，那么写上你的大名:方面后续考核
     * @param users  登陆的账号和密码
     * @return   查询出来的用户信息
     * @throws Exception
     */
    Users doLoginSerivce(Users users)throws  Exception;

    /**
     * 用户信息的修改
     * @param users   修改的用户数据
     * @return
     * @throws Exception
     */
    int updateUsersInfo(Users users)throws Exception;

    /**
     * 当用户第一次点击用户管理的时候，就需要查询出所用的用户信息
     //是没有对应的查询条件的,所在这里为null ,没有任何的条件值进行查询所有的用户信息
     * @param users  查询用户的条件的数据
     * @return
     * @throws Exception
     */
    List<Users> selectUsersListAndWhere(Users  users)throws Exception;

    /**
     * 用户的添加数据
     * @param users  添加的数据
     * @return
     * @throws Exception
     */
    int addUsersService(Users users)throws Exception;

    /**
     * 用户的删除
     * @param users  删除的数据信息
     * @return
     * @throws Exception
     */
    int deleteUsersServcie(Users users)throws Exception;

    /**
     * 用户的信息修改
     * @param users  需要修改的数据信息
     * @return
     * @throws Exception
     */
    int updateUsersService(Users users) throws Exception;
}
