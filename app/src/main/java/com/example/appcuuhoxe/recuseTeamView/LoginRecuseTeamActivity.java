package com.example.appcuuhoxe.recuseTeamView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appcuuhoxe.MainActivity;
import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.userView.VerifyLoginActivity;
import com.example.appcuuhoxe.userView.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

public class LoginRecuseTeamActivity extends AppCompatActivity {
    Button btn_register;
    Button btn_login;
    EditText edt_password;
    Button btn_goUser;
    EditText edt_phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_recuse_team);
        btn_register = findViewById(R.id.btn_register);
        edt_password = findViewById(R.id.edt_password);
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
    private void checkUserExist(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String phoneNumber ="+84"+ edt_phone.getText().toString().substring(1) ;
        Query queryPhoneNumber =db.collection("DoiCuuHo").whereEqualTo("phone", phoneNumber)
                .whereEqualTo("password",edt_password.getText().toString())
                .whereEqualTo("role","doicuuho");
        queryPhoneNumber.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    int check = task.getResult().getDocuments().size();
                    if(check == 0){
                        edt_phone.setError("Tài khoản hoặc mật khẩu sai");
                    }else {
                        LoginFireBase();
                    }
                }
            }
        });
    }
    private void LoginFireBase(){
        Intent intent = new Intent(LoginRecuseTeamActivity.this, VerifyLoginActivity.class);
        String phoneNumber = edt_phone.getText().toString().substring(1) ;
        intent.putExtra("phone","+84"+phoneNumber);
        startActivity(intent);
    }
}