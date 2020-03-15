package com.driver.ridev.api_interface;

import com.driver.ridev.pojo.login.UserLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("driver/login")
    Call<UserLogin> callLoginApi(
            @Field("email") String email,
            @Field("password") String password
    );


}
