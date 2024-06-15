package com.example.appcuuhoxe.userView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.appcuuhoxe.MainActivity;
import com.example.appcuuhoxe.R;
import com.example.appcuuhoxe.utils.AndroidUtils;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyLoginActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_verify_login);
        edt_otp = findViewById(R.id.edt_otp);
        btn_resend_otp = findViewById(R.id.btn_resend_otp);
        btn_verify = findViewById(R.id.btn_verify);

        phoneNumber = getIntent().getExtras().getString("phone");

        sendOTP(phoneNumber,false);

        btn_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = edt_otp.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verifycationCode,enteredOTP);
                signIn(credential);
            }
        });
        btn_resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendOTP(phoneNumber,true);
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
//                                signIn(phoneAuthCredential);
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
    void signIn(PhoneAuthCredential phoneAuthCredential){
        myAuth.signInWithCredential(phoneAuthCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(VerifyLoginActivity.this, MainActivity.class);
                    intent.putExtra("phone",phoneNumber);
                    startActivity(intent);
                }else {
                    AndroidUtils.showToast(getApplicationContext(),"Xác thực lỗi vui lòng thử lại!");
                }
            }
        });
    }
}