package com.nm.system.controller;

import com.my.web.servlet.RequestBeanUtils;
import com.nm.finance.service.ISalaryRec;
import com.nm.finance.service.imp.SalaryRecImp;
import com.nm.pojo.Menu;
import com.nm.pojo.SalaryRec;
import com.nm.pojo.Users;
import com.nm.system.service.IMenuSerivce;
import com.nm.system.service.IUsersSerivce;
import com.nm.system.service.imp.MenuSerivceImp;
import com.nm.system.service.imp.UsersSerivceImp;
import com.nm.utiles.DateForMat;
import org.apache.commons.beanutils.ConvertUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 用户登录servlet
 * @author 容学斌
 */
@WebServlet("/system/doLogin")
public class LgonServlet extends HttpServlet {

    ISalaryRec iSalaryRec = new SalaryRecImp();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        IUsersSerivce iUsersSerivce=new UsersSerivceImp();
        IMenuSerivce iMenuSerivce=new MenuSerivceImp();


      //获取数据数据
        //格式化字符集
     //   request.setCharacterEncoding("utf-8");
     //   response.setCharacterEncoding("utf-8");
                //获取对应的数据的
      // String name= request.getParameter("");
        /**
         * 之前是采用的String name= request.getParameter("");方式获取页码上的数据
         *    如数据多了，那么就要写多个String name= request.getParameter("");
         *  为了减少代码   RequestBeanUtils.requestToBean()  的工具类来来进行获取
         *  页码上的数据
         *     有个必要的条件
         *        页码上的name的值 必须要与实体类上的
         *        属性名称相同(get set 方法把get set去掉首小写)
         *
         *   如工具出行了乱码
         *       则设置字符集
         *          在tomcat 设置中的  VMxxx -Dfile.encoding=UTF-8
         *
         * */


        Users users = RequestBeanUtils.requestToBean(request, Users.class);
              System.out.println(users);
              //
               try {


                    //调用Serivce层的逻辑
                   //创建方法  用户登录的方法
                   //查询到了数据(账号和密码)
                   Users  user = iUsersSerivce.doLoginSerivce(users);
                   //进行判断当前的用户是否有数据
                   HttpSession session = request.getSession();

                    if(user!=null  && !"".equals(user.getuAccout())){
                     //就表示当前的用户是在数据中存在的
                        //将对应的用户的信息添加到Session中
                     session.setAttribute("users",user);
                  /**
                      * 如果用户登录成功之后，那么则立马查询
                      *         当前用户所对应的菜单的数据信息
                      */
                 List<Menu> listMneu = iMenuSerivce.selectMenuService(user.getR_Id());
                    //进行返回当前用户登录的菜单的数据
                     //将这里的数据存入到Session中
                     session.setAttribute("listMneu",listMneu);

                     /**
                      * 如果用户登录成功，
                      *     将图像报表显示给页面
                      *
                      */
                        ConvertUtils.register(new DateForMat("yyyy-MM"), Date.class);
                        SalaryRec salaryRec = RequestBeanUtils.requestToSimpleBean(request, SalaryRec.class);
                        List<SalaryRec> salaryRecs = iSalaryRec.selectSalaryHighcharts(salaryRec);
                        System.out.println(salaryRecs);
                        //然后将图形报表的数据存入到session中
                        session.setAttribute("salaryRecs",salaryRecs);

                        //就跳转到首页
                     request.getRequestDispatcher("/view/index.jsp").forward(request,response);

                 }else{
                     //如没有当前的用户就跳转到登陆页面
                     request.getRequestDispatcher("/view/login.jsp").forward(request,response);
                 }


               }catch (Exception  e){
                    //如出现了异常也是到登陆的页面
                   e.printStackTrace();
                   //将异常的信息返回给用户(提示用户)
                   request.setAttribute("tip", e.getMessage());
                   request.getRequestDispatcher("/view/login.jsp").forward(request,response);


               }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         doPost(request,response);
    }
}
