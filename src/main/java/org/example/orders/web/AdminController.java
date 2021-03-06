package org.example.orders.web;

import org.example.orders.model.Role;
import org.example.orders.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
public class AdminController extends AbstractUserController {
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", super.getAll());
        return "users";
    }

    @GetMapping("/add-role/{id}")
    public String addRole(@PathVariable int id){
        User user = get(id);
        if (!user.getRoles().contains(Role.OPERATOR)) {
            super.setRole(id);
        }
        return "redirect:/admin";
    }
}
