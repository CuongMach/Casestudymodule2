package com.codegym.model;

import java.io.Serializable;

public class Product implements Serializable {
    private String id;
    private String name;
    private Double quanlity;
    private Double price;

    public Product(){

    }

    public Product(String id, String name, Double quanlity, Double price) {
        this.id = id;
        this.name = name;
        this.quanlity = quanlity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(Double quanlity) {
        this.quanlity = quanlity;
    }

    @Override
    public String toString() {
        return this.id + " Tên sản phẩm: " + this.name +" Số lượng: " + this.quanlity + " Giá cả: " + this.price + "/kg VND \n";
    }
}
