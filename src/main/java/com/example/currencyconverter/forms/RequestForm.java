package com.example.currencyconverter.forms;

import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.entities.UserRequest;

public class RequestForm {

    private String char_code;

    private Integer nominal;

    private String name;

    private Double value;


    public RequestForm(String char_code, Integer nominal, String name, Double value) {
        this.char_code = char_code;
        this.nominal = nominal;
        this.name = name;
        this.value = value;
    }

    public String getChar_code() {
        return char_code;
    }

    public void setChar_code(String char_code) {
        this.char_code = char_code;
    }

    public Integer getNominal() {
        return nominal;
    }

    public void setNominal(Integer nominal) {
        this.nominal = nominal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public UserRequest toUserRequest(User user) {
        return new UserRequest(char_code, name, nominal, value, user);
    }
}
