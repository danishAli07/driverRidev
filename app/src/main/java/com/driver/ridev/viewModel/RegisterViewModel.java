package com.driver.ridev.viewModel;

import android.util.Log;

import com.driver.ridev.activity.RegisterActivity;
import com.driver.ridev.api.ApiClient;
import com.driver.ridev.api_interface.ApiInterface;
import com.driver.ridev.pojo.login.UserLogin;
import com.driver.ridev.pojo.login.UserRegister;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel {

    private final RegisterActivity mRegisterActivity;

    public RegisterViewModel(RegisterActivity mRegisterActivity) {
    this.mRegisterActivity= mRegisterActivity;
    }
    public void callSignUpApi(String first_name, String last_name, String email, String phone, String password, String con_password){
        Log.d("danny","callLoginApi email :"+email + " password :"+password);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserRegister> call =apiInterface.callRegisterApi(first_name, last_name, email, phone, password, con_password);
        call.enqueue(new Callback<UserRegister>() {
            @Override
            public void onResponse(Call<UserRegister> call, Response<UserRegister> response) {
                Log.d("danny","callLoginApi onResponse  response : "+response.toString());
                if (response.code() == 200){
                    mRegisterActivity.onRegisterSuccess(response);
                } else {
                    mRegisterActivity.onRegisterError(response);
                }
            }

            @Override
            public void onFailure(Call<UserRegister> call, Throwable t) {
                Log.d("danny","callLoginApi onFailure  t : "+t.getMessage());
                t.printStackTrace();
            }
        });


    }
}
