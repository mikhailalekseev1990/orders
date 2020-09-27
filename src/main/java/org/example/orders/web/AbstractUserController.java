package org.example.orders.web;

import org.example.orders.model.User;
import org.example.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractUserController {

    @Autowired
    UserService userService;

    public List<User> getAll() {
        return userService.getAll();
    }

    public User get(int id) {
        return userService.get(id);
    }

    public boolean setRole(int id) {
        return userService.setRole(id);
    }

}
