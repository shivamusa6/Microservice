package com.in28minutes.microservice.currencyexchangeservice.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
public class ExchangeValue {

    @Id
    private  Long id;
    @Column(name="currency_from")
    private  String from;
    @Column(name="currency_to")
    private  String to;
    private BigDecimal conversionMultiple;
    private int port;

    public ExchangeValue(Long id, String from, String to, BigDecimal conversionMultiple) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }



    public ExchangeValue() {
    }

    public Long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public BigDecimal getConversionMultiple() {
        return conversionMultiple;
    }


}
