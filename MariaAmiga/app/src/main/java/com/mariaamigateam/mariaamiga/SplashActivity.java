package com.mariaamigateam.mariaamiga;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        setContentView(R.layout.activity_splash);
    }
    @Override
    protected void onStart() {
        super.onStart();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user == null) {
                    startActivity(new Intent(SplashActivity.this, welcomeActivity.class));
                    finish();
                } else {
                    db.collection("usuarios")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            String idDco = document.getId().toString();
                                            if (user.getEmail().equals(idDco)){
                                                if ((boolean) document.getData().get("perfilCompleto") && (boolean) document.getData().get("gustosCompleto")) {
                                                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                                                    finish();
                                                } else if((boolean) document.getData().get("perfilCompleto") == false){
                                                    startActivity(new Intent(SplashActivity.this, completeRegisterActivity.class));
                                                    finish();
                                                }
                                                else if((boolean) document.getData().get("perfilCompleto") && (boolean) document.getData().get("gustosCompleto")==false){
                                                    startActivity(new Intent(SplashActivity.this, pleasuresActivity.class));
                                                    finish();
                                                }
                                            }
                                        }
                                    } else {
                                        Log.w(TAG, "Error getting documents.", task.getException());
                                    }
                                }
                            });
                }
            }
        }, 3000);
    }
}