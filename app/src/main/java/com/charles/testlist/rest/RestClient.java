package com.charles.testlist.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient {
    final static String BASE_URL = "https://api.doordash.com/";

    public static Retrofit retrofit = null;

    public static Retrofit getGateWay() {
        if(retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory
                    .create())
                    .build();
        }
        return retrofit;
    }
}
