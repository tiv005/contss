package com.nm.system.service.imp;

import com.nm.pojo.Menu;
import com.nm.system.dao.IUsersDao;
import com.nm.system.dao.imp.UsersDaoImp;
import com.nm.system.service.IMenuSerivce;

import java.util.List;

public class MenuSerivceImp implements IMenuSerivce {

    //进行调用dao层数据
    IUsersDao  iUsersDao=new UsersDaoImp();

    @Override
    public List<Menu> selectMenuService(Integer r_id) throws Exception {

        //可以进行断言，

        return iUsersDao.selectMenuDao(r_id);
    }
}
