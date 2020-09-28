package org.example.orders.web;

import org.example.orders.model.Order;
import org.example.orders.service.OrderService;
import org.example.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.orders.util.ValidationUtil.assureIdConsistent;
import static org.example.orders.util.ValidationUtil.checkNew;

public class AbstractOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    UserService userService;

    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    public Order getByUser(int id) {
        int userId = userService.getByName(getCurrentUsername()).getId();
        return orderService.getByUser(id, userId);
    }

    public Order getByOperator(int id) {
                return orderService.getByOperator(id);
    }

    public List<Order> getAllByOperator() {
        return orderService.getAllByOperator();

    }

    public List<Order> getAllByUser() {
        int userId = userService.getByName(getCurrentUsername()).getId();
        return orderService.getAllByUser(userId);

    }

    public Order create(Order order) {
        int userId = userService.getByName(getCurrentUsername()).getId();
        checkNew(order);
        return orderService.create(order, userId);
    }

    public void update(Order order, int id) {
        int userId = userService.getByName(getCurrentUsername()).getId();
        assureIdConsistent(order, id);
        orderService.update(order, userId);
    }

    public void changeStatus(int id, String status) {
        orderService.changeStatus(id, status);
    }

    public String changeText(String text){
        List<String> list = new ArrayList<>(Arrays.asList(text.split("")));
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : list) {
            stringBuilder.append(s).append("-");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length()-1).toString();
    }
}
