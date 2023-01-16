package com.example.currencyconverter.handlers;

import com.example.currencyconverter.entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/*
В этом обработки мы переопределяем метод
для перенаправления при авторизации в
зависимости от роли
 */

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        User user = (User) authentication.getPrincipal();

        String redirectURL = request.getContextPath();


        if(user.getRoles().iterator().next().getName().equals("ROLE_USER")) {
            redirectURL= "user";
        } else if (user.getRoles().iterator().next().getName().equals("ROLE_ADMIN")) {
            redirectURL = "admin";
        } else if (user.getRoles().iterator().next().getName().equals("ROLE_MANAGER")) {
            redirectURL = "manager";
        }

        response.sendRedirect(redirectURL);
    }
}
