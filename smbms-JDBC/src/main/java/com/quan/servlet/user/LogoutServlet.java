package com.quan.servlet.user;

import com.quan.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: LogoutServlet
 * @Description: 实现注销功能
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/31 9:39
 */
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 移除用户的Session
        req.getSession().removeAttribute(Constants.USER_SESSION);

        // 返回登录页面
        resp.sendRedirect(req.getContextPath() + "/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
