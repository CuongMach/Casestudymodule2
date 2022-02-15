package com.codegym.model;

import java.io.Serializable;

public class InforCustumer implements Serializable {
    private String name;
    private Double phone;
    private Double quanlity;
    private String time;


    public InforCustumer() {

    }

    public InforCustumer(String name, Double phone, Double quanlity, String time) {
        this.name = name;
        this.phone = phone;
        this.quanlity = quanlity;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPhone() {
        return phone;
    }

    public void setPhone(Double phone) {
        this.phone = phone;
    }

    public Double getQuanlity() {
        return quanlity;
    }

    public void setQuanlity(Double quanlity) {
        this.quanlity = quanlity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Họ tên: " + this.name + ", SDT: " + this.phone + ", Số lượng: " + this.quanlity + ", Thời gian lấy: " + this.time + "\n";
    }
}
