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

public class LoginActivity extends AppCompatActivity {

    private EditText et_username, et_password;
    private Button btn_login;
    private TextView tv_signup, tv_skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        UserService userService = RetrofitInstance.getRetrofitInstance().create(UserService.class);

        et_username =findViewById(R.id.et_username);
        et_password =findViewById(R.id.et_password);
        btn_login = findViewById(R.id.btn_login);
        tv_signup = findViewById(R.id.tv_signup);
        tv_skip = findViewById(R.id.tv_skip);

        tv_signup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();
        });

        tv_skip.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btn_login.setOnClickListener(v -> {
            String username = et_username.getText().toString();
            String password = et_password.getText().toString();

            Call<LoginResponseModel> call = userService.login(username, password);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    LoginResponseModel loginResponseModel = response.body();
                    if(loginResponseModel.getStatus().equals("SUCCESS")){
                        Toast.makeText(LoginActivity.this, "Success Login "+loginResponseModel.getUserModel().getUsername(), Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Failed Login ", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Something Wrong...", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}