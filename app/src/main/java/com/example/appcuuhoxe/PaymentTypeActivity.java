package com.example.appcuuhoxe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class PaymentTypeActivity extends AppCompatActivity {
    ImageView btn_back;
    RadioGroup radio_gr;
    String paymentType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_type);
        btn_back = findViewById(R.id.btn_back);
        radio_gr = findViewById(R.id.radio_gr);
        paymentType = getIntent().getStringExtra("paymentType");
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PaymentTypeActivity.this, RecuseChooseActivity.class);
                paymentType = String.valueOf(radio_gr.getCheckedRadioButtonId());
                intent.putExtra("paymentType",paymentType);
                startActivity(intent);
            }
        });

    }
}