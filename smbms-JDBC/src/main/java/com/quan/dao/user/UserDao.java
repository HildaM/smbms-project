package com.quan.dao.user;

import com.quan.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

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
}
