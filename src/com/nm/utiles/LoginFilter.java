package com.nm.utiles;

import com.nm.pojo.Users;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 容
 * @version 1.0
 * @date 2020/4/20 17:12
 */
//@WebFilter(filterName = "/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //获取session中数据信息,父类转化为子类
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        Users users = (Users) request.getSession().getAttribute("users");

        //进行获取地址信息
        String uri = request.getRequestURI();
        /**
         * 当session中没有数据信息，那么跳转至登录页面
         *      如果有用户信息则放行
         *  还需要放行登录的请求地址和静态资源（js css）
         */
        if(users!=null){
            chain.doFilter(req, resp);
        }else if (uri.contains("doLogin")){
            chain.doFilter(req, resp);
        }else if (uri.contains("resource")){
            chain.doFilter(req, resp);
        }else if (uri.contains("/web/view/login.jsp")){
            chain.doFilter(req, resp);
        }else{
            response.sendRedirect("/web/view/login.jsp");
        }

    }
    public void init(FilterConfig config) throws ServletException {
    }

}
