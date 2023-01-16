package com.example.currencyconverter.controllers;

import com.example.currencyconverter.entities.Role;
import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.repositories.RoleRepo;
import com.example.currencyconverter.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static boolean flag = true;
    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepo roleRepo;


    private void createAdminAndManager() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleManager = new Role("ROLE_MANAGER");

        roleRepo.save(roleAdmin);
        roleRepo.save(roleManager);

        Set<Role> rolesAdmin =new HashSet<>();
        Set<Role> rolesManager = new HashSet<>();

        rolesAdmin.add(roleAdmin);
        rolesManager.add(roleManager);

        User admin = new User("admin", "admin");
        admin.setRoles(rolesAdmin);
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        User manager = new User("manager", "manager");
        manager.setRoles(rolesManager);
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));

        userRepo.save(admin);
        userRepo.save(manager);

    }


    @GetMapping()
    public String auth() {

        if(flag) {
            createAdminAndManager();
            flag = false;
        }

        return "login";
    }

    @PostMapping()
    public String login() {
        return "login";
    }
}
