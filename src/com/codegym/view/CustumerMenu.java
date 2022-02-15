package com.codegym.view;

import com.codegym.controller.ProductManagement;
import com.codegym.model.InforCustumer;
import com.codegym.model.OderCustumer;

import java.io.IOException;
import java.util.Scanner;

public class CustumerMenu {
    Scanner scanner = new Scanner(System.in);
    ProductManagement productManagement = ProductManagement.getInstance();

    public void run() {
        int choice = -1;
        try {
            productManagement.readFileOderCustumer("oder.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            productManagement.readFile("product.txt");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        do {
            showCustumerMenu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    productManagement.showAllProduct();
                    break;
                case 2:
                    oderBuyChicken(choice);
                    break;
                case 3:
                    oderMaking();
                    break;
                case 4:
                    productManagement.showOderCustumer();
                    break;
            }
        } while (choice != 0);
    }

    private void oderBuyChicken(int choice) {
        int choiceBuy = -1;
        scanner.nextLine();
        productManagement.clearOderCustumer();
        do {
            System.out.println("1. Mua tiếp");
            System.out.println("0. Thoát");
            System.out.println("Nhập lựa chọn của bạn");
            choiceBuy = scanner.nextInt();
            scanner.nextLine();
            if (choiceBuy==0){
                break;
            }
            if (choiceBuy == 1) {
                productManagement.showAllProduct();
                System.out.println("Nhập mã sản phẩm muốn mua");
                String id = scanner.nextLine();
                int index = productManagement.findProductById(id);
                if (index == -1) {
                    System.out.println("Không có nhé");
                } else {
                    System.out.println("Nhập số lượng bạn muốn mua");
                    int quality = scanner.nextInt();
                    double qualityChicken = productManagement.getProducts().get(index).getQuanlity();
                    if (quality>qualityChicken){
                        System.out.println("Không đủ số lượng gà muốn mua");
                    }else{
                        Double payMoney = quality * productManagement.getProducts().get(index).getPrice();
                        OderCustumer oderCustumer = new OderCustumer(productManagement.getProducts().get(index),quality,payMoney);
                        productManagement.getOderCustumers().add(oderCustumer);
                        productManagement.showOderCustumer();
                        double total = productManagement.totalPayMoney();
                        System.out.println("Tổng số tiền bạn phải thanh toán là: " +total + "VND");
                        Double newQuality = qualityChicken - quality;
                        productManagement.getProducts().get(index).setQuanlity(newQuality);
                        try {
                            productManagement.writeFileOderCustumer("oder.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        try {
                            productManagement.writeFile("product.txt");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        } while (choice != 0);
    }

    private void oderMaking() {
        scanner.nextLine();
        System.out.println("Giá làm gà là 20k/con");
        System.out.println("Vui lòng điền thông tin của bạn vào đây");
        InforCustumer inforCustumer = inputInforCustumer();
        productManagement.getInforCustumers().add(inforCustumer);
        productManagement.showInforCustumer();
    }

    private void showCustumerMenu() {
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Mua gà");
        System.out.println("3. Làm gà");
        System.out.println("4. Lịch sử đặt hàng");
        System.out.println("0. Thoát");
    }

    private InforCustumer inputInforCustumer() {
        System.out.println("Nhập tên của bạn: ");
        String name = scanner.nextLine();
        System.out.println("Nhập số điện thoại của bạn: ");
        Double phone = scanner.nextDouble();
        System.out.println("Nhập số lượng gà của bạn: ");
        Double quanlity = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Nhập thời gian bạn lấy gà: ");
        String time = scanner.nextLine();
        InforCustumer inforCustumer = new InforCustumer(name, phone, quanlity, time);
        return inforCustumer;
    }
}
