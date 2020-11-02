package com.nm.finance.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.finance.service.ISalaryRec;
import com.nm.finance.service.imp.SalaryRecImp;
import com.nm.pojo.SalaryRec;
import com.nm.pojo.Users;
import com.nm.system.service.IUsersSerivce;
import com.nm.system.service.imp.UsersSerivceImp;
import com.nm.utiles.DateForMat;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 资薪发放servlet
 * @Author 容学斌
 * @Version 1.0
 **/
@WebServlet("/finance/addSalaryServlet")
public class AddSalaryServlet extends HttpServlet {
    IUsersSerivce iUsersSerivce = new UsersSerivceImp();
    ISalaryRec iSalaryRec = new SalaryRecImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try{
                //格式化日期
                ConvertUtils.register(new DateForMat("yyyy-MM"), Date.class);
                //获取页面数据信息
                SalaryRec salaryRec = RequestBeanUtils.requestToSimpleBean(request, SalaryRec.class);

                //进行添加，调用service
                int i = iSalaryRec.addiSalaryRecService(salaryRec);

                //将用户信息返回
                //获取领取人的姓名
                //调用Serivce层的方法
                List<Users> list = iUsersSerivce.selectUsersListAndWhere(new Users());
                //将数据返回给页面
                request.setAttribute("usersList",list);

                String tip = "添加失败";
                if(i>0){
                    tip = "添加成功";
                }
                request.setAttribute("tip",tip);
                //将添加成功数据信息返回给页面
                request.setAttribute("salary",salaryRec);

                //跳转到添加页面
                request.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(request,response);
            }catch (Exception e){

                e.printStackTrace();
                String tip = "添加成功";
                request.setAttribute("tip",tip);
                request.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(request,response);
            }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try{
            //获取领取人的姓名
            //调用Serivce层的方法
            List<Users> list = iUsersSerivce.selectUsersListAndWhere(new Users());
            //将数据返回给页面
            request.setAttribute("usersList",list);

            request.getRequestDispatcher("/view/finance/salary/salarypayment_add.jsp").forward(request,response);
        }catch (Exception e){

            e.printStackTrace();
        }

    }
}
