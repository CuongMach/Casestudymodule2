package com.codegym.controller;

import com.codegym.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserManagement implements WriteFile, ReadFile {

    private static UserManagement userManagement;

    private List<User> users = new ArrayList<>();


    public int size() {
        return users.size();
    }

    private UserManagement() {
        File file = new File("user.txt");
        if (file.exists()) {
            try {
                readFile("user.txt");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static UserManagement getInstance() {
        if (userManagement == null) {
            userManagement = new UserManagement();
        }
        return userManagement;
    }

    public void register(User user) {
        this.users.add(user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkUserLogin(String username, String password) {
        boolean isLogin = false;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername()) && password.equals(users.get(i).getPassword())) {
                isLogin = true;
                break;
            }
        }
        return isLogin;
    }

    public boolean checkUsernameExist(String username) {
        boolean isExist = false;
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                isExist = true;
                break;
            }
        }
        return isExist;
    }

    public int findByuUerName(String username) {
        int index = -1;
        for (int i = 0; i < size(); i++) {
            if (users.get(i).getUsername().equals(username)) {
                return i;
            }
        }
        return index;
    }

    public void updateUser(int index, User newUser) {
        users.set(index, newUser);
    }

    @Override
    public void readFile(String path) throws IOException, ClassNotFoundException {
        InputStream is = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(is);
        this.users = (List<User>) ois.readObject();
    }

    @Override
    public void writeFile(String path) throws IOException {
        OutputStream os = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(this.users);
    }


    public void disPlayAll() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addNew(User user) {
        this.users.add(user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateById(String username, User user) {
        int index = findByuUerName(username);
        this.users.set(index, user);
        try {
            writeFile("user.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User getByName(int index) {
        return users.get(index);
    }
}

