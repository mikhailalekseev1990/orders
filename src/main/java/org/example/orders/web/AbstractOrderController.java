package org.example.orders.web;

import org.example.orders.model.Request;
import org.example.orders.model.Role;
import org.example.orders.model.Status;
import org.example.orders.service.OrderService;
import org.example.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.example.orders.util.ValidationUtil.assureIdConsistent;
import static org.example.orders.util.ValidationUtil.checkNew;

public class AbstractOrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    UserService userService;

    public Request get(int id) {
        int userId = SecurityUtil.authUserId();
        return orderService.get(id, userId);
    }

    public List<Request> getAll() {
        int userId = SecurityUtil.authUserId();
        if(userService.get(userId).getRoles().contains(Role.OPERATOR)) {
            return orderService.getAll();
        }else {
            return orderService.getAllByUser(userId);
        }
    }

    public Request create(Request request) {
        int userId = SecurityUtil.authUserId();
        checkNew(request);
        return orderService.create(request, userId);
    }

    public void update(Request request, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(request, id);
        orderService.update(request, userId);
    }

    public void changeStatus(int id, Status status){
        orderService.changeStatus(id, status);
    }
}
