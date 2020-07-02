package com.example.demo.POJO;

public class User {
    private String account;
    private String password;

    User(String account,String password){
        this.account=account;
        this.password=password;
    }

    User(){}

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
