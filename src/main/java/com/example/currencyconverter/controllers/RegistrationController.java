package com.example.currencyconverter.controllers;

import com.example.currencyconverter.entities.Role;
import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.forms.RegistrationForm;
import com.example.currencyconverter.repositories.RoleRepo;
import com.example.currencyconverter.repositories.UserRepo;
import com.example.currencyconverter.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepo userRepo;


    private PasswordEncoder passwordEncoder;

    private RoleRepo roleRepo;

    public RegistrationController(
            UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(RegistrationForm form) {

        Role userRole = new Role("ROLE_USER");
        roleRepo.save(userRole);

        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User user = form.toUser(passwordEncoder);
        user.setRoles(userRoles);

        userRepo.save(user);
        return "redirect:/login";
    }
}
