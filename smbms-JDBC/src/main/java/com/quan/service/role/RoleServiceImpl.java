package com.quan.service.role;

import com.quan.dao.role.RoleDao;
import com.quan.dao.role.RoleDaoImpl;
import com.quan.pojo.Role;
import com.quan.util.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ClassName: RoleServiceImpl
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/1 19:03
 */
public class RoleServiceImpl implements RoleService{
    // 引入Dao接口
    private RoleDao roleDao;

    // 懒加载
    public RoleServiceImpl() {
        roleDao = new RoleDaoImpl();
    }

    // 获取角色列表
    @Override
    public List<Role> getRoleList() {
        Connection connection = null;
        List<Role> roleList = null;

        try {
            connection = BaseDao.getConnection();
            roleList = roleDao.getRoleList(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return roleList;
    }
}
