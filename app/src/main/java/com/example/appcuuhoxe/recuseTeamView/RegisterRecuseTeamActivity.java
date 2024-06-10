package com.example.appcuuhoxe.recuseTeamView;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.userView.RegisterActivity;
import com.example.appcuuhoxe.VerifyActivity;

public class RegisterRecuseTeamActivity extends AppCompatActivity {
    Button btn_tab_login;
    Button btn_register;
    Button btn_goUser;
    EditText edt_phone;
    EditText edt_password;
    EditText edt_repassword;
    TextView btn_account_exist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_recuse_team);
        btn_tab_login = findViewById(R.id.btn_tab_login);
        btn_register = findViewById(R.id.btn_register);
        btn_account_exist = findViewById(R.id.btn_account_exist);
        btn_goUser = findViewById(R.id.btn_goUser);

        edt_phone = findViewById(R.id.edt_phone);
        edt_password = findViewById(R.id.edt_password);
        edt_repassword = findViewById(R.id.edt_repassword);
        btn_tab_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterRecuseTeamActivity.this, LoginRecuseTeamActivity.class);
                startActivity(intent);
            }
        });
        btn_account_exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterRecuseTeamActivity.this, LoginRecuseTeamActivity.class);
                startActivity(intent);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterRecuseTeamActivity.this, VerifyActivity.class);
                String phoneNumber = edt_phone.getText().toString().substring(1) ;
                intent.putExtra("phone","+84"+phoneNumber);
                startActivity(intent);
            }
        });
        btn_goUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterRecuseTeamActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}