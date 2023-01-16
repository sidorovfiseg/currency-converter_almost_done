package com.example.currencyconverter.servicies;

import com.example.currencyconverter.entities.Currency;
import com.example.currencyconverter.entities.ExchangeRate;
import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.entities.UserRequest;
import com.example.currencyconverter.repositories.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserRequestService {

    private final UserRequestRepo userRequestRepo;

    private final UserRepo userRepo;

    private final CurrencyRepo currencyRepo;

    private final ExchangeRateRepo exchangeRateRepo;


    public UserRequestService(UserRequestRepo userRequestRepo, UserRepo userRepo,
                              CurrencyRepo currencyRepo, ExchangeRateRepo exchangeRateRepo) {
        this.userRequestRepo = userRequestRepo;
        this.userRepo = userRepo;
        this.currencyRepo = currencyRepo;
        this.exchangeRateRepo = exchangeRateRepo;
    }

    public void createUserRequest(UserRequest userRequest) {
        userRequestRepo.save(userRequest);
    }

    public List<UserRequest> getUserRequests(String username) {
        List<UserRequest> requests = new ArrayList<>();
        User user = userRepo.findByUsername(username);
        userRequestRepo.findAllByUserId(user.getId()).forEach(requests::add);
        return requests;
    }

    public List<UserRequest> getUserRequests() {

        return userRequestRepo.findAll();
    }

    public UserRequest findUserRequestById(Long id) {
        return userRequestRepo.findUserRequestById(id);

    }

    public void acceptUserRequest(UserRequest userRequest) {
        String newParseId = "RO" + (2000 + new Random().nextInt(1000));
        String numCode = Integer.valueOf(1000 + new Random().nextInt(1000)).toString();
        Currency currency = new Currency(newParseId, numCode, userRequest.getChar_code(),
                userRequest.getNominal(), userRequest.getName());
        ExchangeRate exchangeRate = new ExchangeRate(currency, userRequest.getValue());

        currencyRepo.save(currency);
        exchangeRateRepo.save(exchangeRate);

    }

    public void dropUserRequest(UserRequest userRequest) {
        userRequestRepo.delete(userRequest);
    }



}
