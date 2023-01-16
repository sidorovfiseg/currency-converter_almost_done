package com.example.currencyconverter.controllers;

import com.example.currencyconverter.entities.Role;
import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.repositories.RoleRepo;
import com.example.currencyconverter.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Controller
public class DefaultController {


    @GetMapping("/")
    public String greeting() {

        return "index";
    }
}
