package com.example.androidapp.entities;

import com.google.gson.annotations.SerializedName;

public class User {

    private String user_name;
    private String password;
    private String confirmPassword;

    private String nickName;
    private String profilePicture;
    private String mail;
    private String phone;
    @SerializedName("_id")
    private String id;

    @SerializedName("manager")
    private boolean manager;

    public String getId() {
        return id;
    }

    public boolean isManager() {
        return manager;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public User(String mail,String user_name, String password, String nickname, String phone) {
        this.user_name = user_name;
        this.password = password;
        this.nickName = nickname;
        this.mail = mail;
        this.phone = phone;
    }
    public User(String user_name, String password) {
        this.user_name = user_name;
        this.password = password;
    }
}