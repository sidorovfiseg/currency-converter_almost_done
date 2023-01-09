package com.example.currencyconverter.repositories;

import com.example.currencyconverter.entities.Currency;
import org.springframework.data.repository.CrudRepository;

// Repo for currencies
public interface CurrencyRepo extends CrudRepository<Currency, Integer> {

}
