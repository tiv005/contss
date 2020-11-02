package com.nm.system.service;

import com.nm.pojo.Menu;

import java.util.List;

public interface IMenuSerivce {
    /**
     * 查询当前登录用户的权限id菜单
     * @param r_id  角色ID编号
     * @return
     * @throws Exception
     */
    List<Menu> selectMenuService(Integer r_id)throws  Exception;

}
