package com.example.prans.news.util;

import com.example.prans.news.model.GetAllUserResponseModel;
import com.example.prans.news.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {

    @GET("/login")
    Call<LoginResponseModel> login(@Query("username") String username, @Query("password") String password);

    @FormUrlEncoded
    @POST("users/register")
    Call<LoginResponseModel> register(@Query("username") String username,
                                      @Query("password") String password, @Query("fullname") String fullname,
                                      @Query("email") String email);

    @GET("user/getAll")
    Call<GetAllUserResponseModel> getAllUser();
}
