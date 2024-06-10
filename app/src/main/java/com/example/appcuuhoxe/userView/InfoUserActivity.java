package com.example.appcuuhoxe.userView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.appcuuhoxe.MainActivity;
import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.models.UserModel;
import com.example.appcuuhoxe.utils.FireBaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentSnapshot;

public class InfoUserActivity extends AppCompatActivity {

    EditText edt_username;
    Spinner spn_gender;
    String phoneNumber;
    String password;
    Button btn_next;
    UserModel userModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);
        spn_gender = findViewById(R.id.spn_gender);
        edt_username = findViewById(R.id.edt_username);
        btn_next = findViewById(R.id.btn_next);
        phoneNumber = getIntent().getExtras().getString("phone");
        password = getIntent().getExtras().getString("pass");
        setSpinnerItem();
        getInfoUser();
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setInfoUser();
            }
        });
    }
    private void setSpinnerItem(){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.gender_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_gender.setAdapter(adapter);
    }
    private void getInfoUser(){
        FireBaseUtils.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    userModel = task.getResult().toObject(UserModel.class);
                    if(userModel !=null){
                        edt_username.setText(userModel.getUsername());
                    }
                }
            }
        });
    }
    private void setInfoUser(){
        String username = edt_username.getText().toString();
        String gender = spn_gender.getSelectedItem().toString();
        String role = "user";
        if(username.isEmpty()||username.length()<3){
            edt_username.setError("Tên phải nhiều hơn 3 ký tự");
            return;
        }
        if(userModel !=null){
            userModel.setUsername(username);
            userModel.setPassword(password);
            userModel.setPhone(phoneNumber);
            userModel.setGender(gender);
            userModel.setRole(role);
        }else {
            userModel = new UserModel(phoneNumber,username,password,gender,role, Timestamp.now());
        }
        FireBaseUtils.currentUserDetails().set(userModel).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(InfoUserActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }
            }
        });
    }
}