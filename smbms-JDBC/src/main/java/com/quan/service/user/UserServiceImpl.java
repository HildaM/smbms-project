package com.quan.service.user;

import com.quan.dao.user.UserDao;
import com.quan.dao.user.UserDaoImpl;
import com.quan.pojo.User;
import com.quan.util.BaseDao;

import java.sql.Connection;
import java.sql.SQLException;

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
}
