package com.quan.dao.role;

import com.quan.pojo.Role;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: RoleDao
 * @Description: 角色接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/1 19:00
 */
public interface RoleDao {
    // 获取角色列表
    public List<Role> getRoleList(Connection connection) throws SQLException;
}
