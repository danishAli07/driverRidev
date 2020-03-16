package com.driver.ridev.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mLoginBtn;
    private SignInButton mGmailBtn;
    private EditText mPasswordET = null;
    private EditText mEmailET = null;
    private LoginViewModel mLoginViewModel;
    private TextView mSignUpTv;

    private static final String TAG = "LoginActivity";

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
        initView();
    }

    private void initialize() {
        mLoginViewModel = new LoginViewModel(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }


    private void initView() {
        mEmailET = findViewById(R.id.input_email);
        mPasswordET = findViewById(R.id.input_password);
        mLoginBtn = findViewById(R.id.btn_login);
        mSignUpTv = findViewById(R.id.link_signup);
        mGmailBtn = findViewById(R.id.login_gmail);
        mLoginBtn.setOnClickListener(this);
        mSignUpTv.setOnClickListener(this);
        mGmailBtn.setOnClickListener(this);
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
            case R.id.login_gmail:

                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, 101);

                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 101) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);

                // Signed in successfully, show authenticated UI.
                onLoggedIn(account);
            } catch (ApiException e) {
                // The ApiException status code indicates the detailed failure reason.
                // Please refer to the GoogleSignInStatusCodes class reference for more information.
                Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
                onLoggedIn(null);
            }
        }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        Intent intent = new Intent(this, MainActivity.class);
        // intent.putExtra(ProfileActivity.GOOGLE_ACCOUNT, googleSignInAccount);

        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        onLoggedIn(account);
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
