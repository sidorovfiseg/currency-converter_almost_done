package com.example.currencyconverter.controllers;

import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.entities.UserExchange;
import com.example.currencyconverter.entities.UserRequest;
import com.example.currencyconverter.forms.RequestForm;
import com.example.currencyconverter.servicies.UserExchangeService;
import com.example.currencyconverter.servicies.UserRequestService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;

@Controller
@RequestMapping("/user")
public class UserExchangeController {
    private final UserExchangeService userExchangeService;
    private final UserRequestService userRequestService;

    private final String title = "Обмен валюты";

    public UserExchangeController(UserExchangeService userExchangeService, UserRequestService userRequestService) {
        this.userExchangeService = userExchangeService;
        this.userRequestService = userRequestService;
    }

    @GetMapping
    public String exchange(Model model)
            throws IOException, SAXException, ParserConfigurationException {
        model.addAttribute("currencies", userExchangeService.getCurrencies());
        model.addAttribute("metaTitle", title);
        return "user";
    }

    @GetMapping("/pair")
    public String accepted() {
        return "accepted";
    }

    @PostMapping()
    public String addExchange(@AuthenticationPrincipal User user,
                              @ModelAttribute UserExchange exchange,
                              Model model)
            throws ParseException, IOException, SAXException, ParserConfigurationException {
        userExchangeService.checkExchangeRatesUpToDate(exchange);
        userExchangeService.setExchangeParams(exchange, user);

        model.addAttribute("currencies", userExchangeService.getCurrencies());
        model.addAttribute("exchanges", userExchangeService.getUserExchanges());
        model.addAttribute("metaTitle", title);

        return "user";
    }

    @PostMapping("/pair")
    public String addPair(@AuthenticationPrincipal User user,
                              @ModelAttribute UserExchange exchange,
                              RequestForm form,
                              Model model)
            throws ParseException, IOException, SAXException, ParserConfigurationException {

        userRequestService.createUserRequest(form.toUserRequest(user));

        return "redirect:/user/pair";
    }



}
