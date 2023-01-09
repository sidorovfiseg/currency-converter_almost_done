package com.example.currencyconverter.parser;

import com.example.currencyconverter.entities.Currency;
import com.example.currencyconverter.entities.ExchangeRate;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
In this file we are parsing our XML and creating List of currencies
and exchangesRates
 */

public class XMLParserSAX implements XMLParser{

    private final List<Currency> currenciesSAX;

    private final List<ExchangeRate> exchangeRatesSAX;

    public XMLParserSAX() {
        this.currenciesSAX = new ArrayList<>();
        this.exchangeRatesSAX = new ArrayList<>();
    }

    /*
    Parse method which is returning ready Currencies
    and exchanges rates
     */
    @Override
    public List<ExchangeRate> parse(String url)
            throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser =factory.newSAXParser();

        XMLHandler handler = new XMLHandler();
        parser.parse(url, handler);

        Currency rub = new Currency(
                "", "643",
                "RUB", 1, "Российский рубль"
        );
        exchangeRatesSAX.add(new ExchangeRate(rub, 1.0));

        return exchangeRatesSAX;

    }

    /*
    In this class we are parsing using override methods
    of DefaultHandler
     */
    private class XMLHandler extends DefaultHandler {

        private String parsedId;

        private String numCode;
        private String charCode;
        private Integer nominal;
        private String name;
        private Double value;
        private String lastElementName;

        //Reading parsedId
        @Override
        public void startElement(String url, String localName, String qName,
                                 Attributes attributes) {

            lastElementName = qName;
            if (qName.equals("Valute")) {
                parsedId = attributes.getValue("ID");
            }
        }

        //Reading each currency info in our currency params
        @Override
        public void characters(char[] ch, int start, int length) {
            String information = new String(ch, start, length);
            information = information.replace("\n", "").trim();

            switch (lastElementName) {
                case "NumCode":
                    numCode = information;
                    break;
                case "CharCode":
                    charCode = information;
                case "Nominal":
                    nominal = Integer.parseInt(information);
                    break;
                case "Value":
                    value = Double.parseDouble(information.replace(",", "."));
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Illegal element" + lastElementName
                    );
            }
        }

        /*
        If data read successfully, pushing Currency to it List
        and alo pushing Exchange rate with extra param value
        witch means price for currency nominal
         */
        @Override
        public void endElement(String uri, String localName, String qName) {
            if(parsedId != null && !parsedId.isEmpty() &&
                    numCode != null && !numCode.isEmpty() &&
                    charCode != null && !charCode.isEmpty() &&
                    nominal != null &&
                    name != null && !name.isEmpty() &&
                    value != null
            ) {
                Currency currency = new Currency(parsedId, numCode, charCode, nominal, name);
                currenciesSAX.add(currency);
                exchangeRatesSAX.add(new ExchangeRate(currency, value));
            }
        }

    }

}


