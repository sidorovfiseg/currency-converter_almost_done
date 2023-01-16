package com.example.currencyconverter.repositories;

import com.example.currencyconverter.entities.Currency;
import org.springframework.data.repository.CrudRepository;

/*
Hibernate так нам создает таблицы в базе
в некоторых местах добавлены интерфейсы
которые по сути нужны для поиска или
удаления в таблицах
 */

public interface CurrencyRepo extends CrudRepository<Currency, Integer> {

}
