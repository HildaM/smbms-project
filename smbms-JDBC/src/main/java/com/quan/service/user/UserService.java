package com.quan.service.user;

import com.quan.pojo.Role;
import com.quan.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: UserService
 * @Description: 用户登录接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 19:29
 */
public interface UserService {
    // 用户登录
    public User login(String userCode, String password);

    // 修改当前用户密码
    public boolean updatePwd(int id, String password);

    // 根据用户名或角，查询用户数量
    public int getUserCount(String userName, int userRole);

    // 获取用户列表
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize);
}
