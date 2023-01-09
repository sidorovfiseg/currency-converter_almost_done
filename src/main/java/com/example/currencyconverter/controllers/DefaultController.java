package com.example.currencyconverter.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DefaultController {

    @GetMapping("/")
    public String greeting(Model model) {
        model.addAttribute("metaTitle", "Главная");
        return "index";
    }
}
