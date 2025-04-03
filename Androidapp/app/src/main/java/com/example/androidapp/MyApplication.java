package com.example.androidapp;
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication instance;
    private String globalUserId;
    private boolean isAdmin;

    private String token;

    private static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getGlobalUserId() {
        return globalUserId;
    }

    public void setGlobalUserId(String value) {
        this.globalUserId = value;
    }
    public static Context getAppContext() {
        return context;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}