package com.example.appcuuhoxe.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseUtils {

    public static boolean isLoggedIn(){
        if(currentUserID()!=null){
            return true;
        }
        return false;
    }
    public static String currentUserID(){
        return FirebaseAuth.getInstance().getUid();
    }
    public static DocumentReference currentUserDetails(){
        return FirebaseFirestore.getInstance().collection("Taikhoan").document(currentUserID());
    }
}
