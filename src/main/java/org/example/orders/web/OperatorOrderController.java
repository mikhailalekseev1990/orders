package org.example.orders.web;

import org.example.orders.model.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/operator")
@PreAuthorize("hasAnyAuthority('ROLE_OPERATOR')")
public class OperatorOrderController extends AbstractOrderController {


    @GetMapping("/change-status/{id}")
    public String viewOrder(@PathVariable int id, Model model) throws IOException {
        Order order = getByOperator(id);
        String text = order.getText();
        model.addAttribute("text", changeText(text));
        model.addAttribute("order", order);
        return "operatorForm";
    }

    @PostMapping
    public String changeStatus(HttpServletRequest request) throws IOException {
       String status =  request.getParameter("status");
       int id = Integer.parseInt(request.getParameter("id"));
       super.changeStatus(id, status);
        return "redirect:/operator";
    }

    @GetMapping
    public String orderListByOperator(Model model) {
        model.addAttribute("orders", super.getAllByOperator());
        return "orders";
    }
}
