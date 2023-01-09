package com.example.currencyconverter.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;


/*
Table for users that clicked exchange button
info about exchange operation how much
one currency you can get from another currency
 */

@Entity
@Table(name = "exchange pairs")
public class UserExchange {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    private Currency sourceCurrency;

    @ManyToOne
    private Currency targetCurrency;

    private String amount;

    private BigDecimal result;

    @ManyToOne
    private User user;

    private LocalDate date;

    private BigDecimal conversionRate;

    public UserExchange() {
    }

    public UserExchange(Integer id, Currency sourceCurrency,
                        Currency targetCurrency, String amount,
                        BigDecimal result, User user, LocalDate date,
                        BigDecimal conversionRate
    ) {
        this.id = id;
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        this.result = result;
        this.user = user;
        this.date = date;
        this.conversionRate = conversionRate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Currency getSourceCurrency() {
        return sourceCurrency;
    }

    public void setSourceCurrency(Currency sourceCurrency) {
        this.sourceCurrency = sourceCurrency;
    }

    public Currency getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Currency targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public BigDecimal getResult() {
        return result;
    }

    public void setResult(BigDecimal result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(BigDecimal conversionRate) {
        this.conversionRate = conversionRate;
    }
}
