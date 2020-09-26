package org.example.orders.web;

import org.example.orders.model.Request;
import org.example.orders.model.Status;
import org.example.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/orders")
public class JspOrderController extends AbstractOrderController {

    @Autowired
    UserService userService;

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Request request = get(id);
        model.addAttribute("request", request);
        return "orderForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Request request = new Request("DRAFT", "");
        model.addAttribute("request", request);
        return "orderForm";
    }

    @PostMapping
    public String save(HttpServletRequest request) throws IOException {
        Request r = new Request(request.getParameter("status"), request.getParameter("text"));

        if (request.getParameter("id").isEmpty()) {
            super.create(r);
        } else {
            super.update(r, getOrderId(request));
        }
        return "redirect:orders";
    }

    @GetMapping("/changeRole")
    public String changeStatus(HttpServletRequest request) throws IOException {
        Status status = Status.valueOf(request.getParameter("status"));
        int id = getOrderId(request);
        changeStatus(id, status);
        return "redirect:orders";
    }

    private int getOrderId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @GetMapping
    public String orderList(Model model) {
        model.addAttribute("user", userService.get(SecurityUtil.authUserId()));
        model.addAttribute("orders", super.getAll());
        return "orders";
    }

}
