package com.example.prans.news.util;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.prans.news.model.Contants.BASE_URL;


public class ApiClient {

    private static Retrofit getRetrofitInstance() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static ApiResponse getApiService() {
        return getRetrofitInstance().create(ApiResponse.class);
    }
}
