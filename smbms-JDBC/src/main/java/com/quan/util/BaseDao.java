package com.quan.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;


/**
 * @ClassName: BaseDao
 * @Description: 操作数据库的工具类
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 16:05
 */
public class BaseDao {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    // 静态代码块，类加载的时候，就会初始化
    static {
        Properties properties = new Properties();
        // 通过类加载器读取对应的资源
        InputStream is = BaseDao.class.getClassLoader().getResourceAsStream("db.properties");

        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("driver");
        url = properties.getProperty("url");
        username = properties.getProperty("username");
        password = properties.getProperty("password");
    }


    // 获取数据库连接
    public static Connection getConnection() {
        Connection connection = null;

        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    // 处理预编译sql的私有工具类
    private static void preCompile(Connection connection, String sql, Object[] params, PreparedStatement preparedStatement) throws SQLException {
        // 预编译的 sql，在后面直接执行就可以了
        preparedStatement = connection.prepareStatement(sql);

        for (int i = 0; i < params.length; i++) {
            // setObject 占位符从 1 开始，但是数组从 0 开始
            preparedStatement.setObject(i + 1, params[i]);
        }
    }


    // 编写查询公共方法
    public static ResultSet execute(Connection connection, String sql, Object[] params, ResultSet resultSet, PreparedStatement preparedStatement) throws SQLException {
        // 预编译sql代码
        preCompile(connection, sql, params, preparedStatement);

        resultSet = preparedStatement.executeQuery();
        return resultSet;
    }


    // 编写增删改公共方法
    public static int execute(Connection connection, String sql, Object[] params, PreparedStatement preparedStatement) throws SQLException {
        // 预编译sql代码
        preCompile(connection, sql, params, preparedStatement);

        int updateRows = preparedStatement.executeUpdate();
        return updateRows;
    }


    // 释放资源
    public static boolean closeResource(Connection connection, ResultSet resultSet, PreparedStatement preparedStatement) {
        boolean flag = true;

        if (resultSet != null) {
            try {
                resultSet.close();
                // GC回收
                resultSet = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if (preparedStatement != null) {
            try {
                preparedStatement.close();
                // GC回收
                preparedStatement = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        if (connection != null) {
            try {
                connection.close();
                // GC回收
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }
}