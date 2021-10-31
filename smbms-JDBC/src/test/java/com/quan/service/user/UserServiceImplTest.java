package com.quan.service.user;


import com.quan.pojo.User;
import org.junit.jupiter.api.Test;

import java.util.List;

class UserServiceImplTest {

    @Test
    void login() {
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "1234ssfssfsf567");

        System.out.println(admin.getUserPassword());
    }

    @Test
    public void getUserCount() {
        UserServiceImpl userService = new UserServiceImpl();
        int userCount = userService.getUserCount(null, 0);

        System.out.println(userCount);
    }

    @Test
    public void getUserList() {
        UserServiceImpl userService = new UserServiceImpl();
        List<User> userList = userService.getUserList(null, 0, 1, 10);

        for (User user : userList) {
            System.out.println(user);
        }
    }
}