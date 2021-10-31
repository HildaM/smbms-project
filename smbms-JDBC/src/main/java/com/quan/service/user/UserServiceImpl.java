package com.quan.service.user;

import com.quan.dao.user.UserDao;
import com.quan.dao.user.UserDaoImpl;
import com.quan.pojo.User;
import com.quan.util.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: UserServiceImpl
 * @Description: 用户登录实现类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 19:30
 */
public class UserServiceImpl implements UserService{

    // 业务层都会调用dao层，所以我们要引入dao层
    private UserDao userDao;
    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    /**
     * service层捕获异常，进行事务处理
     * 事务处理：调用不同dao的多个方法，必须使用同一个connection（connection作为参数传递）
     * 事务完成之后，需要在service层进行connection的关闭，在dao层关闭PreparedStatement和ResultSet对象
     */
    @Override
    public User login(String userCode, String password) {
        Connection connection = null;
        User user = null;

        try {
            connection = BaseDao.getConnection();
            // 通过业务层调用对应的具体数据库操作
            user = userDao.getLoginUser(connection, userCode);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        // 匹配密码
        if (null != user) {
            // 密码错误
            if (!user.getUserPassword().equals(password)) {
                user = null;
            }
        }

        return user;
    }


    // 修改当前用户密码
    @Override
    public boolean updatePwd(int id, String password) {
        // 测试代码
//        System.out.println("UserServletImpl" + password);

        Connection connection = null;
        boolean flag = false;

        // 修改密码
        try {
            connection = BaseDao.getConnection();
            if (userDao.updatePwd(connection, id, password) > 0) {
                flag = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            BaseDao.closeResource(connection, null, null);
        }

        return flag;
    }


    // 根据用户名或角，查询用户数量
    @Override
    public int getUserCount(String userName, int userRole) {
        Connection connection = null;
        int userCount = 0;

        try {
            connection = BaseDao.getConnection();
            userCount = userDao.getUserCount(connection, userName, userRole);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return userCount;
    }

    // 获取角色列表
    @Override
    public List<User> getUserList(String userName, int userRole, int currentPageNo, int pageSize) {
        Connection connection = null;
        List<User> userList = null;

        try {
            connection = BaseDao.getConnection();
            userList = userDao.getUserList(connection, userName, userRole, currentPageNo, pageSize);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            BaseDao.closeResource(connection, null, null);
        }

        return userList;
    }

}
