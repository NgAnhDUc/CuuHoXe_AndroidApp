package com.example.appcuuhoxe.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
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
    public static String GenerateID(String collectionName){
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference collRef = rootRef.collection(collectionName);
        return collRef.document().getId();
    }
    public static DocumentReference currentUserDetails(){
        return FirebaseFirestore.getInstance().collection("Taikhoan").document(currentUserID());
    }
    public static DocumentReference recuseTeamDetails(){
        return FirebaseFirestore.getInstance().collection("DoiCuuHo").document(currentUserID());
    }
    public static DocumentReference DonCuuHoDetail(String documentID){
        return FirebaseFirestore.getInstance().collection("DonCuuHo").document(documentID);
    }
    public static DocumentReference myLocationDetail(){
        return FirebaseFirestore.getInstance().collection("Location").document(currentUserID());
    }
}
