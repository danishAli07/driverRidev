package com.driver.ridev.viewModel;

import android.util.Log;

import com.driver.ridev.activity.LoginActivity;
import com.driver.ridev.api.ApiClient;
import com.driver.ridev.api_interface.ApiInterface;
import com.driver.ridev.pojo.login.UserLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginViewModel {

    private final LoginActivity mLoginActivity;


    public LoginViewModel(LoginActivity loginActivity) {
        this.mLoginActivity = loginActivity;
    }

    public void callLoginApi(String email, String password){
        Log.d("danny","callLoginApi email :"+email + " password :"+password);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserLogin> call = apiInterface.callLoginApi(email,password);
        call.enqueue(new Callback<UserLogin>() {
            @Override
            public void onResponse(Call<UserLogin> call, Response<UserLogin> response) {
                Log.d("danny","callLoginApi onResponse  response : "+response.toString());
                if (response.code() == 200){
                    mLoginActivity.onLoginSuccess(response);
                } else {
                   mLoginActivity.onLoginError(response);
                }
            }

            @Override
            public void onFailure(Call<UserLogin> call, Throwable t) {
                Log.d("danny","callLoginApi onFailure  t : "+t.getMessage());
                t.printStackTrace();
            }
        });

    }
}
