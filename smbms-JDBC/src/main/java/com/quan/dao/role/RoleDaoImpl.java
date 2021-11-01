package com.quan.dao.role;

import com.quan.pojo.Role;
import com.quan.util.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RoleDaoImpl
 * @Description: 角色实现类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/1 19:01
 */
public class RoleDaoImpl implements RoleDao {

    // 获取角色列表
    @Override
    public List<Role> getRoleList(Connection connection) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Role> roleList = new ArrayList<>();

        if (connection != null) {
            String sql = "select * from smbms_role";

            Object[] params = {};
            rs = BaseDao.execute(connection, sql, params, rs, pstm);

            while (rs.next()) {
                Role _role = new Role();
                _role.setId(rs.getInt("id"));
                _role.setRoleCode(rs.getString("roleCode"));
                _role.setRoleName(rs.getString("roleName"));
                roleList.add(_role);
            }

            BaseDao.closeResource(null, rs, pstm);
        }

        return roleList;
    }
}
