package com.nm.system.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.pojo.Cots;
import com.nm.pojo.Users;
import com.nm.system.service.ICotsService;
import com.nm.system.service.imp.CotsServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户删除费用servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/system/deleteCost")
public class DeleteCostServlet extends HttpServlet {
    ICotsService iCotsService = new CotsServiceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            Cots cots = RequestBeanUtils.requestToBean(request,Cots.class);

            int  i=  iCotsService.deleteCostServcie(cots);
            String  tip="删除成功";

            if(i<1){
                tip="删除失败";
            }
            request.setAttribute("tip",tip);
            //进行跳转到对应的页面
            request.getRequestDispatcher("/system/papeCostListServlet").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
