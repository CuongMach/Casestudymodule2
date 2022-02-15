package com.codegym.model;

import java.io.Serializable;

public class OderCustumer implements Serializable {
    private Product product;
    private int quality;
    private double payMoney;

    public OderCustumer() {

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public OderCustumer(Product product, int quality, double payMoney) {
        this.product = product;
        this.quality = quality;
        this.payMoney = payMoney;
    }


    @Override
    public String toString() {
        return product.toString() + ", Số lượng:" + this.quality + ", Thành tiền:" + this.payMoney;
    }
}
