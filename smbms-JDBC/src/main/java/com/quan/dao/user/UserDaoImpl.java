package com.quan.dao.user;

import com.quan.pojo.User;
import com.quan.util.BaseDao;
import com.sun.corba.se.spi.ior.ObjectAdapterId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @ClassName: UserDaoImpl
 * @Description:  UserDao接口实现类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 19:15
 */
public class UserDaoImpl implements UserDao{
    @Override
    public User getLoginUser(Connection connection, String userCode) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        // 以防为空
        if (connection != null) {
            String sql = "select * from smbms_user where userCode = ?";
            Object[] params = {userCode};

            // execute(connection, sql, params, rs, preparedStatement)
            rs = BaseDao.execute(connection, sql, params, rs, pstm);

            // 将找到的User数据装载到User类中
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }

            BaseDao.closeResource(null, rs, pstm);
        }

        return user;
    }


    // 修改当前用户密码
    @Override
    public int updatePwd(Connection connection, int id, int password) throws SQLException {
        PreparedStatement pstm = null;
        int execute = 0;

        if (null != connection) {
            String sql = "update smbms_user set userPassword = ? where id = ?";
            Object[] params = {password, id};
            execute = BaseDao.execute(connection, sql, params, pstm);

            BaseDao.closeResource(connection, null, pstm);
        }

        return execute;
    }
}
