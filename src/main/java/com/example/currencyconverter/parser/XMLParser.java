package com.example.currencyconverter.parser;

import com.example.currencyconverter.entities.ExchangeRate;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


/*
interface for XML parser
 */
public interface XMLParser {
    List<ExchangeRate> parse(String url)
        throws ParserConfigurationException, SAXException, IOException;
}
