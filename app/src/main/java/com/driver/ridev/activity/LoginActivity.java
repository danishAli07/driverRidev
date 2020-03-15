package com.driver.ridev.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.driver.ridev.R;
import com.driver.ridev.pojo.login.UserLogin;
import com.driver.ridev.viewModel.LoginViewModel;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLoginBtn;
    private EditText mPasswordET = null;
    private EditText mEmailET = null;
    private LoginViewModel mLoginViewModel;
    private TextView mSignUpTv;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        initView();
    }

    private void initialize() {
        mLoginViewModel = new LoginViewModel(this);
    }

    private void initView() {
        mEmailET = findViewById(R.id.input_email);
        mPasswordET = findViewById(R.id.input_password);
        mLoginBtn = findViewById(R.id.btn_login);
        mSignUpTv = findViewById(R.id.link_signup);
        mLoginBtn.setOnClickListener(this);
        mSignUpTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                callLoginApi();
                break;
            case R.id.link_signup:
                Intent intent = new Intent(this, RegisterActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void callLoginApi() {
        if (!mEmailET.getText().toString().isEmpty()) {
            if (!mPasswordET.getText().toString().isEmpty()) {
                mLoginViewModel.callLoginApi(mEmailET.getText().toString(), mPasswordET.getText().toString());
            } else {
                mPasswordET.setError("Enter password");
            }
        } else {
            mEmailET.setError("Enter email id");
        }
    }

    public void onLoginSuccess(Response<UserLogin> response) {
        Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    public void onLoginError(Response<UserLogin> response) {
        Toast.makeText(this, response.message().toString(), Toast.LENGTH_SHORT).show();
    }
}
