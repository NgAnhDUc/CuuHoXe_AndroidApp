package com.example.appcuuhoxe.recuseTeamView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appcuuhoxe.MainActivity;
import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.userView.LoginActivity;
import com.example.appcuuhoxe.userView.RegisterActivity;

public class LoginRecuseTeamActivity extends AppCompatActivity {
    Button btn_register;
    Button btn_login;
    Button btn_goUser;
    EditText edt_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_recuse_team);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        edt_phone = findViewById(R.id.edt_phone);
        btn_goUser = findViewById(R.id.btn_goUser);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRecuseTeamActivity.this, RegisterRecuseTeamActivity.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRecuseTeamActivity.this, MainActivity.class);
//                String phoneNumber = edt_phone.getText().toString().substring(1) ;
//                intent.putExtra("phone","+84"+phoneNumber);
                startActivity(intent);
            }
        });
        btn_goUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginRecuseTeamActivity.this, LoginActivity.class);
//                String phoneNumber = edt_phone.getText().toString().substring(1) ;
//                intent.putExtra("phone","+84"+phoneNumber);
                startActivity(intent);
            }
        });
    }
}