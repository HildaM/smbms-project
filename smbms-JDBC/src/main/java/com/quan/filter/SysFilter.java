package com.quan.filter;

import com.quan.pojo.User;
import com.quan.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: SysFilter
 * @Description: 权限过滤器
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/31 9:50
 */
public class SysFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        // 从Session获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);

        if (null == user) {  // 被移除、被注销、未登录
            response.sendRedirect(request.getContextPath() + "error.jsp");
        }

        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
