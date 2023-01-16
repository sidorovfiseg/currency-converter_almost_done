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

/*
Контроллер для авторизации пользователя
В начале мы вызываем первый раз метод
который добавляет в базу админа и менеджера
по сути это костыль и он не должен тут
находится, но уже нет времени исправлять
 */

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


    /*
    метод добавляет админа и менеджера в базу
     */

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

    /*
    get запрос показывает вьюшку
     */
    @GetMapping()
    public String auth() {

        if(flag) {
            createAdminAndManager();
            flag = false;
        }

        return "login";
    }

    /*
    пост запрос перенаправляет на
    странички пользователей
     */
    @PostMapping()
    public String login() {
        return "login";
    }
}
