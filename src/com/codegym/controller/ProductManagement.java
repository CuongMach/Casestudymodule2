package com.codegym.controller;

import com.codegym.model.InforCustumer;
import com.codegym.model.OderCustumer;
import com.codegym.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement implements ReadFile, WriteFile {
    private static ProductManagement productManagement;
    private List<Product> products = new ArrayList<>();

    public int size() {
        return products.size();
    }

    private List<InforCustumer> inforCustumers = new ArrayList<>();

    private List<OderCustumer> oderCustumers = new ArrayList<>();

    public List<InforCustumer> getInforCustumers() {
        return inforCustumers;
    }

    public void setInforCustumers(List<InforCustumer> inforCustumers) {
        this.inforCustumers = inforCustumers;
    }

    private ProductManagement() {
        File file = new File("product.txt");
        if (file.exists()) {
            try {
                readFile("product.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static ProductManagement getInstance() {
        if (productManagement == null) {
            productManagement = new ProductManagement();
        }
        return productManagement;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.products = (List<Product>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.products);
    }

    public void writeFileOderCustumer(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.oderCustumers);
    }

    public void readFileOderCustumer(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.oderCustumers = (List<OderCustumer>) ois.readObject();
    }


    public void showAllProduct() {
        if (size() == 0) {
            System.out.println("Danh sách rỗng");
        }
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void showInforCustumer() {
        if (inforCustumers.size() == 0) {
            System.out.println("Danh sách rỗng");
        }
        for (InforCustumer inforCustumer : inforCustumers) {
            System.out.println(inforCustumer);
        }
    }

    public void showOderCustumer() {
        if (oderCustumers.size() == 0) {
            System.out.println("Danh sách rỗng");
        }
        for (OderCustumer oderCustumer : oderCustumers) {
            System.out.println(oderCustumer);
        }
    }

    public double totalPayMoney() {
        double total = 0;
        for (int i = 0; i < oderCustumers.size(); i++) {
            total = total + oderCustumers.get(i).getPayMoney();
        }
        return total;
    }

    public void addNewProduct(Product product) {
        this.products.add(product);
        try {
            writeFile("product.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int findProductById(String id) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return i;
            }
        }
        return index;
    }

    public void deleteProduct(int index) {

        this.products.remove(index);
        try {
            writeFile("product.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<OderCustumer> getOderCustumers() {
        return oderCustumers;
    }

    public void updateProduct(int index, Product product) {
        this.products.set(index, product);
        try {
            writeFile("product.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int findProductByName(String name) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (products.get(i).getName().equals(name)) {
                return i;
            }
        }
        return index;
    }

    public void showByName(int index) {
        System.out.println(products.get(index));
    }

    public void clearOderCustumer() {
        oderCustumers.clear();
    }


}
