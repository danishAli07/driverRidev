package com.driver.ridev.api_interface;

import com.driver.ridev.pojo.login.UserLogin;
import com.driver.ridev.pojo.login.UserRegister;

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
    @FormUrlEncoded
    @POST("driver/register")
    Call<UserRegister> callRegisterApi(
            @Field("f_name") String f_name,
            @Field("l_name") String l_name,
            @Field("email") String email,
            @Field("mobile") String mobile,
            @Field("password") String password,
            @Field("c_password") String c_password
    );


}
