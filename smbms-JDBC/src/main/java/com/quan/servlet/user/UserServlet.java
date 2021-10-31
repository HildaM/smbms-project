package com.quan.servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.quan.pojo.User;
import com.quan.service.user.UserService;
import com.quan.service.user.UserServiceImpl;
import com.quan.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: UserServlet
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/31 11:08
 */


// 实现Servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");

        if (method == null) return;

        if (method.equals("savepwd")) {      // 更新用户密码
            this.updatePwd(req, resp);
        }
        else if (method.equals("pwdmodify")) {    // 验证旧密码
            this.pwdmodify(req, resp);
        }
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }


    // 更新用户密码
    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) {
        // 从Session里面拿ID
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");

        // 测试代码
//        System.out.println("UserServlet" + newpassword);

        boolean flag = false;

        if (null != attribute && newpassword != null && newpassword.length() != 0) {
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User) attribute).getId(), newpassword);

            if (flag) {
                req.setAttribute("message", "修改密码成功！请退出，使用新密码登录！");
                // 密码修改成功，移除旧的Session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }
            else {
                req.setAttribute("message", "修改密码失败！");
            }
        }
        else {
            req.setAttribute("message", "新密码有问题！");
        }

        // req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
        try {
            resp.sendRedirect(req.getContextPath() + "/jsp/pwdmodify.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 验证旧密码
    private void pwdmodify(HttpServletRequest req, HttpServletResponse resp) {
        // 从Session中获取旧密码
        Object attribute = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        // 万能map
        Map<String, String> resultMap = new HashMap<>();

        if (attribute == null) {                            // Session 失效了 或者 Session过期了
            resultMap.put("result", "sessionerror");
        }
        else if (StringUtils.isNullOrEmpty(oldpassword)) {
            resultMap.put("result", "error");               // 密码为空
        }
        else {
            String userPassword = ((User) attribute).getUserPassword();   // Session用户的密码
            if (oldpassword.equals(userPassword)) {
                resultMap.put("result", "true");            // 旧密码正确
            }
            else {
                resultMap.put("result", "false");           // 旧密码错误
            }
        }


        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            // JsonArray: 工具类 ---> 转换格式
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();     // 刷新
            writer.close();     // 关闭
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
