package org.example.orders.web;

import org.example.orders.model.Request;
import org.example.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/orders")
@PreAuthorize("hasAnyAuthority('ROLE_USER')")
public class UserOrderController extends AbstractOrderController{
    @Autowired
    UserService userService;

    @GetMapping("/create")
    public String create(Model model) {
        Request request = new Request("DRAFT", "ertt");
        model.addAttribute("request", request);
        return "orderForm";
    }

    @GetMapping("/{id}/update")
    public String update(Model model, @PathVariable int id) {
        Request request = getByUser(id);
        if (request.getStatus().equals("DRAFT")) { // проверка для редактирования черновика
            model.addAttribute("request", request);
            return "orderForm";
        } else {
            return "redirect:/orders";
        }
    }

    @GetMapping("/{id}/send")
    public String send(@PathVariable int id) {
        Request request = getByUser(id);
        if (request.getStatus().equals("DRAFT")) { // проверка для редактирования черновика
            changeStatus(id, "SENT");
        }
        return "redirect:/orders";

    }

    @PostMapping
    public String save(HttpServletRequest request) throws IOException {
        request.getParameter("status");
        request.getParameter("text");
        Request r = new Request(request.getParameter("status"), request.getParameter("text"));

        if (request.getParameter("id").isEmpty()) {
            super.create(r);
        } else {
            super.update(r, getOrderId(request));
        }
        return "redirect:/orders";
    }

    @GetMapping
    public String orderListByUser(Model model) {
        model.addAttribute("orders", super.getAllByUser());
        return "orders";
    }

    private int getOrderId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }
}
