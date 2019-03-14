package com.example.newsreader.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiManagerIcon {
    private static Retrofit retrofitInstance;

    private static Retrofit getInstanceIcon(){
        if(retrofitInstance==null){

            retrofitInstance =  new Retrofit.Builder()
                    .baseUrl("https://besticon-demo.herokuapp.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitInstance;
    }

    public static APICalls getIconAPIs(){
        return getInstanceIcon().create(APICalls.class);
    }
}
