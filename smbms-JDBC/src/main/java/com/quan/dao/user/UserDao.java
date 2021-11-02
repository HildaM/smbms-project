package com.quan.dao.user;

import com.quan.pojo.Role;
import com.quan.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: UserDao
 * @Description: 操作接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 19:13
 */
public interface UserDao {
    // 得到登录的用户
    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    // 修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException;

    // 根据用户名或角，查询用户数量
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException;

    // 获取用户列表
    public List<User> getUserList(Connection connection, String userName, int userRole,
                                  int currentPageNo, int pageSize) throws Exception;

    // 添加用户
    public boolean addUser(Connection connection, User user) throws SQLException;

    // 根据userCode查询用户
    public User getUserByUserCode(Connection connection, String userCode) throws SQLException;
}
