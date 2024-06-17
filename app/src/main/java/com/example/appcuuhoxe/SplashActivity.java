package com.example.appcuuhoxe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.appcuuhoxe.userView.LoginActivity;
import com.example.appcuuhoxe.userView.RegisterActivity;
import com.example.appcuuhoxe.utils.AndroidUtils;
import com.example.appcuuhoxe.utils.FireBaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

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
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
    void checkLoginReT(){
        if (FireBaseUtils.isLoggedIn()){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String ID = FireBaseUtils.currentUserID();
            Query queryPhoneNumber = db.collection("DoiCuuHo").document(ID).getParent();
            queryPhoneNumber.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        int check = task.getResult().getDocuments().size();
                        if(check == 0){
//                            AndroidUtils.showToast(getApplicationContext(),"bbb");
                        }else {
                            Intent intent = new Intent(SplashActivity.this, RecuseTeamMainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    }
    void checkLoginUser(){
        if (FireBaseUtils.isLoggedIn()){
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            String ID = FireBaseUtils.currentUserID();
            Query queryPhoneNumber = db.collection("TaiKhoan").document(ID).getParent().whereEqualTo("role","recuseTeam");
            queryPhoneNumber.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if(task.isSuccessful()){
                        int check = task.getResult().getDocuments().size();
                        if(check == 0){
//                            AndroidUtils.showToast(getApplicationContext(),"aaa");
                        }else {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        }
    }
}