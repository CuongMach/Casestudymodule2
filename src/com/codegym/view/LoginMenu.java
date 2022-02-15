package com.codegym.view;

import com.codegym.controller.UserManagement;
import com.codegym.model.User;

import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class LoginMenu {
    UserManagement userManagement = UserManagement.getInstance();
    public Scanner scanner = new Scanner(System.in);
    AdminMenu managementMenu = new AdminMenu();


    public void run() {
        int choice = -1;
        do {
            menu();
            System.out.println("Nhập lựa chọn của bạn");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    doLoginUser();
                    break;
                case 2:
                    doRegister();
                    break;
                case 3:
                    changePassword();
                    break;
            }
        } while (choice != 0);
    }

    private void changePassword() {
        scanner.nextLine();
        System.out.println("Nhập tên tài khoản");
        String username = scanner.nextLine();
        System.out.println("Nhập mật khẩu cũ");
        String password = scanner.nextLine();
        boolean isCheckLogin = userManagement.checkUserLogin(username,password);
        if (isCheckLogin==false){
            System.err.println("Tên tài khoản hoặc mật khẩu không đúng");
        }else {
            System.out.println("Nhập mật khẩu mới");
            String newPassword = inputPassword();
            System.out.println("Nhập lại mật khẩu mới");
            String newPassword2 = inputPassword();
            if (newPassword.equals(newPassword2)==false){
                System.out.println("Mật khẩu không khớp");
            }else{
                int index = userManagement.findByuUerName(username);
                User newUser = new User(username,newPassword);
                userManagement.updateUser(index,newUser);
                System.out.println("Đổi mật khẩu thành công");
                try {
                    userManagement.writeFile("user.txt");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void doLoginUser() {

        System.out.println("Tên đăng nhập");
        scanner.nextLine();
        String username = scanner.nextLine();
        System.out.println("Mật khẩu");
        String password = scanner.nextLine();
        boolean isLogin = userManagement.checkUserLogin(username, password);
        if (isLogin) {
            System.out.println("Đăng nhập thành công");
        } else {
            System.out.println("Tên tài khoản hoặc mật khẩu không đúng!!!");
            doLoginUser();
        }
        managementMenu.run(username);
    }

    private void doRegister() {
        scanner.nextLine();
        System.out.println("Đăng ký tài khoản mới!");
        User user = createUser();
        userManagement.register(user);
    }

    private User createUser() {
        String username = inputUsername();
        String password = inputPassword();
        User user = new User(username, password);
        return user;
    }

    private boolean isPassword (String password){
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{6,12}$";
        if(Pattern.matches(regex,password)) return true;
        return false;
    }
    private String inputPassword() {
        String password;
        do {
            System.out.println("Nhập mật khẩu (6-12 ký tự), có ít nhất một ký tự in hoa và 1 số:");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            password = scanner.nextLine();
           if (!isPassword(password)){
               System.err.println("Mật khẩu phải chứa 6-12 ký tự và có ít nhất 1 ký tự in hoa và 1 số");
           }
        } while (!isPassword(password));
        return password;
    }

    private String inputUsername() {
        String username;
        do {
            System.out.println("Nhập tên tài khoản (6-12 ký tự):");
            username = scanner.nextLine();
            if (username.length() < 6) {
                System.err.println("Tài khoản phải có ít nhất 6 ký tự!");
            } else if (username.length() > 12) {
                System.err.println("Tài khoản chỉ được phép tối đa 12 ký tự!");
            } else if (userManagement.checkUsernameExist(username)) {
            }
        } while (username.length() < 6 || username.length() > 12 || userManagement.checkUsernameExist(username));
        return username;
    }

    private void menu() {
        System.out.println("1. Đăng nhập");
        System.out.println("2. Đăng ký");
        System.out.println("3. Đổi mật khẩu");
        System.out.println("0. Thoát");
    }
}

