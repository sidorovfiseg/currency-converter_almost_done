package com.example.currencyconverter.servicies;

import com.example.currencyconverter.entities.Currency;
import com.example.currencyconverter.entities.ExchangeRate;
import com.example.currencyconverter.parser.XMLParser;
import com.example.currencyconverter.parser.XMLParserSAX;
import com.example.currencyconverter.repositories.CurrencyRepo;
import com.example.currencyconverter.repositories.ExchangeRateRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


/*
Reading url and method from application properties
saving tables to postgress
 */
@Service
public class XMLParserService {

    private final ExchangeRateRepo exchangeRateRepo;

    private final CurrencyRepo currencyRepo;

    @Value("${parse.url}")
    private String url;

    @Value("${parse.method}")
    private String parseMethod;

    private XMLParser parser;

    public XMLParserService(ExchangeRateRepo exchangeRateRepo, CurrencyRepo currencyRepo) {
        this.exchangeRateRepo = exchangeRateRepo;
        this.currencyRepo = currencyRepo;
    }

    public ExchangeRateRepo getExchangeRateRepo() {
        return exchangeRateRepo;
    }

    public CurrencyRepo getCurrencyRepo() {
        return currencyRepo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParseMethod() {
        return parseMethod;
    }

    public void setParseMethod(String parseMethod) {
        this.parseMethod = parseMethod;
    }

    public XMLParser getParser() {
        return parser;
    }



    public void setParser(XMLParser parser) {
        this.parser = parser;
    }

    public void getCurrenciesAndExchangeRates()
            throws ParserConfigurationException, SAXException, IOException {
        if(parseMethod.equalsIgnoreCase("SAX")) {
            parser = new XMLParserSAX();
        } else {
            throw new ParserConfigurationException(
                    "Set Parse method in application.properties"
            );
        }

        List<ExchangeRate> exchangeRates = parser.parse(url);

        Set<Currency> currencies = new TreeSet<>(Comparator.comparing(Currency::getCharCode));
        exchangeRates.forEach(exchangeRate -> currencies.add(exchangeRate.getCurrency()));

        currencyRepo.saveAll(currencies);
        exchangeRateRepo.saveAll(exchangeRates);
    }
}
