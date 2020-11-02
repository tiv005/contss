package com.nm.utiles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器，统一格式化字符集
 */
@WebFilter("/*")
public class CharSetFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //进行统一格式化字符集
        HttpServletRequest   request=(HttpServletRequest)req;
        HttpServletResponse response=(HttpServletResponse)resp;


         request.setCharacterEncoding("utf-8");
         response.setCharacterEncoding("utf-8");

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
