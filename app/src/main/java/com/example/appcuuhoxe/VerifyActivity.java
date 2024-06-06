package com.example.appcuuhoxe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcuuhoxe.utils.AndroidUtils;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity extends AppCompatActivity {
    String phoneNumber;
    String verifycationCode;

    PhoneAuthProvider.ForceResendingToken myforceResendingToken;
    Long timeoutSecond = 60L;
    Button btn_verify;
    EditText edt_otp;
    TextView btn_resend_otp;
    FirebaseAuth myAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);
        edt_otp = findViewById(R.id.edt_otp);
        btn_resend_otp = findViewById(R.id.btn_resend_otp);
        btn_verify = findViewById(R.id.btn_verify);

        phoneNumber = getIntent().getExtras().getString("phone");

        sendOTP(phoneNumber,false);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
    void sendOTP(String phoneNumber,boolean isResendOTP){
        PhoneAuthOptions.Builder builder =
                PhoneAuthOptions.newBuilder(myAuth)
                        .setPhoneNumber(phoneNumber)
                        .setTimeout(timeoutSecond, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                checkVerifyCode();
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                AndroidUtils.showToast(getApplicationContext(),"Gửi mã xác thực lỗi!");
                            }

                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                super.onCodeSent(s, forceResendingToken);
                                verifycationCode = s;
                                myforceResendingToken = forceResendingToken;
                                AndroidUtils.showToast(getApplicationContext(),"Gửi mã xác thực thành công!");
                            }
                        });
        if (isResendOTP){
            PhoneAuthProvider.verifyPhoneNumber(builder.setForceResendingToken(myforceResendingToken).build());
        }else {
            PhoneAuthProvider.verifyPhoneNumber(builder.build());
        }
    }
    void checkVerifyCode(){

    }
}