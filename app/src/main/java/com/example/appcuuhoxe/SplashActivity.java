package com.example.appcuuhoxe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appcuuhoxe.userView.LoginActivity;
import com.example.appcuuhoxe.userView.RegisterActivity;
import com.example.appcuuhoxe.utils.FireBaseUtils;

public class SplashActivity extends AppCompatActivity {
    Button btn_next;
    TextView btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        this.btn_next = findViewById(R.id.btn_next);
        this.btn_login = findViewById(R.id.btn_login);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        checkLogin();
    }
    void checkLogin(){
        if (FireBaseUtils.isLoggedIn()){
            Intent intent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }
}