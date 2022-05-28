package com.mariaamigateam.mariaamiga;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

public class appointmentsActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    private FirebaseUser user;

    private ListView listview;
    private ArrayList<String> titulos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        Button salir = findViewById(R.id.backAgendaBT);
        Button agendarVista = findViewById(R.id.nuevaCitaBT);
        listview = (ListView) findViewById(R.id.listaAgenda);
        actualizarAdaptador();
    }
    private View.OnClickListener regresar = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            finish();
        }
    };
    private View.OnClickListener agendar = new android.view.View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(appointmentsActivity.this, scheduleActivity.class);
            startActivity(intent);
            onPause();
        }
    };
    private void actualizarAdaptador(){
        db.collection("agendamiento")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String idDco = document.getId().toString();
                                if (user.getEmail().equals(idDco)){
                                    ArrayList<Object> objetos = (ArrayList<Object>) document.getData().get("citas");
                                    for(Object a: objetos){
                                        System.out.println(a.toString());
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