package com.example.currencyconverter.controllers;

import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.entities.UserRequest;
import com.example.currencyconverter.servicies.UserExchangeService;
import com.example.currencyconverter.servicies.UserRequestService;
import com.example.currencyconverter.servicies.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

/*
по сути урезанный контроллер для админа
есл что смотри контроллер админа для пояснений
 */

@Controller
public class ManagerController {

    private final UserService userService;
    private final UserRequestService userRequestService;

    private final UserExchangeService userExchangeService;

    public ManagerController(UserService userService, UserRequestService userRequestService,
                             UserExchangeService userExchangeService) {
        this.userService = userService;
        this.userRequestService = userRequestService;
        this.userExchangeService = userExchangeService;
    }

    @GetMapping("/manager")
    public String manager(Model model) {

        model.addAttribute("usersRequest", userRequestService.getUserRequests());

        return "manager";
    }


    @RequestMapping(value = "/manager", params = "submit", method = RequestMethod.POST)
    public String submit(final HttpServletRequest request, Model model) {

        final Long requestId = Long.valueOf(request.getParameter("submit"));
        UserRequest userRequest = userRequestService.findUserRequestById(requestId);
        userRequestService.acceptUserRequest(userRequest);
        userRequestService.dropUserRequest(userRequest);
        model.addAttribute("usersRequests", userRequestService.getUserRequests());
        return "redirect:/manager";

    }

    @RequestMapping(value = "/manager", params = "delete", method = RequestMethod.POST)
    public String delete(final HttpServletRequest request, Model model) {
        final Long rowId = Long.valueOf(request.getParameter("delete"));
        UserRequest userRequest = userRequestService.findUserRequestById(rowId);
        userRequestService.dropUserRequest(userRequest);
        model.addAttribute("usersRequests", userRequestService.getUserRequests());
        return "redirect:/manager";
    }

}