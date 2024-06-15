package com.example.appcuuhoxe.recuseTeamView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.appcuuhoxe.MainActivity;
import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.models.RecuseTeamModel;
import com.example.appcuuhoxe.models.UserModel;
import com.example.appcuuhoxe.utils.FireBaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class InfoRecuseTeamActivity extends AppCompatActivity {

    EditText edt_username;
    EditText edt_address;
    String phoneNumber;
    String password;
    Button btn_next;
    RecuseTeamModel recuseTeamModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_recuse_team);
        edt_address = findViewById(R.id.edt_address);
        edt_username = findViewById(R.id.edt_username);
        btn_next = findViewById(R.id.btn_next);
        phoneNumber = getIntent().getExtras().getString("phone");
        password = getIntent().getExtras().getString("pass");
        getInfoUser();
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfoUser();
            }
        });
    }
    private void getInfoUser(){
        FireBaseUtils.recuseTeamDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    recuseTeamModel = task.getResult().toObject(RecuseTeamModel.class);
                    if(recuseTeamModel !=null){
                        edt_username.setText(recuseTeamModel.getUsername());
                    }
                }
            }
        });
    }
    private void setInfoUser(){
        String username = edt_username.getText().toString();
        String address = edt_address.getText().toString();
        String role = "recuseTeam";
        if(username.isEmpty()||username.length()<3){
            edt_username.setError("Tên phải nhiều hơn 3 ký tự");
            return;
        }
        if(recuseTeamModel !=null){
            recuseTeamModel.setUsername(username);
            recuseTeamModel.setPassword(password);
            recuseTeamModel.setPhone(phoneNumber);
            recuseTeamModel.setAddress(address);
            recuseTeamModel.setRole(role);
        }else {
            recuseTeamModel = new RecuseTeamModel(phoneNumber,username,password,null,role,address, Timestamp.now());
        }
        FireBaseUtils.recuseTeamDetails().set(recuseTeamModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(InfoRecuseTeamActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}