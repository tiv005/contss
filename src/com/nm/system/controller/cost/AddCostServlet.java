package com.nm.system.controller.cost;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.pojo.Cots;
import com.nm.system.service.ICotsService;
import com.nm.system.service.imp.CotsServiceImp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/system/addCost")
public class AddCostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ICotsService iCotsService = new CotsServiceImp();
        try{
        //获取添加对应费用的信息
            Cots cots = RequestBeanUtils.requestToBean(request,Cots.class);
            //调用service层
           int i =  iCotsService.addCotsSerivce(cots);
            String tip = "添加失败";
           if(i>0){
                tip = "添加成功";
           }
            //将异常信息信息返回给页面
           request.setAttribute("tip",tip);

           //将添加的数据返回给页面
            request.setAttribute("cots",cots);
           //进行跳转到添加页面
            request.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
            //如果在添加的时候出现了异常
            //跳转到添加页面
            request.getRequestDispatcher("/view/system/cost/cost_add.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
