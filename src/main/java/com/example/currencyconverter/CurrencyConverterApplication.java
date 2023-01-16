package com.example.currencyconverter;

import com.example.currencyconverter.entities.Role;
import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.repositories.RoleRepo;
import com.example.currencyconverter.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CurrencyConverterApplication {


	public static void main(String[] args) {

		SpringApplication.run(CurrencyConverterApplication.class, args);

	}

}
