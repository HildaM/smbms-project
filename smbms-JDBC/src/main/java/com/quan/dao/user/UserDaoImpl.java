package com.quan.dao.user;

import com.mysql.cj.util.StringUtils;
import com.quan.pojo.Role;
import com.quan.pojo.User;
import com.quan.util.BaseDao;
import com.sun.corba.se.spi.ior.ObjectAdapterId;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public int updatePwd(Connection connection, int id, String password) throws SQLException {
        // 测试代码
//        System.out.println("UserDaoImpl" + password);

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

    // 根据用户名或角，查询用户数量
    @Override
    public int getUserCount(Connection connection, String userName, int userRole) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int count = 0;

        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select count(1) as count from smbms_user u, smbms_role r where u.userRole = r.id");

            // 存放我们的参数
            ArrayList<Object> paramsList = new ArrayList<Object>();

            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                paramsList.add("%" + userName + "%");
            }
            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                paramsList.add(userRole);
            }

            Object[] params = paramsList.toArray();

            // 输出测试
            System.out.println("UserDaoImpl -> getUserCount：" + sql.toString());

            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);

            if (rs.next()) {
                // 从结果集中获取结果集数量
                count = rs.getInt("count");
            }

            BaseDao.closeResource(null, rs, pstm);
        }

        return count;
    }

    // 获取用户列表
    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<User> userList = new ArrayList<>();

        if (connection != null) {
            StringBuilder sql = new StringBuilder();
            sql.append("select u.*, r.roleName as userRoleName from smbms_user u, smbms_role r where u.userRole = r.id");

            ArrayList<Object> list = new ArrayList<>();
            if (!StringUtils.isNullOrEmpty(userName)) {
                sql.append(" and u.userName like ?");
                list.add("%" + userName + "%");
            }
            if (userRole > 0) {
                sql.append(" and u.userRole = ?");
                list.add(userRole);
            }

            // 在数据库中，分页使用limit
            /**
              limit startIndex, pageSize
             **/
            sql.append(" order by creationDate DESC limit ?,?");
            currentPageNo = (currentPageNo - 1) * pageSize;   // 因为我们传进去的页面数从1开始，但是程序是从0开始的，所以要减1
            list.add(currentPageNo);
            list.add(pageSize);

            // System.out.println("UserDaoImpl---getUserList:" + sql.toString());

            Object[] params = list.toArray();
            rs = BaseDao.execute(connection, sql.toString(), params, rs, pstm);
            while (rs.next()) {
                User _user = new User();
                _user.setId(rs.getInt("id"));
                _user.setUserCode(rs.getString("userCode"));
                _user.setUserName(rs.getString("userName"));
                _user.setGender(rs.getInt("gender"));
                _user.setBirthday(rs.getDate("birthday"));
                _user.setPhone(rs.getString("phone"));
                _user.setUserRole(rs.getInt("userRole"));
                _user.setUserRoleName(rs.getString("userRoleName"));
                userList.add(_user);
            }

            BaseDao.closeResource(null, rs, pstm);
        }

        return userList;
    }

    // 添加用户
    @Override
    public boolean addUser(Connection connection, User user) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;

        if (connection != null) {
            String sql = "insert into smbms_user (id, userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate, modifyBy, modifyDate) values (?, ?, ?, ?, ?, ?, ? ,? ,? ,? ,?, ?, ?)";

            // 注入参数
            ArrayList<Object> tempParams = new ArrayList<>();
            tempParams.add(user.getId());
            tempParams.add(user.getUserCode());
            tempParams.add(user.getUserName());
            tempParams.add(user.getUserPassword());
            tempParams.add(user.getGender());
            tempParams.add(user.getBirthday());
            tempParams.add(user.getPhone());
            tempParams.add(user.getAddress());
            tempParams.add(user.getUserRole());
            tempParams.add(user.getCreatedBy());
            tempParams.add(user.getCreationDate());
            tempParams.add(user.getModifyBy());
            tempParams.add(user.getModifyDate());

            Object[] params = tempParams.toArray();

            if (BaseDao.execute(connection, sql, params, pstm) > 0) {
                // 成功添加
                flag = true;
            }

            BaseDao.closeResource(null, null, pstm);
        }

        return flag;
    }

    // 根据userCode查询用户
    @Override
    public User getUserByUserCode(Connection connection, String userCode) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if (connection != null) {
            String sql = "select * from smbms_user where userCode = ?";
            Object[] params = {userCode};

            rs = BaseDao.execute(connection, sql, params, rs, pstm);

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

    // 通过id删除指定User
    @Override
    public boolean deleteUserById(Connection connection, Integer userId) throws SQLException {
        PreparedStatement pstm = null;
        boolean flag = false;

        if (connection != null) {
            String sql = "delete from smbms_user where id = ?";
            Object[] params = {userId};

            if (BaseDao.execute(connection, sql, params, pstm) > 0) {
                flag = true;
            }

            BaseDao.closeResource(null, null, pstm);
        }

        return flag;
    }
}
