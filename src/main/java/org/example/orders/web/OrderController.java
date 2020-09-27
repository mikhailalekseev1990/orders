package org.example.orders.web;

import org.example.orders.model.Request;
import org.example.orders.security.SecurityUtil;
import org.example.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Objects;

@Controller
@RequestMapping("/orders")
public class OrderController extends AbstractOrderController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable int id) {
        Request request = get(id);
        if (request.getStatus().equals("DRAFT")) { // проверка для редактирования черновика
            model.addAttribute("request", request);
            return "orderForm";
        } else {
            return "redirect:/orders";
        }
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/send/{id}")
    public String send(@PathVariable int id) {
        Request request = get(id);
        if (request.getStatus().equals("DRAFT")) { // проверка для редактирования черновика
            changeStatus(id, "SENT");
        }
        return "redirect:/orders";

    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/create")
    public String create(Model model) {
        Request request = new Request("DRAFT", "");
        model.addAttribute("request", request);
        return "orderForm";
    }

    @PreAuthorize("hasAnyAuthority('USER')")
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

    @PreAuthorize("hasAnyAuthority('OPERATOR')")
    @GetMapping("/changeStatus/{id}")
    public String changeStatus(Model model, @PathVariable int id) throws IOException {
        Request request = get(id);
        model.addAttribute("request", request);
        return "orderForm";

    }

    private int getOrderId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.parseInt(paramId);
    }

    @PreAuthorize("hasAnyAuthority('USER')")
    @GetMapping("/user")
    public String orderListByUser(Model model) {
        model.addAttribute("user", userService.get(SecurityUtil.authUserId()));
        model.addAttribute("orders", super.getAllByUser());
        return "orders";
    }

    @PreAuthorize("hasAnyAuthority('OPERATOR')")
    @GetMapping("/operator")
    public String orderListByOperator(Model model) {
        model.addAttribute("user", userService.get(SecurityUtil.authUserId()));
        model.addAttribute("orders", super.getAllByOperator());
        return "orders";
    }

}
