package com.example.currencyconverter.servicies;

import com.example.currencyconverter.entities.Currency;
import com.example.currencyconverter.entities.ExchangeRate;
import com.example.currencyconverter.entities.User;
import com.example.currencyconverter.entities.UserExchange;
import com.example.currencyconverter.repositories.CurrencyRepo;
import com.example.currencyconverter.repositories.ExchangeRateRepo;
import com.example.currencyconverter.repositories.UserExchangeRepo;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/*
Здесь реализована вся логика
для расчета валюты в валютной паре
 */

@Service
public class UserExchangeService {
    private final CurrencyRepo currencyRepo;

    private final ExchangeRateRepo exchangeRateRepo;

    private final UserExchangeRepo exchangeRepo;

    private final XMLParserService xmlParserService;

    public UserExchangeService(CurrencyRepo currencyRepo,
                               ExchangeRateRepo exchangeRateRepo,
                               UserExchangeRepo exchangeRepo,
                               XMLParserService xmlParserService) {
        this.currencyRepo = currencyRepo;
        this.exchangeRateRepo = exchangeRateRepo;
        this.exchangeRepo = exchangeRepo;
        this.xmlParserService = xmlParserService;
    }

    public void checkExchangeRatesUpToDate(UserExchange exchange)
            throws ParserConfigurationException, SAXException, IOException {
        Currency sourceCurrency = exchange.getSourceCurrency();
        Currency targetCurrency = exchange.getTargetCurrency();

        if (sourceCurrency == null || targetCurrency == null) {
            xmlParserService.getCurrenciesAndExchangeRates();
        } else {
            ExchangeRate sourceExRate = exchangeRateRepo.findByCurrencyId(sourceCurrency.getId());
            boolean sourceUpToDate = sourceExRate.getDate().isEqual(LocalDate.now());

            ExchangeRate targetExRate =exchangeRateRepo.findByCurrencyId(targetCurrency.getId());
            boolean targetUpToDate = targetExRate.getDate().isEqual(LocalDate.now());

            if (!sourceUpToDate || !targetUpToDate) {
                xmlParserService.getCurrenciesAndExchangeRates();
            }

        }
    }

    public List<Currency> getCurrencies()
            throws IOException, SAXException, ParserConfigurationException {
        if (currencyRepo.count() == 0) {
            xmlParserService.getCurrenciesAndExchangeRates();
        }

        List<Currency> currencies = new ArrayList<>();
        currencyRepo.findAll().forEach(currencies::add);
        return currencies;
    }

    public List<UserExchange> getUserExchanges() {
        List<UserExchange> exchanges = new ArrayList<>();
        exchangeRepo.findAll().forEach(exchanges::add);
        return exchanges;
    }


    public BigDecimal getThisConversionExchangeRate(UserExchange userExchange) {
        int sourceId = userExchange.getSourceCurrency().getId();
        BigDecimal sourceExchangeRate = BigDecimal.valueOf(exchangeRateRepo
                .findByCurrencyId(sourceId)
                .getValue());
        int targetId = userExchange.getTargetCurrency().getId();
        BigDecimal targetExchangeRate = BigDecimal.valueOf(exchangeRateRepo
                .findByCurrencyId(targetId)
                .getValue());
        return sourceExchangeRate.divide(targetExchangeRate, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal convert(UserExchange userExchange) throws ParseException {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("###.###", decimalFormatSymbols);
        decimalFormat.setParseBigDecimal(true);

        BigDecimal sourceAmount = (BigDecimal) decimalFormat.parse(userExchange.getAmount());
        BigDecimal conversionRate = getThisConversionExchangeRate(userExchange);
        return sourceAmount.multiply(conversionRate)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public void setExchangeParams(UserExchange userExchange, User user)
            throws ParseException {
        userExchange.setConversionRate(getThisConversionExchangeRate(userExchange));
        userExchange.setResult(convert(userExchange));
        userExchange.setUser(user);
        userExchange.setDate(LocalDate.now());
        exchangeRepo.save(userExchange);
    }




}
