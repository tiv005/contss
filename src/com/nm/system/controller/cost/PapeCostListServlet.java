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
import java.util.List;

/**
 * 费用管理servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/system/papeCostListServlet")
public class PapeCostListServlet extends HttpServlet {
    ICotsService iCotsService = new CotsServiceImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            Cots cots = RequestBeanUtils.requestToBean(request, Cots.class);
            //调用service层方法
            //查询出所有费用
            List<Cots>  cotsList = iCotsService.selectCotsListAndWhere(cots);
            System.out.println(cotsList);
            //将查询数据返回页面
            request.setAttribute("cotsList",cotsList);
            //再一次将查询数据返回页面
            request.setAttribute("cots",cots);
            System.out.println("费用管理servlet");

            //进行跳转到费用管理的页面
            request.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            //调用service层方法
            //查询出所有费用
           List<Cots>  cotsList = iCotsService.selectCotsListAndWhere(new Cots());
            //将查询数据返回页面
            request.setAttribute("cotsList",cotsList);
            //进行跳转到费用管理的页面
            request.getRequestDispatcher("/view/system/cost/cost_list.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
