package org.example.orders.web;

import org.example.orders.model.Role;
import org.example.orders.security.SecurityUtil;
import org.example.orders.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
public class RootController {

    @Autowired
    UserService userService;
    private String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

    @GetMapping(value = "/")
    public String root() {
        Set<Role> roles = userService.get( userService.getByName(getCurrentUsername()).getId()).getRoles();
        if (roles.contains(Role.ADMIN)) {
            return "redirect:admin";
        } else if (roles.contains(Role.OPERATOR)) {
            return "redirect:operator";
        } else {
            return "redirect:orders";
        }
    }

    @GetMapping(value = "/login")
    public String login(ModelMap model,
                        @RequestParam(value = "error", required = false) boolean error,
                        @RequestParam(value = "message", required = false) String message) {
        model.put("error", error);
        model.put("message", message);
        return "login";
    }

}
