package com.example.currencyconverter.entities;

import org.springframework.security.core.GrantedAuthority;

/*
Roles for authorization
 */
public enum Role implements GrantedAuthority {

    USER,
    ADMIN,
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}
