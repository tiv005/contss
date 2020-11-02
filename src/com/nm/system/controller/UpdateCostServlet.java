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
import java.util.List;

/**
 * 费用修改servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/system/updateCost")
public class UpdateCostServlet extends HttpServlet {
    ICotsService iCotsService = new CotsServiceImp();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{

            //获取需要修改的信息（修改数据信息）
            Cots cost= RequestBeanUtils.requestToBean(request,Cots.class);
            //进行调用service层的数据信息
            int i =  iCotsService.updateCostService(cost);
            //默认情况下是修改成功的
            String str = "修改失败";
            if(i>0){
                str= "修改成功";

            }
            //将提示信息数据反馈回页面
            request.setAttribute("tip",str);

            //需要将再一次修改数据返回给页面
            request.setAttribute("cost",cost);
            //进行跳转页面
            request.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(request,response);
        }catch (Exception e){
            String tip = "修改失败";
            request.setAttribute("tip",tip);
            request.getRequestDispatcher("/view/system/cost/cost_update.jsp").forward(request,response);
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            //获取数据数据信息id编号进行查询出元数据（没有修改数据信息）
            String cId = request.getParameter("costId");

            //将String类型转化为int类型
            int Id = Integer.parseInt(cId);

            //调用service层数据，来进行查询需要修改的数据,调用之前现成查询数据
            Cots cots = new Cots();
            cots.setcId(Id);
            //这里通过id通过查询数据，这里查询出来数据是一条
            List<Cots> cotsList = iCotsService.selectCotsListAndWhere(cots);
            Cots cs = cotsList.get(0);

            //再一次将数据返回页面
            request.setAttribute("cost", cs);

            //进行跳转到jsp页面
            request.getRequestDispatcher("/web/view/system/cost/cost_update.jsp").forward(request, response);
        }
            catch (Exception e) {

                e.printStackTrace();
            }
        }
    }
