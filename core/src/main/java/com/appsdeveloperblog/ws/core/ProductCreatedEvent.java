package com.appsdeveloperblog.ws.core;

import java.math.BigDecimal;

public class ProductCreatedEvent {


    public ProductCreatedEvent() {

    }

    private String productID;
    private String tittle ;
    private BigDecimal price ;
    private  Integer quantity ;

    public ProductCreatedEvent(String productID, String tittle, BigDecimal price, Integer quantity) {
        this.productID = productID;
        this.tittle = tittle;
        this.price = price;
        this.quantity = quantity;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
}
