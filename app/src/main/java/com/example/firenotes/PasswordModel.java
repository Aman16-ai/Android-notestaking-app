package com.example.firenotes;

public class PasswordModel {
    private int id;
    private String title;
    private String password;
    public PasswordModel() {

    }
    public PasswordModel(int id,String title,String password) {
        this.id = id;
        this.title = title;
        this.password = password;
    }
    public PasswordModel(String title,String password) {
        this.title = title;
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPassword() {
        return password;
    }
}
