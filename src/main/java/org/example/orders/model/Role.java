package org.example.orders.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    OPERATOR;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }
}
