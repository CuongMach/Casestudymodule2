package com.codegym.view;
public class Main {
    public static void main(String[] args) {
        System.out.println("---Xin chào---");
        System.out.println("Thông tin liên hệ người bán");
        System.out.println("SĐT: 0123456789");
        System.out.println("Địa chỉ: Hà Nội");
        LoginMenu loginMenu = new LoginMenu();
        loginMenu.run();
    }
}