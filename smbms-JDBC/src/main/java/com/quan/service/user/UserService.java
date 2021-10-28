package com.quan.service.user;

import com.quan.pojo.User;

/**
 * @ClassName: UserService
 * @Description: 用户登录接口
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/10/28 19:29
 */
public interface UserService {
    // 用户登录
    public User login(String userCode, String password);
}
