package com.example.appcuuhoxe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcuuhoxe.models.UserModel;
import com.example.appcuuhoxe.utils.AndroidUtils;
import com.example.appcuuhoxe.utils.FireBaseUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;

public class BillActivity extends AppCompatActivity {
    ImageView btn_back;
    String address;
    String provide;
    String madoncuuho;
    TextView tv_addressUser;
    TextView tv_nameUser;
    TextView tv_phoneUser;
    TextView tv_paymentType;
    TextView tv_serviceType;
    UserModel userModel;
    String serviceType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        btn_back = findViewById(R.id.btn_back);
        tv_addressUser = findViewById(R.id.tv_addressUser);
        tv_nameUser = findViewById(R.id.tv_nameUser);
        tv_phoneUser = findViewById(R.id.tv_phoneUser);
        tv_paymentType = findViewById(R.id.tv_paymentType);
        tv_serviceType = findViewById(R.id.tv_serviceType);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BillActivity.this, RecuseChooseActivity.class));
            }
        });
        address = getIntent().getStringExtra("address");
        provide = getIntent().getStringExtra("provide");
        madoncuuho = getIntent().getStringExtra("madoncuuho");
        serviceType = getIntent().getStringExtra("serviceType");
        setInfoUserView();
    }
    private void getInfoUser(){
        FireBaseUtils.currentUserDetails().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    userModel = task.getResult().toObject(UserModel.class);
                    if(userModel !=null){
                        tv_nameUser.setText(userModel.getUsername());
                        tv_phoneUser.setText(userModel.getPhone());
                    }
                }
            }
        });
    }
    void setInfoUserView(){
        tv_addressUser.setText(address);
        getInfoUser();
        tv_serviceType.setText(serviceType);
    }
}