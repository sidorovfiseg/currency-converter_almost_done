package com.example.currencyconverter.controllers;

import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.entities.UserRequest;
import com.example.currencyconverter.servicies.UserRequestService;
import com.example.currencyconverter.servicies.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/*
Контроллер для тех, кто зашел с ролью админа,
здесь обрабатываютс post и get запросы
 */

@Controller
public class AdminController {

    private final UserService userService;

    private final UserRequestService userRequestService;

    public AdminController(UserService userService, UserRequestService userRequestService) {
        this.userService = userService;
        this.userRequestService = userRequestService;
    }

    /*
    гет запрос показывает заявки и зарегестрированных пользователей кроме админа и менеджера
     */
    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsersExceptAdminAndManager());
        model.addAttribute("usersRequest", userRequestService.getUserRequests());
        return "admin";
    }


    /*
    пост запрос на добавление заявки
     */
    @RequestMapping(value = "/admin", params = "submit_request", method = RequestMethod.POST)
    public String submit_request(final HttpServletRequest request, Model model) {

        final Long requestId = Long.valueOf(request.getParameter("submit_request"));
        UserRequest userRequest = userRequestService.findUserRequestById(requestId);
        userRequestService.acceptUserRequest(userRequest);
        userRequestService.dropUserRequest(userRequest);
        model.addAttribute("usersRequests", userRequestService.getUserRequests());
        return "redirect:/admin";

    }

    /*
    пост запрос на удаление заявки
     */
    @RequestMapping(value = "/admin", params = "delete_request", method = RequestMethod.POST)
    public String delete_request(final HttpServletRequest request, Model model) {
        final Long rowId = Long.valueOf(request.getParameter("delete_request"));
        UserRequest userRequest = userRequestService.findUserRequestById(rowId);
        userRequestService.dropUserRequest(userRequest);
        model.addAttribute("usersRequests", userRequestService.getUserRequests());
        return "redirect:/admin";
    }

    /*
    пост запрос на удаление пользователя
     */
    @RequestMapping(value = "/admin", params = "delete_user", method = RequestMethod.POST)
    public String delete_user(final HttpServletRequest request, Model model) {
        final String username = String.valueOf(request.getParameter("delete_user"));
        User user = userService.getUserByName(username);
        userService.deleteUser(username);
        model.addAttribute("users", userService.getAllUsersExceptAdminAndManager());
        model.addAttribute("usersRequests", userRequestService.getUserRequests());
        return "redirect:/admin";

    }



}
