package com.example.currencyconverter.controllers;

import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.forms.RegistrationForm;
import com.example.currencyconverter.repositories.UserRepo;
import com.example.currencyconverter.servicies.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(
            UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String form() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@Valid RegistrationForm form, BindingResult result, Model model) {

        if(result.hasErrors()) {
            return "registration";
        }

        userRepo.save(form.toUser(passwordEncoder));
        model.addAttribute("users", userRepo.findAll());
        return "redirect:/login";
    }
}
