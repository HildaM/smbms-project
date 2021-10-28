package com.quan.service.user;


import com.quan.pojo.User;
import org.junit.jupiter.api.Test;

class UserServiceImplTest {

    @Test
    void login() {
        UserServiceImpl userService = new UserServiceImpl();
        User admin = userService.login("admin", "1234ssfssfsf567");

        System.out.println(admin.getUserPassword());
    }
}