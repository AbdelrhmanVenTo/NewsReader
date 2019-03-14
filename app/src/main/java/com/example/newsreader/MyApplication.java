package com.example.newsreader;


import android.app.Application;

import com.example.newsreader.DataBase.NewsDataBase;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        NewsDataBase.init(this);
    }
}
