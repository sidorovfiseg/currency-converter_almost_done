package com.example.currencyconverter.repositories;

import com.example.currencyconverter.entities.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

// Repo for exchangingRate
public interface ExchangeRateRepo extends CrudRepository<ExchangeRate, Integer> {

    ExchangeRate findByCurrencyId(int id);
}
