package com.example.currencyconverter.entities;

import jakarta.persistence.*;

/*
Creating table for currencies, the params are parsed from
the xml file which one we are getting from Central Bank site
 */

@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String parsedId;

    private String numCode;

    private String charCode;

    private Integer nominal;

    private String name;

    public Currency() {

    }

    public Currency(String parsedId, String numCode, String charCode, Integer nominal, String name) {
        this.parsedId = parsedId;
        this.numCode = numCode;
        this.charCode = charCode;
        this.nominal = nominal;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParsedId() {
        return parsedId;
    }

    public void setParsedId(String parsedId) {
        this.parsedId = parsedId;
    }

    public String getNumCode() {
        return numCode;
    }

    public void setNumCode(String numCode) {
        this.numCode = numCode;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
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
}
