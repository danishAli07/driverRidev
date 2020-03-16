package com.driver.ridev.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.driver.ridev.R;
import com.driver.ridev.pojo.login.UserLogin;
import com.driver.ridev.pojo.login.UserRegister;
import com.driver.ridev.viewModel.LoginViewModel;
import com.driver.ridev.viewModel.RegisterViewModel;

import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mSignUpBtn;
    private EditText mPasswordET = null;
    private EditText mFirstNameET = null;
    private EditText mLastNameET = null;
    private EditText mPhoneET = null;
    private EditText mConfirmPasswordET = null;
    private EditText mEmailET = null;
    private RegisterViewModel mRegisterViewModel;
    private TextView mBackToLoginTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mSignUpBtn = findViewById(R.id.btn_signup);
        mEmailET = findViewById(R.id.input_email_signup);
        mFirstNameET = findViewById(R.id.input_first_name_signup);
        mLastNameET = findViewById(R.id.input_last_name_signup);
        mPhoneET = findViewById(R.id.input_phone_signup);
        mPasswordET = findViewById(R.id.input_password_signup);
        mConfirmPasswordET = findViewById(R.id.input_confirm_password_signup);
        mBackToLoginTv = findViewById(R.id.go_login_tv);
        mSignUpBtn.setOnClickListener(this);
        mBackToLoginTv.setOnClickListener(this);

        initialize();
        initView();
    }

    private void initView() {

    }

    private void initialize() {
        mRegisterViewModel = new RegisterViewModel(RegisterActivity.this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_signup:
                callSignUpApi();
                break;
            case R.id.go_login_tv:
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
                break;

            default:
                break;
        }

    }

    private void callSignUpApi() {

        if(!mFirstNameET.getText().toString().isEmpty() && !mLastNameET.getText().toString().isEmpty() && !mEmailET.getText().toString().isEmpty()
        && !mPhoneET.getText().toString().isEmpty() && !mPasswordET.getText().toString().isEmpty() && !mConfirmPasswordET.getText().toString().isEmpty()) {

            mRegisterViewModel.callSignUpApi(mFirstNameET.getText().toString(), mLastNameET.getText().toString(), mEmailET.getText().toString(), mPhoneET.getText().toString(),
                    mPasswordET.getText().toString(), mConfirmPasswordET.getText().toString());
        }else {
            Toast.makeText(this, "Fill all fields properly", Toast.LENGTH_SHORT).show();
        }

    }

    public void onRegisterSuccess(Response<UserRegister> response) {
        Toast.makeText(this, "Register successfully", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        finish();
    }

    public void onRegisterError(Response<UserRegister> response) {
        Toast.makeText(this, response.message().toString(), Toast.LENGTH_SHORT).show();
    }
}
