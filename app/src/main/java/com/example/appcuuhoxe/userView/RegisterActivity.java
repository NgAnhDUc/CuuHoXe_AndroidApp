package com.example.appcuuhoxe.userView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.recuseTeamView.RegisterRecuseTeamActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class RegisterActivity extends AppCompatActivity {
    Button btn_tab_login;
    Button btn_register;
    Button btn_goRecuseTeam;
    EditText edt_phone;
    EditText edt_password;
    EditText edt_repassword;
    TextView btn_account_exist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        btn_tab_login = findViewById(R.id.btn_tab_login);
        btn_register = findViewById(R.id.btn_register);
        btn_account_exist = findViewById(R.id.btn_account_exist);
        btn_goRecuseTeam = findViewById(R.id.btn_goRecuseTeam);

        edt_phone = findViewById(R.id.edt_phone);
        edt_password = findViewById(R.id.edt_password);
        edt_repassword = findViewById(R.id.edt_repassword);
        btn_tab_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_account_exist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ValidationPassword())return;
                checkRegister();
            }
        });
        btn_goRecuseTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, RegisterRecuseTeamActivity.class);
                startActivity(intent);
            }
        });
    }
    boolean ValidationPassword(){
        String passwordStr = edt_password.getText().toString();
        String repasswordStr = edt_repassword.getText().toString();
        if (!passwordStr.equals(repasswordStr)){
            edt_repassword.setError("Không đúng");
            return false;
        }
        if(passwordStr.length()<8){
            edt_password.setError("Mật khẩu có độ dài hơn 8 ký tự");
            return false;
        }
        return true;
    }
    void checkRegister(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String phoneNumber ="+84"+ edt_phone.getText().toString().substring(1) ;
        Query queryPhoneNumber =db.collection("Taikhoan").whereEqualTo("phone", phoneNumber);
        queryPhoneNumber.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    int check = task.getResult().getDocuments().size();
                    if(check == 0){
                        RegisterFireBase();
                    }else {
                        edt_phone.setError("Tài khoản đã tồn tại");
                        return;
                    }
                }
            }
        });
    }
    void RegisterFireBase(){
        Intent intent = new Intent(RegisterActivity.this, VerifyActivity.class);
        String phoneNumber = edt_phone.getText().toString().substring(1) ;
        intent.putExtra("phone","+84"+phoneNumber);
        intent.putExtra("pass",edt_password.getText().toString());
        startActivity(intent);
    }
}