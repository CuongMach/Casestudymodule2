package com.codegym.view;

import com.codegym.controller.ProductManagement;
import com.codegym.model.Product;

import java.util.Scanner;

public class AdminMenu {
    Scanner scanner = new Scanner(System.in);
    ProductManagement productManagement = ProductManagement.getInstance();
    CustumerMenu custumerMenu = new CustumerMenu();

    public void run(String username) {
        if (username.equals("admin123")) {
            int choice = -1;
            do {
                menu();
                System.out.println("Nhập lựa chọn");
                choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        productManagement.showAllProduct();
                        break;
                    case 2:
                        scanner.nextLine();
                        System.out.println("Nhập thông tin sản phẩm");
                        Product product = inputProduct();
                        productManagement.addNewProduct(product);
                        break;
                    case 3:
                        showDeleteProduct();
                        break;
                    case 4:
                        showUpdateProduct();
                        break;
                    case 5:
                        findProduct();
                        break;
                    case 6:
                        productManagement.showInforCustumer();
                        break;
                    case 7:
                        productManagement.showOderCustumer();
                        break;
                    case 0:
                        System.err.println("Quay lại thành công");
                        break;
                    default: {
                        System.out.println("Nhập lại");
                    }
                }
            } while (choice != 0);
        } else {
            custumerMenu.run();
        }

    }

    private void findProduct() {
        scanner.nextLine();
        System.out.println("Nhập tên sản phẩm muốn tìm");
        String name = scanner.nextLine();
        int index = productManagement.findProductByName(name);
        if (index != -1) {
            productManagement.showByName(index);
        } else {
            System.out.println("Không tìm thấy");
        }
        return;
    }

    private void showUpdateProduct() {
        scanner.nextLine();
        System.out.println("Nhập ID sản phẩm cần sửa");
        String id = scanner.nextLine();
        int index = productManagement.findProductById(id);
        if (index != -1) {
            System.out.println("Nhập thông tin mới");
            Product product1 = inputProduct();
            productManagement.updateProduct(index, product1);
        } else {
            System.out.println("Không có sản phẩm");
        }
    }

    private void showDeleteProduct() {
        scanner.nextLine();
        System.out.println("Nhập ID sản phẩm cần xóa");
        String id = scanner.nextLine();
        int index = productManagement.findProductById(id);
        if (index != -1) {
            productManagement.deleteProduct(index);
            System.out.println("Xóa thành công");
        } else {
            System.out.println("Không tồn tại sản phẩm");
        }
    }

    private Product inputProduct() {
        System.out.println("Nhập ID sản phẩm");
        String id = scanner.nextLine();
        System.out.println("Nhập tên sản phẩm");
        String name = scanner.nextLine();
        System.out.println("Số lượng sản phẩm");
        Double quanlity = scanner.nextDouble();
        System.out.println("Nhập giá sản phẩm");
        Double price = scanner.nextDouble();
        Product product = new Product(id, name, quanlity, price);
        return product;
    }

    private void menu() {
        System.out.println("1. Danh sách sản phẩm");
        System.out.println("2. Thêm sản phẩm");
        System.out.println("3. Xóa sản phẩm");
        System.out.println("4. Cập nhật sản phẩm");
        System.out.println("5. Tìm kiếm sản phẩm");
        System.out.println("6. Danh sách đơn làm gà");
        System.out.println("7. Danh sách đơn mua gà");
        System.out.println("0. Quay lại");
    }
}
