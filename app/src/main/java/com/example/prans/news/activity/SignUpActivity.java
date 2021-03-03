package com.example.prans.news.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prans.news.R;
import com.example.prans.news.model.LoginResponseModel;
import com.example.prans.news.util.RetrofitInstance;
import com.example.prans.news.util.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    private EditText et_email, et_password, et_username, et_fullname;
    private Button btn_signup;
    private TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_fullname = findViewById(R.id.et_fullname);
        et_username = findViewById(R.id.et_username);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        btn_signup = findViewById(R.id.btn_signup);
        tv_login = findViewById(R.id.tv_login);

        tv_login.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        UserService userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);
        btn_signup.setOnClickListener(v -> {
            String email = et_email.getText().toString();
            String username = et_username.getText().toString();
            String fullname = et_fullname.getText().toString();
            String password = et_password.getText().toString();

            Call<LoginResponseModel> call = userService.register(username, password, fullname, email);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    LoginResponseModel loginResponseModel = response.body();
                    if(loginResponseModel.getStatus().equals("success")){
                        Toast.makeText(SignUpActivity.this, "Success Register "+loginResponseModel.getUserModel().getUsername(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignUpActivity.this, "Failed to Register ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Toast.makeText(SignUpActivity.this, "Failed Register" + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}